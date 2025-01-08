<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <c:import url="/stuffbase.jsp">
        <c:param name="title">従業員登録</c:param>
        <c:param name="body">
            <h1>従業員作成</h1>

            <form action="/MealMate/stuff/create_stuff_1" method="post">
                <div class="input-group">
                    <div class="form-group">
                        <label for="s_name">名前</label>
                        <input type="text" name="s_name" id="s_name" placeholder="名前を入力してください" required>
                    </div>

                    <div class="form-group">
                        <label for="mail">メールアドレス</label>
                        <input type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" required>
                    </div>
                </div>


            <p>店長権限を付与</p>
            <input type="checkbox" name="manager_permission">



<button type="submit">作成</button>
            </form>


            <div id="decision" class="button-group">

                <button type="button" id="cancel">キャンセル</button>
            </div>
        </c:param>
    </c:import>

