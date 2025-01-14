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

@WebServlet(urlPatterns={"/staff/store/change"})
public class store_change_in extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//		セッションからグループIDを取得
		HttpSession session = req.getSession();

		String se_g_id = "";
		se_g_id = (String) session.getAttribute("g_id");


		System.out.println(se_g_id);


//		グループID仮置き
		se_g_id = "001";
		System.out.println("仮置き後");
		System.out.println(se_g_id);



//		jspからグループIDを取得
		String jsp_gru_id = req.getParameter("group_id");
		System.out.println(jsp_gru_id);


//		jspから店舗IDを取得
		String jsp_store_id = req.getParameter("store_id");
		System.out.println("店舗ID");
		System.out.println(jsp_store_id);


		if(se_g_id.equals(jsp_gru_id)){


	    	StoresDAO dao = new StoresDAO();


try{


//	    		店舗情報取得DAO
	    		List<Stores> s_list = dao.searchBysId(jsp_store_id);

				System.out.println("daoの結果");
				System.out.println(s_list);




//				表示データリスト
				req.setAttribute("storesList",s_list );



				req.getRequestDispatcher("/stuff/store_info_change_1.jsp").forward(req, resp);


} catch (Exception e) {
	// TODO 自動生成された catch ブロック
	e.printStackTrace();
	System.out.println("店舗情報取得DAOでエラー");
}








		}else{
			System.out.println("URLからの偽造確認");
		}






    }

}
