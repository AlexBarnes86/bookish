<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" commandName="newBook">
	<fieldset>
		<legend>New Book</legend>
		<form:label for="titleInput" path="title">Title</form:label><form:input id="titleInput" path="title"/><br>
		<form:label for="summaryTextArea" path="summary">Summary</form:label><form:textarea id="summaryTextArea" path="summary"/>
	</fieldset>
	<form:button id="createButton">Create New Book</form:button>
</form:form>