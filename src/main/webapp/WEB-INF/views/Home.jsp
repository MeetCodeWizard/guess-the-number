<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<!-- Header Section -->
		<div class="text-center mb-4">
			<h1>Welcome to the Game</h1>
			<!-- Buttons in One Line -->
			<div class="d-flex justify-content-center gap-3 mt-4">
				<button class="btn btn-primary btn-lg"
					onclick="location.href='/play'">Play</button>
				<button class="btn btn-danger btn-lg"
					onclick="location.href='/logout'">Logout</button>
			</div>
		</div>


		<!-- User List Section -->
		<div class="card shadow">
			<div class="card-header bg-info text-white d-flex justify-content-between">
				<h3 class="mb-0">User List</h3>
				<button class="btn btn-danger"
					onclick="location.href='/gameHistory'">Game History</button>
			</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead class="table-dark">
						<tr>
							<th scope="col">Sr No</th>
							<th scope="col">First Name</th>
							<th scope="col">Credit Score</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>${user.getFirstname()}</td>
								<td>${user.getMaster_credit()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>