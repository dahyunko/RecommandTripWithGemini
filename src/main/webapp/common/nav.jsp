<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header text-title-font">
	<div class="header_title logo" onclick="location.href='${root}/index.jsp'">EnjoyTrip</div>
	<div class="header_move">
		<div class="">
			<a href="${root }/map/area.jsp" class="link-txt">지역별여행지</a>
		</div>
		<div class="">
			<a href="${root }/map/area.jsp" class="link-txt">나의여행계획</a>
		</div>
		<div class="">
			<a href="${root}/diary?action=list" class="link-txt">핫플자랑하기</a>
		</div>
		<div class="">
			<a href="" class="link-txt">여행정보공유</a>
		</div>
	</div>
	<div class="header_login">
		<c:if test="${empty userinfo}">
			<%
			if (request.getAttribute("msg") != null) {
			%>
			<script>
			window.alert("<%=request.getAttribute("msg")%>");
			</script>
			<%}%>
			<div>
				<a href="${root}/user?action=showlogin" class="link-txt">로그인</a>
			</div>
			<div>
				<a href="${root}/user?action=mvjoin" class="link-txt">회원가입</a>
			</div>
		</c:if>
		<c:if test="${not empty userinfo}">
			<div>
			<span>${userinfo.userName } 님</span>
				<a href="${root}/user?action=logout">로그아웃</a>
			</div>
		</c:if>
	</div>
</div>