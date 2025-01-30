//	商品登録

package s_foods;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Foods;
import dao.FoodsDAO;
import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/or/manual"})

public class s_foods_regist extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


//		DAO
		FoodsDAO f_dao = new FoodsDAO();

//		食材一覧を取得
		List<Foods> foods_list_dao = f_dao.all();

		System.out.println("DAOの結果");
		System.out.println(foods_list_dao);






		req.setAttribute("foodsList", foods_list_dao);


		req.getRequestDispatcher("/stuff/foods_regist2.jsp").forward(req, resp);



	}


	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    HttpSession session = req.getSession();

	    String gru_id = (String) session.getAttribute("s_g_id");
	    gru_id = "001"; // グループIDの仮置き

	    String y_or_n_jan = req.getParameter("not_jan");
	    boolean y_or_n_janB = "on".equals(y_or_n_jan);

	    // 商品名とJANコードを取得
	    String[] pro_names = req.getParameterValues("pro_name[]");
	    String[] jancodes = req.getParameterValues("jancode[]");

	    // rest_foodsの取得
	    List<String[]> rest_foods_list = new ArrayList<>();
	    for (int i = 0; ; i++) {
	        String[] rest_foods = req.getParameterValues("rest_foods[" + i + "][]");
	        if (rest_foods == null) break;
	        rest_foods_list.add(rest_foods);
	    }





	    System.out.println(jancodes);

	    // デバッグ用に出力
	    System.out.println("JANコードなし: " + y_or_n_janB);
	    if (pro_names != null) {
	        for (int i = 0; i < pro_names.length; i++) {
	            System.out.println("商品名: " + pro_names[i] + " (Index: " + i + ")");
	        }
	    }

	    System.out.println(jancodes);
	    if (jancodes != null) {
	        for (int i = 0; i < jancodes.length; i++) {
	            System.out.println("JANコード: " + jancodes[i] + " (Index: " + i + ")");
	        }

	        System.out.println(rest_foods_list);
	    }


	    String[] rest_foods = new String[0];










	    System.out.println(rest_foods);
	    System.out.println("ここから登録");


//	    ここから登録準備～登録へ

//	    JANコードが存在するかどうか
//	    if(y_or_n_janB==false){
//		    ランダムJANコードが必要ない

	    	System.out.println("1");




	    	System.out.println("2");







//            jspへ持って行って申し分ない整っているリスト用
            ArrayList<ArrayList<String>> next_full_list = new ArrayList<ArrayList <String>>();

//          ↑の入れ子用
          ArrayList<String> next_sub = new ArrayList<String>();
		    System.out.println("初期next_full_list");
		    System.out.println(next_full_list);






//            登録用jspへ持って行って申し分ない整っているリスト用
            ArrayList<ArrayList<String>> re_next_full_list = new ArrayList<ArrayList <String>>();

//          ↑の入れ子用
          ArrayList<String> re_next_sub = new ArrayList<String>();
		    System.out.println("初期re_next_full_list");
		    System.out.println(re_next_full_list);



//			rest_foodのIDとnameの分割用
		    String[] rest_half;

//		    分割した後の[0]
		    String half00 = "";

//		    分割した後の[1]
		    String half01 = "";


//		    何個目の商品なのか
		    int product_count = 0;



//	    	データがある
		    if (pro_names != null && jancodes != null) {
		    	System.out.println("3");



//		        for (int ii = 0; ii < pro_names.length; ii++) {

		        	System.out.println("4");

//		        	food_end = true;
		            System.out.println("商品名: " + pro_names );
		            System.out.println("JANコード: " + jancodes);









//				    for (int i = 0; i < rest_foods_list.size(); i++) {
				        rest_foods = rest_foods_list.get(0);


//				        制限食材を全てリストへ
				        for (String rest_food : rest_foods) {

				        	rest_half = rest_food.split(",");

//				        	ID
				        	half00 = rest_half[0];
//				        	name
				        	half01 = rest_half[1];



				        	System.out.println("ハーフ確認");
				        	System.out.println(half00);
				        	System.out.println(half01);





//				        	endが来たらjspに持っていく用のリストに追加してsubをリセット
				        	if(half00.equals("end")){

                                // subに商品名、JANコードを追加
                                next_sub.add(pro_names[product_count]);
                                next_sub.add(jancodes[product_count]);

                                // re_subに商品名、JANコードを追加
                                re_next_sub.add(pro_names[product_count]);
                                re_next_sub.add(jancodes[product_count]);

                                next_full_list.add(new ArrayList<>(next_sub));
                                re_next_full_list.add(new ArrayList<>(re_next_sub));

                                System.out.println("マジre_sub最終");
                                System.out.println(re_next_sub);

                                System.out.println("マジsub最終");
                                System.out.println(next_sub);

                                next_sub.clear();
                                re_next_sub.clear();

                                product_count++;


				        	}else{

//				        	subに追加していく
			        		System.out.println("next_subに追加");
				        	next_sub.add(half01);


			        		System.out.println("re_next_subに追加");
				        	re_next_sub.add(half00);


				        	System.out.println("rest_foods: " +half00 + half01 + " (Form Index: "  + ")");
				            System.out.println(next_sub);
				        	} //endがきたら




				        } //制限食材をリストに






			    System.out.println("最終next_sub");
			    System.out.println(next_sub);

			    System.out.println("最終re_next_sub");
			    System.out.println(re_next_sub);



//		    } //商品名、janコード取得
			    System.out.println("最終next_full_list");
			    System.out.println(next_full_list);

			    System.out.println("最終re_next_full_list");
			    System.out.println(re_next_full_list);




		    }



//			表示データリスト
//			画面表示用
			req.setAttribute("next_full_list",next_full_list );
			req.setAttribute("y_or_n_janB",y_or_n_janB );
			req.setAttribute("gru_id",gru_id );



//			データ送信、登録用
			req.setAttribute("re_next_full_list",re_next_full_list );

			req.getRequestDispatcher("/stuff/foods_regist_con.jsp").forward(req, resp);



//	    } //JANが存在するかIFの






	}
}






