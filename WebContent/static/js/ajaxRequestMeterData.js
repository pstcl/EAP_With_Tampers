function getMeterData(locationid,meterDiv) 
{
	alert(locationid+":"+meterDiv);
	

	$('#meterDiv').css('display', 'none');

	$.ajax({
		url : "/EAP/getMeterDetails",
		type : "POST",
		data : {
			locationid : locationid
		},
		success : function(response) {
			hideProgressBar();

			$("#meterDiv").replaceWith(response);

		},
		error : function(data, status, er) {
			alert("Error" + data + er);
		}
	});


}

function populateTableContainer(data, status) {
	$("#meterDiv").replaceWith(data);
}