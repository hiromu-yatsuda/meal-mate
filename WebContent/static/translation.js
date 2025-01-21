// translation.js
document.addEventListener('DOMContentLoaded', () => {
    const outputText1 = document.getElementById('outputText1');
    const outputText2 = document.getElementById('outputText2');
    const uploadMessageEl = document.getElementById('uploadMessage');

    // AWS Translate 用の言語コードに変換する関数
    function convertToAWSLang(langCode) {
        return langCode.split('-')[0];
    }

    function getSourceSelectedLang(selectId) {
        const sel = document.getElementById(selectId);
        return sel.value;
    }

    function getTargetSelectedLang(selectId) {
        const sel = document.getElementById(selectId);
        return sel.value;
    }

    function sendTextToServer(text, source_lang, target_lang) {
        const awsTargetLang = convertToAWSLang(target_lang);
        fetch(contextPath + '/uploadTest', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                text: text,
                source_lang: source_lang,
                target_lang: awsTargetLang
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

    // 上段の音声認識設定
    let recognizing1 = false;
    let recognition1;
    const recordBtn1 = document.getElementById('recordBtn1');

    if ('webkitSpeechRecognition' in window) {
        recognition1 = new webkitSpeechRecognition();
    } else if ('SpeechRecognition' in window) {
        recognition1 = new SpeechRecognition();
    }

    if (recognition1) {
        recognition1.lang = getSourceSelectedLang('userLang1');
        recognition1.interimResults = false;

        recognition1.onresult = (event) => {
            const text = event.results[0][0].transcript;
            console.log('上段の認識結果:', text);
            outputText1.textContent = text;
            sendTextToServer(text, getSourceSelectedLang('userLang1'), getTargetSelectedLang('userLang2'));
        };

        recognition1.onerror = (e) => {
            console.error('上段の認識エラー', e);
            uploadMessageEl.style.color = 'red';
            uploadMessageEl.textContent = '上段 音声認識に失敗しました';
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
        recordBtn1.disabled = true;
        recordBtn1.textContent = '未対応';
    }

    // 下段の音声認識設定
    let recognizing2 = false;
    let recognition2;
    const recordBtn2 = document.getElementById('recordBtn2');

    if ('webkitSpeechRecognition' in window) {
        recognition2 = new webkitSpeechRecognition();
    } else if ('SpeechRecognition' in window) {
        recognition2 = new SpeechRecognition();
    }

    if (recognition2) {
        recognition2.lang = getSourceSelectedLang('userLang2');
        recognition2.interimResults = false;

        recognition2.onresult = (event) => {
            const text = event.results[0][0].transcript;
            console.log('下段の認識結果:', text);
            outputText2.textContent = text;
            sendTextToServer(text, getSourceSelectedLang('userLang2'), getTargetSelectedLang('userLang1'));
        };

        recognition2.onerror = (e) => {
            console.error('下段の認識エラー', e);
            uploadMessageEl.style.color = 'red';
            uploadMessageEl.textContent = '下段 音声認識に失敗しました';
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

    document.getElementById('sendFixedDataBtn').addEventListener('click', () => {
        const fixedText = "こんばんは";
        const fixedSourceLang = "ja";
        const fixedTargetLang = "en"; // 例として英語を設定
        sendTextToServer(fixedText, fixedSourceLang, fixedTargetLang);
    });

});
