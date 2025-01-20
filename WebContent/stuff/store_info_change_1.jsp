<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="body">

    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
    <h1>店舗情報変更</h1>


<form action="/meal-mate/staff/store/change" method="post" enctype="multipart/form-data">

<div id="upload-form" class="store-change-form" >




<c:forEach var="store" items="${storesList}">
    <div id="store-change-form" class="store-change-form">
        <p>店舗名</p>
        <p>${store.name}</p>






        <input type="hidden" value="${store.storeCode }" name="store_id">




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



        <div class="tel">
            <label for="tel_num">電話番号：</label>
            <input type="text" value="${store.phoneNum}" name="tel_num" id="tel_num" placeholder="電話番号を入力してください" >
        </div>



        <div class="time">
            <label for="time1">営業時間</label>
            <input type="time" value="${store.openingTime}" name="time1" id="time1">
            <p>～</p>
            <input type="time" value="${store.closingTime}" name="time2" id="time2">
        </div>



        <div class="amount
        ">
            <label for="amount">平均利用額</label>
            <input type="text" value="${store.avg_amount_low}" name="amount1" id="amount1">
            <p>～</p>
            <input type="text" value="${store.avg_amount_high}" name="amount2" id="amount2">
        </div>












    <div class="file">

<label for="file1">メイン写真</label>
 	<input type="file" id="file-input00" name="file1[]" accept="image/*" multiple>


 	<div id="file_figure">
 	<div id="preview1"></div>

 	<div id="figure_after">
 	<label>設定中</label>



 	<%
Object fogure1 = pageContext.findAttribute("store").getClass().getMethod("getFigure1").invoke(pageContext.findAttribute("store"));
Object fogure2 = pageContext.findAttribute("store").getClass().getMethod("getFigure2").invoke(pageContext.findAttribute("store"));
Object fogure3 = pageContext.findAttribute("store").getClass().getMethod("getFigure3").invoke(pageContext.findAttribute("store"));




System.out.println("えりんぎ！！！！！！！！");
System.out.println(fogure1);
System.out.println(fogure2);
System.out.println(fogure3);

if (fogure1 != null) {
    out.println("<img id='store_figure' src='" + request.getContextPath() + "/img/shop/" + fogure1 + "' style='max-width: 100px; display: block;'>");
}

if (fogure2 != null) {
    out.println("<img id='store_figure' src='" + request.getContextPath() + "/img/shop/" + fogure2 + "' style='max-width: 100px; display: block;'>");
}

if (fogure3 != null) {
    out.println("<img id='store_figure' src='" + request.getContextPath() + "/img/shop/" + fogure3 + "' style='max-width: 100px; display: block;'>");
}


%>



</div>

</div>
</div>

</c:forEach>

    </div>





    <div id="decision" class="button-group">
        <button type="submit" onclick="uploadPhoto()">保存</button>




        <button type="button">戻る</button>
	</div>



	</form>



<script>


let selectedFiles = [];

document.getElementById('file-input00').addEventListener('change', function(event) {
    handleFileSelect(event, 'preview1', 'file-input00');
});

function handleFileSelect(event, previewId, inputId) {
    const preview = document.getElementById(previewId);
    const input = document.getElementById(inputId);
    const files = Array.from(input.files);

    if (files.length > 3) {
        alert('それぞれ選択できるファイルは3枚です');
        input.value = ''; // 入力をクリア
        return;
    }

    preview.innerHTML = ''; // 既存のプレビューをクリア
    selectedFiles = files; // 選択されたファイルを保存

    files.forEach((file, index) => {
        const reader = new FileReader();
        reader.onload = function(e) {
            const imgContainer = document.createElement('div');
            imgContainer.style.display = 'inline-block';
            imgContainer.style.position = 'relative';
            imgContainer.style.margin = '10px';
            imgContainer.style.textAlign = 'right'; // 右寄せにする

            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.maxWidth = '100px';
            img.style.display = 'block'; // ブロック要素にすることでボタンが下に配置される

            const deleteButton = document.createElement('button');
            deleteButton.textContent = '×';
            deleteButton.style.backgroundColor = 'gray'; // 背景色を灰色に変更
            deleteButton.style.color = 'white';
            deleteButton.style.border = 'none';
            deleteButton.style.cursor = 'pointer';
            deleteButton.style.fontSize = '10px'; // フォントサイズを小さくする
            deleteButton.style.padding = '2px 5px'; // パディングを小さくする
            deleteButton.style.marginTop = '5px'; // 画像との間に余白を追加

            deleteButton.addEventListener('click', function() {
                imgContainer.remove();
                selectedFiles.splice(index, 1); // 選択されたファイルリストから削除
                updateInputFiles(inputId); // input要素のファイルリストを更新
            });

            imgContainer.appendChild(img);
            imgContainer.appendChild(deleteButton);
            preview.appendChild(imgContainer);
        };
        reader.readAsDataURL(file);
    });
}

function updateInputFiles(inputId) {
    const input = document.getElementById(inputId);
    const dataTransfer = new DataTransfer();

    selectedFiles.forEach(file => {
        dataTransfer.items.add(file);
    });

    input.files = dataTransfer.files;
}
</script>



    </c:param>



</c:import>
