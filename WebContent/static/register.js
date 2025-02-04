// カテゴリーとアイコンのデータを定義
// 各カテゴリー（fruits, vegetablesなど）に対応するアイコンデータを保持するオブジェクト
// name: アイコンの名前, src: アイコン画像のパス
//const iconData = {
//    fruits: [
//        { name: "apple", src: "icons/apple.png", id:""}, // りんごアイコン
//        { name: "banana", src: "icons/banana.png",id:""}, // バナナアイコン
//        { name: "orange", src: "icons/orange.png",id:"" }, // オレンジアイコン
//        { name: "peach", src: "icons/peach.png", id:""}, // ももアイコン
//        { name: "kiwi", src: "icons/kiwi.png",id:""}, // きういアイコン
//    ],
//        vegetables: [
//        { name: "matsutake", src: "icons/matsutake.png",id:"" }, // 松茸アイコン
//        { name: "sesame", src: "icons/goma.png",id:"" }, // ごまアイコン
//        { name: "yam", src: "icons/yamaimo.png",id:"" } // 山芋アイコン
//    ],
//    meats: [
//        { name: "chicken", src: "icons/chicken.png",id:"" }, // 鶏肉アイコン
//        { name: "beef", src: "icons/beef.png",id:"" }, // 牛肉アイコン
//        { name: "pork", src: "icons/pork.png",id:"" } // 豚肉アイコン
//    ],
//    seafood: [
//        { name: "mackerel", src: "icons/saba.png",id:"" }, // 鯖アイコン
//        { name: "crab", src: "icons/kani.png",id:"" }, // カニアイコン
//        { name: "shrimp", src: "icons/ebi.png",id:"" }, // エビアイコン
//        { name: "abalone", src: "icons/awabi.png",id:"" },// アワビアイコン
//        { name: "salmon roe", src: "icons/ikura.png",id:"" }, // いくらアイコン
//        { name: "salmon", src: "icons/sake.png",id:"" }, // 鮭アイコン
//        { name: "squid", src: "icons/ika.png",id:"" } // イカアイコン
//    ],
//
//    dairy: [
//        { name: "milk", src: "icons/milk.png",id:"" }, // 牛乳アイコン
//        { name: "egg", src: "icons/egg.png",id:"" }, // 卵アイコン
//        { name: "flour", src: "icons/komugiko.png",id:"" } // 小麦粉アイコン
//    ],
//    sonota: [
//        { name: "walnut", src: "icons/kurumi.png",id:"" }, // くるみアイコン
//        { name: "soba", src: "icons/soba.png",id:"" }, // 蕎麦アイコン
//        { name: "peanuts", src: "icons/pinattsu.png",id:"" }, // ピーナッツアイコン
//        { name: "cashew nuts", src: "icons/kashunattsu.png",id:"" }, // カシューナッツアイコン
//        { name: "soy", src: "icons/daizu.png",id:"" }, // 大豆アイコン
//        { name: "gelatine", src: "icons/gelatine.png",id:"" } // ゼラチンアイコン
//    ],
//};

let iconData;

$.ajax({
	url: "/meal-mate/user/get_ings",
	type: "GET",
	dataType: "json"
}).done(res => {
	iconData = res;
}).fail(err => {
	console.log(err);
})

// DOM要素の取得
// 各HTML要素を取得し、後で操作するための変数に格納
const categories = document.getElementById("categories"); // カテゴリー一覧のコンテナ
const icons = document.getElementById("icons"); // アイコン一覧のコンテナ
const selectedIcons = document.getElementById("selected-icons"); // 選択されたアイコンの表示エリア
const submitButton = document.getElementById("submit-button"); // 送信ボタン

// 選択されたアイコンを管理する配列
// 現在選択中のアイコンを追跡するための配列
let selectedIconList = [];

// カテゴリークリック時の処理
// カテゴリーがクリックされたときに、そのカテゴリーに対応するアイコンを表示する
categories.addEventListener("click", (event) => {
    if (event.target.classList.contains("category")) { // クリックされた要素がカテゴリーであることを確認
        const category = event.target.dataset.category; // カテゴリー名を取得
        displayIcons(category); // 対応するアイコンを表示する関数を呼び出し
    }
});

