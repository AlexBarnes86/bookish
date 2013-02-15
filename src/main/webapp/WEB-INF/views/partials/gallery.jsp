<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="bookGalleryPane" class="contentPane">
	<c:if test="${books != null}">
		<c:forEach items="${books}" var="book">
			<c:set var="book" value="${book.value}"/>
			
			<c:set var="bookClass" value=""/>
			<c:if test="${curBook != null && curBook.id == book.id}">
				<c:set var="bookClass" value="selectedBook"/>
			</c:if>
			
			<div class="galleryBook">
				<a href="<spring:url value="/books?selected=${book.id}"/>">
					<img class="${bookClass}" src="${book.image}" alt="${book.title}">
				</a>
				<div class="adminPanel">
					<form action="<spring:url value="/book/${book.id}"/>" method="get">
						<button>Edit</button>
					</form>
					<form action="<spring:url value="/book/${book.id}"/>" method="post">
						<input type="hidden" name="_method" value="delete">
						<button>Delete</button>
					</form>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>