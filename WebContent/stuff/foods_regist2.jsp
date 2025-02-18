<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase2.jsp">
<c:param name="title">従業員 - 商品登録</c:param>
    <c:param name="body">


<style>

body {
    background: linear-gradient(to bottom,
        #2c3e50 0%,    /* 上の紺色開始 */
        #2c3e50 10%,   /* 上の紺色維持 */
        white 10%,     /* 10% から白へ */
        white 90%,     /* 90% まで白 */
        #2c3e50 90%,   /* 90% から紺色 */
        #2c3e50 100%   /* 下の紺色維持 */
    );
    background-attachment: fixed;
}



#plusButton{
    padding: 0.75rem 1.5rem;
    font-size: 0.8rem;
    font-weight: bold;
    color: white;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}

#decisionButton{
    padding: 0.75rem 1.5rem;
    font-size: 0.8rem;
    font-weight: bold;
    color: white;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
}


</style>


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

