package s_foods;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/comp"})
public class s_foods_regist_in extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {













	}

}
