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

//    	現在のページの取得用
    	int get_page = 1;

//    	次のページ送信用
    	int current_page = 1;

    	if (req.getParameter("page") != null){


//        	次のページ
        	get_page = Integer.parseInt(req.getParameter("page"));

//        	if ( get_page > 0 ) {
                current_page = get_page;
//            }

    	}

    	System.out.println(current_page);



    	GroupsDAO dao = new GroupsDAO();



    	try {



//    		取得データの選別
//    		current_page
//    		取得件数
    		int num_dao = 20;

//    		どこから
    		int start_dao = 0;


    		start_dao = (current_page - 1) * num_dao;



//    		20データずつのグループ一覧を取得
    		List<Groups> g_list = dao.all_20(num_dao, start_dao);

//    		全てのデータ（ページネーション用）
    		List<Groups> g_list_all = dao.all();


			System.out.println(g_list_all);
			System.out.println(g_list_all.size());

    		System.out.println(g_list);
			System.out.println(g_list.size());


//			List<String> a2_list = new ArrayList<>();
//			List<Groups> aa = new ArrayList<Groups>();




//			ページネーションの設定
//			ページ数カウント
			int g_list_page = 0;

//			全データ数
			int g_list_size = g_list_all.size();


			int page_count = g_list_size / 20;
			System.out.println(page_count);

			int page_count_rem = g_list_size % 20;
			System.out.println(page_count_rem);

			if(page_count_rem > 0){
				g_list_page = page_count + 1;

			}else{
				g_list_page = page_count;
			}







//			現在のページ
			req.setAttribute("current_page", current_page);

//			全ページ数
			req.setAttribute("total_page", g_list_page);

//			表示データリスト

			req.setAttribute("groupsList",g_list );
//			req.setAttribute("groupsList", g_list);
			req.getRequestDispatcher("/admin/group_list.jsp").forward(req, resp);











    	} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("グループ一覧取得DAOでエラー");
			req.getRequestDispatcher("/admin/a_top.jsp").forward(req, resp);
		}






    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
