<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/partials/top.jsp"/>

<div class="leftColumn">
	<header id="bookishLogo" class="contentPaneLeft">
		<div id="logo">
			<img src="http://placekitten.com/300/100" alt="Bookish">
		</div>
	</header>
	
	<div id="bookCategoriesPane" class="contentPaneLeft">
		Book Categories
	</div>
</div>

<div class="middleColumn">
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
					<a href="<spring:url value="/gallery?selected=${book.id}"/>"><img src="${book.image}" alt="${book.title}"></a>
				</div>
			</c:forEach>
		</c:if>
	</div>
</div>

<div class="rightColumn">
	<div id="controlPane" class="contentPaneRight">
		<div id="searchBar">
			<input type="text"/>
		</div>
		<div id="controlButtons">
			<button>Help</button>
			<button>Sign Up</button>
			<button>Log In</button>
		</div>
	</div>
	<div id="bookSummaryPane" class="contentPaneRight">
		<div id="bookSummary">
			<c:if test="${curBook != null}">
				<h3><a href="#">${curBook.title}</a></h3>
				<spring:escapeBody>${curBook.summary}</spring:escapeBody>
			</c:if>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/partials/bottom.jsp"/>