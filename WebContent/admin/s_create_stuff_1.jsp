<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代表者登録</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- ナビゲーションを最上部に配置 -->
    <header>
        <%@ include file="../adminnav.jsp" %>
    </header>
    <c:import url="/adminbase.jsp">
        <c:param name="title">代表者登録</c:param>
        <c:param name="body">
            <h1>代表者作成</h1>

            <form class="form-create" id="stuff_reg" action="/meal-mate/admin/stuff/create_stuff_1" method="post">
                <div class="input-group">
                    <div class="form-create-group">
                        <label for="s_name">名前</label>
                        <input class="input-create" type="text" name="s_name" id="s_name" placeholder="名前を入力してください" required>
                    </div>

                    <div class="form-create-group">
                        <label for="mail">メールアドレス</label>
                        <input class="input-create" type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" required>
                    </div>

                    <div class="form-create-group">
                        <label for="manager_permission">
                            店長権限を付与
                            <input type="checkbox" name="manager_permission" id="manager_permission" style="margin-left: 10px;">
                        </label>
                    </div>


			<div class="groupList">
				<label for="guroups_list">グループ</label>
				<select id="groups_list" name="groups_list">
					<c:forEach var="group" items="${groupsList}">
						<option value="${group.groupCode}">${group.groupCode}：${group.name}</option>
					</c:forEach>
				</select>
			</div>


                </div>
                <div id="decision" class="button-group">
                <button type="submit">作成</button>
            </form>

                <button  onclick="history.back()" id="cancel">キャンセル</button>
            </div>
        </c:param>
    </c:import>
</body>
</html>
