<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="contentPane">
	<%--Use iframes to isolate the content away from the site so we don't screw up the site layout, etc --%>
	<c:if test="${book.content != null && !book.content.isEmpty()}">
		<iframe id="bookFrame" src="<spring:url value="/book/${book.id}/content"/>"></iframe>
	</c:if>
</div>

<sec:authorize access="hasRole('ROLE_ADMIN')">
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
				<form:label path="summary">Summary</form:label><form:textarea cssClass="tinymce" path="summary"/><br>
				<form:label path="content">Content</form:label><form:textarea cssClass="tinymce" path="content"/>
			</fieldset>
			<input type="submit" value="Update"/>
		</form:form>
		<form:form commandName="book" method="delete">
			<button>Delete</button>
		</form:form>
	</div>
</sec:authorize>