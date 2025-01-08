<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - top</c:param>
    <c:param name="body">

        <h1>TOP</h1>



        <form action="/meal-mate/admin/a_create_store_1" method="get">
            <!-- フォームの入力フィールド -->
            <button type="submit">店舗作成</button>
        </form>



        <form action="/meal-mate/admin/a_create_group_1" method="get">
            <!-- フォームの入力フィールド -->
            <button type="submit">グループ作成</button>
        </form>


        <form action="" method="get">
            <!-- フォームの入力フィールド -->
            <button type="submit">グループ・店舗一覧</button>
        </form>


    </c:param>
</c:import>
