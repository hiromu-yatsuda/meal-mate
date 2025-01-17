let obj = {};

// Quagga
Quagga.init(
    {
        inputStream: {
            name: "Live",
            type: "LiveStream",
            target: document.getElementById("my_quagga"),
        },
        decoder: {
            readers: ["ean_reader"],
        },
        frequency: 20,
    },
    (err) => {
        if (err) {
            console.log(err);
            return;
        }
        console.log("すたーと");
        Quagga.start();
    }
);

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
		text = foodText;
	})
}

function checkBarcode(num) {
    for (let key in obj) {
        if (key == num) {
            obj[num] += 1;
            if (obj[num] >= 7) {
                getFoods(num);
                console.log(text);
                obj = {};
            }
            return;
        }
    }
    obj[num] = 1;
}

function calcRect(box) {
    const xDistance = -100;
    const x1 = box[1][0];
    const x2 = box[2][0];
    const x = (x1 + x2) / 2 + xDistance;
    const yDistance = 100;
    const y1 = box[1][1];
    const y = y1 + yDistance;

    return [x, y];
}

function drawText(result, foods) {
    const ctx = Quagga.canvas.ctx.overlay;
    const canvas = Quagga.canvas.dom.overlay;
    if (result == null) {
        arText = "";
        ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));
        console.log("none");
        return;
    }
    if (typeof result != "object") {
    }
    if (result.boxes == undefined) {
    }
    ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));

    const rect = calcRect(result.box);

    ctx.font = "100px serif";
    ctx.fillStyle = "rgba(40, 255, 40)";
    ctx.fillText(foods, rect[0], rect[1]);
}

let time = 0;
const timeAllowed = 20;
let text = "";

Quagga.onDetected((result) => {
    time = timeAllowed;
    const barcode = result.codeResult.code;
    console.log(barcode);
    checkBarcode(barcode);
    drawText(result, text);
});

Quagga.onProcessed((result) => {
    const ctx = Quagga.canvas.ctx.overlay;
    const canvas = Quagga.canvas.dom.overlay;

    time--;

    if (time <= 0) {
        ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));
        time = 0;
    }
});
