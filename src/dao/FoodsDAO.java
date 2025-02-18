package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Categories;
import bean.Foods;

public class FoodsDAO extends DAO {
	public List<Foods> all() throws Exception{
		List<Foods> list = new ArrayList<Foods>();

		// データベースに接続
		Connection con = getConnection();

		// 実行したいSQL文をプリペアードステートメントで準備
		PreparedStatement st = con.prepareStatement(
				"SELECT f.id as id, f.name as name, f.icon as icon, c.id as cid, c.name as cname FROM FOODS as f join categories as c on f.category_id = c.id"
				);

		ResultSet rs = st.executeQuery();

		// 結果から1件ずつ取り出すループ
		while (rs.next()) {
			Foods f = new Foods();
			Categories categories = new Categories();

//			値のセット
            categories.setId(rs.getInt("cid"));
            categories.setName(rs.getString("cname"));
			f.setId(rs.getInt("id"));
			f.setFoodName(rs.getString("name"));
			f.setIcon(rs.getString("icon"));
			f.setCategories(categories);

			// リストに追加
			list.add(f);
		}
		// データベースとの接続を解除（必ず書く！！！！！！！！）
		st.close();
		con.close();

		// リストを返却
		return list;

	}

	   public List<Foods> allEnglish() throws Exception{
	        List<Foods> list = new ArrayList<Foods>();

	        // データベースに接続
	        Connection con = getConnection();

	        // 実行したいSQL文をプリペアードステートメントで準備
	        PreparedStatement st = con.prepareStatement(
	                "SELECT f.id as id, f.english_name as name, f.icon as icon, c.id as cid, c.name as cname FROM FOODS_ENGLISH as f join categories as c on f.category_id = c.id"
	                );

	        ResultSet rs = st.executeQuery();

	        // 結果から1件ずつ取り出すループ
	        while (rs.next()) {
	            Foods f = new Foods();
	            Categories categories = new Categories();

//	          値のセット
	            categories.setId(rs.getInt("cid"));
	            categories.setName(rs.getString("cname"));
	            f.setId(rs.getInt("id"));
	            f.setFoodName(rs.getString("name"));
	            f.setIcon(rs.getString("icon"));
	            f.setCategories(categories);

	            // リストに追加
	            list.add(f);
	        }
	        // データベースとの接続を解除（必ず書く！！！！！！！！）
	        st.close();
	        con.close();

	        // リストを返却
	        return list;

	    }

	// idが見当たらない場合も考える
	public Foods getFoodsById(int id) throws Exception {
	    Foods food = new Foods();
	    Connection connection = getConnection();
	    PreparedStatement pStatement = connection.prepareStatement("select * from foods where id = ?");

	    pStatement.setInt(1, id);

	    ResultSet rSet = pStatement.executeQuery();

	    if (rSet.next()) {
	        food.setId(rSet.getInt("id"));
	        food.setFoodName(rSet.getString("name"));
	        food.setIcon(rSet.getString("icon_"));
	    }

	    pStatement.close();
	    connection.close();

	    return food;
	}

	public Map<String, String> getAllNameAndId() throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement pStatement = connection.prepareStatement("select id, name from foods");
	    Map<String, String> foodMap = new HashMap<>();

	    ResultSet rSet = pStatement.executeQuery();

	    while (rSet.next()) {
	        foodMap.put(rSet.getString("name"), Integer.toString(rSet.getInt("id")));
	    }

	    pStatement.close();
	    connection.close();

	    return foodMap;
	}

	public List<Foods> getFoods(String barcode, String userId) throws Exception {
	    List<Foods> foods = new ArrayList<>();
	    Connection connection = getConnection();

	    PreparedStatement pStatement = connection.prepareStatement("select * from rest_foods rf join product_foods pf on rf.foods_id = pf.foods_id"
                + " join foods f on rf.foods_id = f.id where rf.user_account_id = ? and pf.product_id = ?");

	    pStatement.setString(1, userId);
	    pStatement.setString(2, barcode);

//

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



//	商品削除
	public int delete(String jancode) throws Exception {
		 Connection connection = getConnection();
		 PreparedStatement pStatement1 = connection.prepareStatement("DELETE FROM GROUP_PRODUCTS   WHERE  JAN_CODE = ? ");
		 PreparedStatement pStatement3 = connection.prepareStatement("DELETE FROM PRODUCTS   WHERE  JAN_CODE = ?");
		 PreparedStatement pStatement2 = connection.prepareStatement("DELETE FROM PRODUCT_FOODS   WHERE  PRODUCT_ID  = ? ");


	        pStatement1.setString(1, jancode);
	        pStatement2.setString(1, jancode);
	        pStatement3.setString(1, jancode);


	        int line = pStatement1.executeUpdate();
	        line = pStatement2.executeUpdate();
	        line = pStatement3.executeUpdate();

	        pStatement1.close();
	        pStatement2.close();
	        pStatement3.close();
	        connection.close();

	        return line;
	 }







}
