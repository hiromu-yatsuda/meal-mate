<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員作成_確認</c:param>
	<c:param name="body">

<h1>使わない</h1>
<h1>従業員作成確認</h1>



<table>
<tr>
<td>名前</td>
<td>笹木ささみ</td>
</tr>
<tr>
<td>メールアドレス</td>
<td>dfgding@dgfom.dfsg</td>

</tr>

</table>

<table>
<tr>
<td>名前</td>
<td>笹木ささぽ</td>
</tr>
<tr>
<td>メールアドレス</td>
<td>dfgding@dgfom.dfsg</td>

</tr>

<tr>
<td>店長権限</td>
</tr>
</table>


    <c:forEach var="proData" items="${stuff_reg}">
        <table border="1">
            <tr>
                <td>名前</td>
                <td>${proName_List}</td>
            </tr>
            <tr>
                <td>メールアドレス</td>
                <td>${jancode}</td>
            </tr>

        </table>
        <br/>
    </c:forEach>

    <p>上記の従業員を作成しますか？</p>

    <div id="decision">

<div id="confirmed">
        <button type="button" id="confirmed"> 作成 </button>
</div>
<div id="cancel">
        <button type="button" id="cancel"> キャンセル </button>
</div>
</div>



	</c:param>
</c:import>