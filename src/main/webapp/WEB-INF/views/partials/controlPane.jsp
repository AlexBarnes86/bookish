<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="controlPane" class="contentPaneRight">
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
	<div id="controlButtons">
		<form action="/help" method="get">
			<input type="submit" value="Help"/>
		</form>
		<sec:authorize access="hasRole('ROLE_USER')">
			<form action="<spring:url value="/j_spring_security_logout"/>">
				<input type="submit" value="Log Out"/>
			</form>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<form action="<spring:url value="/signup"/>">
				<input type="submit" value="Sign Up"/>
			</form>
			<form action="<spring:url value="/spring_security_login"/>">
				<input type="submit" value="Log In"/>
			</form>
		</sec:authorize>
	</div>
</div>