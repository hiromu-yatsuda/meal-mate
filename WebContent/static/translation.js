// translation.js

// DOM（Document Object Model）が完全に読み込まれた後に実行されるイベントリスナー
document.addEventListener('DOMContentLoaded', () => {
	// 翻訳ボタンや出力テキストエリア、読み上げボタンなどの要素を取得
	const recordBtn1 = document.getElementById('recordBtn1');		// 上段の録音ボタン
	const recordBtn2 = document.getElementById('recordBtn2');		// 下段の録音ボタン
    const translateBtn1 = document.getElementById('translateBtn1'); // 上段の翻訳ボタン
    const translateBtn2 = document.getElementById('translateBtn2'); // 下段の翻訳ボタン
    const outputText1 = document.getElementById('outputText1');     // 上段の出力テキストエリア
    const outputText2 = document.getElementById('outputText2');     // 下段の出力テキストエリア
    const readaloudBtn1 = document.getElementById('readaloudBtn1'); // 上段の読み上げボタン
    const readaloudBtn2 = document.getElementById('readaloudBtn2'); // 下段の読み上げボタン

	// 音声ファイルを再生するための変数
	let audio;

    // 詳細な言語コードマッピングを使用した関数
    /**
     * AWS（Amazon Web Services）で使用する言語コードに変換する関数
     * @param {string} langCode - 元の言語コード（例: "en-US"）
     * @returns {string} AWS対応の言語コード（例: "en"）
     */

    function convertToAWSLang(langCode) {
        const mapping = {
            "ja-JP": "ja",
            "en-US": "en",
            "es-ES": "es",
            "fr-FR": "fr",
            "de-DE": "de",
            "zh-CN": "zh",
            "ko-KR": "ko",
            "it-IT": "it",
            "ru-RU": "ru",
            "pt-PT": "pt",
            "ar-SA": "ar",
            "vi-VN": "vi" // ベトナム語を追加
            // 必要に応じて他のマッピングを追加
        };
        // マッピングが存在しない場合は、単純に最初の部分を返す
        return mapping[langCode] || langCode.split('-')[0];
    }

    /**
     * 指定されたセレクトボックスから選択された言語コードを取得する関数
     * @param {string} selectId - セレクトボックスのID
     * @returns {string} 選択された言語コード
     */
    function getSelectedLang(selectId) {
        const sel = document.getElementById(selectId);
        return sel.value;
    }

    // テキストをサーバーに送信して翻訳を行う関数
    // 引数　翻訳前の文, 翻訳前の言語コード, 翻訳後の言語コード, 翻訳後に行う関数
    function sendTextToServer(text, source_lang, target_lang, func) {
    	// AWS用に言語コードを変換
        const awsSourceLang = convertToAWSLang(source_lang);
        const awsTargetLang = convertToAWSLang(target_lang);

        // フェッチAPIを使用してサーバーにPOSTリクエストを送信
        fetch('https://1cvx7hjm1f.execute-api.us-east-1.amazonaws.com/dev/translate', { // 'contextPath' はアプリケーションのベースURL
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }, // JSON形式でデータを送信
            body: JSON.stringify({
                text: text,
                source_lang: awsSourceLang,
                target_lang: awsTargetLang
            })
        })

        // レスポンスがOKでない場合の処理
        .then(response => {
            if (!response.ok) {
                throw new Error('サーバーエラー');
            }
            return response.text();
        })

        // 送信後に行われる関数
        // 引数　受信したJSON文字列
        .then(result => {
        	// 送信が成功した場合の処理
            console.log('テキスト送信成功');
            console.log(result)
            // JSON.parse(result)関数を実行する
            // 引数　Json文字列
            // obj　受信したオブジェクト
            const obj = JSON.parse(result); // =>JSONをJavaScriptのオブジェクトに変換
            console.log(obj.translatedText) // 翻訳されたテキスト
            console.log(obj.outputMp3)		// 音声ファイルのURL

            // 翻訳後に行う関数を実行
            // 引数　translatedTextというデータを持っているオブジェクト＝受信したデータ
            func(obj)
        })

        .catch(error => {
        	// エラーが発生した場合の処理
            console.error('テキスト送信失敗:', error);
        });
    }

    // 上段（対話相手）の音声認識設定
    let recognition1;			// 音声認識オブジェクト
    let recognizing1 = false;	// 音声認識が現在行われているかどうかのフラグ

    // ブラウザがwebkitSpeechRecognitionをサポートしているか確認
    if ('webkitSpeechRecognition' in window) {
        recognition1 = new webkitSpeechRecognition(); // webkitベースのブラウザ用
    } else if ('SpeechRecognition' in window) {
        recognition1 = new SpeechRecognition(); // その他のブラウザ用
    }

    if (recognition1) {
    	// 音声認識の初期設定
        recognition1.lang = getSelectedLang('userLang1');	// 初期言語設定
        recognition1.interimResults = false;				// 中間結果を取得しない

        // 音声認識結果が得られたときの処理
        recognition1.onresult = (event) => {
            const text = event.results[0][0].transcript; // 認識されたテキスト
            console.log('上段の認識結果:', text);
            outputText1.value = text; // 上段のテキストエリアに認識結果を表示
        };

        // 音声認識中にエラーが発生したときの処理
        recognition1.onerror = (e) => {
            console.error('上段の認識エラー', e);
            uploadMessageEl.style.color = 'red';
            uploadMessageEl.textContent = '上段 音声認識に失敗しました';
        };

        // 録音ボタンを有効化し、アイコンを設定
        recordBtn1.disabled = false;
        recordBtn1.textContent = '🎤';

        // 録音ボタンがクリックされたときの処理
        recordBtn1.addEventListener('click', () => {
            if (!recognizing1) {
            	// 録音開始する場合
                // 録音開始前に選択されている言語を取得し、音声認識に設定
                recognition1.lang = getSelectedLang('userLang1');
                recognition1.start();	// 音声認識を開始
                recognizing1 = true;	// フラグを更新
                recordBtn1.textContent = 'STOP'; // ボタンのテキストを変更（録音終了ボタンに）
                // CSS用にクラスを付与
                recordBtn1.classList.add('recording');
            } else {
            	// 録音停止する場合
                recognition1.stop();	// 音声認識を停止
                recognizing1 = false;	// フラグを更新
                recordBtn1.textContent = '🎤'; // ボタンのテキストを元に戻す（録音ボタンに）
                // CSS用に付与したクラスを削除
                recordBtn1.classList.remove('recording');
            }
        });

    } else {
    	// 音声認識がサポートされていない場合の処理
        recordBtn1.disabled = true;			// 録音ボタンを無効化
        recordBtn1.textContent = 'OFF';	// ボタンに「OFF」と表示
    }

    // 下段（外国人利用者）の音声認識設定
    let recognition2;			// 音声認識オブジェクト
    let recognizing2 = false;	// 音声認識が現在行われているかどうかのフラグ

    // ブラウザがWebkitSpeechRecognitionをサポートしているか確認
    if ('webkitSpeechRecognition' in window) {
        recognition2 = new webkitSpeechRecognition(); // Webkitベースのブラウザ用
    } else if ('SpeechRecognition' in window) {
        recognition2 = new SpeechRecognition(); // その他のブラウザ用
    }

    if (recognition2) {
    	// 音声認識の初期設定
        recognition2.lang = getSelectedLang('userLang2');	// 初期言語設定
        recognition2.interimResults = false;				// 中間結果を表示しない

        // 音声認識結果が得られたときの処理
        recognition2.onresult = (event) => {
            const text = event.results[0][0].transcript; // 認識されたテキスト
            console.log('下段の認識結果:', text);
            outputText2.value = text; // 下段のテキストエリアに認識結果を表示
        };

        // 音声認識中にエラーが発生したときの処理
        recognition2.onerror = (e) => {
            console.error('下段の認識エラー', e);
            uploadMessageEl.style.color = 'red';
            uploadMessageEl.textContent = '下段 音声認識に失敗しました';
        };

        // 録音ボタンを有効化し、アイコンを設定
        recordBtn2.disabled = false;
        recordBtn2.textContent = '🎤';

        // 録音ボタンがクリックされたときの処理
        recordBtn2.addEventListener('click', () => {
            if (!recognizing2) {
            	// 録音開始する場合
            	// 録音開始前に選択されている言語を取得し、音声認識に設定
                recognition2.lang = getSelectedLang('userLang2');
                recognition2.start();	// 音声認識を開始
                recognizing2 = true;	// フラグを更新
                recordBtn2.textContent = 'STOP'; // ボタンのテキストを変更（録音終了ボタンに）
                // CSS用にクラスを付与
                recordBtn2.classList.add('recording');
            } else {
            	// 録音停止する場合
                recognition2.stop();	// 音声認識を停止
                recognizing2 = false;	// フラグを更新
                recordBtn2.textContent = '🎤'; // ボタンのテキストを元に戻す（録音ボタンに）
                // CSS用に付与したクラスを削除
                recordBtn2.classList.remove('recording');
            }
        });

    } else {
    	// 音声認識がサポートされていない場合の処理
        recordBtn2.disabled = true;			// 録音ボタンを無効化
        recordBtn2.textContent = 'OFF';	// ボタンに「未対応」と表示
    }

    // 対話相手の翻訳ボタン1（上段）の設定
    // 上段のテキストを翻訳して下段に表示
    if (translateBtn1) {
        translateBtn1.addEventListener('click', () => {
            const text = outputText1.value; // 上段のテキストエリアからテキストを取得
            const sourceLang = getSelectedLang('userLang1'); // 翻訳元の言語コードを取得
            const targetLang = getSelectedLang('userLang2'); // 翻訳後の言語コードを取得
            if (text) {
            	// sendTextToServer関数を実行する
            	// 41行目に定義されている
            	// 引数　翻訳前の文, 翻訳前の言語コード, 翻訳後の言語コード, 翻訳後に行う関数
            	//															引数data　translatedTextという
            	//															データを持っているオブジェクト＝受信したデータ
                sendTextToServer(text, sourceLang, targetLang, (data) => {
                    if (data && data.translatedText) {
                        outputText2.value = data.translatedText;// 下段のテキストエリアに翻訳結果を表示
                        console.log('上段の翻訳結果:', data.translatedText);

                        if (data.outputMp3) {
                        	// インスタンスを作成して音声ファイルを読み込み
                            audio = new Audio(data.outputMp3);
                            // ボタン表示
                            readaloudBtn2.style.display = 'inline-block';
                        } else {
                            audio = null;
                            // ボタン非表示
                            readaloudBtn2.style.display = 'none';
                        }
                    } else {
                    	outputText2.value = '';
                        audio = null;
                        readaloudBtn2.style.display = 'none';
                    }
                });
            } else {
            	// テキストが存在しない場合
                console.warn("上段に翻訳するテキストがありません");
            }
        });
    }

    // 外国人利用者の翻訳ボタン2（下段）の設定
    // 下段のテキストを翻訳して上段に表示
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
                        outputText1.value = data.translatedText; //上段のテキストエリアに翻訳結果を表示
                        console.log('下段の翻訳結果:', data.translatedText);

                        if (data.outputMp3) {
                        	// インスタンスを作成して音声ファイルを読み込み
                            audio = new Audio(data.outputMp3);
                            // ボタン表示
                            readaloudBtn1.style.display = 'inline-block';
                        } else {
                            audio = null;
                            // ボタン非表示
                            readaloudBtn1.style.display = 'none';
                        }
                    } else {
                    	outputText1.value = '';
                        audio = null;
                        readaloudBtn1.style.display = 'none';
                    }
                });
            } else {
            	// テキストが存在しない場合
                console.warn("下段に翻訳するテキストがありません");
            }
        });
    }

    /**
     * 読み上げボタン1（上段）のクリックイベント設定
     * 上段の翻訳結果を音声で再生する
     */
    if (readaloudBtn1) {
        readaloudBtn1.addEventListener('click', () => {
            if (audio) {           // audioオブジェクトが存在する場合
            	audio.load();
                audio.play();      // 音声ファイルを再生
                console.log('音声ファイルを再生しました:', audio.src);
            } else {
                console.warn("再生する音声ファイルがありません"); // 音声ファイルがない場合
            }
        });
    }

    /**
     * 読み上げボタン1（上段）のクリックイベント設定
     * 上段の翻訳結果を音声で再生する
     */
    if (readaloudBtn2) {
        readaloudBtn2.addEventListener('click', () => {
            if (audio) {           // audioオブジェクトが存在する場合
            	audio.load();
                audio.play();      // 音声ファイルを再生
                console.log('音声ファイルを再生しました:', audio.src);
            } else {
                console.warn("再生する音声ファイルがありません"); // 音声ファイルがない場合
            }
        });
    }
});