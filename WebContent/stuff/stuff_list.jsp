<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品一覧</c:param>
    <c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>

        <h1>従業員一覧</h1>
        <div class="form-create" id="stuff_reg">


<table class="staff_list">
<thead>
<tr>
<th>氏名</th>
<th>メールアドレス</th>
<th>最終ログイン日</th>
<th>店長権限</th>
</tr>
</thead>

<c:forEach var="staff" items="${staff_list}">
<thead>
<tr>
<td>${staff.name }</td>
<td>${staff.email }</td>
<td>${staff.last_login }</td>
<td>${staff.is_admin }</td>
</tr>
</thead>
</c:forEach>
</table>

</div>


    </c:param>
</c:import>
