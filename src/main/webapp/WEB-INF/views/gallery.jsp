<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="templateLeftColumn" scope="request" value="/WEB-INF/views/gallery/leftColumn.jsp"/>
<c:set var="templateMiddleColumn" scope="request" value="/WEB-INF/views/gallery/middleColumn.jsp"/>
<c:set var="templateRightColumn" scope="request" value="/WEB-INF/views/gallery/rightColumn.jsp"/>

<jsp:include page="/WEB-INF/views/templates/threecolumn.jsp"/>