package s_store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Stores;
import dao.StoresDAO;

@WebServlet(urlPatterns={"/staff/store/change/store_select"})
public class store_change extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		セッションからグループIDを取得
		HttpSession session = req.getSession();

		String g_id = "";
		g_id = (String) session.getAttribute("g_id");




//		グループID仮置き
		g_id = "001";



		System.out.println(g_id);

    	StoresDAO dao = new StoresDAO();




    	try {

//    		店舗一覧リスト
    		List<Stores> s_list = dao.searchBygId(g_id);

			System.out.println("daoの結果");
			System.out.println(s_list);




//			表示データリスト
			req.setAttribute("storesList",s_list );

			req.getRequestDispatcher("/stuff/store_info_change_0.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("一覧取得DAOでエラー");
		}



    }



}
