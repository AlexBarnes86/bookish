<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="bookCategoriesPane" class="contentPaneLeft">
	<h3><a href="<spring:url value="/book/${book.id}"/>">${book.title}</a></h3>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="adminPanel">
			<form action="<spring:url value="/book/${book.id}/parts"/>" method="post">
				<input id="newPartTextField" name="title" type="text"></input>
				<input type="submit" value="+"></input>
			</form>
		</div>
		<div style="clear:both">&nbsp;</div>
	</sec:authorize>
	<c:choose>
		<c:when test="${book != null && book.getChildren() != null}">
			<ul>
				<c:set var="tree" value="${book}" scope="request"/>
				<c:set var="displayPage" value="/WEB-INF/views/bookPart/bookPartNode.jsp" scope="request"/>
				<jsp:include page="/WEB-INF/views/partials/treeView.jsp"/>
			</ul>
		</c:when>
		<c:otherwise>
			No Content
		</c:otherwise>
	</c:choose>
</div>