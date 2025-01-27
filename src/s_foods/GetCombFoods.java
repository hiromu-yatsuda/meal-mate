package s_foods;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CombFoods;
import dao.CombFoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/getAllCombFoods"})
public class GetCombFoods extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        List<CombFoods> combFoods = (new CombFoodsDAO()).getAllCombFoods();
        StringBuilder jsonBuilder = new StringBuilder("[");

        for (CombFoods cf: combFoods) {
            StringBuilder item = new StringBuilder("{\"id\": \"" + cf.getId() + "\", "
                    + "\"cfName\": \"" + cf.getName() + "\", "
                    + "\"foods\": [");
            for (String food: cf.getFoods()) {
                item.append("\"" + food + "\", ");
            }
            item.delete(item.length() - 2, item.length());
            item.append("]}, ");
            jsonBuilder.append(item);
        }

        jsonBuilder.delete(jsonBuilder.length() - 2, jsonBuilder.length());
        jsonBuilder.append("]");

        System.out.println(jsonBuilder);
        out.print(jsonBuilder);
    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
