<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - アカウント作成</c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>

<h1>アカウント作成</h1>

        <form class="form-create" action="/meal-mate/admin/create_accounts_1" method="post">

            <div class="id">
                <label>ID：</label>
                <input type="text" name="id" id="id" placeholder="IDを入力してください" required>
            </div>

            <!--
            <div class="password">
                <label>パスワード：</label>
                <input type="text" name="pass" id="pass" placeholder="パスワードを入力してください" required>
            </div>
            -->

            <p>アカウント作成</p>
            <button type="submit">登録</button>
        </form>

    </c:param>
</c:import>
