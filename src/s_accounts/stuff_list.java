package s_accounts;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GroupAccounts;
import dao.GroupAccountsDAO;

@WebServlet(urlPatterns={"/staff/staff_list"})
public class stuff_list extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//    	セッションからグループコードを取得する
    	HttpSession session = req.getSession();
    	String g_id = (String) session.getAttribute("g_id");
    	g_id = "101";

//		DAOGroup
		GroupAccountsDAO dao = new GroupAccountsDAO();



		try {
			List<GroupAccounts> staff_accounts_all = dao.list_seachByGid(g_id);
			System.out.println(staff_accounts_all);


			for(GroupAccounts a :  staff_accounts_all){

			    System.out.println("名前");
		    	String name = a.getName();
		    	System.out.println(name);

			    System.out.println("メアド");
		    	String mail = a.getEmail();
		    	System.out.println(mail);

			    System.out.println("最終ログイン");
		    	Date log = a.getLast_login();
		    	System.out.println(log);

			    System.out.println("店長");
		    	boolean ad = a.isIs_admin();
		    	System.out.println(ad);


			}


		    req.setAttribute("staff_list",staff_accounts_all);

			req.getRequestDispatcher("/stuff/stuff_list.jsp").forward(req, resp);


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("データ取得DAOでエラー");
		}



    }

}
