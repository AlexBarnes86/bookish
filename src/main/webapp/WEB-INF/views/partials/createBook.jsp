<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form action="/books" method="post" commandName="newBook">
	<fieldset>
		<legend>New Book</legend>
		<form:label for="titleInput" path="title">Title</form:label><form:input id="titleInput" path="title"/>
		<c:choose>
			<c:when test="${categoryTree == null}">
				<form:hidden path="category.id" value="${categoryRoot.id}"/>
			</c:when>
			<c:when test="${categoryTree.children == null || categoryTree.children.size() == 0}">
				<form:hidden path="category.id" value="${categoryTree.id}"/>
			</c:when>
			<c:otherwise>
				<form:label for="categoryInput" path="category.id">Category</form:label>
				<form:select id="categoryInput" path="category.id">
					<form:options items="${categoryTree.children}" itemLabel="name" itemValue="id"/>
				</form:select>
			</c:otherwise>
		</c:choose>
		<form:label for="imageInput" path="image">Image</form:label><form:input id="imageInput" path="image"/>
		<form:label for="summaryTextArea" path="summary">Summary</form:label><form:textarea id="summaryTextArea" cssClass="tinymce" path="summary"/>
		<form:label for="contentTextArea" path="content">Content</form:label><form:textarea id="contentTextArea" cssClass="tinymce" path="content"/>
	</fieldset>
	<form:button id="createButton">Create New Book</form:button>
</form:form>