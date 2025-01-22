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
        PreparedStatement pStatement = connection.prepareStatement("select foods_id from product_foods where foods_id = ?");

        pStatement.setString(1, janCode);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            int foodId = rSet.getInt("food_id");
            Foods food = fDao.getFoodsById(foodId);
            foods.add(food);
        }

        pStatement.close();
        connection.close();

        return foods;
    }
}
