package u_regist;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Foods;
import dao.FoodsDAO;
import dao.RestFoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/get_ings"})
public class GetIngredientsFoods extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        HttpSession session = req.getSession();
//        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
//        List<Foods> foods = (new FoodsDAO()).allEnglish();
//        String userId = (String)session.getAttribute("user_id");
//        List<Foods> restFoods = (new RestFoodsDAO()).getRestFoods(userId);
//        List<JSONObject> restJsonList = new ArrayList<>();
//        JSONObject json = new JSONObject();
//        JSONObject foodsJson = new JSONObject();
//
//        List<JSONObject> fruits = new ArrayList<>();
//        List<JSONObject> vegetable = new ArrayList<>();
//        List<JSONObject> meat = new ArrayList<>();
//        List<JSONObject> seafood = new ArrayList<>();
//        List<JSONObject> dairy = new ArrayList<>();
//        List<JSONObject> other = new ArrayList<>();
//
//        for (Foods f: foods) {
//            JSONObject food = new JSONObject();
//            food.put("name", f.getFoodName());
//            food.put("src", f.getIcon());
//            food.put("id", f.getId());
//
//            switch (f.getCategories().getId()) {
//            case 1:
//                fruits.add(food);
//                break;
//            case 2:
//                vegetable.add(food);
//                break;
//            case 3:
//                meat.add(food);
//                break;
//            case 4:
//                seafood.add(food);
//                break;
//            case 5:
//                dairy.add(food);
//                break;
//            case 6:
//                other.add(food);
//                break;
//            }
//        }
//
//        foodsJson.put("fruits", fruits);
//        foodsJson.put("vegetable", vegetable);
//        foodsJson.put("meat", meat);
//        foodsJson.put("seafood", seafood);
//        foodsJson.put("dairy", dairy);
//        foodsJson.put("other", other);
//        json.put("foodsJson", foodsJson);
//
//        for (Foods f: restFoods) {
//            JSONObject rfJson = new JSONObject();
//            rfJson.put("name", f.getFoodName());
//            rfJson.put("src", f.getIcon());
//            rfJson.put("id", f.getId());
//
//            System.out.println(String.format("%s: %d", f.getFoodName(), f.getId()));
//
//            restJsonList.add(rfJson);
//        }
//
//        json.put("userRegs", restJsonList);
//
//        System.out.println(json);
//
//        out.print(json);

        HttpSession session = req.getSession();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<Foods> foods = (new FoodsDAO()).allEnglish();
        String userId = (String)session.getAttribute("user_id");
        List<Foods> restFoods = (new RestFoodsDAO()).getRestFoods(userId);
        List<Map<String, Object>> restJsonList = new ArrayList<>();
        Map<String, Object> data = new HashMap<String, Object>();
        Map<String, List<Map<String, Object>>> foodsJson = new HashMap<>();
        String json = new String();

        List<Map<String, Object>> fruits = new ArrayList<>();
        List<Map<String, Object>> vegetable = new ArrayList<>();
        List<Map<String, Object>> meat = new ArrayList<>();
        List<Map<String, Object>> seafood = new ArrayList<>();
        List<Map<String, Object>> dairy = new ArrayList<>();
        List<Map<String, Object>> other = new ArrayList<>();

        for (Foods f: foods) {
            Map<String, Object> food = new HashMap<>();
            food.put("name", f.getFoodName());
            food.put("src", f.getIcon());
            food.put("id", f.getId());

            switch (f.getCategories().getId()) {
            case 1:
                fruits.add(food);
                break;
            case 2:
                vegetable.add(food);
                break;
            case 3:
                meat.add(food);
                break;
            case 4:
                seafood.add(food);
                break;
            case 5:
                dairy.add(food);
                break;
            case 6:
                other.add(food);
                break;
            }
        }

        foodsJson.put("fruits", fruits);
        foodsJson.put("vegetable", vegetable);
        foodsJson.put("meat", meat);
        foodsJson.put("seafood", seafood);
        foodsJson.put("dairy", dairy);
        foodsJson.put("other", other);
        data.put("foodsJson", foodsJson);

        for (Foods f: restFoods) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", f.getFoodName());
            map.put("src", f.getIcon());
            map.put("id", f.getId());

            System.out.println(String.format("%s: %d", f.getFoodName(), f.getId()));

            restJsonList.add(map);
        }

        data.put("userRegs", restJsonList);

        json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(json);

        out.print(json);

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // TODO 自動生成されたメソッド・スタブ

    }

}
