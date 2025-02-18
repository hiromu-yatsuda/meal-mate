// 変数定義
const container = document.querySelector("#container");
const plusButton = document.querySelector("#plusButton");
const decisionButton = document.querySelector("#decisionButton");
const cancelButton = document.querySelector("#cancelButton");
const numList = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
let num;
let foodsId;
let foodName;
let combFoods;
let productId = 1;

// 関数定義

// 食材のデータを取得
function getFoodsData() {
	$.ajax({
		url: "/meal-mate/getAllFoods",
		type: "GET",
		dataType: "json"
	}).done(function (res) {
		foodsId = res["id"];
		foodName = res["name"];

		// getCombFoodsData()が完了してからaddInputField()をする
		getCombFoodsData();
	}).fail(function (err) {
		console.log(err);
		console.log("err");
	})
}

// 化合物のデータを取得
function getCombFoodsData() {
	$.ajax({
		url: "/meal-mate/getAllCombFoods",
		type: "GET",
		dataType: "json"
	}).done(function (res) {
		combFoods = res;

		// 最初の1件分
		addInputField();
	}).fail(function (err) {
		console.log("failed");
		console.log(err);
	})
}

function setCheckboxOn(elm, id) {
	const list = elm.classList;

	for (let item of list) {
		if (!(c = document.querySelector(`#${item + id}`)).checked) {
			c.checked = true;
		}
	}
}

// 商品を1件増やす(名前は適切?)
function addInputField() {
    let count = 0;
    let productElm = document.createElement("div");
    let br0 = document.createElement("br");
    let isJan = document.createElement("input");
    let br1 = document.createElement("br");
    let nameNullWarn = document.createElement("div");
    let nameLabel = document.createElement("label");
    let name = document.createElement("input");
    let br2 = document.createElement("br");
    let janNullWarn = document.createElement("div");
    let janLengthWarn = document.createElement("div");
    let janLabel = document.createElement("label");
    let jan = document.createElement("input");
    let br3 = document.createElement("br");

    // プロパティの設定
    // <div id=`product${productId}`></div>
    productElm.id = `product${productId}`;

    // <input type="checkbox" id="isJan">
    isJan.type = "checkbox";
    isJan.id = "isJan";

    // 名前が未入力の場合のエラーコメント
    nameNullWarn.textContent = "※この項目は必須です";
    nameNullWarn.id = "NameNull";
    nameNullWarn.style.color = "red";
    nameNullWarn.hidden = true;

    // 商品名:
    nameLabel.textContent = "商品名:";

    // <input type="text" placeholder="商品名" id="name">
    name.type = "text";
    name.placeholder = "商品名";
    name.id = "name";
    name.required = true;

    // Janコードが未入力の場合のエラーコメント
    janNullWarn.textContent = "※この項目は必須です";
    janNullWarn.id = "JanNull";
    janNullWarn.style.color = "red";
    janNullWarn.hidden = true;

    // Janコードが13桁でない場合のエラーコメント
    janLengthWarn.textContent = "※13桁の数字を入力してください";
    janLengthWarn.id = "notThirteen";
    janLengthWarn.style.color = "red";
    janLengthWarn.hidden = true;

    // JANコード:
    janLabel.textContent = "JANコード:";

    // <input type="number" placeholder="JANコード" class="janCode" id="jan" minlength="13" maxlength="13">
    jan.type = "number";
    jan.placeholder = "JANコード";
    jan.id = `jan${productId}`;
    jan.classList.add("janCode");
    jan.setAttribute("readonly", true);
    jan.required = true;

    productElm.appendChild(br0);
    productElm.appendChild(isJan);
    productElm.appendChild(br1);
    productElm.appendChild(nameNullWarn);
    productElm.appendChild(nameLabel);
    productElm.appendChild(name);
    productElm.appendChild(br2);
    productElm.appendChild(janNullWarn);
    productElm.appendChild(janLengthWarn);
    productElm.appendChild(janLabel);
    productElm.appendChild(jan);
    productElm.appendChild(br3);

    let table = document.createElement("table");
    table.id = "foods";

    while (count < foodName.length) {
        let tr = document.createElement("tr");
        for (let i = 0; i < 5; i++) {
            if (count < foodName.length) {
            	// <td></td>の作成
            	let td = document.createElement("td");
            	// <input>の作成
            	let checkbox = document.createElement("input");
            	// <label></label>の作成
            	let label = document.createElement("label");

            	// プロパティの設定
            	// <input type="checkbox" id=`${foodName[count] + productId}` class=`${foodName[count]}` data-food-id=`${foodsId[count]}`>
            	checkbox.type = "checkbox";
            	checkbox.id = foodName[count] + productId;
            	checkbox.classList.add(foodName[count]);
            	checkbox.setAttribute("data-food-id", foodsId[count]);

            	// <label for=`${foodName[count] + productId}`>foodName[count]</label>
            	label.setAttribute("for", checkbox.id);
            	label.textContent = foodName[count];

            	td.appendChild(checkbox);
            	td.appendChild(label);
            	tr.appendChild(td);
                count++;
            }
        }
        table.appendChild(tr);
    }
    productElm.appendChild(table);

    // ここに化合物を追加
    count = 0;
    const combTable = document.createElement("table");
    while (count < combFoods.length) {
    	const combTr = document.createElement("tr");
    	for (let i = 0; i < 5; i++) {
    		if (count < combFoods.length) {
    			// <td></td>
    			const combTd = document.createElement("td");

    			// <input type="checkbox" id=`comb${combFoods[i]["id"]}`>
    			const combCheckbox = document.createElement("input");
    			combCheckbox.type = "checkbox";
    			combCheckbox.id = `comb${count}`;

    			// <label class=`${combFoods[i]["foods"][j]} ... comb${combFoods[i]["id"]}` onclick="関数(this)"></label>
    			const combLabel = document.createElement("label");
    			combLabel.textContent = combFoods[count]["cfName"];
    			const id = productId;
    			combLabel.setAttribute("for", `comb${count}`);
    			combLabel.className = combFoods[count]["foods"].join(" ");
    			combLabel.onclick = function() {setCheckboxOn(this, id)};

    			combTd.appendChild(combCheckbox);
    			combTd.appendChild(combLabel);
    			combTr.appendChild(combTd);

    			count++;
    		} else {
    			break;
    		}
    	}
    	combTable.appendChild(combTr);
    }

    const br4 = document.createElement("br");
    productElm.appendChild(br4);
    productElm.appendChild(combTable);
    container.appendChild(productElm);
    productId++;

    const elm = (n = document.querySelectorAll(".janCode"))[n.length - 1];

	elm.addEventListener("keyup", (e) => {
		if (numList.includes(e.key) && elm.value <= 999999999999) {
			elm.value += e.key;
		}
	});
	elm.addEventListener("keydown", (e) => {
		if (e.key == "Backspace") {
			elm.value = elm.value.slice(0, -1);
		}
	});
}

