package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Foods;

public class FoodsDAO extends DAO {
	public List<Foods> all() throws Exception{
		List<Foods> list = new ArrayList<Foods>();

		// データベースに接続
		Connection con = getConnection();

		// 実行したいSQL文をプリペアードステートメントで準備
		PreparedStatement st = con.prepareStatement(
				"SELECT ID,NAME FROM FOODS "
				);

		ResultSet rs = st.executeQuery();

		// 結果から1件ずつ取り出すループ
		while (rs.next()) {
			Foods f = new Foods();

//			値のセット
			f.setId(rs.getInt("id"));
			f.setFoodName(rs.getString("name"));

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



}
