<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
	<head>
		<meta charset="UTF-8">

		<title>${ param.title }</title>
		<link rel="stylesheet" type="text/css" href="/meal-mate/static/stuff.css">
		<script src="/meal-mate/static/stuff.js"></script>
	</head>
	<body>

		${ param.body }
	</body>
</html>