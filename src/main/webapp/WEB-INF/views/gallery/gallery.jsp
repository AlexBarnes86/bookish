<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="bookGalleryPane" class="contentPane">
	<c:if test="${books != null}">
		<c:forEach items="${books}" var="book">
			<c:set var="book" value="${book.value}"/>
			
			<c:set var="bookClass" value=""/>
			<c:if test="${curBook != null && curBook.id == book.id}">
				<c:set var="bookClass" value="selectedBook"/>
			</c:if>
			
			<div class="galleryBook">
				<a href="<spring:url value="/books?category=${book.category.id}&selected=${book.id}"/>">
					<img class="${bookClass}" src="${book.image}" alt="${book.title}">
				</a>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="adminControl">
						<form action="<spring:url value="/book/${book.id}"/>" method="post">
							<input type="hidden" name="_method" value="delete">
							<input type="submit" value="Delete"/>
						</form>
					</div>
				</sec:authorize>
			</div>
		</c:forEach>
	</c:if>
</div>