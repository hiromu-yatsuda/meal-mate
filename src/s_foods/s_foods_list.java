package s_foods;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductsList;
import dao.ProductsDAO;
import tool.CommonServlet;


//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/list"})
public class s_foods_list extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    	HttpSession session = req.getSession();


	    String gru_id = (String) session.getAttribute("s_g_id");


//	    食材名
	    List<String> pro_food_name_list = new ArrayList<>();


	    ProductsDAO p_dao = new ProductsDAO();


//	    グループIDから商品データを取得（商品名、JANコード、食材ID、食材名）
	    List<ProductsList> products_list = p_dao.searchGroupId(gru_id);

	    System.out.println("DAOの結果");
	    System.out.println(products_list);

	    for(ProductsList p:products_list){
		    System.out.println("商品名");
	    	String pro_name = p.getPro_name();
	    	System.out.println(pro_name);

		    System.out.println("JANコード");
	    	String pro_jan = p.getJancode();
	    	System.out.println(pro_jan);

		    System.out.println("食材ID");
	    	String pro_foodId = p.getFoods_id();
	    	System.out.println(pro_foodId);

		    System.out.println("食材名");
	    	String pro_foodName = p.getFoods_name();
	    	System.out.println(pro_foodName);


	    }


	    req.setAttribute("product_list",products_list );


		req.getRequestDispatcher("/stuff/foods_list.jsp").forward(req, resp);


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

}
