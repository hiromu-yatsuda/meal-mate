<!-- translation.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
<head>
    <meta charset="UTF-8">
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

            <!-- 対話相手 (上段) -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang1" name="userLang1">
                        <option value="ja">Japanese</option>
                        <option value="en">English</option>
                        <option value="es">Spanish</option>
                        <option value="fr">French</option>
                    </select>
                    <button class="record-btn" id="recordBtn1" disabled>準備中...</button>
                </div>
                <p class="output-text1" id="outputText1">私は強い</p>
                <button class="read-aloud-btn">🔊</button>
            </div>

            <hr>

            <!-- 外国人利用者 (下段) -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang2" name="userLang2">
                        <option value="en">English</option>
                        <option value="ja">Japanese</option>
                        <option value="es">Spanish</option>
                        <option value="fr">French</option>
                    </select>
                    <button class="record-btn" id="recordBtn2" disabled>準備中...</button>
                </div>
                <p class="output-text2" id="outputText2">I'm strong.</p>
                <button class="translate-btn">⇧</button>
            </div>
        </div>

        <!-- アップロード結果メッセージ or 翻訳結果表示用 -->
        <div id="uploadMessage" style="text-align:center; color:blue; font-weight:bold;"></div>
    </div>

    <script>
    document.addEventListener('DOMContentLoaded', () => {
        const outputText1 = document.getElementById('outputText1');
        const outputText2 = document.getElementById('outputText2');
        const uploadMessageEl = document.getElementById('uploadMessage');

        // --------------------------------------------------
        // 1) 上段: Web Speech API を使って音声認識 → テキスト
        // --------------------------------------------------
        let recognizing1 = false;
        let recognition1;
        const recordBtn1 = document.getElementById('recordBtn1');

        // ブラウザの対応チェック (Chrome, Edge, Safariなど)
        if ('webkitSpeechRecognition' in window) {
            recognition1 = new webkitSpeechRecognition();
        } else if ('SpeechRecognition' in window) {
            recognition1 = new SpeechRecognition();
        }

        if (recognition1) {
            // 言語を選択したい場合は下記を動的に変更してもOK
            recognition1.lang = 'ja-JP';
            recognition1.interimResults = false;  // 確定結果のみ取得

            recognition1.onresult = (event) => {
                const text = event.results[0][0].transcript;
                console.log('上段の認識結果:', text);
                // テキストを画面表示
                outputText1.textContent = text;
                // テキストをサーバーに送信 (必要なら)
                sendTextToServer(text, getSelectedLang('userLang1'));
            };

            recognition1.onerror = (e) => {
                console.error('上段の認識エラー', e);
                uploadMessageEl.style.color = 'red';
                uploadMessageEl.textContent = '音声認識に失敗しました';
            };

            recordBtn1.disabled = false;
            recordBtn1.textContent = '🎤';

            recordBtn1.addEventListener('click', () => {
                if (!recognizing1) {
                    recognition1.start();
                    recognizing1 = true;
                    recordBtn1.textContent = '録音停止';
                    uploadMessageEl.textContent = '';
                } else {
                    recognition1.stop();
                    recognizing1 = false;
                    recordBtn1.textContent = '🎤';
                }
            });
        } else {
            // Web Speech API未対応
            recordBtn1.disabled = true;
            recordBtn1.textContent = '未対応';
        }

        // --------------------------------------------------
        // 2) 下段: Web Speech API を使って音声認識 → テキスト
        // --------------------------------------------------
        let recognizing2 = false;
        let recognition2;
        const recordBtn2 = document.getElementById('recordBtn2');

        if ('webkitSpeechRecognition' in window) {
            recognition2 = new webkitSpeechRecognition();
        } else if ('SpeechRecognition' in window) {
            recognition2 = new SpeechRecognition();
        }

        if (recognition2) {
            // 言語: 例として英語
            recognition2.lang = 'en-US';
            recognition2.interimResults = false;

            recognition2.onresult = (event) => {
                const text = event.results[0][0].transcript;
                console.log('下段の認識結果:', text);
                outputText2.textContent = text;
                // テキストをサーバーに送信
                sendTextToServer(text, getSelectedLang('userLang2'));
            };

            recognition2.onerror = (e) => {
                console.error('下段の認識エラー', e);
                uploadMessageEl.style.color = 'red';
                uploadMessageEl.textContent = '音声認識に失敗しました';
            };

            recordBtn2.disabled = false;
            recordBtn2.textContent = '🎤';

            recordBtn2.addEventListener('click', () => {
                if (!recognizing2) {
                    recognition2.start();
                    recognizing2 = true;
                    recordBtn2.textContent = '録音停止';
                    uploadMessageEl.textContent = '';
                } else {
                    recognition2.stop();
                    recognizing2 = false;
                    recordBtn2.textContent = '🎤';
                }
            });
        } else {
            recordBtn2.disabled = true;
            recordBtn2.textContent = '未対応';
        }

        // --------------------------------------------------
        // 3) ユーティリティ
        // --------------------------------------------------
        // 言語選択
        function getSelectedLang(selectId) {
            const sel = document.getElementById(selectId);
            return sel.value; // 'ja'/'en'/'es'/'fr' など
        }

        // サーバーにテキスト送信 (fetch + JSON例)
        function sendTextToServer(text, lang) {
            // 例: /uploadText にPOSTする
            // 実際にはServletやAPIのURLに合わせて書き換えてください
            fetch('<%= request.getContextPath() %>/uploadText', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    text: text,
                    language: lang
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('サーバーエラー');
                }
                return response.text();
            })
            .then(result => {
                console.log('テキスト送信成功:', result);
                uploadMessageEl.style.color = 'green';
                uploadMessageEl.textContent = 'テキストを送信しました: ' + result;
            })
            .catch(error => {
                console.error('テキスト送信失敗:', error);
                uploadMessageEl.style.color = 'red';
                uploadMessageEl.textContent = 'テキスト送信に失敗しました。';
            });
        }

    });
    </script>
</body>
</html>
