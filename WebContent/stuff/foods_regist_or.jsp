<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員 - 商品登録 - 選択</c:param>
	<c:param name="body">
	<h1>登録方法を選択してください</h1>

				<button onclick="location.href=''">入力</button>
				<button onclick="location.href=''">CSV</button>

	</c:param>
</c:import>