// 入力された情報をjson形式で取得
function collectProductData() {
	const products = [];
	const productElms = document.querySelectorAll('[id^="product"]');
	let isOk = true;

	productElms.forEach((e) => {
		const productData = {};
		const nameNullWarn = e.querySelector("#NameNull");
		const janNullWarn = e.querySelector("#JanNull");
		const janLengthWarn = e.querySelector("#notThirteen");
		const foodsBox = e.querySelectorAll('#foods input[type="checkbox"]');

		const isJan = e.querySelector("#isJan").checked;
		const name = e.querySelector("#name").value;
		const jan = e.querySelector('[id^="jan"]').value;
		const foods = Array.from(foodsBox)
						.filter(i => i.checked)
						.map(j => j.className);

		if (name === "") {
			nameNullWarn.hidden = false;
			isOk = false;
		} else {
			nameNullWarn.hidden = true;
		}

		if (jan === "" || jan === undefined) {
			janNullWarn.hidden = false;
			isOk = false;
		} else {
			janNullWarn.hidden = true;
			if (jan.length != 13) {
				janLengthWarn.hidden = false;
				isOk = false;
			} else {
				janLengthWarn.hidden = true;
			}
		}

		productData.isJan = isJan;
		productData.name = name;
		productData.jan = jan;
		productData.foodName = foods;

		const checkedItemsId = [];
		const itemCheckboxes = e.querySelectorAll('input[type="checkbox"]:not(#isJan):not([id^="comb"])');

		itemCheckboxes.forEach((item) => {
			if (item.checked) {
				checkedItemsId.push(item.dataset.foodId);
			}
		});

		productData.checkedItemsId = checkedItemsId;

		products.push(productData);
	});

	if (!isOk) {
		return false;
	}

	return products;
}

function sendData() {
	const data = collectProductData();
	if (!data) {
		return;
	}

	fetch("/meal-mate/stuff/regist_confirm", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data)
	}).then(res => res.text())
	.then(data => {
		window.location.href = "/meal-mate/stuff/foods_regist_con.jsp";
	})
}

// メイン処理
window.onload = () => {
    getFoodsData();
};

plusButton.addEventListener("click", () => {
    addInputField();
});

decisionButton.addEventListener("click", () => {
	sendData();
});