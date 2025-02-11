<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${param.title}</title>
<style>
    body {
        margin: 0;
        padding: 0;
        height: 100vh;
    }

    .nav {
        border: solid 2px #d3d3d3;
        border-radius: 8px;
        background-color: #fff;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        justify-content: center;
        padding: 1% 5%;

    }

    .hako {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 100%;
    }

    .hako a {
        font-size: 1.3rem; /* アイコンサイズ */
        text-decoration: none;
        color: #333;
        padding: 5px 20px;
        border: 2px solid #d3d3d3; /* 枠線 */
        border-radius: 8px; /* 角を丸くする */
        background-color: #f9f9f9; /* 背景色 */
        transition: transform 0.3s, color 0.3s, background-color 0.3s;
    }


    .botannnoyatu {
        font-size: 1.3rem; /* アイコンサイズ */
        text-decoration: none;
        color: #333;
        padding: 5px 20px;
        border: 2px solid #d3d3d3; /* 枠線 */
        border-radius: 8px; /* 角を丸くする */
        background-color: #f9f9f9; /* 背景色 */
        transition: transform 0.3s, color 0.3s, background-color 0.3s;
    }


</style>
</head>
<body>
<div class="nav">
    <div class="hako">
        <a href="javascript:void(0)" onclick="history.back()">🔙</a>
        <c:choose>
		    <%-- ログイン済みの場合 --%>
		    <c:when test="${not empty sessionScope.user_name}">
		        <span>Welcome, ${sessionScope.user_name}</span>
		    </c:when>
		    <%-- 未ログインの場合 --%>
		    <c:otherwise>
		        <form action="/meal-mate/user/login" method="GET">
		            <input class="botannnoyatu" type="submit" value="Login">
		        </form>
		    </c:otherwise>
		</c:choose>
        <form action="/meal-mate/user/top" method="GET">
        	<input class="botannnoyatu" type="submit" value="🏠">
        </form>
    </div>
</div>
</body>
</html>
