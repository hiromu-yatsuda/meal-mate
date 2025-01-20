package s_foods;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/or"})
public class s_foods_regist_or extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {



		req.getRequestDispatcher("/stuff/foods_regist_or.jsp").forward(req, resp);


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

}
