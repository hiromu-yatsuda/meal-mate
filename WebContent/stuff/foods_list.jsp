<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品一覧</c:param>
    <c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>

    <h1>商品一覧</h1>
	<form class="form-create" id="stuff_reg">



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

	</form>
    </c:param>
</c:import>
