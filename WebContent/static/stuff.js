//	住所を座標に
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('getCoordinatesButton2').addEventListener('click', function(event) {
    	console.log("daaaaaaaaaaaaa ");

    	event.preventDefault();
        var address = document.getElementById('address_a').value;
        console.log("Entered Address: " + address); // 取得した住所をコンソールに出力

        fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=YOUR_API_KEY`)
            .then(response => response.json())
            .then(data => {
                if (data.status === 'OK') {
                    var location = data.results[0].geometry.location;
                    var latitude = location.lat;
                    var longitude = location.lng;

                    console.log("ja_Latitude: " + latitude); // 座標をコンソールに出力
                    console.log("js_Longitude: " + longitude); // 座標をコンソールに出力

                    fetch('/MealMate/map', {
                        method: 'post',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: `latitude=${latitude}&longitude=${longitude}`
                    })
                    .then(response => response.text())
                    .then(data => {
                        console.log(data);
                    });
                } else {
                    alert('座標を取得できませんでした');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});

//	従業員-商品登録
//	「＋」ボタンクリック -> 入力項目追加
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('addButton').addEventListener('click', function() {
        var form = document.getElementById('foods_reg');
        var clone = form.cloneNode(true);
        document.body.insertBefore(clone, document.getElementById('pro_plus'));
    });
});



//	従業員-店舗情報変更
//	写真をアップロード用
function uploadPhoto() {
    const form = document.getElementById('uploadForm');
    const formData = new FormData(form);

    fetch('UploadServlet', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            displayUploadedImages(data.images);
        } else {
            alert('アップロードに失敗しました');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function displayUploadedImages(images) {
    const container = document.getElementById('uploadedImages');
    container.innerHTML = ''; // 既存の画像をクリア

    images.forEach(image => {
        const imgElement = document.createElement('img');
        imgElement.src = 'path/to/images/' + image.filename;
        container.appendChild(imgElement);
    });
}

