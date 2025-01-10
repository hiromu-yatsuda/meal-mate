package s_accounts;

import java.io.IOException;
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


//		DAOGroup
		GroupAccountsDAO dao = new GroupAccountsDAO();



		try {
			List<GroupAccounts> staff_accounts_all = dao.list_seachByGid(g_id);
			System.out.println(staff_accounts_all);



		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("データ取得DAOでエラー");
		}



    }

}
