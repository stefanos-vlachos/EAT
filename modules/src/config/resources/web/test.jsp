<%--@EAT({"modules/testcases/jdkAll/WildflyRelease-20.0.0.Final/web/test-configurations/src/test/resources","modules/testcases/jdkAll/WildflyRelease-24.0.0.Final/web/test-configurations/src/test/resources","modules/testcases/jdkAll/WildflyRelease-13.0.0.Final/web/test-configurations/src/test/resources","modules/testcases/jdkAll/WildflyRelease-17.0.0.Final/web/test-configurations/src/test/resources","modules/testcases/jdkAll/WildflyRelease-10.1.0.Final/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Wildfly/web/test-configurations/src/test/resources","modules/testcases/jdkAll/WildflyJakarta/web/test-configurations/src/test/resources","modules/testcases/jdkAll/ServerBeta/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap71x/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap71x-Proposed/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap72x/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap72x-Proposed/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap70x/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap70x-Proposed/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap7Plus/web/test-configurations/src/test/resources","modules/testcases/jdkAll/Eap7.1.0.Beta/web/test-configurations/src/test/resources"}) --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<tags:test text="${0}  ${fn:length ([1, 2])}"/>
