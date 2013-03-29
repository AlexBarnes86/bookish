<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="cssLayout" scope="request" value="/css/twocolumn.css"/>

<jsp:include page="/WEB-INF/views/partials/top.jsp"/>
	<div class="leftColumn">
		<jsp:include page="${templateLeftColumn}"/>
	</div>
	
	<div class="rightColumn">
		<jsp:include page="${templateRightColumn}"/>
	</div>
<jsp:include page="/WEB-INF/views/partials/bottom.jsp"/>