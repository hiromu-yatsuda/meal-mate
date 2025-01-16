<!-- translation.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
<head>
    <meta charset="UTF-8">
    <title>録音機能サンプル (サーバーアップロード版)</title>
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
        .read-aloud-btn,
        .translate-btn {
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
                    <button class="record-btn" id="recordBtn2" disabled>準備中...</button>
                </div>
                <p class="output-text2">I'm strong.</p>
                <button class="translate-btn">⇧</button>
            </div>
        </div>

        <!-- アップロード結果メッセージ表示領域 -->
        <div id="uploadMessage" style="text-align:center; color:blue; font-weight:bold;"></div>
    </div>

    <script>
    document.addEventListener('DOMContentLoaded', () => {
        // アップロード結果メッセージ表示用
        const uploadMessageEl = document.getElementById('uploadMessage');

        // -------- 上段録音 --------
        let isRecording1 = false;
        let recorder1;
        let audioData1 = [];
        let audioExtension1 = '';
        const recordBtn1 = document.getElementById('recordBtn1');

        navigator.mediaDevices.getUserMedia({ audio: true })
            .then(stream => {
                recorder1 = new MediaRecorder(stream);

                recorder1.addEventListener('dataavailable', e => {
                    audioData1.push(e.data);
                    audioExtension1 = getExtension(e.data.type);
                });

                // 録音停止 → Blob化 → サーバーへアップロード
                recorder1.addEventListener('stop', () => {
                    const audioBlob = new Blob(audioData1, { type: `audio/${audioExtension1.replace('.', '')}` });
                    uploadAudio(audioBlob, audioExtension1);
                });

                recordBtn1.disabled = false;
                recordBtn1.textContent = '🎤';
            })
            .catch(err => {
                console.error('マイクへのアクセスが拒否されました', err);
                recordBtn1.disabled = true;
                recordBtn1.textContent = 'マイク不可';
            });

        recordBtn1.addEventListener('click', () => {
            if (!recorder1) return;

            if (!isRecording1) {
                // 録音開始
                audioData1 = [];
                recorder1.start();
                isRecording1 = true;
                recordBtn1.textContent = '録音中...';
                uploadMessageEl.textContent = '';
            } else {
                // 録音停止
                recorder1.stop();
                isRecording1 = false;
                recordBtn1.textContent = '🎤';
            }
        });


        // -------- 下段録音 --------
        let isRecording2 = false;
        let recorder2;
        let audioData2 = [];
        let audioExtension2 = '';
        const recordBtn2 = document.getElementById('recordBtn2');

        navigator.mediaDevices.getUserMedia({ audio: true })
            .then(stream => {
                recorder2 = new MediaRecorder(stream);

                recorder2.addEventListener('dataavailable', e => {
                    audioData2.push(e.data);
                    audioExtension2 = getExtension(e.data.type);
                });

                recorder2.addEventListener('stop', () => {
                    const audioBlob = new Blob(audioData2, { type: `audio/${audioExtension2.replace('.', '')}` });
                    uploadAudio(audioBlob, audioExtension2);
                });

                recordBtn2.disabled = false;
                recordBtn2.textContent = '🎤';
            })
            .catch(err => {
                console.error('マイクへのアクセスが拒否されました', err);
                recordBtn2.disabled = true;
                recordBtn2.textContent = 'マイク不可';
            });

        recordBtn2.addEventListener('click', () => {
            if (!recorder2) return;

            if (!isRecording2) {
                audioData2 = [];
                recorder2.start();
                isRecording2 = true;
                recordBtn2.textContent = '録音中...';
                uploadMessageEl.textContent = '';
            } else {
                recorder2.stop();
                isRecording2 = false;
                recordBtn2.textContent = '🎤';
            }
        });


        // -------- 拡張子取得 --------
        function getExtension(audioType) {
            let extension = 'wav';
            const matches = audioType.match(/audio\/([^;]+)/);
            if (matches) {
                extension = matches[1];
            }
            return '.' + extension;
        }

        // -------- サーバーアップロード --------
        function uploadAudio(blob, extension) {
            // フォームデータを準備
            const formData = new FormData();
            formData.append('file', blob, 'recorded' + extension);

            fetch('<%= request.getContextPath() %>/uploadTest', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('サーバーエラー');
                }
                return response.text();
            })
            .then(result => {
                console.log('アップロード成功:', result);
                uploadMessageEl.style.color = 'green';
                uploadMessageEl.textContent = `音声アップロードが完了しました: ${result}`;
            })
            .catch(error => {
                console.error('アップロード失敗:', error);
                uploadMessageEl.style.color = 'red';
                uploadMessageEl.textContent = 'アップロードに失敗しました。';
            });
        }
    });
    </script>
</body>
</html>
