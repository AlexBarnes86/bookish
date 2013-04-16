<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="contentPane">
	<form:form commandName="newUser" action="/user" method="post">
		<fieldset>
			<legend>Create New User</legend>
			
			<label for="newUserName">Username</label>
			<form:input id="newUserName" path="username"/>
			<form:errors path="username" cssClass="fieldError"/>
			
			<label for="newUserPassword">Password</label>
			<form:password id="newUserPassword" path="password"/>
			<form:errors path="password" cssClass="fieldError"/>
			
			<label for="newUserPasswordConfirm">Confirm</label>
			<form:password id="newUserPasswordConfirm" path="passwordConfirm"/>
			<form:errors path="passwordConfirm" cssClass="fieldError"/>
			
			<input type="submit" value="Create User"/>
		</fieldset>
	</form:form>
</div>