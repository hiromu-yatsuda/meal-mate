<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録</c:param>
    <c:param name="body">

<header>
    <%@ include file="../stuffnav.jsp" %>
</header>

<script>


//従業員-商品登録
//「＋」ボタンクリック -> 入力項目追加
function addChildElement() {
const container = document.querySelector("#container");
const childElement = document.querySelector(".input-group");
const copiedChild = childElement.cloneNode(true);

//複製された要素の入力フィールドをクリア
const inputs = copiedChild.querySelectorAll("input[type='text']");
inputs.forEach(input => input.value = "");

const checkboxes = copiedChild.querySelectorAll("input[type='checkbox']");
checkboxes.forEach(checkbox => checkbox.checked = false);

container.appendChild(copiedChild);
}

document.addEventListener('DOMContentLoaded', function() {
const plusButton = document.querySelector("#addButton");
if (plusButton) {
plusButton.addEventListener("click", function () {
    addChildElement();
});
}
});


let formIndex = 0;

document.getElementById('addButton').addEventListener('click', function() {
formIndex++;
let newForm = document.querySelector('#form-container').cloneNode(true);
newForm.querySelectorAll('input').forEach(input => {
    if (input.name.includes('rest_foods')) {
        input.name = input.name.replace('rest_foods[]', 'rest_foods[' + formIndex + '][]');
    } else {
        input.name = input.name.replace('[]', '[' + formIndex + ']');
    }
});
document.querySelector('#form-in').appendChild(newForm);
});



</script>




<div id="form-in">
    <form action="/meal-mate/stuff/foods/regist/or/manual" method="post">
        <p>※JANコードが存在しない場合は「✓」をいれ、JANコード入力欄には「00」と入力してください。</p>
        <input type="checkbox" name="not_jan">
        <div id="form-container">
            <div id="container">
                <div class="input-group">
                    <div class="form-name">
                        <label>商品名</label>
                        <input type="text" name="pro_name[]" id="pro_name" placeholder="商品名を入力してください" required>
                    </div>
                    <div class="form-jancode">
                        <label>JANコード</label>
                        <input type="text" name="jancode[]" id="no" placeholder="JANコードを入力してください" required>
                    </div>


                    <table>


<c:forEach var="foods" items="${foodsList}" varStatus="status">
    <c:if test="${status.index % 5 == 0}">
        <tr>
    </c:if>
    <td>
        <input type="checkbox" name="rest_foods[0][]" value="${foods.id},${foods.foodName}">
        <input type="hidden" value="${foods.id}" name="rest_foods_id[]">${foods.foodName}
    </td>
    <c:if test="${status.index % 5 == 4 || status.last}">
        </tr>
    </c:if>
</c:forEach>

                    </table>


                    <input type="hidden" value="end,end" name="rest_foods[0][]">
                </div>
            </div>
        </div>
        <button type="button" id="addButton"> + </button>
        <div id="confirmed">
            <button type="submit" id="confirmed"> 決定 </button>
        </div>
    </form>
    <div id="cancel">
        <button type="button" id="cancel"> キャンセル </button>
    </div>
</div>



    </c:param>
</c:import>

