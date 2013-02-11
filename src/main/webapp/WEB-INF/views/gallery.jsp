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
				<div class="galleryBook">
					<a href="<spring:url value="${book.link}"/>"><img src="${book.image}" alt="${book.title}"></a>
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
		<spring:escapeBody>${curBook.summary}</spring:escapeBody>
	</div>
</div>

<jsp:include page="/WEB-INF/partials/bottom.jsp"/>