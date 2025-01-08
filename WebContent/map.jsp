<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>現在地マップ</title>
    <link rel="stylesheet" href="map.css">
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBst_2c0DVcw9uXT4rX_lPjwYNTiToYJk4"></script>
    <script src="script.js" defer></script>
</head>
<body>
    <h1>現在地マップ</h1>
    <div id="map"></div>
    <div id="info">
        <button id="locateBtn">現在地を取得</button>
    </div>
</body>
</html>
