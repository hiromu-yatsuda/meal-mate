<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
    <h1>店舗情報変更</h1>


<c:forEach var="store" items="${storesList}">
    <div id="store-change-form" class="store-change-form">
        <p>店舗名</p>
        <p>${store.name}</p>

<form id="upload-form" class="store-change-form" enctype="multipart/form-data">


		<label>公開ボタン</label>

		<%
		Object action1 = pageContext.findAttribute("store").getClass().getMethod("isActive").invoke(pageContext.findAttribute("store"));

		System.out.println(action1);

	    boolean isActive = false;
	    if (action1 != null && action1 instanceof Boolean) {
	        isActive = (Boolean) action1;
	    }

	    if (isActive) {
	        System.out.println("公開");
	        out.println("<input type='checkbox' name='action' id='action' checked>");
	    } else {
	    	System.out.println("非公開");
	        out.println("<input type='checkbox' name='action' id='action'>");
	    }

		%>

		<input type="checkbox" name="action" id="action" checked>
        <input type="checkbox" name="action" id="action" >


        <div class="tel">
            <label for="tel_num">電話番号：</label>
            <input type="text" name="tel_num" id="tel_num" placeholder="電話番号を入力してください" required>
        </div>

        <div class="mail">
            <label for="mail">メールアドレス：</label>
            <input type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" required>
        </div>

        <div class="time">
            <label for="time1">営業時間</label>
            <input type="time" name="time1" id="time1">
            <p>～</p>
            <input type="time" name="time2" id="time2">
        </div>


    <div class="file">

            <input type="file" id="file-input" name="file" accept="image/*" multiple>

        <div id="uploaded-images">
            <!-- 既存の画像を表示 -->

            <!-- データベースから画像を取得して表示するコードをここに追加 -->
        </div>
    </div>
    </div>

    </c:forEach>


    <div id="decision" class="button-group">
        <button type="button" onclick="uploadPhoto()">保存</button>

        </form>

        <button type="button">戻る</button>
	</div>
    </c:param>
</c:import>
