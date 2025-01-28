package s_foods;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.CommonServlet;


//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/list"})
public class s_foods_list extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    	HttpSession session = req.getSession();


	    String gru_id = (String) session.getAttribute("s_g_id");
	    gru_id = "101"; // グループIDの仮置き







	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

}
