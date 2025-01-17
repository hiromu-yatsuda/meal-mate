<!-- uploadTest.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
    <title>録音アップロードテスト</title>
</head>
<body>
<h1>録音アップロードテスト</h1>

<button id="recordBtn" disabled>録音準備中...</button>
<div id="message"></div>

<script>
document.addEventListener('DOMContentLoaded', () => {
    let recorder = null;
    let isRecording = false;
    let audioData = [];
    let audioExtension = '';

    const recordBtn = document.getElementById('recordBtn');
    const messageEl = document.getElementById('message');

    // 1. マイク許可を取得
    navigator.mediaDevices.getUserMedia({ audio: true })
        .then(stream => {
            recorder = new MediaRecorder(stream);

            // 録音中にデータが取得されたとき
            recorder.addEventListener('dataavailable', e => {
                audioData.push(e.data);
                audioExtension = getExtension(e.data.type);
            });

            // 録音停止時に Blob 化 & サーバー送信
            recorder.addEventListener('stop', () => {
                const audioBlob = new Blob(audioData, { type: `audio/${audioExtension.replace('.', '')}` });

                uploadAudio(audioBlob)
                    .then(result => {
                        messageEl.textContent = 'アップロード成功: ' + result;
                    })
                    .catch(err => {
                        messageEl.textContent = 'アップロード失敗: ' + err;
                    });
            });

            // マイクが使える場合はボタンを有効化
            recordBtn.disabled = false;
            recordBtn.textContent = '録音開始';
        })
        .catch(err => {
            console.error('マイクへのアクセスが拒否されました:', err);
            recordBtn.disabled = true;
            recordBtn.textContent = 'マイク不可';
        });

    // 2. ボタンクリックで録音開始/停止をトグル
    recordBtn.addEventListener('click', () => {
        if (!recorder) return;

        if (!isRecording) {
            audioData = [];
            recorder.start();
            isRecording = true;
            recordBtn.textContent = '録音停止';
            messageEl.textContent = '';
        } else {
            recorder.stop();  // stop でアップロード開始
            isRecording = false;
            recordBtn.textContent = '録音開始';
        }
    });

    // 拡張子を推定する関数
    function getExtension(audioType) {
        let extension = 'wav';
        const matches = audioType.match(/audio\/([^;]+)/);
        if (matches) {
            extension = matches[1];
        }
        return '.' + extension;
    }

    // サーバーにBlobをアップロードする関数
    async function uploadAudio(blob) {
        const formData = new FormData();
        formData.append('file', blob, 'test' + audioExtension);

        const response = await fetch('<%= request.getContextPath() %>/uploadTest', {
            method: 'POST',
            body: formData
        });
        if (!response.ok) {
            throw new Error('サーバーエラー');
        }
        return await response.text(); // "Uploaded: test.wav" 等
    }
});
</script>
</body>
</html>
