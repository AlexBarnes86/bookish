<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/webjars/bootstrap/2.3.1/css/bootstrap.min.css" var="bootstrap_css_url"/>
<spring:url value="/webjars/bootstrap/2.3.1/css/bootstrap-responsive.min.css" var="bootstrap_responsive_css_url"/>

<!DOCTYPE html>
<!--[if lt IE 7]>	  <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>		 <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>		 <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Bookish</title>
		<meta name="description" content="Book management and viewing platform">
		<meta name="viewport" content="width=device-width">
		<base href="<spring:url value="/"/>"/>
		
		<%--TODO: add apple-touch-icon.png --%>
		<link rel="icon" href="<spring:url value="/favicon.ico"/>" type="image/x-icon" />
		<link rel="stylesheet" href="${bootstrap_css_url}"/>
		<link rel="stylesheet" href="${bootrstap_responsive_css_url}"/>
		<link rel="stylesheet" href="<spring:url value="/css/main.css"/>">
		<%--Use http://modernizr.com/downloads/modernizr-latest.js for debugging --%>
		<script src="<spring:url value="/webjars/modernizr/2.6.2/modernizr.min.js"/>"></script>
	</head>
	<body>
		<!--[if lt IE 7]>
			<p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
		<![endif]-->