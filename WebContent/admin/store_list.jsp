<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
    <c:param name="title">管理 - </c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

        <h1>店舗一覧</h1>
<form class="form-create">
        <%-- グループ名 --%>
        <h3>セブンイレブン</h3>

        <table>
            <tr>
                <td>店舗名</td>
                <td>電話番号</td>
            </tr>
            <tr>
                <td>大宮店</td>
                <td>0334579384</td>
            </tr>
            <tr>
                <td>大宮西口店</td>
                <td>03323479384</td>
            </tr>
        </table>
</form>
        <%-- ページネーションなし --%>

    </c:param>
</c:import>
