<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
	<link rel="stylesheet" href="${root }/assets/css/area.css" />
    <link rel="stylesheet" href="${root }/assets/css/gemini.css" />
    <title>Document</title>
  </head>
  <body>
    <!-- ... Your HTML and CSS -->
    <div class="main_map">
    <%@ include file="/common/nav.jsp"%>
      <div id="map" class=""></div>
      <div class="plans col_content box">
        <!--  -->
      </div>
    </div>
    <script src="./assets/js/key.js"></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=yourkey&libraries=services,clusterer,drawing"
    ></script>
    <script type="importmap">
      {
        "imports": {
          "@google/generative-ai": "https://esm.run/@google/generative-ai"
        }
      }
    </script>
    <script type="module" src="${root }/assets/js/gemini.js"></script>
  </body>
</html>
    
