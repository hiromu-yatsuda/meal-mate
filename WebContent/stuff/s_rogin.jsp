<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
	<c:import url="/stuffbase.jsp">
	    <c:param name="title">管理 - ログイン</c:param>
	    <c:param name="body">
		<h1>ログイン</h1>
	        <div class="login-page">
	            <form class="form-create" action="/meal-mate/stuff/rogin" method="post">

					<p>${error}</p>

	                <div class="login-form__group login-form__group--mail">
	                    <label class="login-form__label">メールアドレス：</label>
	                    <input class="login-form__input" type="text" name="mail" id="mail" placeholder="メールアドレスを入力してください" required>
	                </div>

	                <div class="login-form__group login-form__group--password">
	                    <label class="login-form__label">パスワード：</label>
	                    <input class="login-form__input" type="text" name="pass" id="pass" placeholder="パスワードを入力してください" required>
	                </div>

	                <button class="login-form__button" type="submit">ログイン</button>
	            </form>
	        </div>

	    </c:param>
	</c:import>
</body>
