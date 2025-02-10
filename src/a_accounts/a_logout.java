package a_accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/admin/logout"})
public class a_logout extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {


		String se_null = null;


    	HttpSession session = req.getSession();

//		ログイン成功→アカウント名セッションへ
		session.setAttribute("a_id", se_null);

		req.getRequestDispatcher("/admin/a_rogin.jsp").forward(req, resp);


	}

}
