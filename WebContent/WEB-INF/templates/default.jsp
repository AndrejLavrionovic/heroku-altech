<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="header" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:forEach var="css" items="${stylesheets}">
	<link href="<c:url value="${css}" />" type="text/css" rel="stylesheet" />
</c:forEach>
<c:if test="${selctFields == 1}">
	<style type="text/css">
		.selNew{
			display: inline-block;
			background:url('${pageContext.request.contextPath}/src/img/select_arrow_20x30.png') no-repeat right white;
			-webkit-appearance: none;
			-moz-appearance: none;
		}
		.selError{
			background:url('${pageContext.request.contextPath}/src/img/select_arrow_20x30.png') no-repeat right #FFE0DD;
		}
	</style>
</c:if>

<script src="${pageContext.request.contextPath}/src/js/jquery-3.0.0.min.js"></script>

<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
</head>
<body>
	<div id="container">
		
		<div id = "header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
		<div id = "content"><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
		<div id = "footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>

	</div>
	
	<script src="${pageContext.request.contextPath}/src/js/showdate.js"></script>
</body>
</html>