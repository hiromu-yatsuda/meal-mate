<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
	<c:param name="title">従業員 - 商品登録 - 選択</c:param>
	<c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>

	<h1>登録方法を選択してください</h1>

	<div class="csvsenntaku">
		<div class="option">
			<h2>CSV入力</h2>
			<a href="http://localhost:8080/MealMate/stuff/foods_regist_csv.jsp">
				<img class="text_imgcsvsenntaku" src="../img/csv.jpg" alt="CSV入力">
			</a>
		</div>
		<div class="option">
			<h2>手入力</h2>
			<a href="http://localhost:8080/MealMate/stuff/foods_regist.jsp">
				<img class="text_imgcsvsenntaku" src="../img/手入力.jpg" alt="手入力">
			</a>
		</div>
	</div>
	</c:param>
</c:import>
