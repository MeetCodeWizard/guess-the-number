<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card shadow">
					<div class="card-header text-center bg-success text-white">
						<h3>Login</h3>
					</div>
					<div class="card-body">
						<form action="/loginProcess" method="post">
							<!-- Email Field -->
							<div class="mb-3">
								<label for="email" class="form-label">Email</label> <input
									type="text" class="form-control" id="email" name="email" value="${userState.email}">
								<div class="text-left text-danger mt-2">
									<p>
										<!-- Email Validation -->
										${result.getFieldError("email").getDefaultMessage()}
									</p>
								</div>
							</div>
							<!-- Password Field -->
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									type="password" class="form-control" id="password"
									name="password">
								<div class="text-left text-danger mt-2">
									<p>
										<!-- Password Validation -->
										${result.getFieldError("password").getDefaultMessage()}
									</p>
								</div>
							</div>
							<!-- Submit Button -->
							<div class="d-grid">
								<button type="submit" class="btn btn-success">Login</button>
							</div>
						</form>
						<!-- Signup Option -->
						<div class="text-center mt-3">
							<p>
								Don't have an account? <a href="/signup">Signup</a>
							</p>
						</div>
						<div class="text-center mt-3">
							<p class="font-weight-bold text-danger">${loginRequest}</p>
						</div>
						<div class="text-center mt-3">
							<p class="font-weight-bold text-danger">${loginError}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
