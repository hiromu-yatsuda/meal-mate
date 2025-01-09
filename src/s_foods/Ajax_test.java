package s_foods;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet(urlPatterns={"/main/all-class"})
public class Ajax_test extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String schoolCd = req.getParameter("schoolCd");
        int year = Integer.parseInt(req.getParameter("year"));

        String string = "{classes: [\"value\": \"select\", \"text\": \"select\"]}";

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(string.toString());

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
