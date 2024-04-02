<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.ssafy.diary.dto.DiaryDto"%>

<%@ include file="/common/header.jsp"%>

<title>Document</title>
<link rel="stylesheet" href="${root }/assets/css/diary.css" />
<link rel="stylesheet" href="${root }/assets/css/area.css" />
</head>
<div>
	<%@ include file="/common/nav.jsp"%>
</div>
<body>
	<div class="title">
		<mark class="txt">여행 일기 쓰기</mark>
	</div>

	<form class="input-form" method="POST" , action="${root}/diary">
		<input type="hidden" name="action" value="modify"> <input
			type="hidden" name="diaryNo" value="${diary.diaryNo}">

		<div class="mb-3">
			<label for="title" class="form-label text-title-font">일기 제목</label> <input
				type="text" class="form-control text-content-font" id="title"
				name="title" value="${diary.title}" />
		</div>
		<div class="mb-3">
			<label for="color" class="form-label text-title-font">일기 테마
				색상</label> <input type="color" class="form-control form-control-color"
				id="exampleColorInput" name="color" value="${diary.color}"
				title="Choose your color" />
		</div>
		<div class="mb-3">
			<label for="formFileMultiple" class="form-label text-title-font">여행
				사진</label>
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="true">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<div class="diary_section">
							<img src="${root}/assets/img/${diary.diaryImg}"
								class="d-block diary_img" alt="..." />
						</div>
					</div>
					<div class="carousel-item">
						<div class="diary_section">
							<img src="${root}/assets/img/travel_diary_2.jpg"
								class="d-block diary_img" alt="..." />
						</div>
					</div>
					<div class="carousel-item">
						<div class="diary_section">
							<img src="${root }/assets/img/travel_diary_3.jpg"
								class="d-block diary_img" alt="..." />
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label text-title-font">일기 작성</label>
			<textarea class="form-control" id="content" name="content" rows="3">${diary.content}</textarea>
		</div>
		<div class="col-12 text-end">
			<input class="btn text-content-font btn-blue" type="submit"
				value="일기 수정하기" />
		</div>
	</form>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
