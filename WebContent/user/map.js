let map, marker;

// Googleマップを初期化
function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 35.6895, lng: 139.6917 }, // デフォルトは東京
        zoom: 12,
    });

    marker = new google.maps.Marker({
        map: map,
    });
}

// 現在地を取得してマップに表示
function locateUser() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const userLocation = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                map.setCenter(userLocation);
                map.setZoom(15);
                marker.setPosition(userLocation);
            },
            () => {
                alert("現在地を取得できませんでした。");
            }
        );
    } else {
        alert("お使いのブラウザは現在地取得に対応していません。");
    }
}

// ボタンイベント
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("locateBtn").addEventListener("click", locateUser);
    initMap();
});
