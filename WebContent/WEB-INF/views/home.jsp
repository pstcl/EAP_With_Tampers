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


<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Energy Accounting</title>


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
	<%-- 	<%@include file="authheader.jsp"%> --%>
	<div id="app" class="container">

		<nav class="navbar navbar-expand-lg navbar-light bg-faded  fixed-top">
			<a class="navbar-brand" href="javascript:window.location='home'">
				<img alt="PSTCL" width="70px;" height="50px;"
				src="<c:url value='/static/img/pstcl.png' />">
			</a>

			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbarNavAltMarkup" class="navbar-collapse collapse">


				<ul class="navbar-nav  mr-auto">
					<li><h3>
							<span class="navbar-text"> <spring:message
									code="sldc.energyaccounts.title"></spring:message>
							</span>
						</h3></li>
				</ul>

				<ul class="navbar-nav">

					<li><span class="navbar-text"></span></li>

					<li class="nav-item"><a class="nav-link"
						href="javascript:window.location='firstLoginPwdChange'"><span
							class="glyphicon glyphicon-home"></span> <c:choose>
								<c:when test="${passwordChangeDue}">
									<span> <i class="fa fa-warning"
										style="font-size: 18px; color: red;"><i
											class="fas fa-exclamation-circle"></i>Please Change Password</i>
									</span>
								</c:when>
								<c:otherwise>
							Change Password
							</c:otherwise>
							</c:choose></a></li>
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/logout" />"><i
							class="fas fa-sign-out-alt"></i> Logout </a></li>
				</ul>

			</div>


		</nav>
	</div>


	<div class="container-fluid">
		<div class="row d-flex d-md-block flex-nowrap wrapper">
			<!-- 			<div class="col-md-3 float-left col-1 pl-0 pr-0 collapse width hide" -->
			<!-- 				id="sidebar"> -->



			<%-- 					<jsp:include page="filter.jsp"> --%>
			<%-- 						<jsp:param value="searchOilReports" name="actionName" /> --%>
			<%-- 					</jsp:include> --%>



			<!-- 			</div> -->
			<div class="col-md-9 float-left col px-5 pl-md-2 pt-2 main">


				<!-- 				<a href="#" data-target="#sidebar" data-toggle="collapse"><i -->
				<!-- 					class="fa fa-navicon fa-2x py-2 p-1"></i></a> -->

				<div class="page-header">


					<h4>Welcome ${currentUser.username}</h4>
				</div>
				<p class="lead">Energy Accounting Portal</p>
				<hr>


				<div class="row" style="align-items: center;">

					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">



						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='uploadMultiZip'">

								<div class="card" style="align-items: center;"
									data-toggle="tooltip" data-placement="top"
									title="Upload one of more zip file(s)">
									<i class="fas fa-cloud-upload-alt  fa-5x"></i>
									<div class="card-body">
										<div class="app-title">
											Upload Zip File(s)<br>
										</div>
									</div>
								</div>
							</a>
						</div>

						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='uploadTxtFile'">
								<div class="card" style="align-items: center;"
									data-toggle="tooltip" data-placement="top"
									title="Upload single text File">
									<i class="fas fa-file-upload   fa-5x"></i>
									<div class="card-body">
										<div class="app-title">
											Upload Text File<br>
										</div>
									</div>
								</div>
							</a>
						</div>
					</sec:authorize>

					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN','ROLE_SE')">


						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='pendingRepoFiles?month=2&year=2019'">
								<div class="card" style="align-items: center;"
									data-toggle="tooltip" data-placement="top"
									title="List of Files in the Database">
									<i class="fas fa-database  fa-5x"></i>
									<div class="card-body">
										<div class="app-title">
											Files Database<br>
										</div>
									</div>
								</div>
							</a>
						</div>
					</sec:authorize>



					<div class="form-control col-sm-4">
						<a class="nav-link"
							href="javascript:window.location='substationMaster'">
							<div class="card" style="align-items: center;"
								data-toggle="tooltip" data-placement="top"
								title="List of Energy Meters">
								<i class="fas fa-tachometer-alt fa-5x"></i>
								<div class="card-body">
									<div class="app-title">
										Energy Meter Master<br>
									</div>
								</div>
							</div>
						</a>

					</div>
					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">

						<div class="form-control col-sm-4">


							<a href="#" role="button" class="nav-link btn popovers"
								data-toggle="popover" title=""
								data-content="<a href='getLossReport?month=1&year=2019' title='Feb 2019'>February,2019</a>
								<br>
								<a href='getLossReport?month=0&year=2019' title='Jan 2019'>January,2019</a>
								<br>
								<a href='getLossReport?month=11&year=2018' title='Dec 2018'>December,2018</a>"
								data-original-title="Select Month"><span>
									<div class="card" data-toggle="tooltip" data-placement="top"
										title="View loss report for the selected month">
										<i class="fas fa-receipt fa-5x"></i>
										<div class="card-body">
											<div class="app-title">
												Loss Report<br>
											</div>
										</div>
									</div>
							</span></a>
						</div>
						<!-- 						<div class="form-control col-sm-4"> -->
						<!-- 							<a class="nav-link" href="javascript:window.location='getReport'"> -->
						<!-- 								<div class="card"> -->
						<!-- 									<img class="card-img-top" -->
						<%-- 										src="<c:url value='/static/img/tf1.jpg' />""> --%>
						<!-- 									<div class="card-body"> -->
						<!-- 										<div class="app-title">Reports (Sep 2018)</div> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 							</a> -->
						<!-- 						</div> -->

						<!-- 						<div class="form-control col-sm-4"> -->
						<!-- 							<a class="nav-link" -->
						<!-- 								href="javascript:window.location='getOctReport'"> -->
						<!-- 								<div class="card"> -->
						<!-- 									<img class="card-img-top" -->
						<%-- 										src="<c:url value='/static/img/tf1.jpg' />""> --%>
						<!-- 									<div class="card-body"> -->
						<!-- 										<div class="app-title">Reports(Oct 2018)</div> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 							</a> -->
						<!-- 						</div> -->


						<div class="form-control col-sm-4">


							<a href="#" role="button" class="nav-link btn popovers"
								data-toggle="popover" title=""
								data-content="<a href='getPendingLossReportLocation?month=1&year=2019' title='Feb'>Feb,2019</a>
								<br><a href='getPendingLossReportLocation?month=0&year=2019' title='Jan'>Jan,2019</a>
								<br>
								
								<a href='getPendingLossReportLocation?month=11&year=2018' title='Dec'>December,2018</a>"
								data-original-title="Select Month"><span>
									<div class="card" data-toggle="tooltip" data-placement="top"
										title="Meters for which no data has been uploaded yet!">
										<i class="fas fa-binoculars fa-5x"></i>

										<div class="card-body">
											<div class="app-title">
												Pending Locations<br>
											</div>
										</div>
									</div>
							</span></a>
						</div>

						<!-- 						<div class="form-control col-sm-4"> -->
						<!-- 							<a class="nav-link" -->
						<!-- 								href="javascript:window.location='getPendingLossReportLocation?month=8&year=2018'"> -->
						<!-- 								<div class="card"> -->
						<!-- 									<img class="card-img-top" -->
						<%-- 										src="<c:url value='/static/img/tf1.jpg' />""> --%>
						<!-- 									<div class="card-body"> -->
						<!-- 										<div class="app-title">Pending Data(Sept)</div> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 							</a> -->
						<!-- 						</div> -->


						<!-- 						<div class="form-control col-sm-4"> -->
						<!-- 							<a class="nav-link" -->
						<!-- 								href="javascript:window.location='getPendingLossReportLocation?month=9&year=2018'"> -->
						<!-- 								<div class="card"> -->
						<!-- 									<img class="card-img-top" -->
						<%-- 										src="<c:url value='/static/img/tf1.jpg' />""> --%>
						<!-- 									<div class="card-body"> -->
						<!-- 										<div class="app-title">Pending Data(Oct)</div> -->
						<!-- 									</div> -->
						<!-- 								</div> -->
						<!-- 							</a> -->
						<!-- 						</div> -->

					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_SS_AE','ROLE_SR_XEN','ROLE_SE')">

						<div class="form-control col-sm-4">
							<a class="nav-link"
								href="javascript:window.location='uploadZipPM'">
								<div class="card" style="align-items: center;"
									data-toggle="tooltip" data-placement="top"
									title="Upload zip files generated by CMRI!">
									<i class="fas fa-cloud-upload-alt fa-5x"></i>
									<div class="card-body">
										<div class="app-title">Upload File(s)</div>
									</div>
								</div>
							</a>
						</div>
						<sec:authorize access="hasAnyRole('ROLE_SS_AE','ROLE_SR_XEN')">

							<div class="form-control col-sm-4">
								<a class="nav-link"
									href="javascript:window.location='substationHome?month=2&year=2019'">
									<div class="card" style="align-items: center;"
										data-toggle="tooltip" data-placement="top"
										title="List of locations along with files uploaded in current month!">
										<i class="fas fa-database fa-5x"></i>
										<div class="card-body">
											<div class="app-title">Substation File Master</div>
										</div>
									</div>
								</a>
							</div>

						</sec:authorize>

						<div class="form-control col-sm-4">

							<a href="#" role="button" class="nav-link btn popovers"
								data-toggle="popover" title=""
								data-content="<a href='getPendingLossReportLocationPM?month=1&year=2019' title='Feb 2019'>Feb,2019</a>
								<br>
								<a href='getPendingLossReportLocationPM?month=0&year=2019' title='Jan 2019'>Jan,2019</a>
								<br>
								<a href='getPendingLossReportLocationPM?month=11&year=2018' title='Dec'>December,2018</a>"
								data-original-title="Select Month"><span>
									<div class="card" data-toggle="tooltip" data-placement="top"
										title="Meters for which no data has been uploaded yet!">
										<i class="fas fa-binoculars fa-5x"></i>

										<div class="card-body">
											<div class="app-title">
												Pending Locations<br>
											</div>
										</div>
									</div>
							</span></a>
						</div>
					</sec:authorize>

					<!-- Column for tamper Log -->

					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">

						<div class="form-control col-sm-4">


							<a href="#" role="button" class="nav-link btn popovers"
								data-toggle="popover" title=""
								data-content="<a href='getTamperLossReport?month=1&year=2019' title='Feb 2019'>February,2019</a>
								<br>
								<a href='getTamperLossReport?month=0&year=2019' title='Jan 2019'>January,2019</a>
								<br>
								<a href='getTamperLossReport?month=11&year=2018' title='Dec 2018'>December,2018</a>"
								data-original-title="Select Month"><span>
									<div class="card" data-toggle="tooltip" data-placement="top"
										title="View Tamper loss report for the selected month">
										<i class="fas fa-receipt fa-5x"></i>
										<div class="card-body">
											<div class="app-title">
												Tamper Loss Report<br>
											</div>
										</div>
									</div>
							</span></a>
						</div>
					</sec:authorize>
					<!-- Column for tamper log -->
					<sec:authorize
						access="hasAnyRole('ROLE_SLDC_USER','ROLE_SLDC_ADMIN')">

						<div class="form-control col-sm-4">


							<a href="#" role="button" class="nav-link btn popovers"
								data-toggle="popover" title=""
								data-content="<a href='getIRDetails?month=2&year=2019' title='Feb 2019'>February,2019</a>
								<br>
								<a href='getIRDetails?month=1&year=2019' title='Jan 2019'>January,2019</a>
								<br>
								<a href='getIRDetails?month=12&year=2018' title='Dec 2018'>December,2018</a>"
								data-original-title="Select Month"><span>
									<div class="card" data-toggle="tooltip" data-placement="top"
										title="View Instant Register Details for the selected month">
										<i class="fas fa-receipt fa-5x"></i>
										<div class="card-body">
											<div class="app-title">
												Instant Registers Details Report<br>
											</div>
										</div>
									</div>
							</span></a>
						</div>
					</sec:authorize>



				</div>
			</div>
		</div>
		<script>
			// sandbox disable popups
			if (window.self !== window.top && window.name != "view1") {
				;
				window.alert = function() {/*disable alert*/
				};
				window.confirm = function() {/*disable confirm*/
				};
				window.prompt = function() {/*disable prompt*/
				};
				window.open = function() {/*disable open*/
				};
			}

			// prevent href=# click jump
			document.addEventListener("DOMContentLoaded", function() {
				var links = document.getElementsByTagName("A");
				for (var i = 0; i < links.length; i++) {
					if (links[i].href.indexOf('#') != -1) {
						links[i].addEventListener("click", function(e) {
							console.debug("prevent href=# click");
							if (this.hash) {
								if (this.hash == "#") {
									e.preventDefault();
									return false;
								} else {
									/*
									var el = document.getElementById(this.hash.replace(/#/, ""));
									if (el) {
									  el.scrollIntoView(true);
									}
									 */
								}
							}
							return false;
						})
					}
				}
			}, false);
		</script>
</body>
</html>