<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
        padding: 20px;
    }
    
    .hako {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 100%;
    }
    
    .hako a {
        font-size: 4.5rem; /* アイコンサイズ */
        text-decoration: none;
        color: #333;
        padding: 10px 20px;
        border: 2px solid #d3d3d3; /* 枠線 */
        border-radius: 8px; /* 角を丸くする */
        background-color: #f9f9f9; /* 背景色 */
        transition: transform 0.3s, color 0.3s, background-color 0.3s;
    }

    .hako a:hover {
        transform: scale(1.2);
        color: #007bff;
        background-color: #e0e0e0; /* ホバー時の背景色 */
        border-color: #007bff; /* ホバー時の枠線色 */
    }
</style>
</head>
<body>
<div class="nav">
    <div class="hako">
        <a href="javascript:void(0)" onclick="history.back()">🔙</a>
        <a href="http://localhost:8080/meal-mate/auth/login.jsp">Login</a>
        <a href="http://localhost:8080/meal-mate/user/top.jsp">🏠</a>
    </div>
</div>
</body>
</html>
