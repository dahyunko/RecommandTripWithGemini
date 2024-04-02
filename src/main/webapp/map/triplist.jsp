<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

    <title>SSAFY BOOK CAFE</title>
    <script src="${root }/assets/js/key.js"></script>
    <link rel="stylesheet" href="${root }/assets/css/map.css" />
    <link rel="stylesheet" href="${root }/assets/css/area.css" />
  </head>
  <body>
    <!-- 중앙 content start -->
    <div class="main_map">
    <%@ include file="/common/nav.jsp"%>
      <!-- 중앙 center content end -->
      <!-- kakao map start -->
      <div id="map" class=""></div>
      <!-- kakao map end -->
      <!-- 관광지 검색 start -->
      <div id="search_box">
        <form class="" onsubmit="return false;" role="search">
          <select id="search-area" class="form-select me-2">
            <option value="0" selected>검색 할 지역 선택</option>
          </select>
          <select id="search-content-id" class="form-select me-2">
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
          <input
            id="search-keyword"
            class="form-control me-2"
            type="search"
            placeholder="검색어"
            aria-label="검색어"
          />
          <button id="btn-search" class="btn btn-outline-success" type="button">검색</button>
        </form>

        <div class="row_content">
          <div id="trip-list2" class="box">
            <!-- card -->
          </div>
        </div>
        <!-- 관광지 검색 end -->
      </div>
    </div>
    <!-- 중앙 content end -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2542df776f47e9038ac94dd697a39019&libraries=services,clusterer,drawing"
    ></script>
    <script src="${root }/assets/js/map.js"></script>
    <script>
      // index page 로딩 후 전국의 시도 설정.
      let areaUrl =
        "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
        serviceKey +
        "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

      // fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
      fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOption(data));

      function makeOption(data) {
        let areas = data.response.body.items.item;
        // console.log(areas);
        let sel = document.getElementById("search-area");
        areas.forEach((area) => {
          let opt = document.createElement("option");
          opt.setAttribute("value", area.code);
          opt.appendChild(document.createTextNode(area.name));

          sel.appendChild(opt);
        });
      }

      // 검색 버튼을 누르면..
      // 지역, 유형, 검색어 얻기.
      // 위 데이터를 가지고 공공데이터에 요청.
      // 받은 데이터를 이용하여 화면 구성.
      
      document.getElementById("btn-search").addEventListener("click", () => {
        let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
        // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
        // let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

        hideMarkers();

        let queryString = `serviceKey=\${serviceKey}&numOfRows=30&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
        let areaCode = document.getElementById("search-area").value;
        let contentTypeId = document.getElementById("search-content-id").value;
        let keyword = document.getElementById("search-keyword").value;

        if (parseInt(areaCode)) queryString += `&areaCode=\${areaCode}`;
        if (parseInt(contentTypeId)) queryString += `&contentTypeId=\${contentTypeId}`;
        // if (!keyword) {
        //   alert("검색어 입력 필수!!!");
        //   return;
        // } else searchUrl += `&keyword=${keyword}`;
        let service = ``;
        if (keyword) {
          service = `searchKeyword1`;
          queryString += `&keyword=\${keyword}`;
        } else {
          service = `areaBasedList1`;
        }
        let searchUrl = baseUrl + service + "?" + queryString;

        fetch(searchUrl)
          .then((response) => response.json())
          .then((data) => makeList(data));
      });
    </script>
     <script>
      //스와이프 기능 구현
      let isDown = false;
      let startX;
      let scrollLeft;

      const searchList = document.getElementById("trip-list2");

      searchList.addEventListener("mousedown", (event) => {
        isDown = true;
        searchList.classList.add("active");
        startX = event.pageX - searchList.offsetLeft;
        scrollLeft = searchList.scrollLeft;
      });

      searchList.addEventListener("mousemove", (event) => {
        if (!isDown) return;

        event.preventDefault();
        const x = event.pageX - searchList.offsetLeft;
        const walk = (x - startX) * 2; //스크롤 속도 조절
        searchList.scrollLeft = scrollLeft - walk;
      });

      searchList.addEventListener("mouseleave", () => {
        isDown = false;
        searchList.classList.remove("active");
      });

      searchList.addEventListener("mouseup", () => {
        isDown = false;
        searchList.classList.remove("active");
      });
    </script>
  </body>
</html>
