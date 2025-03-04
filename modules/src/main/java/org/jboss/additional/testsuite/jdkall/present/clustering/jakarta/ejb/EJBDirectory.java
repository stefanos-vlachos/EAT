/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
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

package org.jboss.additional.testsuite.jdkall.present.clustering.ejb;

import jakarta.ejb.EJBHome;
import jakarta.ejb.SessionBean;
import jakarta.transaction.UserTransaction;

/**
 * Jakarta Enterprise Beans lookup helper
 *
 * @author Paul Ferraro
 */
import org.jboss.eap.additional.testsuite.annotations.EAT;

@EAT({"modules/testcases/jdkAll/WildflyJakarta/clustering/src/main/java#27.0.0.Alpha4","modules/testcases/jdkAll/WildflyRelease-27.0.0.Final/clustering/src/main/java"})
public interface EJBDirectory extends AutoCloseable {
    <T> T lookupStateful(String beanName, Class<T> beanInterface) throws Exception;

    default <T> T lookupStateful(Class<? extends T> beanClass, Class<T> beanInterface) throws Exception {
        return this.lookupStateful(beanClass.getSimpleName(), beanInterface);
    }

    <T> T lookupStateless(String beanName, Class<T> beanInterface) throws Exception;

    default <T> T lookupStateless(Class<? extends T> beanClass, Class<T> beanInterface) throws Exception {
        return this.lookupStateless(beanClass.getSimpleName(), beanInterface);
    }

    <T> T lookupSingleton(String beanName, Class<T> beanInterface) throws Exception;

    default <T> T lookupSingleton(Class<? extends T> beanClass, Class<T> beanInterface) throws Exception {
        return this.lookupSingleton(beanClass.getSimpleName(), beanInterface);
    }

    <T extends EJBHome> T lookupHome(String beanName, Class<T> homeInterface) throws Exception;

    default <T extends EJBHome> T lookupHome(Class<? extends SessionBean> beanClass, Class<T> homeInterface) throws Exception {
        return this.lookupHome(beanClass.getSimpleName(), homeInterface);
    }

    UserTransaction lookupUserTransaction() throws Exception;

    @Override
    void close() throws Exception;
}
