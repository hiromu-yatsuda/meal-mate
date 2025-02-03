package a_accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/admin/stuff/create_stuff_1/comp"})
public class stuff_create_accounts_comp extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {


		System.out.println("リダイレクト成功");



		req.getRequestDispatcher("/admin/s_create_stuff_3.jsp")
		.forward(req, resp);
	}

}
