package s_accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/stuff/logout"})
public class logout extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {

		String se_null = null;


    	HttpSession session = req.getSession();
//	    名前
		session.setAttribute("s_name", se_null);

//	    グループID
		session.setAttribute("s_g_id", se_null);

//		店長権限
		session.setAttribute("s_action", se_null);


		req.getRequestDispatcher("/stuff/s_rogin.jsp").forward(req, resp);

	}



}
