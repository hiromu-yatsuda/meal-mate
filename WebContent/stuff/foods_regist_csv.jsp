<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
	<c:import url="/stuffbase.jsp">
	    <c:param name="title">従業員 - 商品登録_CSV</c:param>
	    <c:param name="body">


	        <header>
	            <%@ include file="../stuffnav.jsp" %>
	        </header>

	        <h1>CSV入力</h1>
			<div class="form-csv">
		        <div id="csv_read">

		        <form action="/meal-mate/stuff/foods/regist/csv" method="post" enctype="multipart/form-data">
		            <button type="button" id="appu" onclick="location.href=''">CSVファイルをアップロード</button>
		            <input type="file" name="file" accept=".csv" required /><br>

		            <button type="submit" id="sousin" onclick="location.href=''">送信</button>
		        </form>
		        </div>

		        <div id="download">


					<a href="${pageContext.request.contextPath}/img/files/テンプレート.csv" download="テンプレート.csv" charset="UTF-8">テンプレートダウンロード(csv)</a>



		            <h5>例</h5>
		            <h5>JANコード,商品名,えび,かに,くるみ,小麦,そば</h5>
		            <h5>8493494,キムチ鍋,えび,小麦</h5>
		            <h5>63830384,ハンバーグ,かに</h5>
		        </div>
			</div>
	    </c:param>
	</c:import>
</body>