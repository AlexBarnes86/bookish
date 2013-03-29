<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
Parameters: 

TreeView tree
String displayPage
--%>

<c:if test="${tree != null}">
	<c:forEach var="node" items="${tree.getChildren()}">
		<c:if test="${node != null}">
			<c:choose>
				<c:when test="${node.getChildren() != null && node.getChildren().size() > 0}">
					<li>
						<c:set var="node" value="${node}" scope="request"/>
						<jsp:include page="${displayPage}"/>
						<ul>
							<jsp:include page="/WEB-INF/views/partials/treeView.jsp"/>
						</ul>
					</li>
				</c:when>
				<c:otherwise>
					<c:set var="node" value="${node}" scope="request"/>
					<li><jsp:include page="${displayPage}"/></li>
				</c:otherwise>
			</c:choose>
		</c:if>
	</c:forEach>
</c:if>