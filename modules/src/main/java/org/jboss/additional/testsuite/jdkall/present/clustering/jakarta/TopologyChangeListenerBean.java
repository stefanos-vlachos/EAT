/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.additional.testsuite.jdkall.present.clustering;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.infinispan.Cache;
import org.infinispan.distribution.DistributionManager;
import org.infinispan.distribution.LocalizedCacheTopology;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.TopologyChanged;
import org.infinispan.notifications.cachelistener.event.TopologyChangedEvent;
import org.jboss.logging.Logger;

/**
 * Jakarta Enterprise Beans that establishes a stable topology.
 * @author Paul Ferraro
 */
import org.jboss.eap.additional.testsuite.annotations.EAT;

@EAT({"modules/testcases/jdkAll/WildflyJakarta/clustering/src/main/java#27.0.0.Alpha4","modules/testcases/jdkAll/WildflyRelease-27.0.0.Final/clustering/src/main/java"})
@Stateless
@Remote(TopologyChangeListener.class)
@Listener(sync = false)
public class TopologyChangeListenerBean implements TopologyChangeListener {

    private static final Logger logger = Logger.getLogger(TopologyChangeListenerBean.class);

    @Override
    public void establishTopology(String containerName, String cacheName, long timeout, String... nodes) throws InterruptedException {
        Set<String> expectedMembers = Stream.of(nodes).sorted().collect(Collectors.toSet());
        Cache<?, ?> cache = findCache(containerName, cacheName);
        if (cache == null) {
            throw new IllegalStateException(String.format("Cache %s.%s not found", containerName, cacheName));
        }
        cache.addListener(this);
        try {
            synchronized (this) {
                DistributionManager dist = cache.getAdvancedCache().getDistributionManager();
                LocalizedCacheTopology topology = dist.getCacheTopology();
                Set<String> members = getMembers(topology);
                long start = System.currentTimeMillis();
                long now = start;
                long endTime = start + timeout;
                while (!expectedMembers.equals(members)) {
                    logger.infof("%s != %s, waiting for a topology change event. Current topology id = %d", expectedMembers, members, topology.getTopologyId());
                    this.wait(endTime - now);
                    now = System.currentTimeMillis();
                    if (now >= endTime) {
                        throw new InterruptedException(String.format("Cache %s/%s failed to establish view %s within %d ms.  Current view is: %s", containerName, cacheName, expectedMembers, timeout, members));
                    }
                    topology = dist.getCacheTopology();
                    members = getMembers(topology);
                }
                logger.infof("Cache %s/%s successfully established view %s within %d ms. Topology id = %d", containerName, cacheName, expectedMembers, now - start, topology.getTopologyId());
            }
        } finally {
            cache.removeListener(this);
        }
    }

    private static Cache<?, ?> findCache(String containerName, String cacheName) {
        try {
            Context context = new InitialContext();
            try {
                EmbeddedCacheManager manager = (EmbeddedCacheManager) context.lookup("java:jboss/infinispan/container/" + containerName);
                return manager.cacheExists(cacheName) ? manager.getCache(cacheName) : null;
            } finally {
                context.close();
            }
        } catch (NamingException e) {
            return null;
        }
    }

    private static Set<String> getMembers(LocalizedCacheTopology topology) {
        return topology.getMembers().stream().map(Object::toString).sorted().collect(Collectors.toSet());
    }

    @TopologyChanged
    public void topologyChanged(TopologyChangedEvent<?, ?> event) {
        if (!event.isPre()) {
            synchronized (this) {
                this.notify();
            }
        }
    }
}
