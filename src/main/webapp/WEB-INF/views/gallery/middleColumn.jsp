<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/views/gallery/gallery.jsp"/>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="adminPanel">
		<jsp:include page="/WEB-INF/views/book/newBook.jsp"/>
	</div>
</sec:authorize>