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

        <h1>店舗一覧</h1>



<div class="form-create">

<h3><%= request.getAttribute("g_id") %></h3>

<table>
<thead>
<tr>
<th>店舗名</th>
<th>電話番号</th>
</tr>
</thead>

<thead>
<c:forEach var="store" items="${storesList}">
<tr>
<td>${store.name}</td>
<td>${store.phoneNum}</td>
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
