package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;

public class RestFoodsDAO extends DAO {
    public List<Foods> getRestFoods(int userId) throws Exception {
        List<Foods> foods = new ArrayList<Foods>();
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select foods_id from rest_foods where foods_id = ?");

        pStatement.setInt(1, userId);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Foods food = (new FoodsDAO()).getFoodsById(rSet.getInt("foods_id"));
            foods.add(food);
        }

        return foods;
    }
}
