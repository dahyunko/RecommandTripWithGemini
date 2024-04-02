<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<title>Document</title>
<link rel="stylesheet" href="${root }/assets/css/diary.css" />
<link rel="stylesheet" href="${root }/assets/css/area.css" />
</head>
<body>
	<div>
		<%@ include file="/common/nav.jsp"%>
	</div>
	<div class="title">
		<mark class="txt">여행 일기 쓰기</mark>
	</div>

	<form class="input-form" method="POST" , action="${root}/diary">
		<input type="hidden" name="action" value="write">
		<div class="mb-3">
			<label for="title" class="form-label text-title-font">일기 제목</label> <input
				type="text" class="form-control text-content-font" id="title"
				name="title" placeholder="여행일기" />
		</div>
		<div class="mb-3">
			<label for="exampleColorInput" class="form-label text-title-font">일기
				테마 색상</label> <input type="color" class="form-control form-control-color"
				id="exampleColorInput" name="color" value="#F0FAFF"
				title="Choose your color" />
		</div>
		<div class="mb-3">
			<label for="formFileMultiple" class="form-label text-title-font">여행
				사진 추가하기</label> <input class="form-control text-content-font" type="file"
				id="formFileMultiple" name="imageFile" multiple />
		</div>
		<div class="mb-3">
			<label for="content" class="form-label text-title-font">일기 작성</label>
			<textarea class="form-control" id="content" name="content" rows="3"></textarea>
		</div>
		<div class="col-12 text-end">
			<input class="btn text-content-font btn-blue" type="submit"
				value="일기 생성하기" />
		</div>
	</form>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
