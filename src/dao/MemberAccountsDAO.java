package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Languages;
import bean.MemberAccounts;

public class MemberAccountsDAO extends DAO {
    /**
     * @param email
     * @return true: 存在する<br> false: 存在しない
     * @throws Exception
     */
    public boolean searchByEmail(String email) throws Exception {
        Connection connection = getConnection();
        boolean isExist = true;

        PreparedStatement pStatement = connection.prepareStatement("select * from member_accounts where email = ?");

        pStatement.setString(1, email);

        ResultSet rSet = pStatement.executeQuery();

        if (!rSet.next()) {
            isExist = false;
        }

        pStatement.close();
        connection.close();

        return isExist;
    }


    public List<MemberAccounts> list_searchByEmail(String email) throws Exception {
        Connection connection = getConnection();

        List<MemberAccounts> accounts_list = new ArrayList<MemberAccounts>();

        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM MEMBER_ACCOUNTS where email = ? ");

        pStatement.setString(1, email);

        ResultSet rSet = pStatement.executeQuery();


        while (rSet.next()) {

        	MemberAccounts account = new MemberAccounts();

        	account.setId(rSet.getString("id"));
        	account.setName(rSet.getString("name"));
        	account.setPassword(rSet.getString("password"));
        	account.setLanguage_id(rSet.getString("language_id"));

        	accounts_list.add(account);
        }

        pStatement.close();
        connection.close();

        return accounts_list;


    }






    public Languages searchLanguageByMemberId(String mId) throws Exception {
        Connection connection = getConnection();
        PreparedStatement pStatement = connection.prepareStatement("select l.id as id, l.name as lang_name, l.code as lang_code from member_accounts m join languages l on m.language_id = l.id where m.id = '?';");
        Languages language = null;

        pStatement.setString(1, mId);

        ResultSet rSet = pStatement.executeQuery();

        if (rSet.next()) {
            language = new Languages();

            language.setId(rSet.getInt("id"));
            language.setName(rSet.getString("lang_name"));
            language.setCode(rSet.getString("lang_code"));
        }

        pStatement.close();
        connection.close();

        return language;
    }


    public boolean up_last_log(String email) throws Exception {
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("update MEMBER_ACCOUNTS set LAST_LOGIN  = now() where EMAIL  = ? ");

        pStatement.setString(1, email);

        int line = pStatement.executeUpdate();

        pStatement.close();
        connection.close();

        return line > 0 ? true: false;

    }


//    登録
    public boolean insert(String id,String name, String email,  String password, int lang_id) throws Exception {
        Connection connection = getConnection();

//        insert into MEMBER_ACCOUNTS  values ('000020', 'aaaa', 'asdfghj@fyuj.kjhg', 'pass', null, '1',now())
        PreparedStatement pStatement = connection.prepareStatement("insert into MEMBER_ACCOUNTS  values (?, ?, ?, ?, null, ? ,now())");

        pStatement.setString(1, id);
        pStatement.setString(2, name);
        pStatement.setString(3, email);
        pStatement.setString(4, password);
        pStatement.setInt(5, lang_id);

        int line = pStatement.executeUpdate();

        pStatement.close();
        connection.close();

        return line > 0 ? true: false;

    }




//  IDの重複チェック
//  dup　false:重複していない
  public boolean id_dup(String id) throws Exception {
      Connection connection = getConnection();

      PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM MEMBER_ACCOUNTS where ID like ?");

      pStatement.setString(1, id);


//      int line = pStatement.executeUpdate();

      ResultSet rSet = pStatement.executeQuery();
      boolean dup = true;
      if(!rSet.next()){
    	  dup=false;
      }

      pStatement.close();
      connection.close();

      return dup;

  }





}

































