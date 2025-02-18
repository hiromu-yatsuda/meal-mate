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
        padding: 44.24% 5% 0%;
    }
    .text_img {
        width: 100px; /* 幅を指定 */
        height: 100px; /* 高さを指定 */
        object-fit: contain; /* アスペクト比を保つ */
        justify-content: center; /* 横方向の中央揃え */
    }
	.apuri {
	    display: grid;
	    grid-template-columns: repeat(2, 1fr); /* 2列に固定 */
    	gap: 40px 0px;
	    justify-content: center; /* 横方向の中央揃え */
	    align-items: center; /* 縦方向の中央揃え */
	    place-items: center; /* グリッド内の要素を中央に配置 */
	    width: 100%; /* 横幅を100%にする */
	}
	.login {
        display: flex; /* フレックスボックスを使用 */
        justify-content: center; /* 横方向で中央揃え */
        align-items: center; /* 縦方向で中央揃え */
        margin-top: 20px; /* 上に少しスペースを追加 */
    }
</style>
</head>
<body>

<div class="top">
    <div class="apuri">
        <form action="/meal-mate/user/ingredients_register" method="GET">
        	<input type="image" src="/meal-mate/img/touroku.png" class="text_img">
        </form>
        <form action="/meal-mate/user/ar" method="GET">
        	<input type="image" src="/meal-mate/img/AR.png" class="text_img">
        </form>
        <form action="/meal-mate/user/map" method="GET">
        	<input type="image" src="/meal-mate/img/map.png" class="text_img">
        </form>
        <form action="/meal-mate/user/translate" method="GET">
        	<input type="image" src="/meal-mate/img/sei.png" class="text_img">
        </form>
    </div>
</div>

<div class="login">
    <c:choose>
        <%-- ログイン済みの場合 --%>
        <c:when test="${not empty sessionScope.user_name}">
            <span>Welcome, ${sessionScope.user_name}</span>
        </c:when>
        <%-- 未ログインの場合 --%>
        <c:otherwise>
            <form action="/meal-mate/user/login" method="GET">
                <input type="image" src="/meal-mate/img/login3.png" class="text_img">
            </form>
        </c:otherwise>
    </c:choose>
</div>

<form action="/meal-mate/user/create_user_1" method="get">
    <!-- フォームの入力フィールド -->
</form>

</body>
</html>
