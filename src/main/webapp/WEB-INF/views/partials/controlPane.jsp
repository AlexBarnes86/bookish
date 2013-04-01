<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="controlPane" class="contentPaneRight">
	<%--TODO: Implement search capability
	<div id="searchBar">
		<form id="searchForm" method="get">
			<input id="searchTextField" type="text" name="search"/>
			<select name="type">
				<option>All</option>
				<option>Book Title</option>
				<option>Book Summary</option>
				<option>Book Content</option>
			</select>
			<input type="submit" value="Go"/>
		</form>
	</div>
	--%>
	<div id="controlButtons">
		<a href="<spring:url value="/about"/>">About</a>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<a href="<spring:url value="/users"/>">User Administration</a>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<a href="<spring:url value="/j_spring_security_logout"/>">Log Out</a>
		</sec:authorize>
		
		<sec:authorize access="isAnonymous()">
			<%-- TODO: Implement signup
			<form action="<spring:url value="/signup"/>">
				<input type="submit" value="Sign Up"/>
			</form>
			--%>
			<a href="<spring:url value="/spring_security_login"/>">Log In</a>
		</sec:authorize>
	</div>
</div>