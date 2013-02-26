<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<a href="<spring:url value="/book/${book.id}/part/${node.id}"/>">${node.title}</a>
<div class="adminPanel">
	<form action="<spring:url value="/book/${book.id}/part/${node.id}"/>" method="post">
		<input type="hidden" name="parent" value="${node.id}">
		<input type="text" name="name"><input type="submit" value="+"/>
	</form>
	<form action="<spring:url value="/book/${book.id}/part/${node.id}"/>" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" value="-">
	</form>
</div>