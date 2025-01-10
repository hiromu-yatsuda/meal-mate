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
        String barcode = req.getParameter("barcode");

        String string = "{\"classes\": [\"banana\", \"apple\", \"lemon\"]}";

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(string.toString());

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
