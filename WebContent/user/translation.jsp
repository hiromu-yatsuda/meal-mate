<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<%@ include file="./../userbase.jsp" %>
<head>
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
            <!-- 対話相手 -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang1" name="userLang1">
                        <option value="ja">Japanese</option>
                        <option value="en">English</option>
                        <option value="es">Spanish</option>
                        <option value="fr">French</option>
                    </select>
                    <button class="record-btn">🎤</button>
                </div>
                <p class="output-text1">私は強い</p>
                <button class="read-aloud-btn">🔊</button>
            </div>

            <hr>

            <!-- 外国人利用者 -->
            <div class="user-block">
                <div class="language-selection">
                    <select id="userLang2" name="userLang2">
                        <option value="en">English</option>
                        <option value="ja">Japanese</option>
                        <option value="es">Spanish</option>
                        <option value="fr">French</option>
                    </select>
                    <button class="record-btn">🎤</button>
                </div>
                <p class="output-text2">I'm strong.</p>
                <button class="translate-btn">⇧</button>
            </div>
        </div>
    </div>
</body>
</html>
