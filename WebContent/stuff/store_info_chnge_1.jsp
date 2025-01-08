<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="body">


    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
        <h1>店舗情報変更</h1>

        <form id="store_change">

            <p>店舗名：</p>
            <p>山田うどん</p>

            <div class="tel">
                <label>電話番号：</label>
                <input type="text" name="tel_num" id="tel_num" placeholder="電話番号を入力してください" required>
            </div>

            <div class="mail">
                <label>メールアドレス：</label>
                <input type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" required>
            </div>

            <div class="time">
                <label>営業時間</label>
                <input type="time" name="time1" id="time1">
                <p>～</p>
                <input type="time" name="time2" id="time2">
            </div>

        </form>

        <div class="file">
            <form id="uploadForm" enctype="multipart/form-data">
                <input type="file" id="fileInput" name="file" accept="image/*" multiple>
            </form>
            <div id="uploadedImages">
                <!-- 既存の画像を表示 -->

                <!-- データベースから画像を取得して表示するコードをここに追加 -->

            </div>
        </div>

        <%-- 写真アップロード用判定js付き --%>
        <div class="button-group">
	        <button type="button" onclick="uploadPhoto()">保存</button>

	        <button type="button">戻る</button>
		</div>
    </c:param>
</c:import>
