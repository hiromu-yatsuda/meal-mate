// translation.js
document.addEventListener('DOMContentLoaded', () => {
    const translateBtn1 = document.getElementById('translateBtn1');
    const translateBtn2 = document.getElementById('translateBtn2');
    const outputText1 = document.getElementById('outputText1');
    const outputText2 = document.getElementById('outputText2');
    const readaloudBtn1 = document.getElementById('readaloudBtn1');
    const readaloudBtn2 = document.getElementById('readaloudBtn2');

	// 音声ファイル用の変数
	let audio;

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

    function getSelectedLang(selectId) {
        const sel = document.getElementById(selectId);
        return sel.value;
    }

    // 引数　翻訳前の文, 翻訳前の言語コード, 翻訳後の言語コード, 翻訳後に行う関数
    function sendTextToServer(text, source_lang, target_lang, func) {
        const awsSourceLang = convertToAWSLang(source_lang);
        const awsTargetLang = convertToAWSLang(target_lang);

        // 送信を行う
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
        // 送信後に行われる関数
        // 引数　受信したJSON文字列
        .then(result => {
            console.log('テキスト送信成功');
            console.log(result)
//          JSON.parse(result)関数を実行する
//			引数　Json文字列
//			obj	受信したオブジェクト
            const obj = JSON.parse(result); // =>JSONをJavaScriptのオブジェクトに変換
            console.log(obj.translatedText)
            console.log(obj.outputMp3)
            console.log(obj.message)
            // 翻訳後に行う関数を実行
            // 引数　translatedTextというデータを持っているオブジェクト＝受信したデータ
            func(obj)
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
        recognition1.lang = getSelectedLang('userLang1');
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
        recognition2.lang = getSelectedLang('userLang2');
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
                recognition2.lang = getSelectedLang('userLang2');
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

    // 翻訳ボタン1（上段）の設定
    if (translateBtn1) {
        translateBtn1.addEventListener('click', () => {
            const text = outputText1.value;
            const sourceLang = getSelectedLang('userLang1');
            const targetLang = getSelectedLang('userLang2');
            if (text) {
            	// sendTextToServer関数を実行する
            	// 41行目に定義されている
            	// 引数　翻訳前の文, 翻訳前の言語コード, 翻訳後の言語コード, 翻訳後に行う関数
            	//															引数data　translatedTextという
            	//															データを持っているオブジェクト＝受信したデータ
                sendTextToServer(text, sourceLang, targetLang, (data) => {
                    if (data && data.translatedText) {
                        outputText2.value = data.translatedText;
                        console.log('上段の翻訳結果:', data.translatedText);
                        // インスタンスを作成して音声ファイルを読み込み
                        audio = new Audio(data.outputMp3);
                    }
                });
            } else {
                console.warn("上段に翻訳するテキストがありません");
            }
        });
    }



    // 翻訳ボタン2（下段）の設定
 // 翻訳ボタン1（上段）の設定
    if (translateBtn2) {
        translateBtn2.addEventListener('click', () => {
            const text = outputText2.value;
            const sourceLang = getSelectedLang('userLang2');
            const targetLang = getSelectedLang('userLang1');
            if (text) {
            	// sendTextToServer関数を実行する
            	// 41行目に定義されている
            	// 引数　翻訳前の文, 翻訳前の言語コード, 翻訳後の言語コード, 翻訳後に行う関数
            	//															引数data　translatedTextという
            	//															データを持っているオブジェクト＝受信したデータ
                sendTextToServer(text, sourceLang, targetLang, (data) => {
                    if (data && data.translatedText) {
                        outputText1.value = data.translatedText;
                        console.log('下段の翻訳結果:', data.translatedText);
                        // インスタンスを作成して音声ファイルを読み込み
                        audio = new Audio(data.outputMp3);
                    }
                });
            } else {
                console.warn("下段に翻訳するテキストがありません");
            }
        });    }

    if (readaloudBtn1) {

    	readaloudBtn1.addEventListener('click', () => {

    		// 音声ファイルを再生
    		audio.play();
    	})
    }

    if (readaloudBtn2) {

    	readaloudBtn2.addEventListener('click', () => {

    		// 音声ファイルを再生
    		audio.play();
    	})
    }

});
