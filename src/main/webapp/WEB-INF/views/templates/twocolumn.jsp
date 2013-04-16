<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/views/partials/top.jsp"/>
<div class="row-fluid">
	<div class="span3">
		<jsp:include page="${templateLeftColumn}"/>
	</div>
	
	<div class="span9">
		<jsp:include page="${templateRightColumn}"/>
	</div>
</div>
<jsp:include page="/WEB-INF/views/partials/bottom.jsp"/>