package a_top;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns={"/admin/top"})
public class top extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String a_id = (String) session.getAttribute("a_id");
		System.out.println("セッション");
		System.out.println(a_id);
//
////		セッションがnull　→　ログイン画面へ
//if(a_id == null || a_id.isEmpty()){

//	req.getRequestDispatcher("a_rogin.jsp").forward(req, resp);
//}else{


		String error_reset = null;
//		セッションで行っていた代用者作成用エラーメッセージ
//		をリセットする
		session.setAttribute("store_create_error", error_reset);
		session.setAttribute("error_staff_create_regist", error_reset);
    	req.getRequestDispatcher("/admin/a_top.jsp").forward(req, resp);

//}
    }

}
