<table class="table table-striped table-bordered table-hover">


	<tr>
		<th>Location Id</th>
		<td>${location.locationId}</td>
	</tr>
	<tr>
		<th>utiltiyName</th>

		<td>${location.utiltiyName}</td>
	</tr>

	<tr>
		<th>Division Name</th>
		<td>${location.substationMaster.divisionMaster.divisionname}</td>
	</tr>

	<tr>
		<th>Station Name</th>

		<td>${location.substationMaster.stationName}</td>
	</tr>


	<tr>
		<th>Device Type</th>
		<td>${location.deviceTypeMaster.deviceType}</td>
	</tr>

	<tr>
		<th>Feeder Name</th>

		<td>${location.feederMaster.feederName}</td>
	</tr>

	<tr>
		<th>Boundary Type</th>

		<td>${location.boundaryTypeMaster.boundaryType}</td>
	</tr>

	<tr>
		<th>Voltage Level</th>

		<td>${location.voltageLevel}</td>
	</tr>

	<tr>
		<th>Status</th>
		<td>${location.location_status}</td>
	</tr>

	<tr>
		<th>Meter Serial No.</th>

		<td>${location.meterMaster.meterSrNo}</td>
	</tr>

	<tr>
		<th>Meter Type</th>

		<td>${location.meterMaster.meterType}</td>
	</tr>

	<tr>
		<th>Meter Category</th>
		<td>${location.meterMaster.meterCategory}</td>
	</tr>

	<tr>
		<th>Meter Make</th>
		<td>${location.meterMaster.meterMake}</td>
	</tr>

	<tr>
		<th>External CT Ratio</th>
		<td>${location.externalCTRation}</td>
	</tr>

	<tr>
		<th>External PT Ratio</th>
		<td>${location.externalPTRation}</td>
	</tr>

	<tr>
		<th>External MF</th>
		<td>${location.externalMF}</td>
	</tr>

	<tr>
		<th>CT Accuracy</th>
		<td>${location.meterMaster.CTAccuracy}</td>
	</tr>
	<tr>
		<th>PT Accuracy</th>
		<td>${location.meterMaster.PTAccuracy}</td>
	</tr>
	<tr>
		<th>Installation Date</th>
		<td>${location.meterMaster.installedDate}</td>
	</tr>
	<tr>
		<th>Deactivation Date</th>
		<td>${location.meterMaster.deactivateddate}</td>
	</tr>


</table>