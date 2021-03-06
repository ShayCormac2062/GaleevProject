<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Вход</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Login_v2/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Login_v2/css/main.css">
<!--===============================================================================================-->
</head>
<body>

<div class="container-login100">
	<div class="wrap-login100">
		<form action="/login" method="post" class="login100-form validate-form">
					<span class="login100-form-title p-b-26">
						Добро пожаловать на "Эх, прокачу!"
					</span>

				<div class="wrap-input100 validate-input" data-validate = "Почта должна содержать '@'">
					<label for="email"><input id="email" class="input100" type="text" name="email">
					<span class="focus-input100" data-placeholder="Почта"></span></label>
				</div>

				<div class="wrap-input100 validate-input" data-validate="Введите пароль">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
					<label for="pass">
						<input id="pass" class="input100" type="password" name="pass">
					<span class="focus-input100" data-placeholder="Пароль"></span>
					</label>
				</div>

				<div class="container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
						<button class="login100-form-btn" type="submit">
							Войти
						</button>
					</div>
				</div>

				<div class="text-center p-t-115">
						<span class="txt1">
							Уже есть аккаунт?
						</span>

					<a class="txt2" href="/register">
						Зарегестрируйтесь
					</a>
				</div>
		</form>
	</div>
</div>


<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/bootstrap/js/popper.js"></script>
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/daterangepicker/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/Login_v2/js/main.js"></script>

</body>
</html>