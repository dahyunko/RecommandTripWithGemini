<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

    <title>Document</title>
    <link rel="stylesheet" href="${root}/assets/css/login.css" />
  </head>
  <body>
    <div class="register">
      <div class="register-title logo">Enjoy Trip</div>
      <div class="whole_register">
        <h2 class="register-name">Register</h2>
        <form method="post" action="${root }/user" id="register-form">
        	<input type="hidden" name="action" value="join" />
          <label for="userName">이름</label>
          <input name="userName" type="text" placeholder="이름..." />
          <label for="userId">아이디</label>
          <input name="userId" type="text" placeholder="아이디..." />
          <label for="userPwd">비밀번호</label>
          <input name="userPwd" type="passowrd" placeholder="비밀번호..." />
          <label for="pwc">비밀번호 확인</label>
          <input name="pwc" type="passowrd" placeholder="비밀번호 확인..." />
          <label for="email">이메일</label>
          <input name="email" type="email" placeholder="enjoytrip@gmail.com" />
          <div>
            <button id="signinbtn" type="submit">
              회원가입하기
            </button>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
