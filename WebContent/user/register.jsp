<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingredients Register</title>
    <link rel="stylesheet" href="/meal-mate/static/register.css">
</head>
<body>
    <header>
        <%@ include file="../userbase.jsp" %>
        <h2>Ingredients Register</h2>
        <div id="selected-icons">
            <!-- 選択されたアイコンがここに表示されます -->
        </div>
        <button id="submit-button">Send</button>
    </header>

    <main>
        <div id="categories">
            <!-- カテゴリーのボタン -->
            <button class="category" id="fruits" data-category="fruits">Fruit</button>
            <button class="category" id="vegetables" data-category="vegetables">Vegetables</button>
            <button class="category" id="meats" data-category="meats">Meat</button>
            <button class="category" id="seafood" data-category="seafood">Fish</button>
            <button class="category" id="dairy" data-category="dairy">Dairy Products</button>
            <button class="category" id="sonota" data-category="sonota">Others</button>
            <button class="category" id="vigan" data-category="vigan">Vegan・Vegetarian</button>
        </div>

        <div id="icons">
            <!-- アイコンリスト（カテゴリーに応じて動的に切り替え） -->
        </div>

    </main>

    <script src="/meal-mate/static/register.js"></script>
</body>
</html>
