package a_store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Stores;
import dao.StoresDAO;

@WebServlet(urlPatterns={"/admin/a_group_list/a_store_list"})
public class store_list extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//    	グループコードを取得
    	String g_id = req.getParameter("g_id");

    	g_id = "146";
    	System.out.println(g_id);

    	StoresDAO dao = new StoresDAO();


    	try {

    		List<Stores> s_list = dao.searchBygId(g_id);
    		System.out.println("daoの結果");
    		System.out.println(s_list);

    		req.getRequestDispatcher("group_list.jsp").forward(req, resp);

    	} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("グループID検索DAOでエラー");

			req.getRequestDispatcher("a_top.jsp").forward(req, resp);

    	}



    }

}
