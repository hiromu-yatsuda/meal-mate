<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
    <c:param name="title">従業員 - 店舗情報変更 - 店舗選択</c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

        <h1>店舗選択</h1>



<div class="form-create">

<h3>ID：${groupId}</h3>

<table>
<thead>
<tr>
<th></th>
<th>店舗名</th>
<th>電話番号</th>
<th>開店時間</th>
<th>閉店時間</th>
<th>平均金額（低）</th>
<th>～</th>
<th>平均金額（高）</th>
<th>写真枚数</th>
<th>公開 / 非公開</th>
</tr>
</thead>

<thead>
<c:forEach var="store" items="${storesList}">
<tr>
<td><button type="submit" name="store_id">編集</button></td>
<td>${store.name}店舗名</td>
<td>${store.phoneNum}電話番号</td>
<td>開店時間</td>
<td>閉店時間</td>
<td>平均時間１</td>
<td>～</td>
<td>平均時間２</td>
<td>写真枚数</td>
<td>公開非公開</td>
</tr>
</c:forEach>
</table>



<div>
    <c:if test="${current_page > 1}">
        <form action="/meal-mate/admin/a_group_list/a_store_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${current_page - 1}" />
            <button type="submit">前へ</button>
        </form>
    </c:if>
    <c:forEach var="i" begin="1" end="${total_page}">
        <form action="/meal-mate/admin/a_group_list/a_store_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${i}" />
            <button type="submit">${i}</button>
        </form>
    </c:forEach>
    <c:if test="${current_page < total_page}">
        <form action="/meal-mate/admin/a_group_list/a_store_list" method="get" style="display:inline;">
            <input type="hidden" name="page" value="${current_page + 1}" />
            <button type="submit">次へ</button>
        </form>
    </c:if>
</div>


 </div>
    </c:param>
</c:import>
