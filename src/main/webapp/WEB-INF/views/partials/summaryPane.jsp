<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:if test="${curBook != null}">
	<div id="bookSummaryPane" class="contentPaneRight">
		<div id="bookSummary">
				<h3><a href="<spring:url value="/book/${curBook.id}"/>">${curBook.title}</a></h3>
				<spring:escapeBody>${curBook.summary}</spring:escapeBody>
		</div>
	</div>
</c:if>