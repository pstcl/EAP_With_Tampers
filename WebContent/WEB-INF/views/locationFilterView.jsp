



<script type="text/javascript">
		function initLocations() {
			$('#circleDD').find('option').remove().end();
			$('#divisionDD').find('option').remove().end();
			$('#substationDD').find('option').remove().end();
			$.post("/EAP/getLocationsModel", {
				circleSelected : null,
				divisionSelected : null,
				substationSelected : null
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
				substationSelected : null
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
				substationSelected : null
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
		
		function submitSS() {
			
			
			$.post("getLocationsModel", {
				circleSelected : $("#circleDD").val(),
				divisionSelected : $("#divisionDD").val(),
				substationSelected : $("#substationDD").val()
			}, ssSelected).done(function() {
				//alert( "Employee Added" );
			}).fail(function(data, status, er) {
				alert("Couldn't load location information !" + data + er);
			});

		}
		function ssSelected(data, status) {

			

		}
		
		
	</script>
<script>
		$(document).ready(function() {
			initLocations();
		});
	</script>



<div class="form-control col-md-4">



	Circle <select id="circleDD" class="form-control input-sm"
		onchange="submitCircle()">

	</select>



</div>

<div class="form-control col-md-4">

	Division <select id="divisionDD" class="form-control input-sm"
		onchange="submitDivision()">
	</select>

</div>

<div class="form-control col-md-4">

	Substation <select id="substationDD" class="form-control input-sm"
		onchange="submitSS()">
	</select>


</div>


