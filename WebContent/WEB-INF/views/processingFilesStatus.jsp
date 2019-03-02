<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<title>Processing in background</title>
</head>

<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item active" aria-current="page">Processing in background</li>
			</ol>
		</nav>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {

			$('.modal-content').resizable({
				//alsoResize: ".modal-dialog",
				minHeight : 300,
				minWidth : 600
			});
			$('.modal-dialog').draggable();
		});
	</script>

	<div class="container-fluid">
		<c:if test="${not empty success}">
			<div class="alert alert-success lead">${success}</div>
		</c:if>
		<span class="lead">Energy Meters Master Data</span>
		
		Files Processing started in background. The results will be reflected in some time in the loss report sheet. List of files pending processing will be updated in the meantime.
		Please do not start processing again untill the background process complete which may take 25-30 minutes depending on the number of files.
	</div>



</body>
</html>