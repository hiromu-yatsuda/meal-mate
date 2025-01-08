package a_group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groups;
import dao.GroupsDAO;

@WebServlet(urlPatterns={"/admin/a_group_list"})
public class group_list extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	GroupsDAO dao = new GroupsDAO();

//		List<Groups> g_list = null;

    	try {

//    		グループ一覧を取得
    		List<Groups> g_list = dao.all();
			System.out.println(g_list);

			req.setAttribute("groupsList", g_list);
			req.getRequestDispatcher("group_list.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("グループ一覧取得DAOでエラー");
			req.getRequestDispatcher("a_top.jsp").forward(req, resp);
		}




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
