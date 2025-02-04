<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
<c:param name="title">従業員 - 権限</c:param>
    <c:param name="body">


<header>
    <%@ include file="../stuffnav.jsp" %>
</header>

<h1>アクセス権限がありません</h1>
<a href="${pageContext.request.contextPath}/staff/top">Topへ</a>

        	<a href="/meal-mate/staff/top">Topへ</a>

    </c:param>
</c:import>

