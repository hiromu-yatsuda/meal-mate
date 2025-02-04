<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>ログイン</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Kosugi');
/* ページ全体のスタイル */
body {
    font-family: Arial, sans-serif; /* フォント設定 */
    background-color: #f9f9f9; /* 背景色 */
    margin: 0; /* 余白をゼロに設定 */
    padding: 0; /* パディングをゼロに設定 */
    height: 100vh; /* ビューポート全体の高さを設定 */
    background: url('../img/happa1.png') no-repeat center center; /* 画像を中央に配置 */
    background-size: cover; /* 画面全体をカバーするように調整 */

}

/* ログインフォームのコンテナスタイル */
.login-container {
    margin: 20px 30px auto; /* 上下中央揃え */
    padding: 20px; /* 内側のスペースを設定 */
    background-color: #fff; /* 背景色を白に設定 */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 軽いシャドウで立体感を追加 */

}

/* ログインフォームのヘッダー */
.login-container h1 {
    text-align: center; /* テキスト中央揃え */
    margin-bottom: 20px; /* 下部のマージン */
    background-color: #f1f1f1; /* ヘッダー背景色 */
    border-bottom: 1px solid #ddd; /* 下線の追加 */

}

/* テキスト入力とパスワード入力のスタイル */
.login-container input[type="text"],
.login-container input[type="password"] {
    display: block;
    width: 100%;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    font-size: 30px; /* 文字サイズを20pxに設定 */
}


/* ラベル要素のスタイル */
.login-container label {
    display: flex; /* 子要素を横並びに配置 */
    align-items: center; /* 子要素を縦中央揃え */
    margin-bottom: 10px; /* 下部のマージン */
    font-family: 'Kosugi', sans-serif;
    font-weight: bold;
}

/* ラベル内のチェックボックス */
.login-container label input {
    margin-right: 5px; /* チェックボックスとテキストの間にスペースを追加 */
}

/* サブミットボタンのスタイル */
.login-container input[type="submit"] {
    width: 100%;
    background-color: #3399FF;
    border: none;
    color: #fff;
    cursor: pointer;
    font-size: 2em;
    margin: 40px 0px 0px 0px;
    border: 8px double #0090bb;
}

/* サブミットボタンのホバー時のスタイル */
.login-container input[type="submit"]:hover {
    background-color: #0056b3; /* ホバー時に色を変更 */
}

/* エラーメッセージのスタイル */
.login-container p {
    color: red; /* テキスト色を赤に設定 */
    text-align: center; /* 中央揃え */
}
</style>
</head>
<body>
<%@ include file="../userbase.jsp" %>
<div class="login-container">
    <!-- ログインフォームのタイトル -->
    <h1>Login</h1>
    <!-- フォームの開始 -->
    <form action="/meal-mate/user/login" method="post">

        <p style="color: red;">${error}</p>
        <!-- E-mailの入力フィールド -->
        <label for="id">E-mail</label>
        <input type="text" id="id" name="id" size="20" maxlength="20" required><br><br>

        <!-- パスワードの入力フィールド -->
        <label for="password">Password</label>
        <input type="password" id="password" name="password" size="20" maxlength="20" required><br><br>

        <!-- サインインページへのリンク -->
        <label><a href="/meal-mate/user/create_user_1">sign up here</a></label>

        <!-- サブミットボタン -->
        <input type="submit" name="login" value="Login">
    </form>

    <!-- エラーメッセージ表示部分 -->
    <ul id="errorMessages">
        <%
        // リクエスト属性からエラーメッセージを取得
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { // エラーメッセージが存在する場合
        %>
        <!-- エラーメッセージをリスト項目として表示 -->
        <li style="color:red;"><%= errorMessage %></li>
        <% } %>
    </ul>
</div>

<script>
/* パスワードの表示/非表示を切り替える関数 */
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
