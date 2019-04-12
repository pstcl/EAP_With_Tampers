<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Location Details</title>
</head>
<body onload="myFunction()" style="margin: 0;">
	<%@include file="authheader.jsp"%>
	<div class="sticky-top">
		<nav aria-label="breadcrumb" class="sticky-top">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="javascript:window.location='home'">Home</a></li>

				<li class="breadcrumb-item active" aria-current="page">Sub
					Station Master</li>
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




	<%@include file="dataTablesHeader.jsp"%>


	<script type="text/javascript">
		function submitform() {

			document.forms['changeDetails'].action = 'addLocationDetails';
			document.forms['changeDetails'].submit();

		}
	</script>
	<div class="row">
		<div class="alert alert-success lead">${locationMaster.error}</div>
	</div>






<script type="text/javascript">

function initAllList(){
	$('feederMaster').find('option').remove().end();
	$('deviceTypeMaster').find('option').remove().end();
	$('boundaryTypeMaster').find('option').remove().end();
	$('utiltiyName').find('option').remove().end();
	$('voltageLevel').find('option').remove().end();
	
	$.post("/EAP/getLocationListModel", {
	
	}, populateAllList).done(function() {
		//alert( "Employee Added" );
	}).fail(function(data, status, er) {
		alert("Couldn't load location information !" + data + er);
	});


}
function populateAllList(data, status) {
	
	$.each(data.feederMaster, function(idx, obj) {
		//alert(obj.circleName);
		$('#feederMaster').append(
				$("<option></option>").attr("value", obj.id)
						.text(obj.feederName));
	});
	
	$.each(data.boundaryTypeMaster, function(idx, obj) {
		//alert(obj.circleName);
		$('#boundaryTypeMaster').append(
				$("<option></option>").attr("value", obj.id)
						.text(obj.boundaryType));
	});
	
	$.each(data.deviceTypeMaster, function(idx, obj) {
		//alert(obj.circleName);
		$('#deviceTypeMaster').append(
				$("<option></option>").attr("value", obj.deviceId)
						.text(obj.deviceType));
	});
	
	$.each(data.utiltiyName, function(idx, obj) {
		//alert(obj.circleName);
		$('#utiltiyName').append(
				$("<option></option>").attr("value", obj)
						.text(obj));
	});
	
	$.each(data.voltageLevel, function(idx, obj) {
		//alert(obj.circleName);
		$('#voltageLevel').append(
				$("<option></option>").attr("value", obj)
						.text(obj));
	});
	
}	



		function initLocations() {
			$('#circleDD').find('option').remove().end();
			$('#divisionDD').find('option').remove().end();
			$('#substationDD').find('option').remove().end();
			
			$.post("/EAP/getLocationsModel", {
				circleSelected : null,
				divisionSelected : null,
				substationSelected : null,
				locationSelected:null,
			}, populate).done(function() {
				//alert( "Employee Added" );
			}).fail(function(data, status, er) {
				alert("Couldn't load location information !" + data + er);
			});

		}
		
		function addEmpty()
		{
			addEmptyCircle();
			addEmptyDivision();
			addEmptySubstation();
			
		}
		function addEmptyCircle()
		{
		$('#circleDD').append(
				$("<option></option>").attr("value", "")
						.text("Select Circle"));
		
		
		}
		function addEmptyDivision()
		{
		
		$('#divisionDD').append(
				$("<option></option>").attr("value", "")
						.text("Select Division"));
		
		}
		function addEmptySubstation()
		{
		
		$('#substationDD').append(
				$("<option></option>")
						.attr("value", "").text(
								"Select Sub-Station"));
		}
		
		
		
		
		function populate(data, status) {
			addEmpty();
			
			$.each(data.circleList, function(idx, obj) {
				//alert(obj.circleName);
				$('#circleDD').append(
						$("<option></option>").attr("value", obj.crCode)
								.text(obj.circleName));
			});
			$.each(data.divisionList, function(idx, obj) {
				//alert(obj.divisionname);
				$('#divisionDD').append(
						$("<option></option>").attr("value", obj.divCode)
								.text(obj.divisionname));
			});
			$.each(data.substationList, function(idx, obj) {
				//alert(obj.stationName);
				$('#substationDD').append(
						$("<option></option>")
								.attr("value", obj.ssCode).text(
										obj.stationName));
			});

				}

		function submitCircle() {
			
			$('#divisionDD').find('option').remove().end();
			$('#substationDD').find('option').remove().end();
			
			$.post("getLocationsModel", {
				circleSelected : $("#circleDD").val(),
				divisionSelected : null,
				substationSelected : null,
				locationSelected:null
			}, circleSelected).done(function() {
				//alert( "Employee Added" );
			}).fail(function(data, status, er) {
				alert("Couldn't load location information !" + data + er);
			});

		}
		function circleSelected(data, status) {

			
			
			addEmptyDivision();
			addEmptySubstation();
		
			$.each(data.divisionList, function(idx, obj) {
				//alert(obj.divisionname);
				$('#divisionDD').append(
						$("<option></option>").attr("value", obj.divCode)
								.text(obj.divisionname));
			});
			$.each(data.substationList, function(idx, obj) {
				//alert(obj.stationName);
				$('#substationDD').append(
						$("<option></option>")
								.attr("value", obj.ssCode).text(
										obj.stationName));
			});
			

		}
		
		function submitDivision() {
			
			
			$('#substationDD').find('option').remove().end();
			
			$.post("getLocationsModel", {
				circleSelected : $("#circleDD").val(),
				divisionSelected : $("#divisionDD").val(),
				substationSelected : null,
				locationSelected:null
			}, divSelected).done(function() {
				//alert( "Employee Added" );
			}).fail(function(data, status, er) {
				alert("Couldn't load location information !" + data + er);
			});

		}
		function divSelected(data, status) {

			
		
			addEmptySubstation();
			
			$.each(data.substationList, function(idx, obj) {
				//alert(obj.stationName);
				$('#substationDD').append(
						$("<option></option>")
								.attr("value", obj.ssCode).text(
										obj.stationName));
			});
		

		}
		
				
	</script>
