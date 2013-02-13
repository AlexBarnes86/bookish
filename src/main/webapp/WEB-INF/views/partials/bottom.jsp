<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<spring:url value="js/vendor/jquery-1.9.0.min.js" var="jquery_url"/>
		<script>window.jQuery || document.write('<script src="${jquery_url}"><\/script>')</script>
		<script src="<spring:url value="js/plugins.js"/>"></script>
		<script src="<spring:url value="js/main.js"/>"></script>
	</body>
</html>