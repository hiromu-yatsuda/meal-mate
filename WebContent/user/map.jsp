<%@page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.2/dist/leaflet.css"
     integrity="sha256-sA+zWATbFveLLNqWO2gtiw3HL/lh1giY/Inf1BJ0z14=" crossorigin=""/>
<link rel="stylesheet" href="/meal-mate/static/leaflet.css">
  <script src="https://unpkg.com/leaflet@1.3.0/dist/leaflet.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div id="myMap"></div>


<!-- ホーム画面に戻るボタン -->
<div class="hako">
    <a href="http://localhost:8080/meal-mate/user/top.jsp" id="homeButton">🏠</a>
</div>
<!-- 現在地ボタン -->
<button id="locationButton">
    <img src="https://cdn-icons-png.flaticon.com/512/684/684908.png" alt="現在地に移動" id="locationIcon">
</button>


<script src="https://unpkg.com/leaflet@1.9.2/dist/leaflet.js"
     integrity="sha256-o9N1jGDZrf5tS+Ft4gbIK7mYMipq9lqpVJ91xHSyKhg=" crossorigin=""></script>
<script src="/meal-mate/static/leaflet.js"></script>
</body>
</html>