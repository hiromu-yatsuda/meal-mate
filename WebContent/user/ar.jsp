<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>QuaggaJS</title>
<link rel="stylesheet" href="/meal-mate/static/ar.css" />
<style>
    html, body {
        margin: 0;
        padding: 0;
        height: 100%; /* 全体の高さを固定 */
        overflow: hidden; /* スクロールを無効化 */
    }

    #my_quagga {
        position: relative;
        width: 98%;
        height: calc(100vw * 9 / 16 * 4); /* 16:9 の比率を基に高さを調整 */
        max-height: 90vh; /* 必要に応じて画面全体に収まるよう制限 */
        background-color: #000; /* 黒背景を設定して見やすくする */
    }

    #console {
        margin-top: 10px;
    }
</style>
</head>
<body>
    <%@ include file="../userbase.jsp" %>
<div id="my_quagga"></div>
<div id="console"></div>
<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="//unpkg.com/@ericblade/quagga2@1.7.4/dist/quagga.min.js"></script>
<script src="//cdn.jsdelivr.net/gh/mtaketani113/jquery-barcode@master/jquery-barcode.js"></script>
<script src="/meal-mate/static/ar.js"></script>
<script>
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
            }
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

        // 結果のコールバック
        Quagga.onDetected(function(data) {
        });
    });
</script>
</body>
</html>
