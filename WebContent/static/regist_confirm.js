const container = document.querySelector("#container");
const jsonElm = document.querySelector("#hidden");
const decideButton = document.querySelector("#decide");
const cancelButton = document.querySelector("#cancel");
//let jsonStr = jsonElm.dataset.json;

function createTable(json) {
	const foods = json["checkedItemsId"];
	// 食材を5列ずつ配置
	const cols = 3;
	// 食材を5列ずつ配置したときの行数
	const rows = Math.ceil(foods.length / cols);

	const table = document.createElement("table");
	table.border = 1;

	// isJan行
	const isJanTr = document.createElement("tr");
	const isJanTh = document.createElement("th");
	const isJanTd = document.createElement("td");

	isJanTh.textContent = "isJan";
	isJanTd.textContent = json["isJan"];
	isJanTd.setAttribute("colspan", cols);

	isJanTr.appendChild(isJanTh);
	isJanTr.appendChild(isJanTd);
	table.append(isJanTr);

	// name行
	const nameTr = document.createElement("tr");
	const nameTh = document.createElement("th");
	const nameTd = document.createElement("td");

	nameTh.textContent = "商品名";
	nameTd.textContent = json["name"];
	nameTd.setAttribute("colspan", cols);

	nameTr.appendChild(nameTh);
	nameTr.appendChild(nameTd);
	table.appendChild(nameTr);

	// jan行
	const janTr = document.createElement("tr");
	const janTh = document.createElement("th");
	const janTd = document.createElement("td");

	janTh.textContent = "JANコード";
	janTd.textContent = json["jan"];
	janTd.setAttribute("colspan", cols);

	janTr.appendChild(janTh);
	janTr.appendChild(janTd);
	table.appendChild(janTr);

	// 食材行
	let count = 0;

	while (count < foods.length) {
		let tr = document.createElement("tr");
		if (count === 0) {
			let foodTh = document.createElement("th");
			foodTh.textContent = "食材";
			foodTh.setAttribute("rowspan", rows);

			tr.appendChild(foodTh);
		}

		for (let j = 0; j < cols; j++) {
			if (count < foods.length) {
				const td = document.createElement("td");

				if (count == (foods.length - 1)) {
//					console.log("最後: " + foods[count]);
//					console.log("最後: " + );
					const colspan = (cols - (foods.length % cols)) % cols + 1;
					td.setAttribute("colspan", colspan);
				}

				td.textContent = foods[count];
				tr.appendChild(td);

				count++;
			} else {
				break;
			}
		}

		table.appendChild(tr);
	}

	container.appendChild(table);
}

json = [
	{"isJan":false,"name":"testname","jan":"1111111111111","checkedItemsId":["1","2","6","7", "8", "9", "10", "11", "12", "13", "14", "15", "16"]},
	{"isJan":false,"name":"test2","jan":"1111111111110","checkedItemsId":["えび","かに","ゼラチン", "グリコーゲン", "グランドピアノ", "パイナップル"]},
	{"isJan":false,"name":"test3","jan":"1111111111101","checkedItemsId":["1","7","13"]}
];

try {
//	let json = JSON.parse(jsonStr);
	json.forEach(e => {
		createTable(e);
	});
	console.log(json);

	// 登録されたらjsからリダイレクト
	// リダイレクト先でデータを取得し、セッションから削除
	// キャンセルボタンが押された場合はその場でセッションから削除したい(ここがムズイ)
	decideButton.addEventListener("click", () => {
		console.log("decide");
	});
	cancelButton.addEventListener("click", () => {
		console.log("cancel");
	});
} catch (err) {
	console.log(err);
	const somethingError = document.createElement("div");
	somethingError.textContent = "なんかえらーがおきてるお!";
	somethingError.style.color = "red";
	container.appendChild(somethingError);
}