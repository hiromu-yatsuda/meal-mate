package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bean.GroupAccounts;

public class GroupAccountsDAO extends DAO {
    /**
     * @param email
     * @return true: 存在する<br> false: 存在しない
     * @throws Exception
     */
    public boolean searchByEmail(String email) throws Exception {
        Connection connection = getConnection();
        boolean isExist = true;

        PreparedStatement pStatement = connection.prepareStatement("select * from group_accounts where email like ?");

        pStatement.setString(1, email);

        ResultSet rSet = pStatement.executeQuery();

        if (!rSet.next()) {
            isExist = false;
        }

        pStatement.close();
        connection.close();

        return isExist;
    }


//  list_seachByEmail
    public List<GroupAccounts> list_seachByEmail(String email) throws Exception {
        Connection connection = getConnection();
        List<GroupAccounts> groupAccounts = new ArrayList<GroupAccounts>();
//        GroupsDAO gDao = new GroupsDAO();

        PreparedStatement pStatement = connection.prepareStatement("select * from group_accounts where email like ?");
        pStatement.setString(1, email);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            GroupAccounts groupAccount = new GroupAccounts();
            groupAccount.setId(rSet.getString("id"));
            groupAccount.setName(rSet.getString("name"));
            groupAccount.setEmail(rSet.getString("email"));
            groupAccount.setPassword(rSet.getString("password"));
            groupAccount.setLastLogin(rSet.getDate("last_login"));
            groupAccount.setPasswordUpdated(rSet.getDate("password_updated"));
//            groupAccount.setGroups(gDao.search(rSet.getString("group_code")));
//            setGroupCode

            groupAccount.setGroupCode(rSet.getString("group_code"));
            groupAccount.setAdmin(rSet.getBoolean("is_admin"));



            groupAccounts.add(groupAccount);
        }

//        if (groupAccounts.isEmpty()) {
//            System.out.println("No data found for email: " + email);
//        }

        pStatement.close();
        connection.close();

        return groupAccounts;
    }



//  従業員一覧画面用
    public List<GroupAccounts> list_seachByGid(String group_id) throws Exception {
        Connection connection = getConnection();
        List<GroupAccounts> groupAccounts = new ArrayList<GroupAccounts>();
//        GroupsDAO gDao = new GroupsDAO();

        PreparedStatement pStatement = connection.prepareStatement("select * from group_accounts where GROUP_CODE  like ?");
        pStatement.setString(1, group_id);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            GroupAccounts groupAccount = new GroupAccounts();
            groupAccount.setId(rSet.getString("id"));
            groupAccount.setName(rSet.getString("name"));
            groupAccount.setEmail(rSet.getString("email"));
            groupAccount.setPassword(rSet.getString("password"));
            groupAccount.setLastLogin(rSet.getDate("last_login"));
            groupAccount.setPasswordUpdated(rSet.getDate("password_updated"));
//            groupAccount.setGroups(gDao.search(rSet.getString("group_code")));
//            setGroupCode

            groupAccount.setGroupCode(rSet.getString("group_code"));
            groupAccount.setAdmin(rSet.getBoolean("is_admin"));



            groupAccounts.add(groupAccount);
        }

//        if (groupAccounts.isEmpty()) {
//            System.out.println("No data found for email: " + email);
//        }

        pStatement.close();
        connection.close();

        return groupAccounts;
    }





    public List<GroupAccounts> all() throws Exception {
        Connection connection = getConnection();
        List<GroupAccounts>groupAccounts = new ArrayList<GroupAccounts>();
        GroupsDAO gDao = new GroupsDAO();

        PreparedStatement pStatement = connection.prepareStatement("select * from group_accounts");

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            GroupAccounts groupAccount = new GroupAccounts();

            groupAccount.setId(rSet.getString("id"));
            groupAccount.setName(rSet.getString("name"));
            groupAccount.setEmail(rSet.getString("email"));
            groupAccount.setLastLogin(rSet.getDate("last_login"));
            groupAccount.setPasswordUpdated(rSet.getDate("password_updated"));
            groupAccount.setGroups(gDao.search(rSet.getString("group_code")));
            groupAccount.setAdmin(rSet.getBoolean("is_admin"));

            groupAccounts.add(groupAccount);
        }

        pStatement.close();
        connection.close();

        return groupAccounts;
    }

    /**
     *
     * @param id
     * @return 存在する: GroupAccounts<br> 存在しない: null
     * @throws Exception
     */
    public GroupAccounts searchById(String id) throws Exception {
        Connection connection = getConnection();
//        GroupsDAO gDao = new GroupsDAO();
        GroupAccounts groupAccount = null;

        PreparedStatement pStatement = connection.prepareStatement("select * from group_accounts where id like ?");
        pStatement.setString(1, id);

        ResultSet rSet = pStatement.executeQuery();

        if (rSet.next()) {
            groupAccount = new GroupAccounts();
            groupAccount.setId(rSet.getString("id"));
            groupAccount.setName(rSet.getString("name"));
            groupAccount.setEmail(rSet.getString("email"));

            groupAccount.setLastLogin(rSet.getDate("last_login"));
            groupAccount.setPasswordUpdated(rSet.getDate("password_updated"));
//            groupAccount.setGroups(gDao.search(rSet.getString("group_code")));
            groupAccount.setAdmin(rSet.getBoolean("is_admin"));
            groupAccount.setGroupCode(rSet.getString("group_code"));

        } else {
            System.out.println("データなし（重複なし）");
        }

        rSet.close();
        pStatement.close();
        connection.close();

        return groupAccount;
    }

    public boolean insert(String id,String name, String email, String gId, String password, boolean isAdmin) throws Exception {
        Connection connection = getConnection();
//        String id = "";
//
//        PreparedStatement pStatement1 = connection.prepareStatement("select * from group_accounts where id = ?");
        // パスワードはとりあえずnull
        PreparedStatement pStatement2 = connection.prepareStatement("insert into group_accounts values (?, ?, ?, ?, null, null, ?, ?)");

//        IDランダム生成サーブレットで作っちゃった
//        while (true) {
//            id = createRandom(6);
//            pStatement1.setString(1, id);
//            ResultSet rSet = pStatement1.executeQuery();
//
//            if (!rSet.next()) {
//                break;
//            }
//        }

        pStatement2.setString(1, id);
        pStatement2.setString(2, name);
        pStatement2.setString(3, email);
        pStatement2.setString(4, password);
        pStatement2.setString(5, gId);
        pStatement2.setBoolean(6, isAdmin);

        int line = pStatement2.executeUpdate();

//        pStatement1.close();
        pStatement2.close();
        connection.close();

        return line > 0 ? true: false;
    }

    private String createRandom(int digits) {
        Random rand = new Random();

        int min = (int)Math.pow(10, digits - 1);
        int max = (int)Math.pow(10, digits) - 1;

        int id = rand.nextInt((max - min) + 1) + min;

        return Integer.toString(id);
    }



    public boolean delete(String id) throws Exception {
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("delete from group_accounts where id = ?");

        pStatement.setString(1, id);

        int line = pStatement.executeUpdate();

        return line > 0 ? true: false;
    }
}























