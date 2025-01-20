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


		req.getRequestDispatcher("/stuff/foods_regist.jsp").forward(req, resp);



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


//	    // 食材名の取得
//	    List<String[]> rest_foods_name_list = new ArrayList<>();
//	    for (int i = 0; ; i++) {
//	        String[] rest_foods_name = req.getParameterValues("rest_foods_id[" + i + "][]");
//	        if (rest_foods_name == null) break;
//	        rest_foods_name_list.add(rest_foods_name);
//	    }



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


	    for (int i = 0; i < rest_foods_list.size(); i++) {
	        rest_foods = rest_foods_list.get(i);
	        for (String rest_food : rest_foods) {

	        	System.out.println("rest_foods: " + rest_food + " (Form Index: " + i + ")");
	        }
	    }


////	    食材名
//	    String[] rest_foods_name = new String[0];
//	    for (int i = 0; i < rest_foods_name_list.size(); i++) {
//	    	rest_foods_name = rest_foods_name_list.get(i);
//	        for (String rest_food_name : rest_foods_name) {
//
//	        	System.out.println("rest_foods: " + rest_food_name + " (Form Index: " + i + ")");
//	        }
//	    }




	    System.out.println(rest_foods);


//	    ここから登録準備～登録へ

//	    JANコードが存在するかどうか
	    if(y_or_n_janB==false){
//		    ランダムJANコードが必要ない


//	    	食材リスト用カウント引数
	    	int food_count = 0;

//	    	食材「end」識別用
	    	boolean food_end = true;


	    	String[] rest_foods_name = new String[0];




//	    	データがある
		    if (pro_names != null && jancodes != null) {
		        for (int i = 0; i < pro_names.length; i++) {


		        	food_end = false;
		            System.out.println("商品名: " + pro_names[i] + " (Index: " + i + ")");
		            System.out.println("JANコード: " + jancodes[i] + " (Index: " + i + ")");

		            while(food_end){
		            	rest_foods_name = rest_foods_list.get(food_count);
		            	food_count ++;

		            	for(String p : rest_foods_name){
		            		System.out.println(p);

		            		String[] f_only_list = p.split(",");
		            		System.out.println(f_only_list);
		            		System.out.println(f_only_list[0]);
		            		System.out.println(f_only_list[1]);

		            	}



		            }



		        }
		    }





	    }else{
//	    	ランダムJANコードが必要

	    }


	}
}






