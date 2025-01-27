package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.CombFoods;

public class CombFoodsDAO extends DAO {

    public List<CombFoods> getAllCombFoods() throws Exception {
        List<CombFoods> combFoods = new ArrayList<>();
        CombFoods comb = null;
        String prevName = null;
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select "
                + "cff.id as id, cf.name as cfName, f.name as fName  "
                + "from comb_foods as cf "
                + "join comb_foods_foods as cff "
                + "on cf.id = cff.comb_foods_id "
                + "join foods as f "
                + "on cff.foods_id = f.id;"
        );

        ResultSet rSet = pStatement.executeQuery();

        rSet.next();

        while (true) {
            if (prevName == null || prevName != rSet.getString("cfName")) {
                if (prevName != null) {
                    combFoods.add(comb);
                }
                comb = new CombFoods();
                prevName = rSet.getString("cfName");
                comb.setId(rSet.getInt("id"));
                comb.setName(prevName);
            }
            comb.setFoods(rSet.getString("fName"));

            if (!rSet.next()) {
                combFoods.add(comb);
                break;
            }
        }

        return combFoods;
    }
}
