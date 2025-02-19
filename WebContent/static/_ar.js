let obj = {}; // バーコード検出回数を記録するオブジェクト
let time = 0; // 検出後の残り時間（フレーム単位）
const timeAllowed = 30; // バーコード検出の有効時間（フレーム単位）
let res; // Quaggaの検出結果を保存する変数
let imageArray = []; // 表示する画像の配列
let prevBarcode = ""; // 前回のバーコードを記録して、同じバーコードを繰り返さないようにする
const frameRate = 10; // 1秒あたりのフレーム数（描画頻度を制御）
let i = 0; // フレームごとのカウンタ
let isOk = false;

// デバッグ用の要素（後で削除する場合を想定）
const consoleElm = document.querySelector("#console"); // コンソール出力用のHTML要素
let consoleItems = []; // コンソールに表示する内容を管理する配列

$(document).ready(function() {
    const $quaggaContainer = $('#my_quagga');

    // カメラの比率とサイズを設定
    const cameraSettings = {
        inputStream: {
            name: "Live",
            type: "LiveStream",
            target: $quaggaContainer[0], // 描画先のターゲット
            constraints: {
                width: 1920, // 必要に応じて変更
                height: 1093, // 高さを調整
                aspectRatio: { ideal: 16 / 9 }, // 16:9 を指定
                facingMode: "environment" // 背面カメラを使用
            }
        },
        decoder: {
            readers: ["code_128_reader", "ean_reader", "ean_8_reader"], // 必要なバーコードリーダーを指定
        },
        frequency: 30, // 検出頻度（1秒間に何回検出を試みるか）
    };

    // QuaggaJS の初期化
    Quagga.init(cameraSettings, function(err) {
        if (err) {
            console.error("QuaggaJS initialization failed:", err);
            return;
        }
        console.log("QuaggaJS initialized successfully.");
        Quagga.start();
    });

 // 検出されたバーコードの処理
    Quagga.onDetected(async (result) => {
        res = result; // 検出結果を保存
        time = timeAllowed; // 検出後の残り時間をリセット
        const barcode = result.codeResult.code; // 検出されたバーコード
        console.log(barcode);
        await checkBarcode(barcode); // 検出回数を確認し処理
    });

    // フレームごとの処理
    Quagga.onProcessed((result) => {
        const ctx = Quagga.canvas.ctx.overlay; // Quaggaのオーバーレイ用Canvasのコンテキスト
        const canvas = Quagga.canvas.dom.overlay; // Quaggaのオーバーレイ用Canvas要素




        time--; // 残り時間を減少

        if (time <= 0) { // 残り時間が0になった場合
            ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height)); // キャンバスをクリア
            imageArray = []; // 画像配列をリセット
            prevBarcode = ""; // 前回のバーコードをリセット
            time = 0; // 時間をリセット
            isOk = false;
        }

//        console.log("imageArray.length: " + imageArray.length);
        if (i === 0 && imageArray.length > 0) { // 描画頻度を制御
            drawText(); // 画像を描画
        } else if (isOk && time > 0) {
        	console.log("drawOkIcon");
        	drawOkIcon();
        }
        i = (i + 1) % frameRate; // フレームカウンタを更新
    });
});
// Quaggaの初期化設定


// デバッグコンソールに出力する関数
function consoleLog(text) {
    consoleElm.innerHTML = ""; // デバッグ用HTML要素をクリア
    consoleItems = [text, ...consoleItems]; // 新しいログを配列の先頭に追加
    consoleItems.forEach((e) => {
        consoleElm.innerHTML += "<p>" + e + "</p><br>"; // HTMLにログを追加
    });
}

