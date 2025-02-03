<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <%@ include file="../stuffnav.jsp" %>
</header>

<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員 - 商品登録_完了</c:param>
	<c:param name="body">

<h1>商品登録が完了しました</h1>

<div class="center">
    <form action="/meal-mate/staff/top" method="get">
        	<button type="submit">Topへ</button>
        	</form>
</div>
	</c:param>
</c:import>