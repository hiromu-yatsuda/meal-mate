package s_foods;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Foods;
import dao.FoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/getAllFoods"})
public class SFoodsRegist extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        List<Foods> foods = (new FoodsDAO()).all();
        StringBuilder sBuilder = new StringBuilder();
        StringBuilder id = new StringBuilder();
        StringBuilder name = new StringBuilder();

        id.append("\"id\": [");
        name.append("\"name\": [");

        for (Foods item: foods) {
            id.append("\"" + item.getId() + "\", ");
            name.append("\"" + item.getFoodName() + "\", ");
        }

        sBuilder.append("{");
        id.delete(id.length()-2, id.length());
        name.delete(name.length()-2, name.length());
        sBuilder.append(id + "], " + name + "]");
        sBuilder.append("}");

        out.print(sBuilder);

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }

}
