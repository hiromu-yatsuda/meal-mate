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

	    String y_or_n_jan = req.getParameter("not_jan[]");
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
	    for (int i = 0; i < rest_foods_list.size(); i++) {
	        String[] rest_foods = rest_foods_list.get(i);
	        for (String rest_food : rest_foods) {
	            System.out.println("rest_foods: " + rest_food + " (Form Index: " + i + ")");
	        }
	    }

	    // ここでデータを処理するロジックを追加
	    // 例えば、データベースに保存するなど
	}
}






