var positions; // marker 배열.
var markers = [];



function makeList(data) {
	console.log(data);
	document
		.getElementsByClassName("row_content")[0]
		.setAttribute("style", "display: flex;");
	let trips = data.response.body.items.item;
	let tripList = ``;
	positions = [];
	trips.forEach((area) => {

		console.log(area);

		tripList += `
    <div style="margin-left: 10px;" class="swiper-slide" >
      <div class="card" style="width: 15rem; height: 17rem;" onclick="moveCenter(${area.mapy}, ${area.mapx});">`;
		if (area.firstimage) {
			tripList += `<img src="${area.firstimage}" class="card-img-top" style="width: auto; height: 10rem;" alt="...">`;
		}
		else {
			tripList += `<img src="../assets/img/no_img.png" class="card-img-top" style="width: auto; height: 10rem;" alt="...">`;
		}
		tripList += ` <div class="card-body">
          <p class="card-title" style="font-size: 15px;">${area.title}</p>
          <p class="card-text" style="font-size: 12px;">${area.addr1} ${area.addr2}</p>
        </div>
      </div>
    </div>
    `;

		let markerInfo = {
			title: area.title,
			latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
		};
		positions.push(markerInfo);
	});
	document.getElementById("trip-list2").innerHTML = tripList;
	// document.getElementById("trip-list").innerHTML = tripList;
	displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		// center: new kakao.maps.LatLng(lat, lon),
		level: 5, // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
if (navigator.geolocation) {
	// GeoLocation을 이용해서 접속 위치를 얻어옵니다
	navigator.geolocation.getCurrentPosition(function(position) {
		var lat = position.coords.latitude, // 위도
			lon = position.coords.longitude; // 경도

		var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			message =
				'<div style="padding:5px; border-radius: 10px;">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다

		// 마커와 인포윈도우를 표시합니다
		displayMarkerUserPosition(locPosition, message);
	});
} else {
	// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

	var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
		message = "geolocation을 사용할수 없어요..";

	displayMarkerUserPosition(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarkerUserPosition(locPosition, message) {
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		map: map,
		position: locPosition,
	});

	markers.push(marker);

	var iwContent = message, // 인포윈도우에 표시할 내용
		iwRemoveable = true;

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
		content: iwContent,
		removable: iwRemoveable,
	});

	// 인포윈도우를 마커위에 표시합니다
	infowindow.open(map, marker);

	// 지도 중심좌표를 접속위치로 변경합니다
	map.setCenter(locPosition);
}

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
