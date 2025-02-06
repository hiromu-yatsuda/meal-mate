package s_foods;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import dao.FoodsDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/stuff/foods/regist/csv"})
@MultipartConfig
public class s_foods_regist_csv extends CommonServlet {
    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {




    	req.getRequestDispatcher("/stuff/foods_regist_csv.jsp").forward(req, resp);
    }

    @Override

    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    	HttpSession session = req.getSession();
	    String gru_id = (String) session.getAttribute("s_g_id");
	    gru_id = "100"; // グループIDの仮置き

	    // ここから追加
	    PrintWriter out = resp.getWriter();
	    List<Map<String, String>> pfMaps = new ArrayList<>();

	    // ファイルの取得
	    Part filePart = req.getPart("file");
	    if (filePart == null || filePart.getSize() == 0) {
	        out.println("ファイルが選択されていない、またはファイルが空です。");
	        return;
	    }

	    // ファイルの読み込み
	    InputStream inputStream = filePart.getInputStream();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	    List<JSONObject> jsonList = new ArrayList<>();
	    JSONObject json = new JSONObject();
        Map<String, String> foodMap = (new FoodsDAO()).getAllNameAndId();
	    String line;

	    while ((line = reader.readLine()) != null) {
	        List<String> idList = new ArrayList<>();
	        String[] columns = line.split(",");

	        if (columns.length < 2) continue;

	        List<String> foods = new ArrayList<>();
            for (int i = 2; i < columns.length; i++) {
                try {
                    idList.add(foodMap.get(columns[i].trim()));
                    foods.add(columns[i].trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

	        json.put("isJan", false);
	        json.put("name", columns[1].trim());
	        json.put("jan", columns[0].trim());
	        json.put("foodName", foods);
	        json.put("checkedItemsId", idList);

	        jsonList.add(json);
	    }
	    reader.close();

	    // 化合物の考慮は必要ない?

	    System.out.println(jsonList);
	    session.setAttribute("json", jsonList);
	    req.getRequestDispatcher("/stuff/foods_regist_con.jsp").forward(req, resp);

	    // ここまで追加


//    	// アップロードされたファイルを取得
//        Part filePart = req.getPart("file");
//
////      jspへ持って行って申し分ない整っているリスト用
////      この型で
////        最終next_full_list
////        [[さけ, 豚肉, ゼラチン, 桃鯖, 39574459585], [えび, 鯖缶, 794937394], [グラタン, 8493947949]]
////        最終re_next_full_list
////        [[17, 22, 27, 桃鯖, 39574459585], [1, 鯖缶, 794937394], [グラタン, 8493947949]]
//
//
////		確認画面にて
////      画面表示用
//        ArrayList<ArrayList<String>> next_full_list = new ArrayList<ArrayList <String>>();
//
////      データ登録用
//        ArrayList<ArrayList<String>> re_next_full_list = new ArrayList<ArrayList <String>>();
//
//
////      JANコード
//        String jancode = null;
//
////		商品名
//        String pro_name = null;
//
//
//
//        // ファイルの内容を読み取るためのBufferedReaderを作成
//        BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
//        String line;
//
//
//
////        実験ターーーーーーーーーーーーーーイム
//     // ファイルの行をリストに格納
//        List<String> lines = new ArrayList<>();
//        while ((line = reader.readLine()) != null) {
//            lines.add(line);
//        }
//
//
//     // ラベルリストを初期化
//        String[] label_list = null;
//
//        String[] foods_list2 = null;
//
//     // forループで行を処理
//     for (int i = 0; i < lines.size(); i++) {
//
////    	 nullデータ排除用
////    	 横何個目なのか
//    	 int ii6 = 0;
//
////    	 list<list>用サブリスト達
//    	 ArrayList<String> next_sub = new ArrayList<>();
//         ArrayList<String> re_next_sub = new ArrayList<>();
//
//
//
//    	// 最初の行をラベルリストとして処理
//         foods_list2 = lines.get(i).split(",");
//
//
//         System.out.println(foods_list2);
//
////         csv1列目（ラベル）
//         if(i<=0){
//        	 label_list = lines.get(i).split(",");
//         }else{
//
//
////        	 csv2行目以降（入力データ）
//         for(int i6 = 0; i6 < foods_list2.length ; i6++){
//
//        	 ii6 = i6;
//
//        	 System.out.println(label_list[i6]);
//        	 System.out.println(foods_list2[i6]);
//
//        	 if(i6==0){
//
////        		 JANコードを避難
//        		 jancode = foods_list2[i6];
//        		 System.out.println("jancode");
//        		 System.out.println(jancode);
//
//
//        	 }else if(i6==1){
//
////        		 商品名を避難
//        		 pro_name = foods_list2[i6];
//        		 System.out.println("pro_name");
//        		 System.out.println(pro_name);
//
//        	 }else{
//
//        		 if(foods_list2[i6].isEmpty()){
//
//        		 }else{
////        		 データ登録用サブにIDを登録
//        		 String s_i6 = String.valueOf(i6 - 1);
//
//        		 System.out.println("エラーの原因↓");
//        		 System.out.println(s_i6);
//
//        		 System.out.println("エラー手前");
//        		 System.out.println(re_next_sub);
//        		 re_next_sub.add(s_i6);
//
//        		 next_sub.add(label_list[i6]);
////        		 foods_list2[i6]
//
//
//        		 System.out.println("登録用");
//        		 System.out.println(s_i6);
//
//        		 System.out.println("表示用");
//        		 System.out.println(label_list[i6]);
//
//        		 }
//
//        	 }
//
//         }
//
//
//         System.out.println("データ何周した？");
//         System.out.println(ii6 + "周");
//
////         0周=改行のみ入ってしまっている
//         if(ii6!=0){
//
//
////     		商品名を追加
//             next_sub.add(pro_name);
//             re_next_sub.add(pro_name);
//
////             JANコードを追加
//             next_sub.add(jancode);
//             re_next_sub.add(jancode);
//
//        	 System.out.println(next_sub);
//        	 System.out.println(re_next_sub);
//
//
//
////             まとめリストに追加
//             next_full_list.add(next_sub);
//             re_next_full_list.add(re_next_sub);
//
//
//         }
//
//         }
//
//     }
//
//
//	 System.out.println(next_full_list);
//	 System.out.println(re_next_full_list);
//
//
//
//
//  // BufferedReaderを閉じる
//     reader.close();
//
//
//
//
////     csvを使う場合、JANコードは必須になるため
//    boolean y_or_n_janB = false;
//
//
//
////	表示データリスト
////	画面表示用
//	req.setAttribute("next_full_list",next_full_list );
//	req.setAttribute("y_or_n_janB",y_or_n_janB );
//	req.setAttribute("gru_id",gru_id );
//
//
//
////	データ送信、登録用
//	req.setAttribute("re_next_full_list",re_next_full_list );
//
//	req.getRequestDispatcher("/stuff/foods_regist_con.jsp").forward(req, resp);




    }


}
