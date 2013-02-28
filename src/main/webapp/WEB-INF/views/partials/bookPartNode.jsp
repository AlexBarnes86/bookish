<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="<spring:url value="/book/${book.id}/part/${node.id}"/>">${node.title}</a>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<span class="adminPanel">
		<%-- TODO: Implement child parts
		<form action="<spring:url value="/book/${book.id}/part/${node.id}"/>" method="post">
			<input type="hidden" name="parent" value="${node.id}">
			<input type="text" name="name"><input type="submit" value="+"/>
		</form>
		--%>
		<form action="<spring:url value="/book/${book.id}/part/${node.id}"/>" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<input type="submit" value="-">
		</form>
	</span>
</sec:authorize>