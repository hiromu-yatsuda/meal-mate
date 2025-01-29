<!-- translation.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	    overflow: hidden; /* スクロールを防止 */
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
	    justify-content: center;
	    align-items: center;
	    gap: 25px; /* セクション間のスペースを調整 */
	    height: 100%; /* セクションをビューポート全体に */
	    box-sizing: border-box;
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

	/* 録音ボタン */
	.language-selection .record-btn {
	    background: #ffcc00;
	    border: none;
	    font-size: 1.1rem;
	    border-radius: 50%;
	    padding: 12px; /* パディングを調整 */
	    cursor: pointer;
	}

	/* テキストエリア */
	.output-text1, .output-text2 {
	    font-size: 1.3rem; /* フォントサイズを適度に拡大 */
	    width: 90%; /* 幅を親要素に合わせる */
	    max-width: 500px; /* 最大幅を調整 */
	    height: 120px; /* 高さを適度に拡大 */
	    border: 1px solid #ccc;
	    border-radius: 5px;
	    padding: 8px; /* 内側のスペースを調整 */
	    background: #f9f9f9;
	    box-sizing: border-box;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	}

	/* ボタンのスタイル */
	.translate-btn, .read-aloud-btn {
	    background: #007BFF;
	    color: white;
	    border: none;
	    border-radius: 5px;
	    font-size: 1.2rem; /* フォントサイズを調整 */
	    padding: 12px 20px; /* パディングを調整 */
	    cursor: pointer;
	    width: 140px; /* 幅を拡大 */
	    height: 50px; /* 高さを調整 */
	    text-align: center;
	}

	.translate-btn:hover, .read-aloud-btn:hover {
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
	    margin: 15px auto; /* 上下マージンを調整 */
	}

	/* モバイル対応 */
	@media (max-width: 480px) {
	    .output-text1, .output-text2 {
	        font-size: 1.1rem; /* フォントサイズを調整 */
	        height: 100px; /* 高さを少し縮小 */
	    }

	    .translate-btn, .read-aloud-btn {
	        font-size: 1rem; /* ボタンのフォントサイズを調整 */
	        padding: 10px 15px; /* パディングを縮小 */
	    }

	    .language-selection select {
	        font-size: 0.9rem; /* ドロップダウンのフォントサイズを縮小 */
	        padding: 8px; /* 内側のスペースを縮小 */
	    }
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
                	<button class="read-aloud-btn" id="readaloudBtn1">🔊</button>
                </div>
                <textarea class="output-text1" id="outputText1"></textarea>
                <div class="language-selection">
                    <select id="userLang1" name="userLang1">
                        <option value="ja-JP">日本語</option>
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
                    </select>
                    <button class="record-btn" id="recordBtn1" disabled>準備中...</button>
                </div>
            </div>

            <hr>

            <!-- 下段 -->
            <div class="user-block2">


                <div class="button-group">
                	<button class="translate-btn" id="translateBtn2">⇧</button>
                	<button class="read-aloud-btn" id="readaloudBtn2">🔊</button>
                </div>
                <textarea class="output-text2" id="outputText2"></textarea>
                <div class="language-selection">
                    <select id="userLang2" name="userLang2">
                        <option value="ja-JP">日本語</option>
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
                    </select>
                    <button class="record-btn" id="recordBtn2" disabled>準備中...</button>
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
