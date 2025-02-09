package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;

public class RestFoodsDAO extends DAO {
    // エラー(?の2コメに値をセットしていない)
    // 引数が足りなそうだけどいつ使う?
    public List<Foods> getRestFoods(String userId) throws Exception {
        List<Foods> foods = new ArrayList<Foods>();
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement(
                "select f.id as id, f.name as name, f.icon as icon, f.category_id as category_id from foods f"
                + " join rest_foods r on f.id = r.foods_id"
                + " where r.user_account_id = ?");

        pStatement.setString(1, userId);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Foods food = new Foods();
            food.setId(rSet.getInt("id"));
            food.setFoodName(rSet.getString("name"));
            food.setIcon(rSet.getString("icon"));

            foods.add(food);
        }

        return foods;
    }

    // 制限食材が登録されてる件数を取得
    public int getRestFoodsSize(String userId) throws Exception {
        List<Foods> restFoods = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select * from rest_foods where user_account_id = ?");

        pStatement.setString(1, userId);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Foods food = new Foods();
            restFoods.add(food);
        }

        return restFoods.size();
    }

    public void setRestFoods(String userId, List<String> idList) throws Exception {
        Connection connection = getConnection();
        PreparedStatement delete = connection.prepareStatement("delete from rest_foods where user_account_id = ?");
        delete.setString(1, userId);
        delete.executeUpdate();
        delete.close();

        System.out.println("dao.idList: " + idList);

        PreparedStatement insert = connection.prepareStatement(""
                + "insert into rest_foods(user_account_id, foods_id) "
                + "values (?, ?)");

        for (String id: idList) {
            insert.setString(1, userId);
            insert.setString(2, id);
            insert.addBatch();
        }

        insert.executeBatch();
        insert.close();

        connection.close();
    }
}
