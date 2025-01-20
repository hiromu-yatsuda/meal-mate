package u_map;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/user/map" })
public class map extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//		StoresDAO dao = new StoresDAO();
		// 店舗情報取得DAO
		try {
//			List<Stores> s_list = dao.all();





			// 表示データリストをjspに
//			req.setAttribute("storesList", s_list);
			req.getRequestDispatcher("/user/map.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

			System.out.print("DAOでエラー");
		}



	}

}