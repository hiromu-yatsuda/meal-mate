<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1" />
<title>${param.title}</title>
<style>
  a {
    font-size: 7em; /* 文字を大きくする */
    display: block; /* ブロック要素に変更 */
    text-align: center; /* 中央揃え */
    margin: 20px auto; /* 上下に余白を追加、横も中央に */
  }
</style>
</head>
<body>
<%@ include file="../userbase.jsp" %>
<div>
<a>食材登録画面</a>
</div>
</body>
</html>
