package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.CombFoods;

public class CombFoodsDAO extends DAO {
    public List<CombFoods> all() throws Exception {
        List<CombFoods>combFoods = new ArrayList<CombFoods>();
        CombFoods combFood = null;
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select * from comb_foods");

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            combFood = new CombFoods();
            combFood.setId(rSet.getInt("id"));
            combFood.setName("name");

            combFoods.add(combFood);
        }

        return combFoods;
    }
}
