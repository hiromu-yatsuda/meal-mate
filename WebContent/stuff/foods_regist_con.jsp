<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員 - 商品登録_確認</c:param>
	<c:param name="body">

<h1>商品登録確認</h1>



<table>
<tr>
<td>商品名</td>
<td>もちもちもっくん</td>
</tr>
<tr>
<td>JANコード</td>
<td>292929292</td>

</tr>


<tr>
<td>原材料</td>
<td>豚</td>
<td>ウシ</td>
<td>パンダ</td>

</tr>
</table>

<table>
<tr>
<td>商品名</td>
<td>もちもちもっくん</td>
</tr>
<tr>
<td>JANコード</td>
<td>292929292</td>

</tr>


<tr>
<td>原材料</td>
<td>豚</td>
<td>ウシ</td>
<td>パンダ</td>

</tr>
</table>

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

    <p>上記の商品を登録しますか？</p>

    <div id="decision">

<div id="confirmed">
        <button type="button" id="confirmed"> 登録 </button>
</div>
<div id="cancel">
        <button type="button" id="cancel"> キャンセル </button>
</div>
</div>



	</c:param>
</c:import>