// 変数定義
const container = document.querySelector("#container");
const plusButton = document.querySelector("#plusButton");
const decisionButton = document.querySelector("#decisionButton");
const cancelButtno = document.querySelector("#cancelButton");
let foodId;
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
		foodId = res["id"];
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
    let nameLabel = document.createElement("label");
    let name = document.createElement("input");
    let br2 = document.createElement("br");
    let janLabel = document.createElement("label");
    let jan = document.createElement("input");
    let br3 = document.createElement("br");

    // プロパティの設定
    // <div id=`product${productId}`></div>
    productElm.id = `product${productId}`;

    // <input type="checkbox" id="isJan">
    isJan.type = "checkbox";
    isJan.id = "isJan";

    // 商品名:
    nameLabel.textContent = "商品名:";

    // <input type="text" placeholder="商品名" id="name">
    name.type = "text";
    name.placeholder = "商品名";
    name.id = "name";

    // JANコード:
    janLabel.textContent = "JANコード:";

    // <input type="number" placeholder="JANコード" id="jan">
    jan.type = "number";
    jan.placeholder = "JANコード";
    jan.id = "jan";

    productElm.appendChild(br0);
    productElm.appendChild(isJan);
    productElm.appendChild(br1);
    productElm.appendChild(nameLabel);
    productElm.appendChild(name);
    productElm.appendChild(br2);
    productElm.appendChild(janLabel);
    productElm.appendChild(jan);
    productElm.appendChild(br3);

    let table = document.createElement("table");

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
            	// <input type="checkbox" id=`${foodName[count] + productId}` class=`${foodName[count]}`>
            	checkbox.type = "checkbox";
            	checkbox.id = foodName[count] + productId;
            	checkbox.classList.add(foodName[count]);

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
}

// 入力された情報をjson形式で取得
function collectProductData() {
	const products = [];
	const productElms = document.querySelectorAll('[id^="product"]');

	productElms.forEach((e) => {
		const productData = {};

		const isJan = document.querySelector("#isJan").checked;
		const name = document.querySelector("#name").value;
		const jan = document.querySelector("#jan").value;

		productData.isJan = isJan;
		productData.name = name;
		productData.jan = jan;

		const checkedItems = [];
		const itemCheckboxes = e.querySelectorAll('input[type="checkbox"]:not(#isJan):not(.comb)');

		itemCheckboxes.forEach((items) => {
			if (items.checked) {
				checkedItems.push(items.className);
			}
		});

		productData.checkedItems = checkedItems;

		products.push(productData);
	});

	return products;
}

function sendData() {
	const data = collectProductData();
	console.log("called");
	console.log(JSON.stringify(data));

	fetch("/meal-mate/getAllFoods", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(data)
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