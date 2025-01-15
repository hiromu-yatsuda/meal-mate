package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bean.Stores;

public class StoresDAO extends DAO {
    public List<Stores> all() throws Exception {

        return searchBygId("");
    }

    public List<Stores> searchBygId(String gId) throws Exception {
//        GroupsDAO gDao = new GroupsDAO();

        List<Stores> stores = new ArrayList<Stores>();
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("select * from Stores where group_code = ? order by name");

        pStatement.setString(1, gId);

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Stores store = new Stores();
//            Groups group = gDao.search(rSet.getString("group_code"));

            store.setStoreCode(rSet.getString("store_code"));
            store.setName(rSet.getString("name"));
//            store.setGroups(group);

            store.setGroup_Code(rSet.getString("group_code"));

            store.setPhoneNum(rSet.getString("phone_num"));
            store.setLatitude(rSet.getDouble("latitude"));
            store.setLongitude(rSet.getDouble("longitude"));
            store.setOpeningTime(rSet.getTime("opening_time"));
            store.setClosingTime(rSet.getTime("closing_time"));
            store.setAvg_amount_low(rSet.getInt("avg_amount_low"));
            store.setAvg_amount_high(rSet.getInt("avg_amount_high"));
            store.setFigure1(rSet.getString("figure1"));
            store.setFigure1(rSet.getString("figure2"));
            store.setFigure1(rSet.getString("figure3"));
            // テーブルのカラムがis_actionになっている(is_activeの間違い?)
            // rSet.getBoolean("is_action")の返り値がTRUEだとエラーの可能性
            store.setActive(rSet.getBoolean("is_action"));

            stores.add(store);
        }

        pStatement.close();
        connection.close();

        return stores;
    }


