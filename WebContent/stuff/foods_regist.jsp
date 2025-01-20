<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録</c:param>
    <c:param name="body">

<header>
    <%@ include file="../stuffnav.jsp" %>
</header>






<div id="form-in">
    <form action="/meal-mate/stuff/foods/regist/or/manual" method="post">
        <p>※JANコードが存在しない場合は「✓」をいれてください</p>
        <input type="checkbox" name="not_jan">
        <div id="form-container">
            <div id="container">
                <div class="input-group">
                    <div class="form-name">
                        <label>商品名</label>
                        <input type="text" name="pro_name[]" id="pro_name" placeholder="商品名を入力してください" required>
                    </div>
                    <div class="form-jancode">
                        <label>JANコード</label>
                        <input type="text" name="jancode[]" id="no" placeholder="JANコードを入力してください" required>
                    </div>


                    <table>

                    	<tr>
                    		<td><input type="checkbox" name="not_foods[]"><strong>該当なし</strong></td>


                    	</tr>

<c:forEach var="foods" items="${foodsList}" varStatus="status">
    <c:if test="${status.index % 5 == 0}">
        <tr>
    </c:if>
    <td>
        <input type="checkbox" name="rest_foods[0][]" value="${foods.id}">
        <input type="hidden" value="${foods.id}" name="rest_foods_id[]">${foods.foodName}
    </td>
    <c:if test="${status.index % 5 == 4 || status.last}">
        </tr>
    </c:if>
</c:forEach>
<input type="hidden" value="end" name="rest_foods[0][]">
                    </table>
                </div>
            </div>
        </div>
        <button type="button" id="addButton"> + </button>
        <div id="confirmed">
            <button type="submit" id="confirmed"> 決定 </button>
        </div>
    </form>
    <div id="cancel">
        <button type="button" id="cancel"> キャンセル </button>
    </div>
</div>



    </c:param>
</c:import>

