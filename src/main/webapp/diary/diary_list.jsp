<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<title>Document</title>
<link rel="stylesheet" href="${root}/assets/css/diary.css" />
<link rel="stylesheet" href="${root}/assets/css/area.css" />
</head>
<body>
	<%@ include file="/common/nav.jsp"%>
	<div class="title">
		<mark class="txt">여행 일기 서랍장</mark>
	</div>
	<div class="diary_main">
		<div class="diary_head_btn">
			<button type="button" class="btn text-title-font btn-green" onclick="location.href='${root}/diary?action=mvwrite'">일기장
				만들기</button>
		</div>
		<div class="diary_list">
		<c:forEach var="diary" items="${diaries}">
			<!-- 일기장 -->
			<div class="card" style="width: 15rem">
				<img src="${root}/assets/img/diary_black.png" class="card-img-top"
					alt="..." />
				<div class="card-body">
					<h5 class="card-title text-title-font">${diary.title}</h5>
					<p class="card-text text-content-font">${diary.content}</p>
					<button type="button" class="btn text-content-font btn-blue" onclick="location.href='${root}/diary?action=view&diaryNo=${diary.diaryNo}'">일기장 펼치기</button>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
	</hr>
	<%@ include file="/common/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
