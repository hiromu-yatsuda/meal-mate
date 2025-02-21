<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
<%@ include file="../userbase.jsp" %>
</header>

<style>
@import url('https://fonts.googleapis.com/css?family=Kosugi');
/* ページ全体のスタイル */
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    margin: 0;
    padding: 0;
    height: 100vh;
    background: url('../img/happa1.png') no-repeat center center;
    background-size: cover;
}

/* フォームのコンテナ */
.form-container {
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 300px;
}

/* ヘッダー */
h1 {
    text-align: center;
    margin-bottom: 20px;
    background-color: #f1f1f1;
    border-bottom: 1px solid #ddd;
}

/* 入力フィールドのスタイル */
.form-container input[type="text"],
.form-container input[type="password"],
.form-container select {
    display: block;
    width: 100%;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    box-sizing: border-box;
    font-size: 16px;
    padding: 8px;
}

/* ラベルのスタイル */
.form-container label {
    display: block;
    font-family: 'Kosugi', sans-serif;
    font-weight: bold;
    margin-bottom: 5px;
}

/* サブミットボタンのスタイル */
.form-container input[type="submit"] {
    width: 100%;
    background-color: #3399FF;
    border: none;
    color: #fff;
    cursor: pointer;
    font-size: 1.2em;
    padding: 10px;
    border-radius: 5px;
    margin-top: 20px;
}

.form-container input[type="submit"]:hover {
    background-color: #0056b3;
}

/* エラーメッセージのスタイル */
.form-container p {
    color: red;
    text-align: center;
}
</style>


<body>
<div class="form-container">
<p>${u_ac_error}</p>
<form class="form-create" action="/meal-mate/user/create_user_1" method="post">
<h1>Sign Up</h1>
<label for="name">NAME</label>
<input type="text" name="name" id="name" placeholder="John Smith" required>

            <label for="email">EMAIL</label>
<input type="text" name="email" id="email" placeholder="Email address" required>

            <label for="pass">PASSWORD</label>
<input type="password" name="pass" id="pass" placeholder="password" required>

            <label for="language_list">Language</label>
<select id="language_list" name="language_list">
<option value="1">日本語</option>
<option value="2" selected>English</option>
<option value="3">Español</option>
<option value="4">Français</option>
<option value="5">Deutsch</option>
<option value="6">中文</option>
<option value="7">한국어</option>
<option value="8">Italiano</option>
<option value="9">Русский</option>
<option value="10">Português</option>
<option value="11">المملكة العربية السعودية</option>
<option value="12">Tiếng Việt</option>
</select>
<input type="submit" value="Account Create">
</form>
</div>
</body>