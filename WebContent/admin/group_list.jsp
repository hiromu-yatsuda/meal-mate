<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - </c:param>
	<c:param name="body">


<h1>グループ一覧</h1>



<table>
<tr>
<td>グループID</td>
<td>グループ名</td>
<td>電話番号</td>
<td>メールアドレス</td>
</tr>
<tr>
<td><a href="">239</a></td>
<td>セブンイレブン</td>
<td>0334579384</td>
<td>afidgui@safd.fgs</td>

</tr>

<tr>
<td><a href="">454</a></td>
<td>みみ青果</td>
<td>03323479384</td>
<td>dsfgi@safd.fgs</td>

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



 <%-- 例 --%>
 <a href="">前へ</a>
  <a href="">1</a>
   <a href="">2</a>
 <a href="">次へ</a>
 <%-- 例 --%>




<%-- ページネーションの設定はサーブレットで行う --%>
<%-- ページネーションリンク --%>
<div>
    <c:if test="${currentPage > 1}">
        <a href="GroupListServlet?page=${currentPage - 1}">前へ</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="GroupListServlet?page=${i}">${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="GroupListServlet?page=${currentPage + 1}">次へ</a>
    </c:if>
</div>


		</c:param>
</c:import>