package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.GroupProducts;
import bean.Groups;
import bean.Products;

public class GroupProductsDAO extends DAO {
    public List<GroupProducts> searchByJanCode(String[] janCode) throws Exception {
        Connection connection = getConnection();
        List<GroupProducts> gProducts = new ArrayList<GroupProducts>();
        // asで取得した名称でgetString等できるかは不明
        PreparedStatement pStatement = connection.prepareStatement("select gp.id as id, g.group_code as group_code, g.phone_num as phone_num, g.email as email, g.name as group_name, p.jan_code as jan_code, p.name as product_name, p.is_common as is_common from group_products gp join groups_s g on gp.group_id = g.group_code join products p on gp.jan_code = p.jan_code where gp.jan_code = ?");

        for (String jc: janCode) {
            pStatement.setString(1, jc);

            ResultSet rSet = pStatement.executeQuery();

            if (rSet.next()) {
                GroupProducts gProduct = new GroupProducts();
                Groups group = new Groups();
                Products product = new Products();

                gProduct.setId(rSet.getInt("id"));
                group.setGroupCode(rSet.getString("group_code"));
                group.setPhoneNum(rSet.getString("phone_num"));
                group.setEmail(rSet.getString("email"));
                group.setName(rSet.getString("group_name"));
                gProduct.setGroup(group);
                product.setJanCode(rSet.getString("jan_code"));
                product.setName(rSet.getString("product_name"));
                product.setCommon(rSet.getBoolean("is_common"));
                gProduct.setProducts(product);

                gProducts.add(gProduct);
            }
        }

        pStatement.close();
        connection.close();

        return gProducts;
    }

    public boolean insert(String janCode, String gId) throws Exception {
        Connection connection = getConnection();

        PreparedStatement nextIdPs = connection.prepareStatement("select id from group_products order by id desc limit 1");
        int nextId = 1;




        PreparedStatement pStatement = connection.prepareStatement("insert into group_products values (?, ?, ?)");


        ResultSet rSet = nextIdPs.executeQuery();
        if (rSet.next()) {
            nextId = rSet.getInt("id") + 1;
        }

        pStatement.setInt(1, nextId);
        pStatement.setString(2, janCode);
        pStatement.setString(3, gId);

        int line = pStatement.executeUpdate();

        nextIdPs.close();
        pStatement.close();
        connection.close();

        return line > 0 ? true: false;
    }

    public boolean searchByJ_G(String janCode, String gId) throws Exception {
        Connection connection = getConnection();

        boolean torf = false;

        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM GROUP_PRODUCTS where JAN_CODE = ? AND GROUP_ID = ? ");

        pStatement.setString(1, janCode);
        pStatement.setString(2, gId);



        ResultSet rSet = pStatement.executeQuery();



        if (rSet == null || !rSet.next()) {
            torf = true; // データが見つからない場合
        } else {
            torf = false; // データが見つかった場合
        }

        rSet.close();
        pStatement.close();
        connection.close();

        return torf;
    }




}
























