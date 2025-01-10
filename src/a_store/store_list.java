package a_store;

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

@WebServlet(urlPatterns={"/admin/a_group_list/a_store_list"})
public class store_list extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



//    	セッションからグループコードを取得する用
    	HttpSession session = req.getSession();
    	String g_id2 = "";


//
    	String g_id = req.getParameter("groupCode");




//    	ページネーションからページ遷移した時
    	if(g_id==null){
    		g_id2 = (String) session.getAttribute("g_id");
    		g_id = g_id2;
    	}


    	System.out.println("グループコード１");
    	System.out.println(g_id);
    	System.out.println(g_id2);


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



////    	仮置き
//    	g_id = "001";
//    	System.out.println(g_id);





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


//			System.out.println("1");


			int page_count = s_list_size / 20;
			System.out.println(page_count);

//			System.out.println("2");


			int page_count_rem = s_list_size % 20;
			System.out.println(page_count_rem);

//			System.out.println("3");

			if(page_count_rem > 0){
				s_list_page = page_count + 1;

//				System.out.println("4");
			}else{
				s_list_page = page_count;
//				System.out.println("4.5");
			}


//			System.out.println("5");

//			現在のページ
			req.setAttribute("current_page", current_page);

//			全ページ数
			req.setAttribute("total_page", s_list_page);


//			System.out.println("6");

//			表示データリスト
			req.setAttribute("storesList",s_list );
//			req.setAttribute("groupsList", g_list);

//			グループコードをセッションに
			session.setAttribute("g_id", g_id);
			System.out.println("セッションg_id");
			System.out.println( (String) session.getAttribute("g_id"));

//			System.out.println("7");


			req.getRequestDispatcher("/admin/store_list.jsp").forward(req, resp);

    	} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("グループID検索DAOでエラー");

			req.getRequestDispatcher("/admin/a_top.jsp").forward(req, resp);

    	}



    }

}