//  ページネーション用にlimit（取得件数） offset（どこから）を設定
    public List<Stores> searchBygId_20(String gId, int num, int start) throws Exception {
//      GroupsDAO gDao = new GroupsDAO();

      List<Stores> stores = new ArrayList<Stores>();
      Connection connection = getConnection();

      PreparedStatement pStatement = connection.prepareStatement("select * from Stores where group_code = ? limit ? offset ?");

      pStatement.setString(1, gId);
      pStatement.setInt(2, num);
      pStatement.setInt(3, start);

      ResultSet rSet = pStatement.executeQuery();

      while (rSet.next()) {
          Stores store = new Stores();
//          Groups group = gDao.search(rSet.getString("group_code"));

          store.setStoreCode(rSet.getString("store_code"));
          store.setName(rSet.getString("name"));
//          store.setGroups(group);
          store.setPhoneNum(rSet.getString("phone_num"));
          store.setLatitude(rSet.getDouble("latitude"));
          store.setLongitude(rSet.getDouble("longitude"));
          store.setOpeningTime(rSet.getTime("opening_time"));
          store.setClosingTime(rSet.getTime("closing_time"));
          store.setAvg_amount_low(rSet.getInt("avg_amount_low"));
          store.setAvg_amount_high(rSet.getInt("avg_amount_high"));
          store.setFigure1(rSet.getString("figure1"));
          store.setFigure1(rSet.getString("figure2"));
          store.setFigure1(rSet.getString("figure3"));
          // テーブルのカラムがis_actionになっている(is_activeの間違い?)
          // rSet.getBoolean("is_action")の返り値がTRUEだとエラーの可能性
          store.setActive(rSet.getBoolean("is_action"));

          stores.add(store);
      }

      pStatement.close();
      connection.close();

      return stores;
  }



    public List<Stores> searchBysId(String sId) throws Exception {
//        GroupsDAO gDao = new GroupsDAO();


        List<Stores> stores_list = new ArrayList<Stores>();



        Connection connection = getConnection();




        PreparedStatement pStatement = connection.prepareStatement("select * from Stores where store_code LIKE ?");



        pStatement.setString(1, sId);

        ResultSet rSet = pStatement.executeQuery();


        while (rSet.next()) {



        Stores store = new Stores();
//        Groups group = gDao.search(rSet.getString("group_code"));


        System.out.println("結果");
        System.out.println(store);



        store.setStoreCode(rSet.getString("store_code"));
        store.setName(rSet.getString("name"));
//        store.setGroups(group);

        store.setGroup_Code(rSet.getString("group_code"));
        store.setPhoneNum(rSet.getString("phone_num"));
        store.setLatitude(rSet.getDouble("latitude"));
        store.setLongitude(rSet.getDouble("longitude"));
        store.setOpeningTime(rSet.getTime("opening_time"));
        store.setClosingTime(rSet.getTime("closing_time"));
        store.setAvg_amount_low(rSet.getInt("avg_amount_low"));
        store.setAvg_amount_high(rSet.getInt("avg_amount_high"));
        store.setFigure1(rSet.getString("figure1"));
        store.setFigure1(rSet.getString("figure2"));
        store.setFigure1(rSet.getString("figure3"));
        // テーブルのカラムがis_actionになっている(is_activeの間違い?)
        // rSet.getBoolean("is_action")の返り値がTRUEだとエラーの可能性
        store.setActive(rSet.getBoolean("is_action"));


        stores_list.add(store);



        }

        pStatement.close();
        connection.close();

        return stores_list;
    }

    public int delete(String sId) throws Exception {
        Connection connection = getConnection();

        PreparedStatement pStatement = connection.prepareStatement("detele from stores where store_code = ?");

        pStatement.setString(1, sId);

        int line = pStatement.executeUpdate();

        pStatement.close();
        connection.close();

        return line;
    }

    public int update(String sCode, Time opTime, Time clTime, int low, int high, String fig1, String fig2, String fig3, boolean isActive) throws Exception {
        Connection connection = getConnection();

        // is_actionをis_activeに直す場合、ここのis_actionも直す
        PreparedStatement pStatement = connection.prepareStatement("update stores set opening_time = ?, closing_time = ?, avg_amount_low = ?, avg_amount_high = ?, figure1 = ?, figure2 = ?, figure3 = ?, is_action = ? where store_code = ?");

        pStatement.setTime(1, opTime);
        pStatement.setTime(2, clTime);
        pStatement.setInt(3, low);
        pStatement.setInt(4, high);
        pStatement.setString(5, fig1);
        pStatement.setString(6, fig2);
        pStatement.setString(7, fig3);
        pStatement.setBoolean(8, isActive);
        pStatement.setString(9, sCode);



        int line = pStatement.executeUpdate();

        pStatement.close();
        connection.close();

        return line;
    }

    public int insert(String name, String phoneNum, String gId, Double latitude, Double longitude ) throws Exception {
        Connection connection = getConnection();
        PreparedStatement checkPs = connection.prepareStatement("select * from stores where store_code = ?");
        String sId = "";

        while (true) {
            sId = createRandom(6);
            checkPs.setString(1, sId);
            ResultSet rSet = checkPs.executeQuery();

            if (!rSet.next()) {
                break;
            }
        }

        PreparedStatement pStatement = connection.prepareStatement("INSERT INTO stores (store_code, name, group_code, phone_num, latitude, longitude, opening_time, closing_time, avg_amount_low, avg_amount_high, figure1, figure2, figure3, is_action) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, null, null, null, null, null, null, null, false)");

        pStatement.setString(1, sId);
        pStatement.setString(2, name);
        pStatement.setString(3, gId);
        pStatement.setString(4, phoneNum);
        // 住所から緯度経度を取得(APIは一定回数まで無料?)
        pStatement.setDouble(5, latitude);
        pStatement.setDouble(6, longitude);

        int line = pStatement.executeUpdate();

        checkPs.close();
        pStatement.close();
        connection.close();

        return line;
    }

    private String createRandom(int digits) {
        Random rand = new Random();
        int min = (int)Math.pow(10, digits - 1);
        int max = (int)Math.pow(10, digits) -1;

        return Integer.toString(rand.nextInt((max - min) + 1) + min);
    }
}






































