// recommendation.js

import { GoogleGenerativeAI } from "@google/generative-ai";

// Fetch your API_KEY
const API_KEY = "your api";

// Access your API key (see "Set up your API key" above)
const genAI = new GoogleGenerativeAI(API_KEY);

var positions = []; // marker 배열.
var markers = [];

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    // center: new kakao.maps.LatLng(lat, lon),
    level: 7, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

async function run() {
  // For text-only input, use the gemini-pro model
  const model = genAI.getGenerativeModel({ model: "gemini-pro" });

  var prompt = "";
  prompt +=
    "해운대 1박 2일 여행 코스 알려줘. 일정은 최대 3개씩 하루에 두 번, 총 6개만 알려줘.";
  prompt += "걷기 다니기 좋은 곳 출발 지점과 도착지점을 함께 추천해줘.";
  prompt +=
    "각 일정에는 날짜(1일차), 시간 (00:00 ~ 00:00), 출발 장소, 도착 장소, 이동 수단, 이동 소요 시간, 이동 수단, 도착 장소 간단한 설명, 예상 비용(1000), 위도, 경도 포함해줘.";
  prompt +=
    "json format으로 추천해줘, 컬럼명은 영어, value는 한국어로 제공해줘";
  prompt += `컬럼명는 다음과 같아, 날짜는 "date", 시간은 "time", 출발 장소는 "start_loc", 도착 장소는 "end_loc", 이동 소요 시간은 "move_time", 이동 수단은 "transport", 간단한 설명은 "des", 비용은 "cost", 위도는 "lat", 경도는 "lon"`;

  const result = await model.generateContent(prompt);
  const response = await result.response;
  const text = response.text();
  console.log(text);
  const jsonData = text.split("```")[1].split("json")[1];
  console.log(JSON.parse(jsonData));
  return JSON.parse(jsonData);
}

async function showPlan() {
  // const planData = await run();
  var planList = [];
  planList = [
    {
      "date": "1일차",
      "time": "10:00 ~ 12:00",
      "start_loc": "해운대 해수욕장 (해운대역 5번 출구)",
      "end_loc": "해운대 달맞이 고래공원",
      "move_time": "30분",
      "transport": "도보",
      "des": "해변 산책로를 따라 가며 해운대 해변의 아름다운 전망을 즐겨보세요.",
      "cost": 0,
      "lat": 35.159929,
      "lon": 129.144765
    },
    {
      "date": "1일차",
      "time": "13:00 ~ 15:00",
      "start_loc": "해운대 달맞이 고래공원",
      "end_loc": "벡스코(BEXCO)",
      "move_time": "20분",
      "transport": "도보",
      "des": "대규모 국제 컨벤션 및 전시장을 방문하여 첨단 시설과 전시회를 둘러보세요.",
      "cost": 0,
      "lat": 35.154783,
      "lon": 129.147492
    },
    {
      "date": "1일차",
      "time": "18:00 ~ 20:00",
      "start_loc": "해운대에서 숙박 시설",
      "end_loc": "해운대 중앙시장",
      "move_time": "20분",
      "transport": "도보",
      "des": "활기찬 시장에서 다양한 길거리 음식, 해산물 및 기념품을 둘러보세요.",
      "cost": 20000,
      "lat": 35.149912,
      "lon": 129.153155
    },
    {
      "date": "2일차",
      "time": "09:00 ~ 11:00",
      "start_loc": "해운대에서 숙박 시설",
      "end_loc": "송정해변공원",
      "move_time": "25분",
      "transport": "택시",
      "des": "해변과 레스토랑이 즐비한 아름다운 해변 공원에서 휴식을 취하고 수영을 즐기세요.",
      "cost": 15000,
      "lat": 35.125117,
      "lon": 129.162122
    },
    {
      "date": "2일차",
      "time": "13:00 ~ 15:00",
      "start_loc": "송정해변공원",
      "end_loc": "동백섬",
      "move_time": "20분",
      "transport": "버스(100번)",
      "des": "경치 좋은 섬에서 자연 경관과 조용한 분위기를 즐기세요.",
      "cost": 2500,
      "lat": 35.100089,
      "lon": 129.130522
    },
    {
      "date": "2일차",
      "time": "18:00 ~ 20:00",
      "start_loc": "동백섬",
      "end_loc": "해운대역",
      "move_time": "20분",
      "transport": "버스(100번)",
      "des": "해운대역으로 돌아가 여행을 마무리하세요.",
      "cost": 2500,
      "lat": 35.166235,
      "lon": 129.145638
    }
  ];

  var plans = document.getElementsByClassName("plans")[0];
  for (let i = 0; i < planList.length; i++) {
    // planList.push(
    //   new Plan(
    //     planData[i].date, 
    //     planData[i].time, 
    //     planData[i].start_loc,
    //     planData[i].end_loc, 
    //     planData[i].move_time, 
    //     planData[i].transport, 
    //     planData[i].des, 
    //     planData[i].cost, 
    //     planData[i].lat, 
    //     planData[i].lon
    //     )
    // );

    plans.innerHTML += `
    <div class="mx-1">
      <div class="card plan"
      style="width: 25rem;">
        <div class="card-body">
          <h5 class="card-title">${planList[i].end_loc}</h5>
          <h6 class="card-subtitle mb-2 text-body-secondary">${planList[i].date}</h6>
          <p class="card-text">
          출발 위치 : ${planList[i].start_loc}</br>
          이동 수단 및 시간 : ${planList[i].transport}/ ${planList[i].move_time} </br>
          설명 : ${planList[i].des} </br>
          비용 : ${planList[i].cost}원 </br>
          예상 소요 시간 : ${planList[i].time}
          </p>
          <a href="https://map.kakao.com/link/search/${planList[i].loc}" target="_blank">조회하기</a>
        </div>
      </div>
    </div>
    `;

    let markerInfo = {
      title: planList[i].loc,
      latlng: new kakao.maps.LatLng(planList[i].lat, planList[i].lon),
    };
    positions.push(markerInfo);
  }
  console.log(positions);

  displayMarker();
}

showPlan();

function displayMarker() {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  console.log("postion", positions);
  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });

    markers.push(marker);
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
  // console.log(lat, lng);
  map.setCenter(new kakao.maps.LatLng(lat, lng));
}

function setMarkers(map) {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(map);
  }
}

function hideMarkers() {
  setMarkers(null);
}

class Plan {
  date;
  time;
  start_loc;
  end_loc;
  move_time;
  transport;
  des;
  cost;
  lat;
  lon;

  constructor( date, time, start_loc, end_loc, move_time,transport, des, cost, lat, lon,){
    this.date = date;
    this.time = time;
    this.start_loc = start_loc;
    this.end_loc = end_loc;
    this.move_time = move_time;
    this.transport = transport;
    this.des = des;
    this.cost = cost;
    this.lat = lat;
    this.lon = lon;
  }
  
}
