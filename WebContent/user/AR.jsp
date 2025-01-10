<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
<style>
</style>
<!-- A-Frame ライブラリ -->
<script src="https://aframe.io/releases/1.6.0/aframe.min.js"></script>

<!-- AR.js ライブラリ -->
<script
	src="https://raw.githack.com/AR-js-org/AR.js/master/aframe/build/aframe-ar.js"></script>

<!-- Quagga.js ライブラリ -->
<script src="https://unpkg.com/@ericblade/quagga2@1.7.4/dist/quagga.min.js"></script>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div>
		<a>AR画面</a>
	</div>
	<!-- A-Frame の AR シーン -->
	<a-scene embedded arjs="patternRatio: 0.90">
		<a-marker id="marker" type="pattern" url="/meal-mate/img/marker-black.patt">
			<!-- <a-image id="image" position="-0.3 0 -1.7" rotation="-90 0 0"></a-image> -->
		</a-marker>
	</a-scene>

	<!-- Quagga.js のカメラストリーム表示用コンテナ -->
	<div id="scanner-container" style="display: none;"></div>

	<script>
        	const markerElm = document.querySelector("#marker");
        	let imageSrc;
        	let obj = {};
            let barcode;

        	function getFoods(barcode) {
        		$.ajax({
        			url: "/meal-mate/ajax-test",
        			type: "GET",
        			data: {
        				"barcode": barcode
        			},
        			dataType: "json"
        		}).done(function (response) {
        			let foodText = "";
        			for (let item of response.classes) {
        				foodText += item + "\n";
        			}
        			// 恐らくitemは画像path
        			// daoから返される名称と画像の名前をそろえる
        			markerElm.innerHTML = "";
        			let imageElm = document.createElement("a-image");
        			imageElm.setAttribute("position", "-0.3 0 -1.7");
        			imageElm.setAttribute("rotation", rotation="-90 0 0");
        			imageSrc = "/meal-mate/img/class.png";
        			imageElm.setAttribute("src", imageSrc);
        			markerElm.appendChild(imageElm);
        		})
        	}

        	function checkBarcode(num) {
                for (let key in obj) {
                  if (key == num) {
                    obj[num] += 1;
                    if (obj[num] >= 7) {
                      getFoods(num);
                      obj = {};
                    }
                    return;
                  }
                }
                obj[num] = 1;
            }

            // Quagga.js を使用してJANコードを読み取る
                Quagga.init(
                    {
                        inputStream: {
                            name: "Live",
                            type: "LiveStream",
                            target: document.querySelector(
                                "#scanner-container"
                            ),
                            constraints: {
                                facingMode: "environment",
                            },
                        },
                        decoder: {
                            readers: ["ean_reader"], // JANコードの読み取り
                        },
                    },

                    function (err) {
                        if (err) {
                            console.error("Quaggaの初期化に失敗しました:", err);
                            return;
                        }
						Quagga.start();
                    }
                );

                // 読み取ったJANコードをa-textのvalueとして設定
                const barcodeText = document.querySelector("#barcode-text");

                // バーコードが検出されたときの処理
                Quagga.onDetected((result) => {
                    barcode = result.codeResult.code;
   	                console.log("読み取ったJANコード:", barcode);
					checkBarcode(barcode);
                });



        </script>
</body>
</html>
