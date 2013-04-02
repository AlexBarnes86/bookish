<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="contentPane">
	<h2>Account Details for: <spring:escapeBody htmlEscape="true">${user.username}</spring:escapeBody></h2> 
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="adminPanel">
			Authorities:
			<c:if test="${!user.authorities.isEmpty()}">
				<ul>
					<c:forEach items="${user.authorities}" var="authority">
						<li>${authority.name}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</sec:authorize>
	
	<form action="<spring:url value="/user/${user.id}"/>" method="post">
		<input type="hidden" name="_method" value="put"/>
		<fieldset>
			<legend>Change Password</legend>
			<label for="passwordField">Password:</label><input id="passwordField" type="password" name="password"/><br>
			<label for="confirmPasswordField">Confirm:</label><input id="confirmPasswordField" type="password" name="passwordConfirm"/><br>
			<input type="submit" value="Save"/>
		</fieldset>
	</form>
</div>