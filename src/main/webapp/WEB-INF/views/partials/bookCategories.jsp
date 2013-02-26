<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.toastedbits.bookish.domain.Category" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<div id="bookCategoriesPane" class="contentPaneLeft">
	<c:choose>
		<c:when test="${categoryTree != null && categoryTree.parent != null}">
			<h3>${categoryTree.name}</h3>
			<a href="<spring:url value="/books"/>">Home</a>
			<a href="<spring:url value="/books?category=${categoryTree.parent.id}"/>">Up</a>
		</c:when>
		<c:otherwise>
			<h3>Home</h3>
		</c:otherwise>
	</c:choose>
	
	<div class="adminPanel">
		<form action="<spring:url value="/category?parent=${categoryTree.id}"/>" method="post">
			<input type="text" name="name"><input type="submit" value="+">
		</form>
	</div>
	
	<c:choose>
		<c:when test="${categoryTree != null && categoryTree.children != null && categoryTree.getChildren().size() > 0}">
			<ul>
				<c:set var="node" value="${categoryTree}" scope="request"/>
				<jsp:include page="/WEB-INF/views/partials/categoryNode.jsp"/>
			</ul>
		</c:when>
	</c:choose>
	<div style="clear:both">&nbsp;</div>
</div>