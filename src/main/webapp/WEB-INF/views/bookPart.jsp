<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="templateLeftColumn" scope="request" value="/WEB-INF/views/bookPart/leftColumn.jsp"/>
<c:set var="templateRightColumn" scope="request" value="/WEB-INF/views/bookPart/rightColumn.jsp"/>

<jsp:include page="/WEB-INF/views/templates/twocolumn.jsp"/>