<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>サインイン</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Kosugi');
/* ページ全体のスタイル */
body {
    font-family: Arial, sans-serif; /* ベースとなるフォント設定 */
    background-color: #f9f9f9; /* 背景色 */
    margin: 0; /* ページ全体の余白を削除 */
    padding: 0; /* ページ全体のパディングを削除 */
    height: 100vh; /* ビューポートの高さを100%に設定 */
    background: url('../img/happa1.png') no-repeat center center; /* 画像を中央に配置 */
    background-size: cover; /* 画面全体をカバーするように調整 */

}

/* サインインフォームのコンテナ */
.signin-container {
    margin: 20px 30px auto; /* 上下中央揃え */
    padding: 20px; /* 内側のスペースを設定 */
    background-color: #fff; /* 背景を白色に設定 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 軽いシャドウで立体感を付与 */
}

/* フォームタイトルのスタイル */
.signin-container h1 {
    text-align: center; /* タイトルを中央揃え */
    margin-bottom: 20px; /* 下部マージン */    background-color: #f1f1f1; /* 背景色を淡いグレーに設定 */
    padding: 10px; /* 内側のスペースを設定 */
    border-bottom: 1px solid #ddd; /* 下線を追加 */
    font-size: 80px; /* フォントサイズを設定 */
}

/* テキストフィールド、パスワード、セレクトボックスのスタイル */
.signin-container input[type="text"],
.signin-container input[type="password"],
.signin-container select {
    display: block;
    width: 100%;
    padding: 25px; /* 内側のスペースを設定 */
    margin-bottom: 10px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    font-size: 50px; /* 文字サイズを20pxに設定 */
}

label {
    display: flex; /* 子要素を横並びに配置 */
    align-items: center; /* 子要素を縦中央揃え */
    margin-bottom: 10px; /* 下部のマージン */
    font-size: 2.2em;
    font-family: 'Kosugi', sans-serif;
    font-weight: bold;
}

/* サブミットボタンのスタイル */
.signin-container input[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #3399FF;
    border: none;
    color: #fff;
    cursor: pointer;
    font-size: 3.5em;
    border: 8px double #0090bb;
}

/* サブミットボタンのホバー時のスタイル */
.signin-container input[type="submit"]:hover {
    background-color: #0056b3; /* ホバー時に背景色を濃く設定 */
}

/* エラーメッセージのスタイル */
.signin-container p {
    color: red; /* テキスト色を赤に設定 */
    text-align: center; /* 中央揃え */
}


</style>
</head>
<body>
<%@ include file="../userbase.jsp" %>
<div class="signin-container">
    <!-- フォームタイトル -->
    <h1>Signin</h1>
    <!-- フォーム開始 -->
    <form action="${pageContext.request.contextPath}/login" method="post">

        <!-- ログインページへのリンク -->
        <label><a href="login.jsp">Click Here for Login</a></label><br><br>

        <!-- 名前入力フィールド -->
        <label for="name">Name</label><br>
        <input type="text" id="name" name="name" size="20" maxlength="20" required><br><br>

        <!-- 言語選択フィールド -->
        <label for="lan">Language</label><br>
        <select id="lan" name="lan" required>
	       	<option value="ja-JP" selected>日本語</option>
			<option value="en-US">English</option>
			<option value="es-ES">Español</option>
			<option value="fr-FR">Français</option>
			<option value="de-DE">Deutsch</option>
			<option value="zh-CN">中文</option>
			<option value="ko-KR">한국어</option>
			<option value="it-IT">Italiano</option>
			<option value="ru-RU">Русский</option>
			<option value="pt-PT">Português</option>
			<option value="ar-SA">المملكة العربية السعودية</option>
			<option value="vi-VN">Tiếng Việt</option>
        </select><br><br>

        <!-- Eメール入力フィールド -->
        <label for="mail">E-mail</label><br>
        <input type="text" id="mail" name="mail" size="20" maxlength="20" required><br><br>

        <!-- パスワード入力フィールド -->
        <label for="password">Password</label><br>
        <input type="password" id="password" name="password" size="20" maxlength="20" required><br><br>

        <!-- パスワード確認フィールド -->
        <label for="confirmpassword">Confirm password</label><br>
        <input type="password" id="confirmpassword" name="confirmpassword" size="20" maxlength="20" required><br><br>

        <!-- サインインボタン -->
        <input type="submit" name="Signin" value="Signin">
    </form>

    <!-- エラーメッセージ表示 -->
    <ul id="errorMessages">
        <%
        // エラーメッセージをリクエスト属性から取得
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { // エラーメッセージが存在する場合
        %>
        <!-- エラーメッセージをリスト項目として表示 -->
        <li style="color:red;"><%= errorMessage %></li>
        <% } %>
    </ul>
</div>

<script>
/* パスワードの表示/非表示切り替え関数 */
function togglePasswordVisibility() {
    var passwordField = document.getElementById("password");
    if (passwordField.type === "password") {
        passwordField.type = "text"; // パスワードを表示
    } else {
        passwordField.type = "password"; // パスワードを非表示
    }
}
</script>
</body>
</html>
