<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>食材登録</title>
    <link rel="stylesheet" href="/meal-mate/static/register.css">
</head>
<body>
    <header>
        <%@ include file="../userbase.jsp" %>
        <h2>食材登録</h2>
        <div id="selected-icons">
            <!-- 選択されたアイコンがここに表示されます -->
        </div>
        <button id="submit-button">送信</button>
    </header>

    <main>
        <div id="categories">
            <!-- カテゴリーのボタン -->
            <button class="category" id="fruits" data-category="fruits">フルーツ</button>
            <button class="category" id="vegetables" data-category="vegetables">野菜</button>
            <button class="category" id="meats" data-category="meats">肉</button>
            <button class="category" id="seafood" data-category="seafood">魚</button>
            <button class="category" id="dairy" data-category="dairy">乳</button>
            <button class="category" id="sonota" data-category="sonota">その他</button>
            <button class="category" id="vigan" data-category="vigan">ヴィーガン・ヴェジタリアン</button>
        </div>

        <div id="icons">
            <!-- アイコンリスト（カテゴリーに応じて動的に切り替え） -->
        </div>

    </main>

    <script src="/meal-mate/static/register.js"></script>
</body>
</html>
