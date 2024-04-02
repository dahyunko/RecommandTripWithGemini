<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<link rel="stylesheet" href="${root}/assets/css/login.css" />
<title>Document</title>
<body>
	<% if(request.getAttribute("msg") != null) {%>
		<script>
			window.alert("<%= request.getAttribute("msg") %>");
		</script>
	<% } %>
	<div class="login-main">
	      <div class="login-title header_title logo">Enjoy Trip</div>
	      <div class="login-wrapper">
	        <h2>Login</h2>
			<form method="post" action="${root}/user" id="login-form">
				<input type="hidden" name="action" value="login"> 
				<input type="text" name="userid" placeholder="Email" /> 
				<input type="password" name="userpwd" placeholder="Password" />
				<!-- <label for="remember-check">
        <input type="checkbox" id="remember-check" />아이디 저장하기
      </label> -->
				<button class="text-content-font" id="signinbtn" type="submit" value="Login" >
            		login
          		</button>
			</form>
		</div>
	</div>
</body>
</html>