//	グループ作成完了コントローラ


package a_group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/admin/a_create_group_1/regist/comp"})
public class group_create_regist_comp extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {


		req.getRequestDispatcher("/admin/group_create_3.jsp").forward(req, resp);
	}

}
