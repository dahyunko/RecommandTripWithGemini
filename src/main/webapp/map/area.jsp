<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
    
    <title>Document</title>
    <!-- style -->
    <link rel="stylesheet" href="${root }/assets/css/area.css" />

    <script src="${root }/assets/js/key.js"></script>
  </head>
  <body>
    <%@ include file="/common/nav.jsp"%>
    <div class="main">
      <div class="area_showcase" id="mainTab">
        <div class="search_area">
          <div class="input-group">
            <input
              type="search"
              class="form-control rounded"
              placeholder="Search"
              aria-label="Search"
              aria-describedby="search-addon"
            />
            <button type="button" class="btn btn-outline-primary">search</button>
          </div>
        </div>
        <div class="area_list">
          <div id="search-area" class="area_show">
            <!-- 지역들 -->
          </div>
          <!-- 지역들 * 10 -->
        </div>
        <div class="area_ads">
          <!-- 광고 -->
          <div class="m-3">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">
                  With supporting text below as a natural lead-in to additional content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
              </div>
            </div>
          </div>
          <div class="m-3">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">
                  With supporting text below as a natural lead-in to additional content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
              </div>
            </div>
          </div>
          <div class="m-3">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title">Special title treatment</h5>
                <p class="card-text">
                  With supporting text below as a natural lead-in to additional content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
              </div>
            </div>
          </div>
          <!-- 광고 * 3 -->
        </div>
      </div>

      <div class="content">
        <div class="content_title text-title-font">우리 지역 핫플이 궁금해?</div>
        <div class="content_main">
          <div class="content_subtitle text-content-font" id="place_content">
            <span style="color: aqua; font-size: 18px; font-weight: bold">경기</span> 추천 여행지
          </div>
          <div class="content_items" id="hot_place_items">
            <!-- 여행지 추천 -->

            <!-- 여행지 추천 *4 -->
          </div>
        </div>
      </div>
    </div>
    </hr>
<%@ include file="/common/footer.jsp"%>
    <script src="${root }/assets/js/area.js"></script>
    <script>
      // index page 로딩 후 전국의 시도 설정.
      let areaUrl =
        "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
        serviceKey +
        "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

      fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOption(data));

      function makeOption(data) {
        let areas = data.response.body.items.item;
        // console.log(areas);
        let sel = document.getElementById("search-area");
        areas.forEach((area) => {
          var areaItem = `
            <div class="area_item" onclick="getLocalInfo(this)">
                <div class="item_round">
                  <label for="" class="item_title">\${area.name}</label>
                </div>
                <input type="hidden" value="\${area.code}" />
              </div>
          `;

          sel.innerHTML += areaItem;
        });

        getLocalInfo(document.getElementsByClassName("area_item")[0]);
      }
    </script>
  </body>
</html>
