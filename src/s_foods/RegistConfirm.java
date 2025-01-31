package s_foods;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.CommonServlet;

@WebServlet(urlPatterns={"/stuff/regist_confirm"})
public class RegistConfirm extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        resp.setContentType("applicatoin/json");
        PrintWriter out = resp.getWriter();

        String gru_id = (String) session.getAttribute("s_g_id");
        gru_id = "001"; // グループIDの仮置き

        String json = req.getParameter("json");

        System.out.println(json);

        System.out.println("じゅんちょー");

        req.setAttribute("jsonArray", json);
        req.getRequestDispatcher("/stuff/foods_regist_con.jsp").forward(req, resp);

        System.out.println("じゅんちょーちょー");
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
