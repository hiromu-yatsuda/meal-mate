// OpenStreetMap タイルレイヤー（英語表記用 Carto Positron）
const cartoPositron = L.tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}{r}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors &copy; <a href="https://carto.com/">CARTO</a>',
    subdomains: 'abcd',
    maxZoom: 19
});

// 地図オブジェクトの作成
var map = L.map('myMap', {
    center: [32.639884, 130.987154], // 地図の初期中心位置（緯度, 経度）
    zoom: 8, // 初期ズームレベル
    layers: [cartoPositron] // 初期レイヤーとして Carto Positron を使用
});

// 現在位置の取得設定
map.locate({ setView: true, maxZoom: 150 });

// 現在位置取得成功時の処理
function success(e) {
    var radius = e.accuracy;
    L.marker(e.latlng, { icon: customIcon }).addTo(map)
        .bindPopup("現在地").openPopup();
    L.circle(e.latlng, radius).addTo(map);
}

// 現在位置取得失敗時の処理
function error(err) {
    var errorMessage = {
        1: "ページが許可を得ていないために、位置情報の取得に失敗しました。",
        2: "1 つ以上の位置の内部ソースが内部エラーを返したために、位置情報の取得に失敗しました。",
        3: "位置情報を取得するための制限時間が情報を取得する前に終了しました。"
    };
    alert(errorMessage[err.code]);
}

// 現在位置取得の成功と失敗イベント
map.on('locationfound', success);
map.on('locationerror', error);

// カスタムアイコン設定（現在地）
var customIcon = L.icon({
    iconUrl: 'https://unpkg.com/leaflet@1.9.2/dist/images/marker-icon.png',
    iconSize: [50, 70],
    iconAnchor: [25, 70],
    popupAnchor: [0, -70]
});

// カスタムアイコン設定（店舗ピン）
var customIconpin = L.icon({
    iconUrl: '/meal-mate/img/gennzaiti.png',
    iconSize: [50, 70],
    iconAnchor: [25, 70],
    popupAnchor: [0, -70]
});

// 詳細表示用の要素を作成
var detailContainer = document.createElement('div');
detailContainer.style.position = 'absolute';
detailContainer.style.bottom = '0';
detailContainer.style.left = '0';
detailContainer.style.height = '50%';
detailContainer.style.width = '96%';
detailContainer.style.backgroundColor = 'white';
detailContainer.style.borderTop = '1px solid #ccc';
detailContainer.style.overflowY = 'auto';
detailContainer.style.padding = '20px';
detailContainer.style.boxShadow = '0 -2px 5px rgba(0, 0, 0, 0.2)';
detailContainer.style.zIndex = '1000';
detailContainer.style.display = 'none';
detailContainer.style.margin = '2%';
detailContainer.style.borderRadius = '15px';
detailContainer.id = 'detailContainer';

document.body.appendChild(detailContainer);

const detailTextStyle = 'font-size: 0.7em; line-height: 1.3em; text-align: center;';
detailContainer.style.cssText += detailTextStyle;

function showDetails(content) {
    detailContainer.innerHTML = content;
    detailContainer.style.display = 'block';
}

function hideDetails() {
    detailContainer.style.display = 'none';
}

function addMarker(lat, lng, message) {
    var marker = L.marker([lat, lng], { icon: customIconpin }).addTo(map);
    marker.on('click', function() {
        showDetails(message);
    });
}

$.ajax({
	url: "/meal-mate/user/map/ajax",
	type: "GET",
	dataType: "json"
}).done(function (res) {
	const storeLength = res[Object.keys(res)[0]].length;
	console.log("件数: " + storeLength)

	for (let i = 0; i < storeLength; i++) {
		addMarker(res["latitude"][i], res["longitude"][i],
		`<h1 class="heading-2"><img src='../img/omise.png'> ${ res["storeName"][i] }</h1>
		<br><h2><img src='../img/zikan.png'> ${res["openingTime"][i] } ～ ${res["closingTime"][i] }</h2>
		<br><h2><img src='../img/okane.png'> ${res["avgAmountLow"][i] } ～ ${res["avgAmountHigh"][i] }円</h2>
		<br><h2><img src='../img/dennwa.png'> ${res["phoneNum"][i] }</h2>
		<br><h2>${res["figure1"][i] }${res["figure2"][i] }${res["figure3"][i] }`);
	}

	console.log("passed");
});

// 地図クリック時に詳細情報を非表示
map.on('click', hideDetails);

document.getElementById('locationButton').onclick = function() {
    map.locate({ setView: true, maxZoom: 16 });
};
