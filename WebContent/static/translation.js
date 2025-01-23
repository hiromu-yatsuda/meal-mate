// translation.js
document.addEventListener('DOMContentLoaded', () => {
    const translateBtn1 = document.getElementById('translateBtn1');
    const translateBtn2 = document.getElementById('translateBtn2');
    const outputText1 = document.getElementById('outputText1');
    const outputText2 = document.getElementById('outputText2');


    // 詳細な言語コードマッピングを使用した関数
    function convertToAWSLang(langCode) {
        const mapping = {
            "ja-JP": "ja",
            "en-US": "en",
            "en-GB": "en",
            "es-ES": "es",
            "es-MX": "es",
            "fr-FR": "fr",
            "de-DE": "de",
            "zh-CN": "zh",
            "ko-KR": "ko",
            "it-IT": "it",
            "ru-RU": "ru",
            "pt-PT": "pt",
            "pt-BR": "pt",
            "ar-SA": "ar"
            // 必要に応じて他のマッピングを追加
        };
        // マッピングが存在しない場合は、単純に最初の部分を返す
        return mapping[langCode] || langCode.split('-')[0];
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
        const awsSourceLang = convertToAWSLang(source_lang);
        const awsTargetLang = convertToAWSLang(target_lang);

        fetch(contextPath + '/uploadTest', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                text: text,
                source_lang: awsSourceLang,
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
            console.log('テキスト送信成功');
            console.log(result)
            const obj = JSON.parse(result); // =>JSONをJavaScriptのオブジェクトに変換
            console.log(obj.translatedText)
            console.log(obj.outputMp3)
            console.log(obj.message)
        })
        .catch(error => {
            console.error('テキスト送信失敗:', error);

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
                // 録音開始前に選択されている言語を取得し、音声認識に設定
                recognition1.lang = getSourceSelectedLang('userLang1');
                recognition1.start();
                recognizing1 = true;
                recordBtn1.textContent = 'OFF';
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
                recognition2.lang = getSourceSelectedLang('userLang2');
                recognition2.start();
                recognizing2 = true;
                recordBtn2.textContent = 'OFF';
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
        const fixedTargetLang = "en";
        outputText1.textContent = fixedText;
        outputText2.textContent = fixedText;
    });

    // 翻訳ボタン1（上段）の設定
    if (translateBtn1) {
        translateBtn1.addEventListener('click', () => {
            const text = outputText1.textContent;
            const sourceLang = getSourceSelectedLang('userLang1');
            const targetLang = getTargetSelectedLang('userLang2');
            if (text) {
                sendTextToServer(text, sourceLang, targetLang, (data) => {
                    if (data && data.translatedText) {
                        outputText1.textContent = data.translatedText;
                        console.log('上段の翻訳結果:', data.translatedText);
                    }
                });
            } else {
                console.warn("上段に翻訳するテキストがありません");
            }
        });
    }



    // 翻訳ボタン2（下段）の設定
    if (translateBtn2) {
        translateBtn2.addEventListener('click', () => {
            const text = outputText2.textContent;
            const sourceLang = getSourceSelectedLang('userLang2');
            const targetLang = getTargetSelectedLang('userLang1'); // 同じ言語に翻訳する場合
            if (text) {
                sendTextToServer(text, sourceLang, targetLang, (data) => {
                    if (data && data.translatedText) {
                        outputText2.textContent = data.translatedText;
                        console.log('下段の翻訳結果:', data.translatedText);
                    }
                });
            } else {
                console.warn("下段に翻訳するテキストがありません");
            }
        });
    }

});
