package u_regist;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RestFoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/update_data"})
public class SetRegistFoods extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plane");
        HttpSession session = req.getSession();
        List<String> idList = Arrays.asList(req.getParameter("idList").split(","));
        // テスト用固定値
        String userId = (String)session.getAttribute("user_id");


        (new RestFoodsDAO()).setRestFoods(userId, idList);

        out.print("ok");
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }

}
