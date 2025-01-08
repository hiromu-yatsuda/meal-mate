//	商品登録

package s_foods;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist"})

public class s_foods_regist extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();


//		グループコードを取得
		String gru_id = req.getParameter("group_code");



		//		食材を参照するDAO

//		食材をリストにする

//		商品登録ページを表示する



//		データをjspに渡す
//		String aaa = "も";
//		req.setAttribute("momo",aaa);

//		画面表示
//		req.getRequestDispatcher("").forward(req,resp);


	}
	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

//		formからデータを受け取る





	}



}


