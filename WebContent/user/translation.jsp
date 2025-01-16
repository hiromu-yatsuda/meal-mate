<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>録音機能サンプル</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            background-color: white;
            padding: 20px;
        }

        .header {
            text-align: center;
            background: #007BFF;
            color: white;
            padding: 15px;
            font-size: 1.5rem;
            font-weight: bold;
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
            font-size: 3rem; /* フォントサイズを大きくする */
            text-align: center; /* 文字を中央揃え */
            margin: 10px auto; /* 上下の空白を設定 */
            width: 90%;
            height: 170px; /* テキスト入力欄を大きくする */
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
            background: #f9f9f9;
            display: flex;
            align-items: center; /* 縦方向中央揃え */
            justify-content: center; /* 横方向中央揃え */
            overflow-y: auto;
        }

        .read-aloud-btn,
        .translate-btn {
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 20px 40px; /* ボタンを画面に合わせて大きくする */
            font-size: 1.6rem; /* ボタンのフォントサイズを調整 */
            cursor: pointer;
            margin-top: 10px;
            transition: background 0.3s ease;
        }

        .read-aloud-btn:hover,
        .translate-btn:hover {
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

            .read-aloud-btn,
            .translate-btn {
                padding: 10px 20px; /* モバイル向けにサイズ調整 */
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
                    <!-- ここで id を recordBtn1 に変更 -->
                    <button class="record-btn" id="recordBtn1">🎤</button>
                </div>
                <p class="output-text1">私は強い</p>
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
                    <!-- ここで id を recordBtn2 に変更 -->
                    <button class="record-btn" id="recordBtn2">🎤</button>
                </div>
                <p class="output-text2">I'm strong.</p>
                <button class="translate-btn">⇧</button>
            </div>
        </div>
    </div>

    <!-- ここから録音機能のスクリプト -->
    <script>
    document.addEventListener('DOMContentLoaded', () => {

        // -------------------------------
        // （上段）録音機能に関する変数
        // -------------------------------
        let isRecording1 = false; // 録音中かどうか
        let recorder1;           // MediaRecorder インスタンス
        let audioData1 = [];     // 録音データ格納用
        let audioExtension1 = '';

        const recordBtn1 = document.getElementById('recordBtn1');

        // マイクへのアクセス（上段用）
        navigator.mediaDevices.getUserMedia({ audio: true })
            .then(stream => {
                recorder1 = new MediaRecorder(stream);

                // 録音中にデータが取得されたとき
                recorder1.addEventListener('dataavailable', e => {
                    audioData1.push(e.data);
                    audioExtension1 = getExtension(e.data.type);
                });

                // 録音停止したタイミング
                recorder1.addEventListener('stop', () => {
                    const audioBlob = new Blob(audioData1);
                    const url = URL.createObjectURL(audioBlob);
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = Math.floor(Date.now() / 1000) + audioExtension1; // ファイル名をタイムスタンプに
                    document.body.appendChild(a);
                    a.click();
                });

                // マイクが使える場合はボタンを活性化
                recordBtn1.disabled = false;
                recordBtn1.textContent = '🎤';
            })
            .catch(err => {
                console.error('マイクへのアクセスが拒否されました', err);
                recordBtn1.disabled = true;
                recordBtn1.textContent = 'マイク不可';
            });

        // （上段）ボタン操作
        recordBtn1.addEventListener('click', () => {
            if (!recorder1) return;

            if (!isRecording1) {
                // 録音開始
                audioData1 = [];
                recorder1.start();
                isRecording1 = true;
                recordBtn1.textContent = '録音中...';
            } else {
                // 録音停止
                recorder1.stop();
                isRecording1 = false;
                recordBtn1.textContent = '🎤';
            }
        });

        // -------------------------------
        // （下段）録音機能に関する変数
        // -------------------------------
        let isRecording2 = false;
        let recorder2;
        let audioData2 = [];
        let audioExtension2 = '';

        const recordBtn2 = document.getElementById('recordBtn2');

        // マイクへのアクセス（下段用）
        navigator.mediaDevices.getUserMedia({ audio: true })
            .then(stream => {
                recorder2 = new MediaRecorder(stream);

                recorder2.addEventListener('dataavailable', e => {
                    audioData2.push(e.data);
                    audioExtension2 = getExtension(e.data.type);
                });

                recorder2.addEventListener('stop', () => {
                    const audioBlob = new Blob(audioData2);
                    const url = URL.createObjectURL(audioBlob);
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = Math.floor(Date.now() / 1000) + audioExtension2;
                    document.body.appendChild(a);
                    a.click();
                });

                recordBtn2.disabled = false;
                recordBtn2.textContent = '🎤';
            })
            .catch(err => {
                console.error('マイクへのアクセスが拒否されました', err);
                recordBtn2.disabled = true;
                recordBtn2.textContent = 'マイク不可';
            });

        // （下段）ボタン操作
        recordBtn2.addEventListener('click', () => {
            if (!recorder2) return;

            if (!isRecording2) {
                audioData2 = [];
                recorder2.start();
                isRecording2 = true;
                recordBtn2.textContent = '録音中...';
            } else {
                recorder2.stop();
                isRecording2 = false;
                recordBtn2.textContent = '🎤';
            }
        });

        // -------------------------------
        // 音声ファイルの拡張子取得関数
        // -------------------------------
        function getExtension(audioType) {
            let extension = 'wav';
            const matches = audioType.match(/audio\/([^;]+)/);
            if (matches) {
                extension = matches[1];
            }
            return '.' + extension;
        }

    });
    </script>
</body>
</html>
