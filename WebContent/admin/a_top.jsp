<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者-TOP</title>
    <!-- CSSをリンク -->
    <link rel="stylesheet" href="../static/admin.css">
</head>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - top</c:param>
    <c:param name="body">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>
        <h1>管理者 - TOP</h1>

 <div class="button-container">
            <div class="form-container">
                <form action="/meal-mate/admin/a_create_store_1" method="get">
                    <button class="button-top" type="submit" id="store">店舗登録</button>
                </form>

                <form action="/meal-mate/admin/a_create_group_1" method="get">
                    <button class="button-top" type="submit" id="group">グループ作成</button>
                </form>

                <form action="/meal-mate/admin/a_group_list" method="get">
                    <button class="button-top" type="submit" id="itiran">グループ・店舗一覧</button>
                </form>
            </div>
        </div>

    </c:param>
</c:import>