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



<p>${store.figure1}</p>
<p>${store.figure3}</p>
<img src="./../img/shop/${store.figure3}">


<img class="" src="<%= request.getContextPath() %>/img/shop/${store.figure3}">

        <input type="hidden" value="${store.storeCode }" name="id">




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
            <input type="text" name="tel_num" id="tel_num" placeholder="電話番号を入力してください" >
        </div>

        <div class="mail">
            <label for="mail">メールアドレス：</label>
            <input type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" >
        </div>

        <div class="time">
            <label for="time1">営業時間</label>
            <input type="time" name="time1" id="time1">
            <p>～</p>
            <input type="time" name="time2" id="time2">
        </div>


    <div class="file">






<label for="file1">メイン写真</label>
 	<input type="file" id="file-input1" name="file1" accept="image/*" multiple>
 	<div id="preview1"></div>


 	 	<input type="file" id="file-input2" name="file2" accept="image/*" multiple>
 	<div id="preview2"></div>

 	 	<input type="file" id="file-input3" name="file3" accept="image/*" multiple>
<p>aaaa</p>

<p>
 	${store.figure3}
 	</p>
 	<div id="preview3"></div>


    </div>
    </div>

    </c:forEach>


    <div id="decision" class="button-group">
        <button type="submit" onclick="uploadPhoto()">保存</button>




        <button type="button">戻る</button>
	</div>

	</div>
	</form>



<script>
document.getElementById('file-input1').addEventListener('change', function(event) {
    handleFileSelect(event, 'preview1', 'file-input1');
});

document.getElementById('file-input2').addEventListener('change', function(event) {
    handleFileSelect(event, 'preview2', 'file-input2');
});

document.getElementById('file-input3').addEventListener('change', function(event) {
    handleFileSelect(event, 'preview3', 'file-input3');
});

function handleFileSelect(event, previewId, inputId) {
    const preview = document.getElementById(previewId);
    const input = document.getElementById(inputId);
    const files = Array.from(input.files);

    if (files.length > 1) {
        alert('それぞれ選択できるファイルは1枚です');
        input.value = ''; // 入力をクリア
        return;
    }

    preview.innerHTML = ''; // 既存のプレビューをクリア

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
                input.value = ''; // 入力をクリア
            });

            imgContainer.appendChild(img);
            imgContainer.appendChild(deleteButton);
            preview.appendChild(imgContainer);
        };
        reader.readAsDataURL(file);
    });
}
</script>



    </c:param>



</c:import>