let obj = {};
let time = 0;
const timeAllowed = 20;
let res;
let imageArray = [];
let prevBarcode = "";
const frameRate = 10;

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
	if (prevBarcode != barcode) {
		prevBarcode = barcode;
		imageArray = [];
		$.ajax({
			url: "/meal-mate/ajax-test",
			type: "GET",
			data: {
				"barcode": barcode
			},
			dataType: "json"
		}).done(function (res) {
			loadFoods(res["paths"]);
			// 恐らくitemは画像path
			// daoから返される名称と画像の名前をそろえる
		}).fail(function (res) {
			console.log(res);
		})
	}
}

function loadFoods(foodsProp) {
	for (let item of foodsProp) {
		const img = new Image();
		img.src = `/meal-mate/img/${ item }`;
		imageArray.push(img);
	}

}

async function checkBarcode(num) {
    for (let key in obj) {
        if (key == num) {
            obj[num] += 1;
            if (obj[num] >= 10) {
                obj = {};
                getFoods(num);
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
    const yDistance = -100;
    const y1 = box[1][1];
    const y = y1 + yDistance;

    return [x, y];
}

function drawText() {
    const ctx = Quagga.canvas.ctx.overlay;
    const canvas = Quagga.canvas.dom.overlay;
    ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));
    if (res == null) {
        return;
    }
    if (typeof res != "object") {
    	return
    }
    if (res.boxes == undefined) {
    	return
    }

    const rect = calcRect(res.box);

    // 食べられない食材が入っている場合、表示上限は暫定9件(後で調整)
    // 多分下のコメントアウトのコードで上手くいく
    const width = 100;
    const height = 100;
    const numTimes = 9;
    const kaisuu = numTimes > imageArray.length? imageArray.length: numTimes;
    let count = 0;
    for (let i=0; i<3; i++) {
    	for (let j=0; j<3; j++) {
    		if (count < kaisuu) {
    			ctx.drawImage(imageArray[count], rect[0] + width*i, rect[1] + height*j, width, height);
        		count++;
    		}
    	}
    }
}

Quagga.onDetected(async (result) => {
    res = result;
	time = timeAllowed;
    const barcode = result.codeResult.code;
    console.log(barcode);
    await checkBarcode(barcode);
});

let i = 0;

Quagga.onProcessed((result) => {
    const ctx = Quagga.canvas.ctx.overlay;
    const canvas = Quagga.canvas.dom.overlay;

    time--;

    if (time <= 0) {
        ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height));
        imageArray = [];
        prevBarcode = "";
        time = 0;
    }

	if (i === 0 && imageArray.length > 0) {
        drawText();

    }
    i = (i+1)%frameRate;
});
