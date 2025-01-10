<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録</c:param>
    <c:param name="body">

<header>
    <%@ include file="../stuffnav.jsp" %>
</header>


<div id="form-in">
	<div id="form-container">
    	<form id="foods_reg">
        	<div id="cont5ainer">
	        	<div class="input-group">
		            <div class="form-name">
		                <label>商品名</label>
		                <input type="text" name="pro_name" id="pro_name" placeholder="商品名を入力してください" required>
		            </div>
		            <div class="form-jancode">
		                <label>JANコード</label>
		                <input type="text" name="jancode" id="no" placeholder="JANコードを入力してください" required>
		            </div>
		            <table>
		                <tr>
		                    <td>豚</td>
		                    <td>牛</td>
		                    <td>魚</td>
		                </tr>
		                <tr>
		                    <td><input type="checkbox" name="1"></td>
		                    <td><input type="checkbox" name="2"></td>
		                    <td><input type="checkbox" name="3"></td>
		                </tr>
		            </table>
	        </div>
        </div>
    </form>
</div>


<button type="button" id="addButton"> + </button>


<div id="decision">
    <div id="confirmed">
        <button type="button" id="confirmed"> 決定 </button>
    </div>
    <div id="cancel">
        <button type="button" id="cancel"> キャンセル </button>
    </div>
</div>

</div>

    </c:param>
</c:import>