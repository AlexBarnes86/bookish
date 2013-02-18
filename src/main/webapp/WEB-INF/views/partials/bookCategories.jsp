<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="bookCategoriesPane" class="contentPaneLeft">
	<h3>Categories</h3>
	<div class="adminPanel">
		<form action="<spring:url value="/category"/>" method="post">
			<input type="text" name="name"><input type="submit" value="+">
		</form>
	</div>
	<c:choose>
		<c:when test="${categories != null && categories.size() > 0}">
			<ul>
			<c:forEach items="${categories}" var="category">
				<li>${category.name}
				<div class="adminPanel">
					<form action="<spring:url value="/category"/>" method="post">
						<input type="hidden" name="parent" value="${category.id}">
						<input type="text" name="name"><input type="submit" value="+"/>
					</form>
					<form action="<spring:url value="/category/${category.id}"/>" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<input type="submit" value="-">
					</form>
				</div>
				</li>
			</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			None
		</c:otherwise>
	</c:choose>
	<div style="clear:both">&nbsp;</div>
</div>