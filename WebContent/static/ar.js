let obj = {}; // バーコード検出回数を記録するオブジェクト
let time = 0; // 検出後の残り時間（フレーム単位）
const timeAllowed = 20; // バーコード検出の有効時間（フレーム単位）
let res; // Quaggaの検出結果を保存する変数
let imageArray = []; // 表示する画像の配列
let prevBarcode = ""; // 前回のバーコードを記録して、同じバーコードを繰り返さないようにする
const frameRate = 10; // 1秒あたりのフレーム数（描画頻度を制御）
let i = 0; // フレームごとのカウンタ

// デバッグ用の要素（後で削除する場合を想定）
const consoleElm = document.querySelector("#console"); // コンソール出力用のHTML要素
let consoleItems = []; // コンソールに表示する内容を管理する配列

// Quaggaの初期化設定
Quagga.init(
    {
        inputStream: {
            name: "Live", // 入力ストリームの名前
            type: "LiveStream", // カメラからのライブ映像を使用
            target: document.getElementById("my_quagga"), // 映像を表示するターゲット要素
        },
        decoder: {
            readers: ["ean_reader"], // 対応するバーコードの形式（EAN形式を指定）
        },
        frequency: 20, // 検出頻度（1秒間に何回検出を試みるか）
    },
    (err) => {
        if (err) {
            console.log(err); // エラーが発生した場合はログに出力
            return;
        }
        console.log("すたーと"); // 初期化が成功した場合のメッセージ
        Quagga.start(); // バーコード検出を開始
    }
);

// デバッグコンソールに出力する関数
function consoleLog(text) {
    console.log(text + "\n"); // 標準コンソールにも出力
    consoleElm.innerHTML = ""; // デバッグ用HTML要素をクリア
    consoleItems = [text, ...consoleItems]; // 新しいログを配列の先頭に追加
    consoleItems.forEach((e) => {
        consoleElm.innerHTML += "<p>" + e + "</p><br>"; // HTMLにログを追加
    });
}

// Ajaxを使ってバーコードに対応する食品情報を取得する関数
function getFoods(barcode) {
    consoleLog("called getFoods"); // 関数が呼び出されたことを記録
    if (prevBarcode != barcode) { // 前回と異なるバーコードの場合のみ実行
        consoleLog("ajax-start"); // Ajax通信開始のログ
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
            loadFoods(res["paths"]); // 取得した画像パスを処理する
            consoleLog("done"); // 通信成功時のログ
        }).fail(function (res) {
            consoleLog(res); // 通信失敗時のログ
        }).always(function (res) {
            consoleLog("always"); // 通信完了後（成功・失敗問わず）のログ
        });
    }
}

// サーバーから受け取った食品画像をロードする関数
function loadFoods(foodsProp) {
    for (let item of foodsProp) {
        const img = new Image(); // 新しいImageオブジェクトを作成
        img.src = `/meal-mate/img/${item}`; // 画像のパスを設定
        imageArray.push(img); // 画像を配列に追加
    }
}

// バーコードの検出回数を確認し、規定回数に達したら食品情報を取得する
async function checkBarcode(num) {
    for (let key in obj) {
        if (key == num) { // 既に検出されたバーコードの場合
            obj[num] += 1; // 検出回数を増加
            if (obj[num] >= 10) { // 規定回数（10回）に達した場合
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

    const width = 130; // 各画像の幅
    const height = 130; // 各画像の高さ
    const numTimes = 9; // 最大表示する画像の数
    const kaisuu = numTimes > imageArray.length ? imageArray.length : numTimes; // 実際の表示数
    let count = 0; // 描画する画像のカウンタ
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            if (count < kaisuu) {
                ctx.drawImage(imageArray[count], rect[0] + width * i, rect[1] + height * j, width, height); // 画像を描画
                count++;
            }
        }
    }
    consoleLog("test"); // デバッグ用のログ
}

// 検出されたバーコードの処理
Quagga.onDetected(async (result) => {
    res = result; // 検出結果を保存
    time = timeAllowed; // 検出後の残り時間をリセット
    const barcode = result.codeResult.code; // 検出されたバーコード
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
    }

    if (i === 0 && imageArray.length > 0) { // 描画頻度を制御
        drawText(); // 画像を描画
    }
    i = (i + 1) % frameRate; // フレームカウンタを更新
});