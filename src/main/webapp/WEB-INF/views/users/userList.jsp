<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="userCategoriesPane" class="contentPaneLeft">
	<ul>
		<c:forEach items="${users}" var="user">
			<li>
				<a href="<spring:url value="/users?selected=${user.id}"/>">${user.username}</a> 
				<form action="<spring:url value="/user/${user.id}"/>" method="post">
					<input type="hidden" name="_method" value="delete"/>
					<input type="submit" value="Delete">
				</form>
			</li>
		</c:forEach>
	</ul>
</div>