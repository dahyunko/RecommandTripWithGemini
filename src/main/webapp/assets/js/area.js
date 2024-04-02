// 지역별여행지에서 지역 눌렀을 때 보여주기

function getLocalInfo(item) {
  // 지역 코드
  let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;

  let queryString = `serviceKey=${serviceKey}&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
  let areaCode = item.getElementsByTagName("input")[0].value;
  let contentTypeId = 12;

  if (parseInt(areaCode)) queryString += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) queryString += `&contentTypeId=${contentTypeId}`;
  let service = ``;
  service = `areaBasedList1`;

  let searchUrl = baseUrl + service + "?" + queryString;
  console.log(searchUrl);

  document.getElementById("place_content").getElementsByTagName("span")[0].innerText = item.getElementsByTagName("label")[0].innerText;
  

  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeListHotPlaces(data));
}

function makeListHotPlaces(data) {
  //console.log(trips);
  let tripList = ``;
  let trips = data.response.body.items.item;
  trips.forEach((area) => {
    tripList += `
    <div class="m-2">
      <div class="card" style="width: 16rem;">`
      if (area.firstimage) {
        tripList += `<img src="${area.firstimage}" class="card-img-top" alt="..." style="height: 12rem; width: auto;"/>`
      } else {
        tripList += `<img src="../assets/img/no_img.png" class="card-img-top" alt="..." style="height: 12rem; width: auto;" />`
      }
        tripList += `
        <div class="card-body" style="height: 6.5rem;">
          <h5 class="card-title">${area.title}</h5>
          <p class="card-text">
            ${area.addr1} ${area.addr2}
          </p>
        </div>
      </div>
    </div>
    `;
  });

  document.getElementById("hot_place_items").innerHTML = tripList;
}