<script>
		$(document).ready(function() {
			initLocations();
			initAllList();
		});
	</script>
	
		<form:form method="POST" modelAttribute="locationMaster" id="changeDetails">
		
	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Circle</b></label>
        <form:select id="circleDD" 
		onchange="submitCircle()" path="circleMaster"></form:select>
		</div>
		</div>


	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Division</b></label>
        <form:select id="divisionDD" 
		onchange="submitDivision()" path="divisionMaster">
	</form:select>
		</div>
		</div>


	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Substation</b></label>
         <form:select id="substationDD" path="substationMaster" >
	</form:select>
		</div>
		</div>


	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Location Id</b></label>
        <form:input path="locationId" />
		</div>
		</div>

	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>FeederMaster</b></label>
        
          <form:select id="feederMaster" path="feederMaster" >
	</form:select>
		</div>
		</div>

	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Boundary Master</b></label>
        
         <form:select id="boundaryTypeMaster" path="boundaryTypeMaster" >
	</form:select>
		</div>
		</div>
		
		<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Device Type Master</b></label>
        
          <form:select id="deviceTypeMaster" path="deviceTypeMaster" >
	</form:select>
		</div>
		</div>

	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Utility Master</b></label>
        
         <form:select id="utiltiyName" path="utiltiyName" >
	</form:select>
		</div>
		</div>

	<div class="row">
		<div class="form-control ">
        <label class="col-lg-3"><b>Voltage Level</b></label>
        
         <form:select id="voltageLevel" path="voltageLevel">
	</form:select>
		</div>
		</div>

	<div class="row">
		<div class="form-control ">
        <label class="col-lg-2"><b>Status</b></label>
        <form:select path="location_status" >
        <option value="ACTIVE">Active</option>
		<option value="INACTIVE">Inactive</option>
        </form:select>
		</div>
		</div>










<input type="button" class="btn btn-info" onclick="submitform()"
					value="Save Details">


</form:form>
</body>
</html>