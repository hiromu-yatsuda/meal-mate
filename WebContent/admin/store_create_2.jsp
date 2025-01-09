<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - 店舗登録_確認</c:param>
	<c:param name="body">
<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

<h1>店舗登録確認</h1>

<form class="form-create" action="/meal-mate/admin/a_create_store_1/regist" method="post">
	<table>
		<tr>
			<td>店舗名</td>
			<td><input type="hidden" name="sto_name" value="${sto_name}">${sto_name}</td>
		</tr>
		<tr>
			<td>電話番号</td>
			<td><input type="hidden" name="sto_tel" value="${sto_tel}">${sto_tel}</td>
		</tr>
		<tr>
			<td>メールアドレス</td>
			<td><input type="hidden" name="sto_mail" value="${sto_mail}">${sto_mail}</td>
		</tr>
		<tr>
			<td>住所</td>
			<td><input type="hidden" name="sto_address" value="${sto_address}">${sto_address}</td>
		</tr>
		<tr>
			<td>グループ名</td>
			<td><input type="hidden" name="gru_id" value="${gru_id}">${gru_st}</td>
		</tr>
	</table>

	<p>上記の店舗を作成しますか？</p>
	<button type="submit">登録</button>


<div id="decision">
	<div id="cancel">
		<button type="button" id="cancel"> キャンセル </button>
	</div>
</div>
</form>
	</c:param>
</c:import>
