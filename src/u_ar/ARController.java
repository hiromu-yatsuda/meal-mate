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
//        String barcode = req.getParameter("barcode");

//        HttpSession session = req.getSession();
        // ユーザIDを取得し、登録されている制限食材を取得
        // ユーザのログイン機能ができていないためコメントアウト
//        String userId = (String)session.getAttribute("u_id");

        // テスト用の固定値
        String userId = "000001";
        String barcode = "4901777316539";

        List<Foods> foods = (new FoodsDAO()).getFoods(barcode, userId);
        System.out.println();

        // 登録した食材がなかった場合の処理も考える
        if (!foods.isEmpty()) {
            StringBuilder sBuilder = new StringBuilder();

        }

        String string = "{\"paths\": [\"AR.png\", \"class.png\", \"csv.jpg\", \"gennzaiti.png\"]}";

        out.print(string);



    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
