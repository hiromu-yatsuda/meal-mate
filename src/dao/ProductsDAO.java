package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

        PreparedStatement pStatement = connection.prepareStatement("select * from products where JAN_CODE like '%?%'");

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
}


























