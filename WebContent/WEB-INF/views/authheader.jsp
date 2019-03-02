<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>
<style>
/* Center the loader */
#loader {
	position: absolute;
	left: 50%;
	top: 50%;
	z-index: 1;
	width: 150px;
	height: 150px;
	margin: -75px 0 0 -75px;
	border: 16px solid #f3f3f3;
	border-radius: 50%;
	border-top: 16px solid #3498db;
	width: 120px;
	height: 120px;
	-webkit-animation: spin 2s linear infinite;
	animation: spin 2s linear infinite;
}

@
-webkit-keyframes spin { 0% {
	-webkit-transform: rotate(0deg);
}

100%
{
-webkit-transform




























:






























rotate






























(360
deg






























);
}
}
@
keyframes spin { 0% {
	transform: rotate(0deg);
}

100%
{
transform






























:






























rotate






























(360
deg































);
}
}

/* Add animation to "page content" */
.animate-bottom {
	position: relative;
	-webkit-animation-name: animatebottom;
	-webkit-animation-duration: 1s;
	animation-name: animatebottom;
	animation-duration: 1s
}

@
-webkit-keyframes animatebottom {from { bottom:-100px;
	opacity: 0
}

to {
	bottom: 0px;
	opacity: 1
}

}
@
keyframes animatebottom {from { bottom:-100px;
	opacity: 0
}

to {
	bottom: 0;
	opacity: 1
}

}
#myDiv {
	display: none;
	text-align: left;
}
</style>
<script type="text/javascript">
	var myVar;

	function myFunction() {
		document.getElementById("loader").style.display = "none";
		document.getElementById("myDiv").style.display = "block";
	}
</script>
<!-- Default card contents  navbar-static-top -->

<div id="loader"></div>

<div id="myDiv" class="container-fluid">
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


					<a class="nav-link" href="javascript:window.location='home'"><span
						class="glyphicon glyphicon-plus"></span>Home</a>

					<a class="nav-link"
						href="javascript:window.location='substationMaster'"><span
						class="glyphicon glyphicon-plus"></span>Energy Meter Master</a>


					
				</ul>
				<ul class="navbar-nav">
					<li><span class="navbar-text">
							<div class="card">


								<div class="card-body">

									<div class="app-title">Logged in
										User:${currentUser.username}</div>
								</div>
							</div>
					</span></li>

					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/logout" />"><span
							class="glyphicon glyphicon-log-out"></span> Logout </a></li>
				</ul>
			</div>


		</nav>
	</div>
</div>