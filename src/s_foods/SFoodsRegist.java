package s_foods;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Foods;
import bean.JsonProduct;
import dao.FoodsDAO;
import dao.ProductsDAO;
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
        StringBuilder sBuilder = new StringBuilder();
        String line;

        while ((line = req.getReader().readLine()) != null) {
            sBuilder.append(line);
        }

        System.out.println(sBuilder);

        ObjectMapper objectMapper = new ObjectMapper();
        List<JsonProduct> products = objectMapper.readValue(sBuilder.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, JsonProduct.class));

        int lines = (new ProductsDAO()).insertProducts(products);
    }

}
