<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
    <c:param name="title">従業員 - 店舗情報変更 - 店舗選択</c:param>
    <c:param name="body">
<link rel="stylesheet" href="../static/stuff.css">
<header>
		<%@ include file="../stuffnav.jsp" %>
        <%@ include file="../stuffbase.jsp" %>
    </header>

        <h1>店舗選択</h1>



<div class="form-create">


<table>
<thead>
<tr>
<th></th>
<th>店舗名</th>
<th>電話番号</th>
<th>開店時間</th>
<th>閉店時間</th>
<th>平均金額（低）</th>
<th>～</th>
<th>平均金額（高）</th>
<th>写真枚数</th>
<th>公開 / 非公開</th>
</tr>
</thead>

<thead>
<c:forEach var="store" items="${storesList}">
<tr>


<td><form action="/meal-mate/staff/store/change" method="get">
<input type="hidden" id="group_id" name="group_id" value="${store.group_Code}">
<input type="hidden" id="store_id" name="store_id" value="${store.storeCode}">
<button type="submit" name="store_id">編集</button>
</form>
</td>


<td>${store.name}</td>
<td>${store.phoneNum}</td>
<td>${store.openingTime}</td>
<td>${store.closingTime}</td>
<td>${store.avg_amount_low}</td>
<td>～</td>
<td>${store.avg_amount_high}</td>
<td><%
                Object figure1 = pageContext.findAttribute("store").getClass().getMethod("getFigure1").invoke(pageContext.findAttribute("store"));
				Object figure2 = pageContext.findAttribute("store").getClass().getMethod("getFigure2").invoke(pageContext.findAttribute("store"));
				Object figure3 = pageContext.findAttribute("store").getClass().getMethod("getFigure3").invoke(pageContext.findAttribute("store"));

				int all_fi = 0;
				if (figure1 != null) {
					all_fi = all_fi + 1 ;
                    System.out.println("maaaaa");
                }
				if (figure2 != null) {
					all_fi = all_fi + 1 ;
					System.out.println("2maaaaa");
                }
				if (figure3 != null) {
					all_fi = all_fi + 1 ;
					System.out.println("3maaaaa");
                }
				out.println(all_fi + "枚登録済み");

            %>
</td>
<td>
<%	Object action = pageContext.findAttribute("store").getClass().getMethod("isActive").invoke(pageContext.findAttribute("store"));
	boolean b_action = (Boolean) action;
if(b_action==true){
	out.println("公開");
}else{
	out.println("非公開");
}

%>
</td>
</tr>
</c:forEach>
</table>




 </div>
    </c:param>
</c:import>
