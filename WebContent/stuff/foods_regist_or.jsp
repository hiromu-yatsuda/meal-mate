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

				<form class="auto_form" action="/meal-mate/stuff/foods/regist/csv" method="get">
					<button class="auto" type="submit">
						<img class="text_imgcsvsenntaku" src="${pageContext.request.contextPath}/img/csv.jpg" alt="CSV入力">
					</button>
				</form>
		</div>
		<div class="option">
			<h2>手入力</h2>
			<form class="manual_form" action="/meal-mate/stuff/foods/regist/or/manual" method="get">

			<button class="manual" type="submit">
			<img class="text_imgcsvsenntaku" src="${pageContext.request.contextPath}/img/手入力.jpg" alt="手入力">
			</button>
			</form>
		</div>
	</div>





	</c:param>
</c:import>
