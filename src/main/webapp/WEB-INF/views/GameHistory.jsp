<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.time.format.TextStyle"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Game History</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container my-5">
		<!-- align-items-center -->
		<div
			class="d-flex align-items-center justify-content-between gap-4 mt-4 mb-4">
			<h2 class="mb-0">Game History</h2>
			<div class="d-flex gap-3">
				<a href="/home" class="btn btn-outline-primary">Dashboard</a> <a
					href="/play" class="btn btn-outline-success">Play</a>
			</div>
		</div>


		<div class="table-responsive">
			<table class="table table-bordered table-striped table-hover">
				<thead class="table-primary text-center">
					<tr>
						<th>Sr No.</th>
						<th>Date</th>
						<th>Time</th>
						<th>Guessed Number</th>
						<th>Original Number</th>
						<th>Winning Status</th>
						<th>Initial Credits</th>
						<th>Credits</th>
						<th>Final Credits</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<%-- Example data (replace with dynamic data from the backend) --%>
					<c:forEach var="log" items="${userLogs}" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${log.getDate().getDayOfMonth()}
								${log.getDate().getMonth().getDisplayName(TextStyle.SHORT,Locale.ENGLISH)}
								${log.getDate().getYear()}</td>
							<td>${log.getDate().toLocalTime()}</td>
							<td>${log.getNumber_guessed()}</td>
							<td>${log.getNumber_generated()}</td>
							<td>${log.getWon() == "false" ? "Lost" : "Won"}</td>
							<td>${log.getInitial_credit()}</td>
							<td>${log.getCredit()}</td>
							<td>${log.getFinal_credit()}</td>
							<%-- <td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td>
							<td>${log}</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Bootstrap Bundle JS (includes Popper) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
