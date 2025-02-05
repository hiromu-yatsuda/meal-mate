<!-- translation.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // セッションから数値の言語IDを取得
    String langId = (String) session.getAttribute("language_id");
    // デフォルトは英語
    String userLangCode = "en-US";
    if (langId != null) {
        switch (langId) {
            case "1":
                userLangCode = "ja-JP";
                break;
            case "2":
                userLangCode = "en-US";
                break;
            case "3":
                userLangCode = "es-ES";
                break;
            case "4":
                userLangCode = "fr-FR";
                break;
            case "5":
                userLangCode = "de-DE";
                break;
            case "6":
                userLangCode = "zh-CN";
                break;
            case "7":
                userLangCode = "ko-KR";
                break;
            case "8":
                userLangCode = "it-IT";
                break;
            case "9":
                userLangCode = "ru-RU";
                break;
            case "10":
                userLangCode = "pt-PT";
                break;
            case "11":
                userLangCode = "ar-SA";
                break;
            case "12":
                userLangCode = "vi-VN";
                break;
            default:
                userLangCode = "en-US";
                break;
        }
    }
%>
<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
	<head>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
		<title>翻訳 (ブラウザで音声→テキスト変換)</title>
		<style>
		/* 全体の基本設定 */
		body {
		    font-family: Arial, sans-serif;
		    background-color: #f5f5f5;
		    color: #333;
		    margin: 0;
		    padding: 0;
		}
		/* メインコンテナのスタイル */
		.container {
		    height: 100vh; /* ビューポートの高さを100%に設定 */
		    display: flex;
		    flex-direction: column;
		    justify-content: space-between;
		    background-color: white;
		    padding: 15px; /* 全体のパディングを微調整 */
		    box-sizing: border-box;
		}
		/* 翻訳セクション */
		.translation-section {
		    display: flex;
		    flex-direction: column;
		    /*justify-content: center;*/
		    align-items: center;
		    gap: 15px; /* セクション間のスペースを調整 */
		    /*height: 100%; /* セクションをビューポート全体に */
		    box-sizing: border-box;
		    margin-top:15%;
		}
		/* 上段と下段の共通スタイル */
		.user-block1 {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    gap: 15px; /* 要素間のスペースを広げる */
		    transform: rotate(180deg); /* 上下左右を反転 */
		}
		/* 上段と下段の共通スタイル */
		.user-block2 {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    gap: 15px; /* 要素間のスペースを広げる */
		}
		/* 言語選択と録音ボタン */
		.language-selection {
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    width: 100%;
		    gap: 10px; /* ボタンとセレクトボックス間を調整 */
		}
		.language-selection select {
		    font-size: 1rem; /* フォントサイズを調整 */
		    padding: 10px; /* パディングを拡大 */
		    border: 1px solid #ccc;
		    border-radius: 5px;
		}
		/* 上段の録音ボタン */
		.language-selection .record-btn1 {
		    background: #ffcc00;
		    border: none;
		    font-size: 1rem;
		    border-radius: 50%;
		    /* 幅と高さを固定 */
		    width: 45px;
		    height: 45px;
		    /* テキストをボタン中央に揃える */
		    display: inline-flex;       /* flexを使うことで縦横中央揃えが簡単 */
		    align-items: center;
		    justify-content: center;
		    cursor: pointer;
		}
		.record-btn1.recording {
			background: #ff9900;
		    transform: scale(1.05);
		}
		/* 下段の録音ボタン */
		.language-selection .record-btn2 {
		    background: #ffcc00;
		    border: none;
		    font-size: 1rem;
		    border-radius: 50%;
		    /* 幅と高さを固定 */
		    width: 45px;
		    height: 45px;
		    /* テキストをボタン中央に揃える */
		    display: inline-flex;       /* flexを使うことで縦横中央揃えが簡単 */
		    align-items: center;
		    justify-content: center;
		    cursor: pointer;
		}
		.record-btn2.recording {
			background: #ff9900;
		}
		/* テキストエリア */
		.output-text1, .output-text2 {
		    font-size: 1.3rem;
		    width: 100%;
		    height: 120px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    padding: 8px;
		    background: #f9f9f9;
		    box-sizing: border-box;
		    resize: none; /* 必要ならリサイズ禁止 */
		    /* display: block; にしておくと文字入力しやすい */
		    display: block;
		}
		/* ボタンのスタイル */
		.translate-btn {
		    background: #007BFF;
		    color: white;
		    border: none;
		    border-radius: 5px;
		    font-size: 1.2rem; /* フォントサイズを調整 */
		    padding: 12px 20px; /* パディングを調整 */
		    cursor: pointer;
		    width: 130px; /* 幅を拡大 */
		    height: 50px; /* 高さを調整 */
		    text-align: center;
		}
		.translate-btn:active {
		    background: #0056b3;
		    transform: scale(1.05);
		}
		/* ボタン間のスペース */
		.button-group {
		    display: flex;
		    justify-content: center;
		    gap: 15px; /* ボタン間の間隔を適度に調整 */
		    width: 100%;
		}
		/* 区切り線 */
		hr {
		    border: 0.5px solid #ddd;
		    width: 90%;
		    margin: 10px auto; /* 上下マージンを調整 */
		}
		/* モバイル対応 */
		@media (max-width: 480px) {
		    .output-text1, .output-text2 {
		        font-size: 1.1rem; /* フォントサイズを調整 */
		        height: 115px; /* 高さを少し縮小 */
		    }
		    .translate-btn {
		        font-size: 1rem; /* ボタンのフォントサイズを調整 */
		        padding: 10px 15px; /* パディングを縮小 */
		    }
		    .language-selection select {
		        font-size: 0.9rem; /* ドロップダウンのフォントサイズを縮小 */
		        padding: 8px; /* 内側のスペースを縮小 */
		    }
		}


		/* テキストエリアと読み上げボタンを重ねるためのラッパ */
		.textarea-container {
		    position: relative; /* 子要素を絶対配置しやすくする */
		    margin: 0 10px; /* 左右に10pxの隙間だけ確保 */
		    width: 100%;
		}
		/* 読み上げボタンをテキストエリアの右下に重ねる */
		.read-aloud-btn {
		    position: absolute;
		    bottom: 1px;       /* テキストエリア下端との隙間 */
		    left: 1px;        /* テキストエリア右端との隙間 */
		    background: none;
		    border: none;
		    font-size: 1.1rem;
		    cursor: pointer;
		    /* ボタンが小さいので、スマホ操作のためにヒット領域を広げたいなら padding 追加 */
		    width: 35px;
		    height: 35px;
		    text-align: center;
		    line-height: 15px;
		}
		.read-aloud-btn:hover {
		    background: rgba(0,0,0,0.1);
		    border-radius: 50%;
		}
		.read-aloud-btn:active {
		    background: rgba(0,0,0,0.2);
		}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="translation-section">
				<!-- 上段 -->
				<div class="user-block1">
	                <div class="button-group">
						<button class="translate-btn" id="translateBtn1">⇧</button>
	                </div>
					<div class="textarea-container">
						<textarea class="output-text1" id="outputText1"></textarea>
						<button class="read-aloud-btn" id="readaloudBtn1" style="display:none;">🔊</button>
					</div>
					<div class="language-selection">
						<select id="userLang1" name="userLang1">
							<option value="ja-JP" selected>日本語</option>
							<option value="en-US">English</option>
							<option value="es-ES">Español</option>
							<option value="fr-FR">Français</option>
							<option value="de-DE">Deutsch</option>
							<option value="zh-CN">中文</option>
							<option value="ko-KR">한국어</option>
							<option value="it-IT">Italiano</option>
							<option value="ru-RU">Русский</option>
							<option value="pt-PT">Português</option>
							<option value="ar-SA">المملكة العربية السعودية</option>
							<option value="vi-VN">Tiếng Việt</option>
						</select>
						<button class="record-btn1" id="recordBtn1" disabled>準備中...</button>
					</div>
				</div>
	    		<hr>
	            <!-- 下段 -->
				<div class="user-block2">
	                <div class="button-group">
						<button class="translate-btn" id="translateBtn2">⇧</button>
	                </div>
					<div class="textarea-container">
						<textarea class="output-text2" id="outputText2"></textarea>
						<button class="read-aloud-btn" id="readaloudBtn2" style="display:none;">🔊</button>
					</div>
					<div class="language-selection">
						<select id="userLang2" name="userLang2">
							<option value="ja-JP" <%= "ja-JP".equals(userLangCode) ? "selected" : "" %>>日本語</option>
							<option value="en-US" <%= "en-US".equals(userLangCode) ? "selected" : "" %>>English</option>
							<option value="es-ES" <%= "es-ES".equals(userLangCode) ? "selected" : "" %>>Español</option>
							<option value="fr-FR" <%= "fr-FR".equals(userLangCode) ? "selected" : "" %>>Français</option>
							<option value="de-DE" <%= "de-DE".equals(userLangCode) ? "selected" : "" %>>Deutsch</option>
							<option value="zh-CN" <%= "zh-CN".equals(userLangCode) ? "selected" : "" %>>中文</option>
							<option value="ko-KR" <%= "ko-KR".equals(userLangCode) ? "selected" : "" %>>한국어</option>
							<option value="it-IT" <%= "it-IT".equals(userLangCode) ? "selected" : "" %>>Italiano</option>
							<option value="ru-RU" <%= "ru-RU".equals(userLangCode) ? "selected" : "" %>>Русский</option>
							<option value="pt-PT" <%= "pt-PT".equals(userLangCode) ? "selected" : "" %>>Português</option>
							<option value="ar-SA" <%= "ar-SA".equals(userLangCode) ? "selected" : "" %>>المملكة العربية السعودية</option>
							<option value="vi-VN" <%= "vi-VN".equals(userLangCode) ? "selected" : "" %>>Tiếng Việt</option>
						</select>
						<button class="record-btn2" id="recordBtn2" disabled>準備中...</button>
					</div>
				</div>
			</div>
	        <div id="uploadMessage" style="text-align:center; color:blue; font-weight:bold;"></div>
		</div>
		<script>
	    	var contextPath = '<%= request.getContextPath() %>';
		</script>
		<script src="/meal-mate/static/translation.js"></script>
	</body>
</html>