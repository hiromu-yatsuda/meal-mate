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
        System.out.println("passed");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        String userId = (String)session.getAttribute("user_id");
        int restFoodsSize = (new RestFoodsDAO()).getRestFoodsSize(userId);
        System.out.println(restFoodsSize);
        if (restFoodsSize == 0) {
            out.print("<script type='text/javascript'>");
            out.print("alert('制限食材が登録されていません');");
            out.print("window.location.href='/meal-mate/user/ingredients_register';");
            out.print("</script>");
        } else {
            req.getRequestDispatcher("/user/ar.jsp").forward(req, resp);
        }
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
