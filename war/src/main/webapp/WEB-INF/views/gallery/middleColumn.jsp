<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/partials/gallery.jsp"/>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="adminPanel">
		<jsp:include page="/WEB-INF/views/partials/createBook.jsp"/>
	</div>
</sec:authorize>