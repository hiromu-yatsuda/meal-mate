<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>TOP</title>
    <!-- CSSをリンク -->
    <link rel="stylesheet" href="../static/stuff.css">
</head>
<body>
    <!-- ナビゲーションを最上部に配置 -->
    <header>
        <%@ include file="../stuffnav.jsp" %>
        <h1>従業員メニュー</h1>
    </header>

    <!-- メインコンテンツ -->
    <main>
        <c:import url="/stuffbase.jsp">
            <c:param name="title">TOP</c:param>
            <c:param name="body">
				<form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="food">商品一覧</button>
				</form>
				<form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="food">店舗作成</button>
				</form><form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="store">店舗作成</button>
				</form><form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="stuff">店舗作成</button>
				</form><form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="stuff">店舗作成</button>
				</form>
            </c:param>
        </c:import>
    </main>
</body>
</html>
