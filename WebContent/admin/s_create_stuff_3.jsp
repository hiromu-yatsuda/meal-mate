<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<header>
    <%@ include file="../adminnav.jsp" %>
</header>

<c:import url="/adminbase.jsp">
	<c:param name="title">代表者 - 登録完了</c:param>
	<c:param name="body">

<h1>代表者作成が完了しました</h1>
<div class="center">
        	<a href="/meal-mate/admin/top">Topへ</a>
		</div>

	</c:param>
</c:import>