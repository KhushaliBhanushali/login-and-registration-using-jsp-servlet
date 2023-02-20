<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<title>edit Profile</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Menu</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="profile">Profile</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="edit">Edit_Profile</a></li>
				<li class="nav-item"><a class="nav-link"
					href="password">Change_Password</a></li>
				<li class="nav-item"><a class="nav-link" href="index.jsp">Logout</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="d-sm-flex align-items-center justify-content-between mb-4">
			<h1 class="h3 mb-0 text-gray-800">Update Profile</h1>
		</div>

		<div class="row">

			<div class="col-lg-12">



				<!-- Default Card Example -->
				<div class="card mb-4">
					<div class="card-body">
						<form method="post" action="update">
							<input type="hidden" id="id" name="id"
								value="<c:out value='${user.id}' />">
							<table class="table table-bordered dataTable" id="dataTable"
								width="100%" cellspacing="0" role="grid"
								aria-describedby="dataTable_info" style="width: 100%;">



								<tbody>
									<tr>
										<th>First Name</th>
										<td><input type="text"
											class="form-control form-control-user" id="firstname"
											name="firstname" value="<c:out value='${user.firstname}' />"
											required="true"></td>
									</tr>
									<tr>
										<th>Last Name</th>
										<td><input type="text"
											class="form-control form-control-user" id="lastname"
											name="lastname" value="<c:out value='${user.lastname}' />"
											required="true"></td>
									</tr>
									<tr>
										<th>Email Id</th>
										<td><input type="email"
											class="form-control form-control-user" id="email"
											name="email" value="<c:out value='${user.email}' />"
											></td>
									</tr>
									<tr>
										<th>Phone</th>
										<td><input type="number"
											class="form-control form-control-user" id="phone"
											name="phone" value="<c:out value='${user.phone}' />"
											></td>
									</tr>
								</tbody>
							</table>
							<button type="submit" name="update"
								class="btn btn-primary btn-user btn-block">Update</button>
						</form>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Begin Page Content -->

	<!-- /.container-fluid -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>