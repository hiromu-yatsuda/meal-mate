package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO extends DAO {

//	アカウント作成
	 public int insert(String id, String pass) throws Exception {
		 Connection connection = getConnection();
		 PreparedStatement pStatement = connection.prepareStatement("insert into A_ACCOUNTS  values(?,?)");

	        pStatement.setString(1, id);
	        pStatement.setString(2, pass);


	        int line = pStatement.executeUpdate();

	        pStatement.close();
	        connection.close();

	        return line;
	 }


//	 IDからパスワードを取得
	 public String serch(String id) throws Exception {

		 String password = null;

		 Connection connection = getConnection();
		 PreparedStatement pStatement = connection.prepareStatement("SELECT *  FROM A_ACCOUNTS where ID LIKE ?");


		 pStatement.setString(1, id);

	        ResultSet rSet = pStatement.executeQuery();


	        if(rSet.next()) {
	        	password = rSet.getString("password");
	        }



	        rSet.close();
	        pStatement.close();
	        connection.close();

	        return password;

	 }

}
