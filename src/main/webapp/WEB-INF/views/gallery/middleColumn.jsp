<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bookGalleryPane" class="contentPane">
	<c:if test="${books != null}">
		<c:forEach items="${books}" var="book">
			<c:set var="book" value="${book.value}"/>
			
			<c:choose>
				<c:when test="${curBook != null && curBook.id == book.id}">
					<c:set var="bookClass" value="galleryBook selectedBook"/>
				</c:when>
				<c:otherwise>
					<c:set var="bookClass" value="galleryBook"/>
				</c:otherwise>
			</c:choose>
			
			<div class="${bookClass}">
				<a href="<spring:url value="/gallery?selected=${book.id}"/>">
					<img src="${book.image}" alt="${book.title}">
				</a>
			</div>
		</c:forEach>
	</c:if>
	<div class="adminPanel">
		<form:form method="post">
			<input type="submit" value="Create new Book"/>
		</form:form>
	</div>
</div>