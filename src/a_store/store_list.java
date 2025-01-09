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
    	String g_id = req.getParameter("groupCode");

    	System.out.println("グループコード１");
    	System.out.println(g_id);


//    	現在のページの取得用
    	int get_page = 1;

//    	次のページ送信用
    	int current_page = 1;

    	if (req.getParameter("page") != null){


//        	次のページ
        	get_page = Integer.parseInt(req.getParameter("page"));


                current_page = get_page;

    	}

    	System.out.println(current_page);



//    	仮置き
    	g_id = "146";
    	System.out.println(g_id);





    	StoresDAO dao = new StoresDAO();


    	try {


//    		データの選別
//    		取得件数
    		int num_dao = 20;

//    		どこから
    		int start_dao = 0;



    		start_dao = (current_page - 1) * num_dao;



//    		20データずつのグループ一覧を取得
    		List<Stores> s_list = dao.searchBygId_20(g_id, num_dao, start_dao);

//    		全てのデータ（ページネーション用）
    		List<Stores> s_list_all = dao.searchBygId(g_id);



    		System.out.println("daoの結果");
    		System.out.println(s_list);
    		System.out.println(s_list_all);




//			ページネーションの設定
//			ページ数カウント
			int s_list_page = 0;

//			全データ数
			int s_list_size = s_list_all.size();














    		req.getRequestDispatcher("/admin/group_list.jsp").forward(req, resp);

    	} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("グループID検索DAOでエラー");

			req.getRequestDispatcher("/admin/a_top.jsp").forward(req, resp);

    	}



    }

}
