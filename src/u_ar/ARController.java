package u_ar;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Foods;
import dao.FoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/ajax-test"})
public class ARController extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("called");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        StringBuilder sBuilder = new StringBuilder("");
        String barcode = req.getParameter("barcode");
        String userId = (String)session.getAttribute("user_id");

        // テスト用の固定値
        userId = "000001";
        barcode = "3904567890123";

        List<Foods> foods = (new FoodsDAO()).getFoods(barcode, userId);
        System.out.println();

        // 登録した食材がなかった場合の処理も考える
        if (foods.isEmpty()) {
            System.out.println("empty");

        }
        // 読み込めた場合
        else {
            sBuilder.append("{\"path\": [");
            foods.forEach((Foods f) -> {
                // ここでjson({path: [...paths]}に変換する(StringBuilder?)
                // 最後に.toString()も多分必要
                System.out.println(f.getIcon());
                sBuilder.append(String.format("\"%s\", ", f.getIcon()));
            });
            sBuilder.delete(sBuilder.length()-2, sBuilder.length()).append("]}");
            System.out.println(sBuilder);
        }

        String string = "{\"paths\": [\"AR.png\", \"class.png\", \"csv.jpg\", \"gennzaiti.png\"]}";

        out.print(sBuilder.toString());



    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
