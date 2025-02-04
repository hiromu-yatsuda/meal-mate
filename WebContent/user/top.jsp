<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>${param.title}</title>
<style>
    body {
        margin: 0;
        padding: 0;
        height: 100vh;
        background: url('../img/happa.png') no-repeat center center; /* 画像を中央に配置 */
        background-size: cover; /* 画面全体をカバーするように調整 */
    	overflow: hidden; /* スクロールを無効にする */
    }
    .top {
        padding: 44.24% 0%;
    }
    .text_img {
        width: 100px; /* 幅を指定 */
        height: 100px; /* 高さを指定 */
        object-fit: contain; /* アスペクト比を保つ */
    }
    .apuri {
        display: flex; /* Flexboxを有効化 */
        flex-wrap: wrap; /* 改行を許可 */
    	gap: 40px 60px;
        justify-content: center; /* 中央揃え */
        align-items: center; /* 垂直方向の調整 */
    }

</style>
</head>
<body>


<form action="/meal-mate/user/create_user_1" method="get">
    <!-- フォームの入力フィールド -->

</form>


<div class="top">
    <div class="apuri">
        <a href="http://localhost:8080/meal-mate/user/register.jsp"><img class="text_img" src="../img/touroku.png"></a>
        <a href="http://localhost:8080/meal-mate/user/AR.jsp"><img class="text_img" src="../img/AR.png"></a>
        <a href="http://localhost:8080/meal-mate/user/map.jsp"><img class="text_img" src="../img/map.png"></a>
        <a href="http://localhost:8080/meal-mate/user/translation.jsp"><img class="text_img" src="../img/sei.png"></a>
        <a href="http://localhost:8080/meal-mate/auth/login.jsp"><img class="text_img" src="../img/login3.png"></a>

    </div>
</div>


</body>
</html>
