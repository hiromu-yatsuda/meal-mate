<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page session="true" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="link">/meal-mate/static/regist_confirm.css</c:param>
    <c:param name="body">

        <header>
            <%@ include file="../stuffnav.jsp" %>
        </header>

<!--
<%
       	Object jsonArray = session.getAttribute("json");

       	if (jsonArray != null) {
       	    session.removeAttribute("json");
       	} else {
       	    jsonArray = "no data";
       	}
%>
<div id="hidden" data-json='<%= jsonArray %>' hidden></div>
-->


        <h1>商品登録確認</h1>
        <div id="container"></div>
        <div id="buttons">
        	<input type="button" id="decide" value="登録">
        	<input type="button" id="cancel" value="キャンセル">
        </div>

		<script src="/meal-mate/static/regist_confirm.js"></script>
    </c:param>
</c:import>