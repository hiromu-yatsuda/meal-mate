<!-- translation.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
    <title>録音機能サンプル (ブラウザで音声→テキスト変換)</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0; padding: 0;
        }
        .container {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            background-color: white;
            padding: 20px;
        }
        .translation-section {
            min-height: 100vh;
            padding: 15%;
        }
        .user-block {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 15%;
        }
        .language-selection {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            margin-bottom: 10px;
        }
        .language-selection select {
            font-size: 1rem;
            padding: 5px;
            margin-right: 10xp;
        }
        .language-selection .record-btn {
            background: #ffcc00;
            border: none;
            font-size: 1.2rem;
            border-radius: 50%;
            padding: 10px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .language-selection .record-btn:hover {
            background: #ff9900;
        }
        .output-text1, .output-text2 {
            font-size: 3rem;
            margin: 10px auto;
            width: 90%;
            height: 170px;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            background: #f9f9f9;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow-y: auto;
        }
		/* ボタンを横並びにし、同じサイズに統一 */
		.button-group {
		    display: flex;
		    gap: 10px; /* ボタン間のスペース */
		    margin-top: 10px; /* 上の要素との間隔 */
		    width: 100%; /* ボタングループの幅を親要素に合わせる */
		}

		/* 既存のボタンスタイル */
		.read-aloud-btn, .translate-btn {
		    background: #007BFF;
		    color: white;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    transition: background 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease, color 0.3s ease; /* トランジションを追加 */
		}

		/* ホバー時のスタイル */
		.read-aloud-btn:hover, .translate-btn:hover {
		    background: #0056b3; /* 背景色を変更 */
		    color: #ffffff; /* テキスト色を変更 */
		    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* シャドウを追加 */
		    transform: scale(1.05); /* ボタンを5%拡大 */
		}

		.button-group .translate-btn,
		.button-group .read-aloud-btn {
			background: #007BFF;
		    flex: 1; /* ボタンを均等に拡張 */
		    padding: 15px 0; /* 上下のパディングを調整 */
		    font-size: 1.6rem; /* フォントサイズを統一 */
		    border-radius: 5px; /* 角を丸くする */
		}
        }

        hr {
            border: 0.5px solid #ddd;
            width: 100%;
            margin: 20px auto;
        }
        @media (max-width: 480px) {
            .output-text1, .output-text2 {
                font-size: 1rem;
                padding: 8px;
                height: 80px;
            }
            .record-btn {
                padding: 8px;
                font-size: 1rem;
            }

        }
    </style>
</head>
<body>
    <div class="container">
        <div class="translation-section">
            <!-- 上段 -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang1" name="userLang1">
                        <option value="ja-JP">日本語</option>
                        <option value="en-US">English (US)</option>
                        <option value="en-GB">English (UK)</option>
                        <option value="es-ES">Español (ES)</option>
                        <option value="es-MX">Español (MX)</option>
                        <option value="fr-FR">Français (FR)</option>
                        <option value="de-DE">Deutsch (DE)</option>
                        <option value="zh-CN">中文</option>
                        <option value="ko-KR">한국어 (대한민국)</option>
                        <option value="it-IT">Italiano (IT)</option>
                        <option value="ru-RU">Русский (RU)</option>
                        <option value="pt-PT">Português (PT)</option>
                        <option value="pt-BR">Português (BR)</option>
                        <option value="ar-SA">المملكة العربية السعودية</option>
                    </select>
                    <button class="record-btn" id="recordBtn1" disabled>準備中...</button>
                </div>
                <textarea class="output-text1" id="outputText1"></textarea>
                <div class="button-group">
                	<button class="translate-btn" id="translateBtn1">⇧</button>
                	<button class="read-aloud-btn" id="readaloudBtn1">🔊</button>
                </div>
            </div>

            <hr>

            <!-- 下段 -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang2" name="userLang2">
                        <option value="ja-JP">日本語</option>
                        <option value="en-US">English (US)</option>
                        <option value="en-GB">English (UK)</option>
                        <option value="es-ES">Español (ES)</option>
                        <option value="es-MX">Español (MX)</option>
                        <option value="fr-FR">Français (FR)</option>
                        <option value="de-DE">Deutsch (DE)</option>
                        <option value="zh-CN">中文</option>
                        <option value="ko-KR">한국어 (대한민국)</option>
                        <option value="it-IT">Italiano (IT)</option>
                        <option value="ru-RU">Русский (RU)</option>
                        <option value="pt-PT">Português (PT)</option>
                        <option value="pt-BR">Português (BR)</option>
                        <option value="ar-SA">المملكة العربية السعودية</option>
                    </select>
                    <button class="record-btn" id="recordBtn2" disabled>準備中...</button>
                </div>
                <textarea class="output-text2" id="outputText2"></textarea>
                <div class="button-group">
                	<button class="translate-btn" id="translateBtn2">⇧</button>
                	<button class="read-aloud-btn" id="readaloudBtn2">🔊</button>
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
