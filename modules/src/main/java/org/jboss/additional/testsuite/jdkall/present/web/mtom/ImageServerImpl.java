/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
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
package org.jboss.additional.testsuite.jdkall.present.web.mtom;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;
import org.jboss.eap.additional.testsuite.annotations.EAT;

//Service Implementation Bean
@MTOM(enabled = false)
@WebService(endpointInterface = "org.jboss.additional.testsuite.jdkall.present.web.mtom.ImageServer")
@EAT({"modules/testcases/jdkAll/WildflyRelease-13.0.0.Final/web/src/main/java","modules/testcases/jdkAll/WildflyRelease-24.0.0.Final/web/src/main/java","modules/testcases/jdkAll/Wildfly/web/src/main/java","modules/testcases/jdkAll/ServerBeta/web/src/main/java","modules/testcases/jdkAll/WildflyRelease-17.0.0.Final/web/src/main/java","modules/testcases/jdkAll/WildflyRelease-20.0.0.Final/web/src/main/java","modules/testcases/jdkAll/Eap72x/web/src/main/java","modules/testcases/jdkAll/Eap72x-Proposed/web/src/main/java","modules/testcases/jdkAll/Eap7/web/src/main/java","modules/testcases/jdkAll/Eap71x-Proposed/web/src/main/java","modules/testcases/jdkAll/Eap71x/web/src/main/java","modules/testcases/jdkAll/Eap7.1.0.Beta/web/src/main/java","modules/testcases/jdkAll/Eap70x/web/src/main/java","modules/testcases/jdkAll/Eap70x-Proposed/web/src/main/java","modules/testcases/jdkAll/Eap64x/web/src/main/java","modules/testcases/jdkAll/Eap64x-Proposed/web/src/main/java","modules/testcases/jdkAll/WildflyRelease-10.1.0.Final/web/src/main/java"})
public class ImageServerImpl implements ImageServer {

    @Override
    public Image downloadImage(String name) {

        try {
            File image = new File(name);

            return ImageIO.read(image);

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }

    }
}
