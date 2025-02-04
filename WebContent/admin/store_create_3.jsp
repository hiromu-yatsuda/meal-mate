<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - 店舗作成_完了</c:param>
    <c:param name="body">
    <link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

        <h1>店舗作成が完了しました</h1>
        <div class="center">
        	<a href="/meal-mate/admin/top">Topへ</a>
		</div>
    </c:param>
</c:import>
