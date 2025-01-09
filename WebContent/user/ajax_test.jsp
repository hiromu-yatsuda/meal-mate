<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajaxテスト</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<select>
<option value="select1">select1</option>
</select>
<script>
const $classSelect = $("select");
function getAllClass() {
	// selectタグ内のoption要素をすべて削除
	$classSelect.html("");
	$.ajax({
		url: "./all-class",
		type: "GET",
		data: {
			"value": "select",
			"text": "select"
		},
		dataType: "json"
	}).done(function (response) {
		for (let item of response.classes) {
			$classSelect.append("<option value=\"" + item.value + "\">" + item.text + "</option>");
		}
	}).always(function () {
		$classSelect.prepend("<option value=\"\" disabled selected>クラス</option>");
	});
}
</script>
</body>
</html>