/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013, Red Hat, Inc., and individual contributors
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

package org.jboss.additional.testsuite.jdkall.present.logging.operations;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.eap.additional.testsuite.annotations.EAT;

import org.jboss.logging.Logger;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@WebServlet("/logger")
@EAT({"modules/testcases/jdkAll/WildflyJakarta/logging/src/main/java#27.0.0.Alpha4","modules/testcases/jdkAll/WildflyRelease-27.0.0.Final/logging/src/main/java"})
public class DefaultLoggingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(DefaultLoggingServlet.class.getPackage().getName());

    public DefaultLoggingServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String msg = request.getParameter("msg");
        if (msg == null) {
            msg = "Test message";
        }
        LOGGER.info(msg);
        // response.setStatus(HttpServletResponse.SC_OK);
    }
}
