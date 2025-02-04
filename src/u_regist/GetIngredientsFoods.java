package u_regist;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Foods;
import dao.FoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/get_ings"})
public class GetIngredientsFoods extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("called");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        List<Foods> foods = (new FoodsDAO()).all();

        StringBuilder jsonStr = new StringBuilder("{ ");
        StringBuilder fruits = new StringBuilder("\"fruits\": [ ");
        StringBuilder vegetable = new StringBuilder("\"vegetables\": [ ");
        StringBuilder meat = new StringBuilder("\"meats\": [ ");
        StringBuilder seafood = new StringBuilder("\"seafood\": [ ");
        StringBuilder dairy = new StringBuilder("\"dairy\": [ ");
        StringBuilder other = new StringBuilder("\"sonota\": [ ");

        for (Foods f: foods) {
            String item = "{ \"name\": \"%s\", \"src\": \"%s\", \"id\": \"%d\"}, ";
            item = String.format(item, f.getFoodName(), f.getIcon(), f.getId());

            switch (f.getCategories().getId()) {
            case 1:
                fruits.append(item);
                break;
            case 2:
                vegetable.append(item);
                break;
            case 3:
                meat.append(item);
                break;
            case 4:
                seafood.append(item);
                break;
            case 5:
                dairy.append(item);
                break;
            case 6:
                other.append(item);
                break;
            }
        }
        if (fruits.length() != 0) {
            fruits.delete(fruits.length()-2, fruits.length()).append(" ], ");
            jsonStr.append(fruits);
        }
        if (vegetable.length() != 0) {
            vegetable.delete(vegetable.length()-2, vegetable.length()).append(" ], ");
            jsonStr.append(vegetable);
        }
        if (meat.length() != 0) {
            meat.delete(meat.length()-2, meat.length()).append(" ], ");
            jsonStr.append(meat);
        }
        if (seafood.length() != 0) {
            seafood.delete(seafood.length()-2, seafood.length()).append(" ], ");
            jsonStr.append(seafood);
        }
        if (dairy.length() != 0) {
            dairy.delete(dairy.length()-2, dairy.length()).append(" ], ");
            jsonStr.append(dairy);
        }
        if (other.length() != 0) {
            other.delete(other.length()-2, other.length()).append(" ]");
            jsonStr.append(other);
        }

        jsonStr.append(" }");

        out.print(jsonStr.toString());

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
