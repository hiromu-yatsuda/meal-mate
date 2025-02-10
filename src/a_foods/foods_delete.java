package a_foods;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodsDAO;

@WebServlet(urlPatterns={"/admin/foods/delete"})
public class foods_delete extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {


	}



	@Override
	protected void doPost(
	        HttpServletRequest req,
	        HttpServletResponse resp
	    ) throws ServletException, IOException {


//		商品のJANコード一覧「,」区切り
		String jan_code = req.getParameter("jan_code");

		String[] jan_code_list =  jan_code.split(",");
		System.out.println(jan_code_list);

		FoodsDAO dao = new FoodsDAO();

		int line = 0;
		for(String jan:jan_code_list){
			System.out.println(jan);


			try {
				line = dao.delete(jan);
				System.out.println(line);
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

		System.out.println("消したで");



	}


}
