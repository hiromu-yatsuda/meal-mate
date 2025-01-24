<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
    <header>
        <%@ include file="../stuffnav.jsp" %>
    </header>
	<c:import url="/stuffbase.jsp">
	    <c:param name="title">user - rogin</c:param>
	    <c:param name="body">
		<h1>ROGIN</h1>
	        <div class="login-page">
	            <form class="form-create" action="/meal-mate/user/rogin" method="post">

	                <div class="email">
				<label>EMAIL：</label>
    			<input type="text" name="email" id="email" placeholder="Email address"  required>
			</div>

	                <div class="password">
				<label>PASSWORD：</label>
    			<input type="text" name="pass" id="pass" placeholder="password"  required>
			</div>

	                <button class="login-form__button" type="submit">rogin</button>
	            </form>
	        </div>

	    </c:param>
	</c:import>
</body>
