<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Meter Details</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>

<script type="text/javascript">
	function getDivisions1() {
		

			document.forms['changeDetails'].action = 'getDivisions';
			document.forms['changeDetails'].submit();

			}
</script>

<script type="text/javascript">
	function getDivisions1() {
		
// 		var circle = $("#circle").val();
		
// 		  var data = {};

// 	        // for each array element of the serializeArray
// 	        // runs the function to create a new attribute on data
// 	        // with the correct value
// 	        $("#changeDetails").serializeArray().forEach( function(element){
// 	            data[element.name] = element.value;
// 	        });
		//console.log(data);
		$.ajax({
			url : "getDivisions",
			type : 'GET',
 			contentType: "application/json",
 			dataType:'json',
            data : { circleSelected : $("#crcode").val() },  
			success : function(response) {
				console.log(response);
				divisionMaster:response.divisionMaster;
                
            	$("#changeDetails").replaceWith(response);
			},
			 error: function(xhr, resp, text) {
	                console.log(xhr, resp, text);
	            }
		});

	}
	
	function getDivisions() {
		console.log($("#crcode").val());

		$.get("getDivisions", {
			circleSelected : $("#crcode").val()
				}, populate).done(function() {
					
			//alert( "Employee Added" );
		}).fail(function(data, status, er) {
			alert("Couldn't load location information !" + data + er);
		});

	}
	
	function populate(data, status) {
	console.log(data);	
	}
	

</script>


<body>



	<form:form action="changeMeterDetails" method="POST"
		modelAttribute="changeMeterSnippet" id="changeDetails">

		<div class="row">
			<label class="col-lg-2">Recent Location Id</label> <b>${changeMeterSnippet.meterMaster.meterSrNo}</b>
		</div>

		<div class="row">
			<label class="col-lg-2">End Date</label>
			<form:input type="date" path="endDate" />
		</div>

		<div class="row">
			<label class="col-lg-2">Circle</label>
			<form:select path="crcode" onchange="getDivisions()" id="crcode">
				<form:option value="NONE" label="Select" />
				<form:options items="${circleMaster}" itemValue="crCode"
					itemLabel="circleName" />
			</form:select>
		</div>

		<div class="row">
			<label class="col-lg-2">Division</label>
			<form:select path="division">
				<form:option value="NONE" label="Select" />
				<form:options items="${divisionMaster}" itemValue="divCode"
					itemLabel="divisionname" />
			</form:select>
		</div>

		<div class="row">
			<label class="col-lg-2">Substation</label>
			<form:select path="substation">
				<form:option value="NONE" label="Select" />
				<form:options items="${substation}" itemValue="ssCode" />
			</form:select>
		</div>

		<div class="row">
			<label class="col-lg-2">Location Id</label>
			<form:select path="location">
				<form:option value="NONE" label="Select" />
				<form:options items="${locationMaster}" itemValue="locationId" />
			</form:select>
		</div>

		<div class="row">
			<label class="col-lg-2">Start Date</label>
			<form:input type="date" path="startDate" />
		</div>


		<div class="form-group">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-send btn-success" type="submit">Send</button>
			</div>
		</div>

	</form:form>
</body>
</html>