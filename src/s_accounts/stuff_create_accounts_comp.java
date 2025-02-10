package s_accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/stuff/create_stuff_1/comp"})
public class stuff_create_accounts_comp extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String se_staff_password = (String) session.getAttribute("staff_password");

		String staff_password = ("※今回のみ表示されますpassword:" + se_staff_password);
		System.out.println("リダイレクト成功");

		req.setAttribute("staff_password",staff_password );

		String reset = null;
		session.setAttribute("staff_password",reset );
		req.getRequestDispatcher("/stuff/create_stuff_3.jsp")
		.forward(req, resp);
	}

}
