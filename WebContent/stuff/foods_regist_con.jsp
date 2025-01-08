<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="body">

        <header>
            <%@ include file="../stuffnav.jsp" %>
        </header>

        <h1>商品登録確認</h1>
        <form class="form-create" id="stuff_reg">

            <!-- 固定の商品情報 -->
            <table border="1">
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

            <br/>

            <table border="1">
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

            <br/>

            <!-- 動的な商品情報 -->
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

            <!-- ボタン -->
            <div id="decision" class="button-group">
                <button type="button" id="confirmed">作成</button>
                <button type="button" id="cancel">キャンセル</button>
            </div>

        </form>

    </c:param>
</c:import>
