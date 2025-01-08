<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - 店舗登録</c:param>
	<c:param name="body">


	<h1>店舗登録</h1>


	<form action="/MealMate/admin/a_create_store_1" method="post">
	<div class="name">
	<label>店舗名：</label>
    <input type="text" name="name" id="name" placeholder="店舗名を入力してください"  required>
</div>

<div class="tel">
	<label>電話番号：</label>
    <input type="text" name="tel_num" id="tel_num" placeholder="電話番号を入力してください"  required>
</div>


<div class="mail">
	<label>メールアドレス：</label>
    <input type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください"  required>
</div>

<div class="address">
	<label>住所：</label>
    <input type="text" name="address" id="address" placeholder="住所を入力してください"  required>
</div>

<div class="groupList">

	<label for="guroups_list">グループ</label>
<select id="groups_list" name="groups_list">
    <c:forEach var="group" items="${groupsList}">
        <option value="${group.groupCode},${group.name}">${group.groupCode}：${group.name}</option>
    </c:forEach>
</select>
</div>

<button type="submit">登録</button>
</form>

<button onclick="location.href=''">登録</button>
<button onclick="location.href=''">キャンセル</button>

	</c:param>
</c:import>