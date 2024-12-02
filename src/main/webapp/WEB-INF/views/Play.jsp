<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Play</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	/* Light background for better readability */
}

.card {
	border: none;
	border-radius: 15px;
}

.card-header {
	background-color: #007bff;
	color: white;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	text-align: center;
}

.original-number {
	color: #28a745; /* Distinct green for the original number */
	font-weight: bold;
}

.result-text {
	font-size: 1.2rem;
	font-weight: bold;
	color: #6c757d; /* Subtle gray for the result text */
}

.btn-primary {
	background-color: #007bff;
	border: none;
}

.btn-secondary {
	background-color: #6c757d;
	border: none;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<!-- Big Card -->
		<div class="card shadow-lg mx-auto" style="max-width: 600px;">
			<div class="card-header">
				<h3>Guess the Number between 1 to 10</h3>
			</div>
			<div class="card-body">
				<div
					class="d-flex justify-content-center align-items-center mb-4 mt-4">
					<!-- Form for Guess Input and Submit -->
					<form action="/guessNumberProcess" method="post" class="d-flex align-items-center">
						<!-- Guess Number Field -->
						<input type="number" class="form-control w-auto me-2"
							placeholder="Guess" id="guessNumber" name="number_guessed" value="${guessedNumber}" required>
						<!-- Guess Submit Button -->
						<input type="submit" class="btn btn-primary px-4" value="Guess" ${creditLimitExceeded != null ? "disabled" : "" }>
						<%-- <input type="submit" class="btn btn-primary px-4" value="Guess" ${creditScore <= 0 ? "disabled" : "" }> --%>
					</form>
				</div>


				<div class="mb-2 text-center">
					<button type="button" class="btn btn-success btn-lg mb-4">
						Original Number : <span class="original-number">${originalNumber != null ? originalNumber : "_" }</span>
					</button>
					<div class="d-flex justify-content-center gap-4 mb-4">
						<button type="button" class="btn btn-outline-primary btn-lg px-4"
							onclick="location.href='/home'">Dashboard</button>
						<button type="button" class="btn btn-warning btn-lg" disabled>
							Credit Score : ${creditScore != null ? creditScore : "_"}</button>
					</div>
				</div>
				<!-- Result Text -->
				<div class="mt-3 text-center">
					<p class="result-text">Result : ${result != null ? result : "_"}</p>
					<p class="text-danger font-weight-bold">${creditLimitExceeded}</p>
					<%-- <p class="text-danger font-weight-bold">${creditScore <= 0 ? "Please Buy Credits to Play Game !!!" : ""}</p> --%>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
