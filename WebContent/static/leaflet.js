// OpenStreetMap タイルレイヤーの作成
// 地図を表示するための背景タイルを OpenStreetMap のタイルレイヤーとして設定しています。
// タイルは指定された URL パターンに従って取得されます。
const osm = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>' // 著作権情報を表示
});

// 地図オブジェクトの作成
// 指定した HTML 要素（id="myMap"）に地図を描画します。
// 地図の初期位置（緯度と経度）やズームレベル、使用するレイヤーを設定します。
var map = L.map('myMap', {
    center: [32.639884, 130.987154], // 地図の初期中心位置（緯度, 経度）
    zoom: 8, // 初期ズームレベル
    layers: [osm] // 初期レイヤーとして OpenStreetMap を使用
});

// 現在位置の取得設定
// ブラウザの位置情報 API を使用して現在地を取得し、地図をその位置に移動させます。
map.locate({ setView: true, maxZoom: 150 }); // maxZoom は位置取得時の最大ズームレベル

// 現在位置取得成功時の処理
// ユーザーの現在位置が取得できた場合に実行される関数
function success(e) {
    var radius = e.accuracy; // 取得した位置情報の精度（半径のメートル数）
    // 現在位置にマーカーを追加し、ポップアップで「現在地」を表示
    L.marker(e.latlng, { icon: customIcon }).addTo(map)
        .bindPopup("現在地").openPopup();
    // 現在地の精度を円で表示
    L.circle(e.latlng, radius).addTo(map);
}

// 現在位置取得失敗時の処理
// 位置情報の取得に失敗した場合に実行される関数
function error(err) {
    var errorMessage = {
        1: "ページが許可を得ていないために、位置情報の取得に失敗しました。", // 許可されていない
        2: "1 つ以上の位置の内部ソースが内部エラーを返したために、位置情報の取得に失敗しました。", // 内部エラー
        3: "位置情報を取得するための制限時間が情報を取得する前に終了しました。" // タイムアウト
    };
    alert(errorMessage[err.code]); // エラーコードに対応するメッセージをアラート表示
}

// 現在位置取得の成功と失敗イベントにそれぞれの関数を設定
map.on('locationfound', success); // 成功時の処理
map.on('locationerror', error); // 失敗時の処理

// カスタムアイコンを設定(現在地)
// 地図上のマーカーアイコンをカスタマイズ
var customIcon = L.icon({
    iconUrl: 'https://unpkg.com/leaflet@1.9.2/dist/images/marker-icon.png', // アイコンの画像URL
    iconSize: [50, 70], // アイコンのサイズ（幅, 高さ）
    iconAnchor: [25, 70], // アイコンの基準点（アイコンの左上が基準、基準点を [25, 70] に設定）
    popupAnchor: [0, -70] // ポップアップが表示される位置（基準点からのオフセット）
});

// カスタムアイコンを設定（店舗のピン）
// 地図上のマーカーアイコンをカスタマイズ
var customIconpin = L.icon({
    iconUrl: '/meal-mate/img/gennzaiti.png', // アイコンの画像URL
    iconSize: [50, 70], // アイコンのサイズ（幅, 高さ）
    iconAnchor: [25, 70], // アイコンの基準点（アイコンの左上が基準、基準点を [25, 70] に設定）
    popupAnchor: [0, -70] // ポップアップが表示される位置（基準点からのオフセット）
});

//詳細表示用の要素を作成
//画面下部に詳細情報を表示するためのコンテナを作成
var detailContainer = document.createElement('div');
detailContainer.style.position = 'absolute'; // 画面に固定する
detailContainer.style.bottom = '0'; // 下部に配置
detailContainer.style.left = '0'; // 左端に配置
detailContainer.style.height = '50%'; // 高さを画面の半分に設定
detailContainer.style.width = '96%'; // 幅を設定
detailContainer.style.backgroundColor = 'white'; // 背景色を白に設定
detailContainer.style.borderTop = '1px solid #ccc'; // 上部に薄い枠線を追加
detailContainer.style.overflowY = 'auto'; // コンテンツが多い場合スクロール可能にする
detailContainer.style.padding = '20px'; // コンテンツ周りに余白を設定
detailContainer.style.boxShadow = '0 -2px 5px rgba(0, 0, 0, 0.2)'; // 影を追加して浮き上がったような効果
detailContainer.style.zIndex = '1000'; // 他の要素より前面に表示
detailContainer.style.display = 'none'; // 初期状態では非表示
detailContainer.style.margin = '2%'; // 外側の余白を設定
detailContainer.style.borderRadius = '15px'; // 角を丸める
detailContainer.id = 'detailContainer'; // id を設定して後から参照可能にする



document.body.appendChild(detailContainer); // 作成した要素を HTML ドキュメントに追加

// 詳細表示テキストのスタイルを大きくし、中央揃えに設定
const detailTextStyle = 'font-size: 1.5em; line-height: 1.3em; text-align: center;'; // 中央揃えを追加
detailContainer.style.cssText += detailTextStyle; // 追加スタイルを適用

// 詳細情報を表示する関数
function showDetails(content) {
    detailContainer.innerHTML = content; // 表示する内容を設定
    detailContainer.style.display = 'block'; // コンテナを表示
}

// 詳細情報を非表示にする関数
function hideDetails() {
    detailContainer.style.display = 'none'; // コンテナを非表示
}

// 指定座標にピンを立てる関数
// 地図上にマーカーを追加し、クリック時に詳細情報を表示する
function addMarker(lat, lng, message) {
    var marker = L.marker([lat, lng], { icon: customIconpin }).addTo(map); // マーカーを追加
    marker.on('click', function() { // マーカーがクリックされたときの動作
        showDetails(message); // 詳細情報を表示
    });
}
$.ajax({
	url: "/meal-mate/user/map/ajax",
	type: "GET",
	dataType: "json"
}).done(function (res) {
//	const keys = Object.keys(res);
	const storeLength = res[Object.keys(res)[0]].length;
	console.log("件数: " + storeLength)

	for (let i=0; i<storeLength; i++) {
		addMarker(res["latitude"][i], res["longitude"][i], "<h1>" + res["storeName"][i] + "</h1>");
	}

	console.log("passed");
})

// 例: 特定の座標にピンを立てる
//addMarker(32.789884, 130.987154, "<h2>ラーメン山口どっこいしょ</h2><br>営業時間 8:00~24:00<br>1000~1050円<br>070-1274-0112");
//addMarker(32.639884, 131.087154, "<h2>うどん屋モッコリ堂</h2><br>営業時間 8:00~24:00<br>1000~1050円<br>070-1274-0112");
//addMarker(36.160657, 139.244206, "<h2>八ツ田の家</h2><br>営業時間 8:00~24:00<br>1000~1050円<br>070-1274-0112");

// 地図クリック時に詳細情報を非表示にする
map.on('click', hideDetails);

// 現在地ボタンの処理
// ボタンをクリックすると現在地を再取得して地図を移動
document.getElementById('locationButton').onclick = function() {
    map.locate({ setView: true, maxZoom: 16 }); // 再度現在地を取得し地図を移動
};
