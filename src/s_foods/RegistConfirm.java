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
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        resp.setContentType("applicatoin/json");
        PrintWriter out = resp.getWriter();

        StringBuilder sBuilder = new StringBuilder();
        String line;

        while ((line = req.getReader().readLine()) != null) {
            sBuilder.append(line);
        }

        System.out.println(sBuilder);

        try {
            session.setAttribute("json", sBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
