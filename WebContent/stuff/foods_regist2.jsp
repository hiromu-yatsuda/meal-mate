<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
<c:param name="title">従業員 - 商品登録</c:param>
    <c:param name="body">


<header>
    <%@ include file="../stuffnav.jsp" %>
</header>

※JANコードが存在しない場合は「✓」をいれ、JANコード入力欄には「00」と入力してください
<div id="container"></div>
<div id="buttonContainer">
    <input type="button" id="plusButton" value="+" /><br />
    <input type="button" id="decisionButton" value="決定" /><br />
    <input type="button" id="cancelButton" value="キャンセル" /><br />
</div>

    <script src="//code.jquery.com/jquery-3.6.1.min.js"></script>

    <script src="/meal-mate/static/regist2.js"></script>
    </c:param>
</c:import>

