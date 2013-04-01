<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="templateLeftColumn" scope="request" value="/WEB-INF/views/users/leftColumn.jsp"/>
<c:set var="templateRightColumn" scope="request" value="/WEB-INF/views/users/rightColumn.jsp"/>

<jsp:include page="/WEB-INF/views/templates/twocolumn.jsp"/>