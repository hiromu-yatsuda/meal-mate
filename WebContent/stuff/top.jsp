<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
	<c:import url="/stuffbase.jsp">
	    <c:param name="title">従業員 - TOP</c:param>
	    <c:param name="body">

<h1>従業員 - TOP</h1>

    <!-- メインコンテンツ -->
    <main>



				<form action="/meal-mate/stuff/foods/list" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="food">商品一覧</button>
				</form>
				<form action="/meal-mate/stuff/foods/regist/or" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="food">商品登録</button>
				</form>


				<form action="/meal-mate/staff/store/change/store_select" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="store">店舗情報変更</button>
				</form>



				<form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="stuff">従業員一覧</button>
				</form><form action="/meal-mate/stuff/create_stuff_1" method="get">
				    <!-- フォームの入力フィールド -->
				    <button class="button-top" type="submit"id="stuff">従業員作成</button>
				</form>


				</main>
            </c:param>
        </c:import>


