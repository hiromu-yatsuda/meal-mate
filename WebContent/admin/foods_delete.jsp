<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - 商品削除</c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

    <h1>商品削除</h1>

<form action="/meal-mate/admin/foods/delete" method="post">

<div class="user_name">
                <label>JANコード（「,」区切りでよろ）</label>
                <input type="text" name="jan_code" id="jan_code" placeholder="111111111,222222222" required>
            </div>
	<button type="submit"></button>
</form>



    </c:param>
</c:import>