// Ajaxを使ってバーコードに対応する食品情報を取得する関数
function getFoods(barcode) {
    if (prevBarcode != barcode) { // 前回と異なるバーコードの場合のみ実行
        prevBarcode = barcode; // 現在のバーコードを記録
        imageArray = []; // 画像配列をリセット
        $.ajax({
            url: "/meal-mate/ajax-test", // サーバーのエンドポイント
            type: "GET", // HTTPメソッド
            data: {
                "barcode": barcode // サーバーに送信するデータ（バーコード）
            },
            dataType: "json" // サーバーからのレスポンスのデータ形式
        }).done(function (res) {
        	console.log("done");
            loadFoods(res["path"]); // 取得した画像パスを処理する
        }).fail(function (err) {
            consoleLog(err); // 通信失敗時のログ
        }).always(res => {
        	if (res.responseText == "") {
        		isOk = true;
        	}
        });
    }
}

// サーバーから受け取った食品画像をロードする関数
function loadFoods(foodsProp) {
	console.log("loadFoods");
	console.log(foodsProp);
	if (foodsProp.length == 0) {
		console.log("0件");
		isOk = true;
	} else {
		isOk = false;
		console.log("length: " + foodsProp.length);
	}
    for (let item of foodsProp) {
        const img = new Image(); // 新しいImageオブジェクトを作成
        img.src = item; // 画像のパスを設定
        imageArray.push(img); // 画像を配列に追加
    }
}

// バーコードの検出回数を確認し、規定回数に達したら食品情報を取得する
async function checkBarcode(num) {
    for (let key in obj) {
        if (key == num) { // 既に検出されたバーコードの場合
            obj[num] += 1; // 検出回数を増加
            if (obj[num] >= 5) { // 規定回数（10回）に達した場合
                obj = {}; // 検出回数をリセット
                getFoods(num); // 食品情報を取得
            }
            return;
        }
    }
    obj[num] = 1; // 新しいバーコードの場合は検出回数を初期化
}

// バーコードの位置を計算する関数
function calcRect(box) {
    const xDistance = -100; // X座標の補正値
    const x1 = box[1][0];
    const x2 = box[2][0];
    const x = (x1 + x2) / 2 + xDistance; // 中央位置を計算し補正
    const yDistance = -50; // Y座標の補正値
    const y1 = box[1][1];
    const y = y1 + yDistance; // 補正を適用
    return [x, y]; // 補正後の座標を返す
}

// 検出されたバーコード上に画像を描画する関数
function drawText() {
    const ctx = Quagga.canvas.ctx.overlay; // Quaggaのオーバーレイ用Canvasのコンテキスト
    const canvas = Quagga.canvas.dom.overlay; // Quaggaのオーバーレイ用Canvas要素
    ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height)); // キャンバスをクリア
    if (res == null || typeof res != "object" || res.boxes == undefined) {
        return; // 検出結果が無効な場合は終了
    }

    const rect = calcRect(res.box); // バーコードの描画位置を計算

    const width = 210; // 各画像の幅
    const height = 210; // 各画像の高さ
    const numTimes = 9; // 最大表示する画像の数
    const kaisuu = numTimes > imageArray.length ? imageArray.length : numTimes; // 実際の表示数
    let count = 0; // 描画する画像のカウンタ

    const batsu = new Image();
    batsu.src = "/meal-mate/user/icons/batu.png";
    ctx.drawImage(batsu, rect[0]-50, rect[1]-50, width, height);

    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (count < kaisuu) {
                ctx.drawImage(imageArray[count], rect[0] + width * j, rect[1] + height * i, width, height); // 画像を描画
                count++;
            } else {
            	break;
            }
        }
    }
}

function drawOkIcon() {
    const ctx = Quagga.canvas.ctx.overlay; // Quaggaのオーバーレイ用Canvasのコンテキスト
    const canvas = Quagga.canvas.dom.overlay; // Quaggaのオーバーレイ用Canvas要素
    ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height)); // キャンバスをクリア
    if (res == null || typeof res != "object" || res.boxes == undefined) {
        return; // 検出結果が無効な場合は終了
    }

    const rect = calcRect(res.box); // バーコードの描画位置を計算
    const width = 210;
    const height = 210;

    const okIcon = new Image;
    okIcon.src = "/meal-mate/user/icons/maru.png";

    ctx.drawImage(okIcon, rect[0], rect[1], width, height);
}

