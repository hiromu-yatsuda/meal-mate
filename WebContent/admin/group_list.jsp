<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - </c:param>
	<c:param name="body">

<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

<h1>グループ一覧</h1>

<div class="form-create">

<table>
<thead>
<tr>
<th>グループID</th>
<th>グループ名</th>
<th>電話番号</th>
<th>メールアドレス</th>
</tr>
</thead>

<thead>
<c:forEach var="group" items="${groupsList}">
<tr>
    <td>
        <form action="/meal-mate/admin/a_group_list/a_store_list" method="get">
            <input type="hidden" name="groupCode" value="${group.groupCode}" />
            <button type="submit" style="background:none;border:none;color:blue;text-decoration:underline;cursor:pointer;">
                ${group.groupCode}
            </button>
        </form>
    </td>
    <td>${group.phoneNum}</td>
    <td>${group.email}</td>
    <td>${group.name}</td>
</tr>
</c:forEach>
</thead>
</table>




<%-- ページネーションの設定はサーブレットで行う --%>
<%-- ページネーションリンク --%>


<div>
    <c:if test="${current_page > 1}">
        <form action="a_group_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${current_page - 1}" />
            <button type="submit">前へ</button>
        </form>
    </c:if>
    <c:forEach var="i" begin="1" end="${total_page}">
        <form action="a_group_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${i}" />
            <button type="submit">${i}</button>
        </form>
    </c:forEach>
    <c:if test="${current_page < total_page}">
        <form action="a_group_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${current_page + 1}" />
            <button type="submit">次へ</button>
        </form>
    </c:if>
</div>
</div>
		</c:param>
</c:import>