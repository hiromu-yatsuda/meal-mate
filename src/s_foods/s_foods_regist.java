//	商品登録

package s_foods;



import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.JsonProduct;
import dao.ProductsDAO;
import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/or/manual"})

public class s_foods_regist extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    HttpSession session = req.getSession();
        resp.setContentType("applicatoin/json");

	    try {
	        if (session.getAttribute("json") != null) {
	            session.removeAttribute("json");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		req.getRequestDispatcher("/stuff/foods_regist2.jsp").forward(req, resp);
	}


	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	    HttpSession session = req.getSession();
	    resp.setContentType("application/json");
	    List<JsonProduct> products = null;
	    String json = "";

	    try {
	        json = session.getAttribute("json").toString();
	        session.removeAttribute("json");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println(json);

	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        products = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, JsonProduct.class));
//	        System.out.println(products);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    String g_id = (String) session.getAttribute("s_g_id");
        g_id = "100"; // グループIDの仮置き

	    if (products != null) {
	        int line = (new ProductsDAO()).insertProducts(products, g_id);
	    }
	}
}






