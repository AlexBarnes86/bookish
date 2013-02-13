<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="bookSummaryPane" class="contentPaneRight">
	<div id="bookSummary">
		<c:if test="${curBook != null}">
			<h3><a href="#">${curBook.title}</a></h3>
			<spring:escapeBody>${curBook.summary}</spring:escapeBody>
		</c:if>
	</div>
</div>