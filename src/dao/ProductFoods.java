package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;

public class ProductFoods extends DAO {
    public List<Foods> searchFoodsByJanCode(String janCode) throws Exception {
        List<Foods> foods = new ArrayList<Foods>();
        FoodsDAO fDao = new FoodsDAO();

        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select * from product_foods pf join foods f on pf.foods_id = f.id where product_id = ?");

        pStatement.setString(1, janCode);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            int foodId = rSet.getInt("foods_id");
            Foods food = new Foods();
            food.setId(rSet.getInt("id"));
            food.setFoodName(rSet.getString("name"));
            food.setIconR(rSet.getString("icon_r"));
            food.setIconG(rSet.getString("icon_g"));
            foods.add(food);
        }

        pStatement.close();
        connection.close();

        return foods;
    }
}
