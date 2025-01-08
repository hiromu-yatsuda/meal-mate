<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員 - 商品一覧</c:param>
	<c:param name="body">


<h1>従業員一覧</h1>



<table>
<tr>
<td>名前</td>
<td>メールアドレス</td>

</tr>
<tr>
<td>笹木ささみ</td>
<td>fdghio@ad.asdg</td>
<td><button id="">削除</button></td>
<tr>
<td>笹木ささぴ</td>
<td>fdghio@ad.asdg</td>
<td><button id="">削除</button></td>
</tr>



</table>




    <c:forEach var="proData" items="${stuff_list}">
        <table border="1">
            <tr>
                <td>名前</td>
                <td>${name}</td>
            </tr>
            <tr>
                <td>メールアドレス</td>
                <td>${mail}</td>
            </tr>

        </table>
        <br/>
    </c:forEach>



		</c:param>
</c:import>