<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="contentPane">
	<%--Use iframes to isolate the content away from the site so we don't screw up the site layout, etc --%>
	<c:if test="${part.content != null && !part.content.isEmpty()}">
		<iframe id="bookFrame" src="<spring:url value="/book/${book.id}/part/${part.id}/content"/>"></iframe>
	</c:if>
</div>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="contentPane adminPanel">
		<form:form commandName="part" method="put">
			<fieldset>
				<legend>Edit Part</legend>
				<form:label path="title">Title</form:label><form:input path="title"/><br>
				<form:label path="summary">Summary</form:label><form:textarea path="summary"/><br>
				<form:label path="content">Content</form:label><form:textarea path="content"/>
			</fieldset>
			<input type="submit" value="Update"/>
		</form:form>
		<form:form commandName="part" method="delete">
			<button>Delete</button>
		</form:form>
	</div>
</sec:authorize>