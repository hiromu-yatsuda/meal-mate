<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
<%@ include file="../stuffnav.jsp" %>
</header>

<c:import url="/stuffbase.jsp">
<c:param name="title">従業員 - 店舗情報変更 - 完了</c:param>
<c:param name="body">

<h1>変更の保存が完了しました</h1>

<div class="center">
<a href="/meal-mate/staff/top">Topへ</a>


</div>
</c:param>
</c:import>