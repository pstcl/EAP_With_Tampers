<%@page import="org.pstcl.ea.util.EAUtil"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Mappings</title>
</head>
<body onload="myFunction()" style="margin: 0;">

	<%@include file="header.jsp"%>

	<script>
		$(document).ready(function() {
			$("[data-toggle=popover]").popover({
				html : true
			});

			$(function() {
				$('[data-toggle="tooltip"]').tooltip()
			})
		});
	</script>

		<div class="row" style="align-items: center;">
		<div class="row">
		<div class="form-control ">
		<a class="nav-link"
								href="javascript:window.location='addMeterDetails'">
        <label class="col"><b>Add New Meter</b></label>
       </a>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
		<a class="nav-link"
								href="javascript:window.location='addLocationDetails'">
        <label class="col"><b>Add New Location</b></label>
        </a>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
		<a class="nav-link"
								href="javascript:window.location='selectMonthForReportLocations'">
        <label class="col"><b>Add Locations For Monthly calculations</b></label>
        </a>
		</div>
		</div>
</div>
</body>
</html>