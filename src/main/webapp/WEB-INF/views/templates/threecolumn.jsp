<jsp:include page="/WEB-INF/views/partials/top.jsp"/>
<div class="row-fluid">
	<div class="span3">
		<jsp:include page="${templateLeftColumn}"/>
	</div>
	
	<div class="span6">
		<jsp:include page="${templateMiddleColumn}"/>
	</div>
	
	<div class="span3">
		<jsp:include page="${templateRightColumn}"/>
	</div>
</div>
<jsp:include page="/WEB-INF/views/partials/bottom.jsp"/>