const container = document.querySelector("#container");
const jsonElm = document.querySelector("#hidden");
const decideButton = document.querySelector("#decide");
const cancelButton = document.querySelector("#cancel");
let jsonStr = jsonElm.dataset.json;

function createTable(json) {
	const foods = json["foodName"];
	// 食材を5列ずつ配置
	const cols = 3;
	// 食材を5列ずつ配置したときの行数
	const rows = Math.ceil(foods.length / cols);

	const table = document.createElement("table");
	table.border = "1px";

	// isJan行
	const isJanTr = document.createElement("tr");
	const isJanTh = document.createElement("th");
	const isJanTd = document.createElement("td");

	isJanTh.textContent = "isJan";
	// 個々の表記はtrue or falseで大丈夫?
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
	let remainNum = foods.length;

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
				td.classList.add("foodtd");

				td.style.width = "150px";

				td.textContent = foods[count];
				console.log("foods[count]: " + foods[count])
				tr.appendChild(td);

				count++;
			} else {
				break;
			}
		}

		remainNum -= cols;

		table.appendChild(tr);
	}

	container.appendChild(table);
}

//json = [
//	{"isJan":false,"name":"testname","jan":"1111111111111","checkedItemsId":["1","2","6","7", "8", "9", "10", "11", "12", "13", "14", "15", "16"], "foodName": ["カシューナッツ", "キウイフルーツ", "オレンジ", "牛肉", "バナナ", "まつたけ", "やまいも", "りんご"]},
//	{"isJan":false,"name":"test2","jan":"1111111111110","checkedItemsId":["1", "2", "3"], "foodName": ["カシューナッツ"]},
//	{"isJan":false,"name":"test3","jan":"1111111111101","checkedItemsId":["1","7","13"], "foodName": ["かに", "えび"]}
//];

try {
	let json = JSON.parse(jsonStr);
	json.forEach(e => {
		createTable(e);
	});
	console.log(json);

	// 登録されたらjsからリダイレクト
	// リダイレクト先でデータを取得し、セッションから削除
	decideButton.addEventListener("click", () => {
		fetch("/meal-mate/stuff/foods/regist/or/manual", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			}
		}).then(res => res.text())
		.then(data => {
			window.location.href = "/meal-mate/stuff/foods_regist_conp.jsp";
		})
	});

} catch (err) {
	console.log(err);
	const somethingError = document.createElement("div");
	somethingError.textContent = "なんかえらーがおきてるお!";
	somethingError.style.color = "red";
	container.appendChild(somethingError);
}

cancelButton.addEventListener("click", () => {
	fetch("/meal-mate/stuff/foods/regist/or/manual", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
		}
	}).then(res => res.text())
	.then(data => {
		window.location.href = "/meal-mate/stuff/foods_regist2.jsp";
	})
});