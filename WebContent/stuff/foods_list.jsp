<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品一覧</c:param>
    <c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>

    <h1>商品一覧</h1>
	<div class="form-create" id="stuff_reg">




<table class="pro_list">
<thead>
<tr>
<th>JANコード</th>
<th>商品名</th>
<th>使用食材</th>
</tr>
</thead>
        <c:forEach var="product" items="${product_list}">
<thead>
<tr>

<td>${product.jancode }</td>
<td>${product.pro_name }</td>
<td>${product.foods_name }</td>

</tr>
</thead>
        </c:forEach>
</table>



	</div>
    </c:param>
</c:import>
