<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Start</title>
</head>
<body>
	<br>
	<br>
	<div style="width: 100%; margin-top: 2px" class="text-center">
		<a href="/prev?counter=${counter}"><button type="button"
				class="btn btn-danger mr-4">Previous Image</button></a> <a
			href="/yes?counter=${counter}"><button type="button"
				class="btn btn-success mr-4 ml-4">Send to Final Folder</button></a> <a
			href="/skip?counter=${counter}"><button type="button"
				class="btn btn-primary ml-4">Next Image</button></a>
	</div>
	<br>
	<br>
	<form></form>
	<div class="text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<img class='img-fluid w-100' src="images/${folderName}/${fileName}"
						style="overflow: hidden;" alt="" />
				</div>
			</div>
		</div>
		<%-- <img src="images/${fileName}" class="img-fluid"
			style="width: 20rem; overflow: hidden;" alt="Responsive image"> --%>
	</div>

</body>
</html>