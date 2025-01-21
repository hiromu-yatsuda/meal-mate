package u_ar;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet(urlPatterns={"/ajax-test"})
public class ARController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
//        int barcode = Integer.parseInt(req.getParameter("barcode"));

//        HttpSession session = req.getSession();
//        // ユーザIDを取得し、登録されている制限食材を取得
//        // ユーザのログイン機能ができていないためコメントアウト
//        int userId = Integer.parseInt((String)session.getAttribute("u_id"));
//        List<Foods> restFoods = (new RestFoodsDAO()).getRestFoods(userId);
//
//        // barcodeから商品に入っている食材を取得
//        ProductFoods pFoods = new ProductFoods();
//        List<Foods> productFoods = pFoods.searchFoodsByJanCode(barcode);
//
//        // 制限食材と商品食材で重複しているものを調べる
//        HashSet<Foods> set1 = new HashSet<>(restFoods);
//        HashSet<Foods> set2 = new HashSet<>(productFoods);
//        set1.retainAll(set2);
//
//        // 登録した食材がなかった場合の処理も考える
//        if (set1.isEmpty()) {
//            out.println("empty");
//        } else {
//            StringBuilder sBuilder = new StringBuilder();
//            sBuilder.append("{\"classes\": [");
//            // 食材名とicon_r, icon_gのpathを用意する(jsonの拡張)(面倒)
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
