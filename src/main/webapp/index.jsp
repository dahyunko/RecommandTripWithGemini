<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<title>Document</title>
<!-- style -->
<link rel="stylesheet" href="${root }/assets/css/style.css" />
<link rel="stylesheet" href="${root }/assets/css/area.css" />
</head>
<body>
<div class="main_body">
<%@ include file="/common/nav.jsp"%>

<!-- container start -->
<div class="main_container">
	<p>Let's Enjoy Your</p>
	<div class="typing">
		<p>Trip In</p>
		<p class="moving">Seoul...</p>
	</div>
	<div class="search_container">
		<!-- 관광지 검색 start -->
		<form class="d-flex my-3" onsubmit="return false;" role="search">
			<select id="search-area" class="form-select me-2">
				<option value="0" selected>검색 할 지역 선택</option>
			</select> <select id="search-content-id" class="form-select me-2">
				<option value="0" selected>관광지 유형</option>
				<option value="12">관광지</option>
				<option value="14">문화시설</option>
				<option value="15">축제공연행사</option>
				<option value="25">여행코스</option>
				<option value="28">레포츠</option>
				<option value="32">숙박</option>
				<option value="38">쇼핑</option>
				<option value="39">음식점</option>
			</select> <input id="search-keyword" class="form-control me-2" type="search"
				placeholder="검색어" aria-label="검색어" />
			<button id="btn-search" class="btn btn-outline-secondary btn-md"
				type="button" onclick="location.href='${root}/map/triplist.jsp'">
				검색</button>
		</form>
	</div>
</div>
<img src="./assets/img/kyeong-bok.jpg" alt="" id="kyeong_bok" />
<!-- container end -->

<!-- footer start -->
<!-- footer end -->
</div>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="./assets/js/main.js"></script>
</body>
</html>
