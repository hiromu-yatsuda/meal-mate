package u_regist;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/update_data"})
public class SetRegistFoods extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String idList = (String)req.getParameter("ids");

        System.out.println(idList);
        out.print("ok");
    }

}
