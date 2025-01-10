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
		            <button type="button" id="appu" onclick="location.href=''">CSVファイルをアップロード</button><br>
		            <button type="button" id="sousin" onclick="location.href=''">送信</button>
		        </div>
		
		        <div id="download">
		            <button onclick="location.href=''">テンプレートダウンロード</button>
		
		            <p>例</p>
		            <p>"aa" , "momo" , "mimi"</p>
		            <p>"パンツ鍋" , "" , "〇"</p>
		            <p>"ミミズ" , "〇" , "〇"</p>
		        </div>
			</div>
	    </c:param>
	</c:import>
</body>