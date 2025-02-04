package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;

public class RestFoodsDAO extends DAO {
    public List<Foods> getRestFoods(String userId) throws Exception {
        List<Foods> foods = new ArrayList<Foods>();
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement(
                "select * from rest_foods rf join product_foods pf on rf.foods_id = pf.foods_id"
                + " join foods f on rf.foods_id = f.id where rf.user_account_id = ? and pf.product_id = ?");

        pStatement.setString(1, userId);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Foods food = (new FoodsDAO()).getFoodsById(rSet.getInt("foods_id"));
            foods.add(food);
        }

        return foods;
    }

    public void setRestFoods(String userId, List<String> idList) throws Exception {
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("insert into rest_foods (user_account_id, foods_id)"
                + "select ?, ? where not exists ( select 1 from rest_foods where user_account_id = ? and foods_id = ? )");

        for (String id: idList) {
            pStatement.setString(1, userId);
            pStatement.setString(2, id);
            pStatement.setString(3, userId);
            pStatement.setString(4, id);

            pStatement.addBatch();
        }

        pStatement.executeBatch();

        pStatement.close();
        connection.close();
    }
}
