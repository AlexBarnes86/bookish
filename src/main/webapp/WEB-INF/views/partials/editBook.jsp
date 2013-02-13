<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="contentPane adminPanel">
	<form:form commandName="book" method="post">
		<input type="hidden" name="_method" value="put"/>
		<fieldset>
			<legend>Edit Book</legend>
			<form:label path="title">Title</form:label><form:input path="title"/><br>
			<form:label path="summary">Summary</form:label><form:textarea path="summary"/>
		</fieldset>
		<button>Save</button>
	</form:form>
	<form action="<spring:url value="/book/${book.id}"/>" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<button>Delete</button>
	</form>
</div>