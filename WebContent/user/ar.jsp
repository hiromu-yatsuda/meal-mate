<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>QuaggaJS</title>
<link rel="stylesheet" href="/meal-mate/static/ar.css" />
<style>
    html, body {
        margin: 0;
        padding: 0;
        height: 100%; /* 全体の高さを固定 */
        overflow: hidden; /* スクロールを無効化 */
    }

    #my_quagga {
        position: relative;
        width: 98%;
        height: calc(100vw * 9 / 16 * 4); /* 16:9 の比率を基に高さを調整 */
        max-height: 90vh; /* 必要に応じて画面全体に収まるよう制限 */
        background-color: #000; /* 黒背景を設定して見やすくする */
    }

    #console {
        margin-top: 10px;
    }
</style>
</head>
<body>
    <%@ include file="../userbase.jsp" %>
<div id="my_quagga"></div>
<div id="console"></div>
<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="//unpkg.com/@ericblade/quagga2@1.7.4/dist/quagga.min.js"></script>
<script src="//cdn.jsdelivr.net/gh/mtaketani113/jquery-barcode@master/jquery-barcode.js"></script>
<script src="/meal-mate/static/ar.js"></script>
<script>

</script>
</body>
</html>
