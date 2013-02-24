<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="contentPane">
	<iframe src="<spring:url value="/book/${book.id}/summary"/>"></iframe><br>
	<iframe src="<spring:url value="/book/${book.id}/content"/>"></iframe>
</div>

<div class="contentPane adminPanel">
	<form:form commandName="book" method="put">
		<fieldset>
			<legend>Edit Book</legend>
			<form:label path="title">Title</form:label><form:input path="title"/><br>
			<form:label path="category.id">Category</form:label>
			<form:select path="category.id">
				<form:options items="${categories}" itemValue="id" itemLabel="name"/>
			</form:select>
			<br>
			<form:label path="image">Image</form:label><form:input path="image"/><br>
			<form:label path="summary">Summary</form:label><form:textarea path="summary"/><br>
			<form:label path="content">Content</form:label><form:textarea path="content"/>
		</fieldset>
		<input type="submit" value="Update"/>
	</form:form>
	<form:form commandName="book" method="delete">
		<button>Delete</button>
	</form:form>
</div>