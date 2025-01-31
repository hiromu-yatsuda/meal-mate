//	商品登録

package s_foods;



import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/or/manual"})

public class s_foods_regist extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	    // ここでcancelボタンが押された時のsessionの削除を書く
	    // if (sessionの内容["items"] != null)的な感じ
	    // できればsessionの内容を確認

//		DAO
//		FoodsDAO f_dao = new FoodsDAO();

//		食材一覧を取得
//		List<Foods> foods_list_dao = f_dao.all();

//		System.out.println("DAOの結果");
//		System.out.println(foods_list_dao);

//		req.setAttribute("foodsList", foods_list_dao);


		req.getRequestDispatcher("/stuff/foods_regist2.jsp").forward(req, resp);



	}


	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    HttpSession session = req.getSession();
	    resp.setContentType("applicatoin/json");
	    PrintWriter out = resp.getWriter();

	    String gru_id = (String) session.getAttribute("s_g_id");
	    gru_id = "001"; // グループIDの仮置き

	    StringBuilder sBuilder = new StringBuilder();
        String line;

        while ((line = req.getReader().readLine()) != null) {
            sBuilder.append(line);
        }

        System.out.println(sBuilder);

        System.out.println("じゅんちょー");

        try {
            session.setAttribute("json", sBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("じゅんちょーちょー");
	}
}






