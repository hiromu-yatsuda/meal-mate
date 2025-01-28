package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;
import bean.JsonProduct;

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
            food.setIcon(rSet.getString("icon"));

            foods.add(food);
        }

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

                PreparedStatement pStatement = connection.prepareStatement("insert into PRODUCT_FOODS values (?, ?,?)");


                pStatement.setInt(1, int_max_pStatement);
                pStatement.setString(2, janCode);
                pStatement.setString(3, foodsId);

                int line = pStatement.executeUpdate();

                pStatement.close();
                connection.close();

                return line;

    	}

    	// 複数件登録する際はこちら↓↓↓
    	public boolean insertProductsFoods(List<JsonProduct> products) throws Exception {
    	    boolean isOk = false;
    	    int line = 0;
    	    int nextId = 1;
    	    Connection connection = getConnection();
    	    PreparedStatement nextIdStatement = connection.prepareStatement("select max(id) as maxId from product_foods");
            ResultSet nextIdResult = nextIdStatement.executeQuery();

            if (nextIdResult.next()) {
                nextId = nextIdResult.getInt("maxId") + 1;
            }

            PreparedStatement pStatement = connection.prepareStatement("insert into product_foods values (?, ?, ?)");

            try {
                connection.setAutoCommit(false);

                for (JsonProduct p: products) {
                    for (String s: p.getCheckedItemsId()) {
                        pStatement.setInt(1, nextId);
                        pStatement.setString(2, p.getJan());
                        pStatement.setString(3, s);
                        pStatement.addBatch();
                        nextId++;
                    }
                }

                line = pStatement.executeBatch().length;

                connection.commit();
                isOk = true;
                System.out.println("product_foods commit");
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
                System.out.println("product_foods: 登録失敗");
            }

            nextIdStatement.close();
            pStatement.close();
            connection.close();

    	    return isOk;
    	}



}
