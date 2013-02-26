<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="bookCategoriesPane" class="contentPaneLeft">
	<div class="adminPanel">
		<form action="<spring:url value="/book/${book.id}/parts"/>" method="post">
			<label for="newPartTextField">Part</label>
			<input id="newPartTextField" name="title" type="text"></input>
			<input type="submit" value="+"></input>
		</form>
	</div>
	<div style="clear:both">&nbsp;</div>
	<c:choose>
		<c:when test="${book != null && book.getChildren() != null}">
			<ul>
				<c:set var="tree" value="${book}" scope="request"/>
				<c:set var="displayPage" value="/WEB-INF/views/partials/bookPartNode.jsp" scope="request"/>
				<jsp:include page="/WEB-INF/views/partials/treeView.jsp"/>
			</ul>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>