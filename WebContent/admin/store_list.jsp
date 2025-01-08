<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - </c:param>
	<c:param name="body">


<h1>店舗一覧</h1>

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



<%--
    <c:forEach var="proData" items="${jancode}">
        <table border="1">
            <tr>
                <td>商品名</td>
                <td>${proName_List}</td>
            </tr>
            <tr>
                <td>JANコード</td>
                <td>${jancode}</td>
            </tr>
            <tr>
                <td>原材料</td>
                <td>
                    <c:forEach var="item" items="${foods}">
                        ${item}<br/>
                    </c:forEach>
                </td>
            </tr>
        </table>
        <br/>
    </c:forEach>


 --%>



 <%-- ページネーションなし --%>



		</c:param>
</c:import>