// アイコン表示関数
// 指定されたカテゴリーに属するアイコンを画面に表示する
function displayIcons(category) {
    icons.innerHTML = ""; // アイコンリストをリセット
    iconData[category].forEach(icon => {
        const iconContainer = document.createElement("div"); // コンテナ要素
        iconContainer.classList.add("icon-container");

        const iconElement = document.createElement("img");
        iconElement.src = icon.src;
        iconElement.alt = icon.name;
        iconElement.id = icon.id;
        iconElement.classList.add("icon");
        iconElement.dataset.name = icon.name;

        const iconLabel = document.createElement("span"); // 名前表示用
        iconLabel.textContent = icon.name;
        iconLabel.classList.add("icon-label");

        iconContainer.appendChild(iconElement);
        iconContainer.appendChild(iconLabel);
        icons.appendChild(iconContainer);
    });
}

// アイコンクリック時の処理
// ユーザーがアイコンをクリックしたときに、そのアイコンを選択済みリストに追加する
icons.addEventListener("click", (event) => {
    if (event.target.classList.contains("icon")) { // クリックされた要素がアイコンであることを確認
        const iconName = event.target.dataset.name; // アイコン名を取得
        const iconSrc = event.target.src; // アイコン画像のパスを取得
        const iconId = event.target.id;
        addSelectedIcon(iconName, iconSrc, iconId); // 選択済みアイコンリストに追加する関数を呼び出し
    }
});

// 選択されたアイコンを追加
// 新しいアイコンを選択済みリストに追加する
function addSelectedIcon(name, src, id) {
    if (selectedIconList.some(icon => icon.name === name)) return; // すでに選択されている場合は何もしない

    const iconWrapper = document.createElement("div"); // アイコンと削除ボタンを含むコンテナを作成
    iconWrapper.classList.add("selected-icon-wrapper"); // コンテナ用のクラスを追加
    iconWrapper.id = id;

    const iconElement = document.createElement("img"); // アイコン画像を作成
    iconElement.src = src; // 画像のパスを設定
    iconElement.alt = name; // 代替テキストを設定
    iconElement.classList.add("selected-icon"); // 選択済みアイコン用のクラスを追加

    const removeButton = document.createElement("button"); // 削除ボタンを作成
    removeButton.textContent = "×"; // ボタンに「×」を表示
    removeButton.classList.add("remove-icon"); // 削除ボタン用のクラスを追加
    removeButton.addEventListener("click", () => { // ボタンがクリックされたときの処理
        selectedIcons.removeChild(iconWrapper); // コンテナを削除
        selectedIconList = selectedIconList.filter(icon => icon.name !== name); // 配列から該当アイコンを削除
    });

    iconWrapper.appendChild(iconElement); // コンテナにアイコンを追加
    iconWrapper.appendChild(removeButton); // コンテナに削除ボタンを追加
    selectedIcons.appendChild(iconWrapper); // 選択済みアイコンの表示エリアにコンテナを追加

    selectedIconList.push({ name, src }); // 配列にアイコンデータを追加
}

// 送信ボタンクリック時の処理
// 現在選択されているアイコンをデータベースに送信する
submitButton.addEventListener("click", () => {
    if (selectedIconList.length === 0) { // 選択されているアイコンがない場合はアラートを表示
        alert("アイコンを選択してください。");
        return;
    }

    // データベースに送信（ここでは例としてコンソールに出力）
//    console.log("送信するデータ:", selectedIconList); // 選択済みアイコンをコンソールに表示
    const sa = Array.from(document.querySelectorAll(".selected-icon-wrapper"));
    const idList = sa.map(e => e.id);

    // ajax通信
    $.ajax({
    	url: "/meal-mate/user/update_data",
    	type: "POST",
    	data: {
    		ids: idList
    	}
    }).done(() => {
    	console.log("success");
    }).fail(err => {
    	console.log("fail");
    	console.log(err);
    })

    alert("アイコンが送信されました！"); // ユーザーに送信完了を通知

    // リセット処理
    selectedIcons.innerHTML = ""; // 選択済みアイコンの表示エリアをクリア
    selectedIconList = []; // 配列を初期化
});
