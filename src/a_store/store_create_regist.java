package a_store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.StoresDAO;

@WebServlet(urlPatterns={"/admin/a_create_store_1/regist"})
public class store_create_regist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		店舗名を取得
		String sto_name = req.getParameter("sto_name");

//		電話番号を取得
		String sto_tel = req.getParameter("sto_tel");

//		メールアドレスを取得
		String sto_mail = req.getParameter("sto_mail");

//		住所
		String sto_address = req.getParameter("sto_address");

//		グループID
		String sto_gru_id = req.getParameter("gru_id");

		System.out.println(sto_name);
		System.out.println(sto_tel);
		System.out.println(sto_mail);
		System.out.println(sto_address);
		System.out.println(sto_gru_id);



		String address = sto_address;
//      if (address == null || address.isEmpty()) {
//          address = "埼玉県熊谷市"; // デフォルトの住所
//      }
      try {
          // 住所から座標を取得するメソッド

              String urlString = "https://msearch.gsi.go.jp/address-search/AddressSearch?q=" + address;
              URL url = new URL(urlString);
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              conn.setRequestMethod("GET");

              int responseCode = conn.getResponseCode();
              if (responseCode != 200) {
                  throw new IOException("Unexpected response code: " + responseCode);
              }

              BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
              String inputLine;
              StringBuilder response = new StringBuilder();
              while ((inputLine = in.readLine()) != null) {
                  response.append(inputLine);
              }
              in.close();

           // レスポンス内容をログに出力
              System.out.println("Response: " + response.toString());


           // JSON レスポンスから座標を抽出
              JSONArray jsonArray = new JSONArray(response.toString());
              JSONObject geometry = jsonArray.getJSONObject(0).getJSONObject("geometry");
              JSONArray coordinates = geometry.getJSONArray("coordinates");
              double lon = coordinates.getDouble(0);
              double lat = coordinates.getDouble(1);

              ArrayList<Double> b_list = new ArrayList<Double>();
              b_list.add(lat);
              b_list.add(lon);

              // 座標をコンソールに表示
              System.out.println(b_list);

              System.out.println(lat);
//              System.out.println(lat instanceof double);


              System.out.println(lon);


//              JSONObject json = new JSONObject(response.toString());
//              double lat = json.getJSONArray("results").getJSONObject(0).getDouble("lat");
//              double lon = json.getJSONArray("results").getJSONObject(0).getDouble("lon");
//
//              ArrayList<Double> b_list= new ArrayList<Double>();
//              b_list.add(lat);
//              b_list.add(lon);

              // 国土地理院APIを使用して住所から座標を取得
//            double[] coordinates = getCoordinatesFromAddress(address);
            // 座標をコンソールに表示
//                System.out.println(b_list);
//            System.out.println("Latitude: " + b_list[0] + ", Longitude: " + b_list[1]);


//              // Double型に変換
              Double latitude = Double.valueOf(lat);
              Double longitude = Double.valueOf(lon);


              System.out.println("Double型↓");
              System.out.println(latitude);
              System.out.println(longitude);


//              店舗登録DAO
              StoresDAO dao = new StoresDAO();


//              try {


				int sto_reg_dao = dao.insert(sto_name, sto_tel, sto_gru_id, latitude, longitude);
				System.out.println(sto_reg_dao);

				req.getRequestDispatcher("/admin/store_create_3.jsp").forward(req, resp);


//              } catch (Exception e) {
//
//
//            	  System.out.println("DAOでエラー起きとるで");
//
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
				HttpSession session = req.getSession();

		       	 String error_message = null;

		  		session.setAttribute("store_create_error", error_message);


				req.getRequestDispatcher("/admin/store_create_3.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("DAOと何だこのエラー");
            HttpSession session = req.getSession();
       	 String error_message = ("正しい情報を入力してください");

 		session.setAttribute("store_create_error", error_message);

            // 初期サーブレットにリダイレクト
//            resp.sendRedirect(req.getContextPath() + "/admin/a_create_store_1");
//   			req.getRequestDispatcher("/admin/a_create_store_1").forward(req, resp);
   	        // エラーページへのリダイレクト
   	        resp.sendRedirect(req.getContextPath() + "/admin/a_create_store_1");

        }







    }
}
