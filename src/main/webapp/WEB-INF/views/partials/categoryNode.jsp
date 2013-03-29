<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:forEach var="child" items="${node.children}">
	<c:if test="${child != null}">
		<c:choose>
			<c:when test="${child.children != null && child.children.size() > 0}">
				<li><a href="<spring:url value="/books?category=${child.id}"/>">${child.name}</a>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="adminPanel">
							<form action="<spring:url value="/category"/>" method="post">
								<input type="hidden" name="parent" value="${child.id}">
								<input type="text" name="name"><input type="submit" value="+"/>
							</form>
							<form action="<spring:url value="/category/${child.id}"/>" method="post">
								<input type="hidden" name="_method" value="delete"/>
								<input type="submit" value="-">
							</form>
						</div>
					</sec:authorize>
					<c:set var="node" value="${child}" scope="request"/>
					<ul>
						<jsp:include page="/WEB-INF/views/partials/categoryNode.jsp"/>
					</ul>
				</li>
			</c:when>
			<c:otherwise>
				<li><a href="<spring:url value="/books?category=${child.id}"/>">${child.name}</a>
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<div class="adminPanel">
							<form action="<spring:url value="/category"/>" method="post">
								<input type="hidden" name="parent" value="${child.id}">
								<input type="text" name="name"><input type="submit" value="+"/>
							</form>
							<form action="<spring:url value="/category/${child.id}"/>" method="post">
								<input type="hidden" name="_method" value="delete"/>
								<input type="submit" value="-">
							</form>
						</div>
					</sec:authorize>
				</li>
			</c:otherwise>
		</c:choose>
	</c:if>
</c:forEach>