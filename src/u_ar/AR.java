package u_ar;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RestFoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/ar"})
public class AR extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html; charset=UTF-8");
        System.out.println("passed");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        String userId = (String)session.getAttribute("user_id");
        int restFoodsSize = (new RestFoodsDAO()).getRestFoodsSize(userId);
        System.out.println(restFoodsSize);
        if (restFoodsSize == 0) {
            req.getRequestDispatcher("/user/alert.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/user/ar.jsp").forward(req, resp);
        }
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
