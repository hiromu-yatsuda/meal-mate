<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- バーコード印刷用に、後ほど大幅修正あり --%>

<c:import url="/adminbase.jsp">
	<c:param name="title">管理 - </c:param>
	<c:param name="body">

<link rel="stylesheet" href="../static/admin.css">
<header>
        <%@ include file="../adminnav.jsp" %>
    </header>

<h1>グループ一覧</h1>


<form class="form-create">
<table>
<thead>
<tr>
<th>グループID</th>
<th>グループ名</th>
<th>電話番号</th>
<th>メールアドレス</th>
</tr>
</thead>

<thead>
<c:forEach var="group" items="${groupsList}">
<tr>
<td>
	<form action="" method="get">
		<input type="hidden" name="groupCode" value="${group.groupCode}" />
        	<button type="submit" style="background:none;border:none;color:blue;text-decoration:underline;cursor:pointer;">
            	${group.groupCode}
    	</button>
    </form>
</td>
<td>${group.phoneNum}</td>
<td>${group.email}</td>
<td>${group.name}</td>
</tr>
</c:forEach>
</thead>
</table>








 <%-- 例 --%>
 <a href="">前へ</a>
  <a href="">1</a>
   <a href="">2</a>
 <a href="">次へ</a>
 <%-- 例 --%>




<%-- ページネーションの設定はサーブレットで行う --%>
<%-- ページネーションリンク --%>

<div>
    <c:if test="${currentPage > 1}">
        <a href="a_group_list?page=${currentPage - 1}">前へ</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="a_group_list?page=${i}">${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="a_group_list?page=${currentPage + 1}">次へ</a>
    </c:if>
</div>
</form>
		</c:param>
</c:import>