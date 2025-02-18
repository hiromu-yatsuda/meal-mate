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

        System.out.println("searchFoodsByJanCode\n\n\n\n\n2");
        pStatement.setString(1, janCode);

        System.out.println("searchFoodsByJanCode\n\n\n\n\n3");
        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            int foodId = rSet.getInt("foods_id");
            Foods food = new Foods();
            System.out.println("searchFoodsByJanCode\n\n\n\n\n4-1");
            food.setId(rSet.getInt("id"));
            System.out.println("searchFoodsByJanCode\n\n\n\n\n4");
            food.setFoodName(rSet.getString("name"));
            food.setIcon(rSet.getString("icon"));

            foods.add(food);
        }

        System.out.println("searchFoodsByJanCode\n\n\n\n\n5");
        pStatement.close();
        connection.close();

        return foods;
    }


    	public int insert(String janCode,String foodsId)throws Exception {
            Connection connection = getConnection();

            PreparedStatement max_pStatement = connection.prepareStatement("SELECT MAX(ID) FROM PRODUCT_FOODS");

            ResultSet resultSet = max_pStatement.executeQuery();

             // 結果セットから値を取得してint型に変換
             int int_max_pStatement = 0;
             if (resultSet.next()) {
                 int_max_pStatement = resultSet.getInt(1);
             }

          // 取得した値の確認
             System.out.println("最大のID: " + int_max_pStatement);


             // 取得した値の確認
             System.out.println("最大のID+1: " + int_max_pStatement);

             int_max_pStatement = int_max_pStatement + 1;

                PreparedStatement pStatement = connection.prepareStatement("insert into PRODUCT_FOODS values (?, ?, ?)");


                System.out.println("proffood.insert\n\n\n\n\n2");
                pStatement.setInt(1, int_max_pStatement);
                System.out.println("proffood.insert\n\n\n\n\n3");
                pStatement.setString(2, janCode);
                pStatement.setInt(3, Integer.parseInt(foodsId));
                System.out.println("proffood.insert\n\n\n\n\n4");

                int line = pStatement.executeUpdate();

                pStatement.close();
                connection.close();

                return line;

    	}
}