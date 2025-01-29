package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.JsonProduct;
import bean.Products;

public class ProductsDAO extends DAO {
    public int insert(String janCode, String pName) throws Exception {
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("insert into products values (?, ?, false)");

       pStatement.setString(1, janCode);
       pStatement.setString(2, pName);

       int line = pStatement.executeUpdate();

       pStatement.close();
       connection.close();

        return line;
    }

    public List<Products> all() throws Exception {
        return search("");
    }

    public List<Products> search(String janCode) throws Exception {
        List<Products> products = new ArrayList<Products>();

        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("select * from products where JAN_CODE = ?");
        pStatement.setString(1, janCode);


        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Products product = new Products();

            product.setJanCode(rSet.getString("jan_code"));
            product.setName(rSet.getString("name"));
            product.setCommon(rSet.getBoolean("is_common"));

            products.add(product);
        }

        pStatement.close();
        connection.close();

        return products;
    }

    public int updateStatusToTrue(String janCode) throws Exception {
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("update products set is_common = true where jan_code = ?");

        pStatement.setString(1, janCode);

        int line = pStatement.executeUpdate();

        pStatement.close();
        connection.close();

        return line;
    }

    // 複数件登録はこちら↓↓↓
    public int insertProducts(List<JsonProduct> products, String g_id) throws Exception {
        int line = 0;
        int nextId1 = 1;
        int nextId2 = 1;
        Connection connection = getConnection();
        PreparedStatement getNextIdStatement1 = connection.prepareStatement("select max(id) as nextId from product_foods");
        PreparedStatement getNextIdStatement2 = connection.prepareStatement("select max(id) as nextId from group_products");
        PreparedStatement pStatement1 = connection.prepareStatement("insert into products values (?, ?, ?)");
        PreparedStatement pStatement2 = connection.prepareStatement("insert into product_foods values (?, ?, ?)");
        PreparedStatement pStatement3 = connection.prepareStatement("insert into group_products values (?, ?, ?)");

        try {
           ResultSet nextIdResult = getNextIdStatement1.executeQuery();
           if (nextIdResult.next()) {
               nextId1 = nextIdResult.getInt("nextId") + 1;
           }

           ResultSet nextGPIdResult = getNextIdStatement2.executeQuery();
           if (nextGPIdResult.next()) {
               nextId2 = nextGPIdResult.getInt("nextId") + 1;
           }





           connection.setAutoCommit(false);

            for (JsonProduct p: products) {
                System.out.println(p.getJan());
                pStatement1.setString(1, p.getJan());
                pStatement1.setString(2, p.getName());
                pStatement1.setBoolean(3, false);
                pStatement1.addBatch();
                pStatement3.setInt(1, nextId2);
                pStatement3.setString(2, p.getJan());
                // ここにgroup_idをセット
                pStatement3.setString(3, g_id);
                pStatement3.addBatch();
                nextId2++;
                for (String s: p.getCheckedItemsId()) {
                    pStatement2.setInt(1, nextId1);
                    pStatement2.setString(2, p.getJan());
                    pStatement2.setString(3, s);
                    pStatement2.addBatch();
                    nextId1++;
                }
            }

            line = pStatement1.executeBatch().length;
            line += pStatement2.executeBatch().length;
            line += pStatement3.executeBatch().length;

            connection.commit();
            System.out.println("commit");
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            line = 0;
        }

        pStatement3.close();
        pStatement2.close();
        pStatement1.close();
        getNextIdStatement2.close();
        getNextIdStatement2.close();
        connection.close();

        return line;
    }
}


























