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
            margin-right: 10px;
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
            text-align: center;
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
        .read-aloud-btn, .translate-btn {
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 20px 40px;
            font-size: 1.6rem;
            cursor: pointer;
            margin-top: 10px;
            transition: background 0.3s ease;
        }
        .read-aloud-btn:hover, .translate-btn:hover {
            background: #0056b3;
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
            .read-aloud-btn, .translate-btn {
                padding: 10px 20px;
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
                        <option value="ar-SA">(المملكة العربية السعودية)</option>
                    </select>
                    <button class="record-btn" id="recordBtn1" disabled>準備中...</button>
                </div>
                <p class="output-text1" id="outputText1">私は強い</p>
                <button class="read-aloud-btn">🔊</button>
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
                        <option value="ar-SA">(المملكة العربية السعودية)</option>
                    </select>
                    <button class="record-btn" id="recordBtn2" disabled>準備中...</button>
                </div>
                <p class="output-text2" id="outputText2">I'm strong.</p>
                <button class="translate-btn">⇧</button>
                <button id="sendFixedDataBtn">テスト用データを送信</button>
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
