<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - グループ作成_確認</c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

        <h1>グループ作成確認</h1>

<div class="form-create">


        <table>
            <tr>
                <td>グループ名</td>
                <td><%= request.getAttribute("name") %></td>
            </tr>
            <tr>
                <td>電話番号</td>
                <td><%= request.getAttribute("tel") %></td>
            </tr>
            <tr>
                <td>メールアドレス</td>
                <td><%= request.getAttribute("mail") %></td>
            </tr>
        </table>

        <p>上記のグループを作成しますか？</p>

        <div id="decision">
            <form action="/meal-mate/admin/a_create_group_1/regist" method="post">
                <div id="confirmed">
                    <button type="submit"> 作成 </button>
                </div>
            </form>

            <div id="cancel">
                <button type="button" id="cancel"> キャンセル </button>
            </div>
        </div>
	</div>	

    </c:param>
</c:import>
