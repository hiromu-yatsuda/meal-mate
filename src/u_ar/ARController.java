package u_ar;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Foods;
import dao.FoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/ajax-test"})
public class ARController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("servlet");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String barcode = req.getParameter("barcode");

//        HttpSession session = req.getSession();
        // ユーザIDを取得し、登録されている制限食材を取得
        // ユーザのログイン機能ができていないためコメントアウト
//        String userId = (String)session.getAttribute("u_id");
        String userId = "000001";
        List<Foods> foods = (new FoodsDAO()).getFoods(barcode, userId);

//        System.out.println("restFoods: " + restFoods.get(0).getFoodName());
//        System.out.println("productFoods: " + productFoods.get(0).getFoodName());
//        System.out.println("set1: " + set1);

        // 登録した食材がなかった場合の処理も考える
//        if (set1.isEmpty()) {
//            out.println("empty");
//        } else {
//            StringBuilder sBuilder = new StringBuilder();
//            sBuilder.append("{\"classes\": [");
//            for (Foods item: set1) {
//                sBuilder.append("\"" + item + "\", ");
//            }
//            sBuilder.delete(sBuilder.length() - 2, sBuilder.length());
//            sBuilder.append("]}");
//
//
//            out.print(sBuilder.toString());
//        }

        String string = "{\"paths\": [\"AR.png\", \"class.png\", \"csv.jpg\", \"gennzaiti.png\"]}";

        out.print(string);



    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
