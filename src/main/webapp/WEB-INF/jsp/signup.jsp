
<html>
	<head>
		<meta charset="UTF-8">
		<title>Signup</title>

		<link href="../css/authCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/commonCSS.css" rel="stylesheet" type="text/css">
		<link href="../css/util.css" rel="stylesheet" type="text/css">
		
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<script language="javascript" src="../js/signup.js"></script>
		
	</head>
	
	<body>
		<div class="container scont">
		
			<div class="form">
				<div class="logo">
					<div class="text-center">
						<h1>Signup</h1>
					</div>
				</div>
			
				<form id="formRegister" method="post" action="register">
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>First Name</label>
						<input type="text" id="nome" class="form-control" placeholder="Enter Firstname" name="nome">
						<label id="lblNome" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Last Name</label>
						<input type="text" id="cognome" class="form-control" placeholder="Enter Lastname" name="cognome">
						<label id="lblCognome" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Email address</label>
						<input type="email" id="mail" class="form-control" placeholder="Enter email" name="mail">
						<label id="lblMail" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Username</label>
						<input type="text" id="username" class="form-control" placeholder="Enter username" name="username">
						<label id="lblUsername" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Password</label>
						<div class="form-row">
							<input type="password" id="password" class="form-control" placeholder="Enter Password" name="password">
							<i class="far fa-eye" id="togglePassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
						</div>
						<label id="lblPassword" style="color:red; font-size: 12"></label>
					</div>
					
					<div class="form-group">
						<i class="fa fa-asterisk ast" aria-hidden="true" style="font-size:12px"></i>
						<label>Confirm Password</label>
						<div class="form-row">
							<input type="password" id="confirmPassword" class="form-control" placeholder="Confirm Password" name="confirmPassword">
							<i class="far fa-eye" id="toggleConfirmPassword" style="margin-left: -35px; margin-top: 10px; cursor: pointer;"></i>
						</div>
						<label id="lblConfirmPassword" style="color:red; font-size: 12"></label>
					</div>
					
					<div>
	                	<button type="submit" id="buttonRegister" class="btn btn-block mbtn tx-tfm vm-background-color">Signup</button>
					</div>
					
					<div class="form-group spc">
						<p class="text-center"><a href="loginPage">Already have an account?</a></p>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>