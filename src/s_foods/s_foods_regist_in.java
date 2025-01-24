package s_foods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Products;
import dao.GroupProductsDAO;
import dao.ProductFoods;
import dao.ProductsDAO;
import tool.CommonServlet;

//アノテーションurl　リクエストされたら実行
@WebServlet(urlPatterns={"/stuff/foods/regist/comp"})
public class s_foods_regist_in extends CommonServlet{

	@Override
	protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {


	}

	@Override
	protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {


//		[[食材,商品名,JANコード],[食材,商品名,JANコード]]
	    String a2 = req.getParameter("re_next_full_list");

//	    JANコード必要or不必要
	    String y_or_n_jan = req.getParameter("y_or_n_janB");
	    boolean b_y_or_n_jan = false;


//	    グループID
	    String gru_id = req.getParameter("gru_id");

//	    仮置き
	    gru_id = "101";

    	System.out.println("しょっぱな");
    	System.out.println(a2);

	    System.out.println("y_or_n_jan");
	    System.out.println(y_or_n_jan);
	    System.out.println("gru_id");
	    System.out.println(gru_id);


	    Object m = req.getParameter("re_next_full_list");
	    System.out.println(m);

	    String ms = (String)m;
	    String ms2 = "";


//	    力技2

//	    ArrayList<String> b1 = new ArrayList<String>();
	    ms2 = ms.replace("[", "@");
	    String ms3 = ms2.replace("]", "@");
	    System.out.println("msのreplace後");
	    System.out.println(ms2);
	    System.out.println(ms3);


//		int n = a.length() - 2;
//
//
//		String ppp = a.substring(2, n);

	    int spsp = ms3.length() -2;

	    String spspsp = ms3.substring(2, spsp);

	    System.out.println("spspのあと");
	    System.out.println(spspsp);

	    System.out.println("ms3のあと");
	    System.out.println(ms3);


	    String[] sp3 = spspsp.split("@, @");
	    System.out.println("s3の後や");
	    System.out.println(sp3);



	    for(String lis1 : sp3){
	    	System.out.println("lis1");
	    	System.out.println(lis1);
//	    	1, 6, 11, 桃鯖, 9584300380
//	    	鯖缶, 987656789
//	    	1, グラタン, 345678909876

	    	String[] lis_solo = lis1.split(",");
	    	System.out.println("lis_solo");
	    	System.out.println(lis_solo);

	    //  ランダムJANコード重複チェック用
	        Boolean dup = false;

//	        ランダムJANコード
	    	String random_jan=null;

//	    	ランダムJANコード生成用
	    	List<String> jan_list = new ArrayList<String>();


//    		dao
    		ProductsDAO p_dao = new ProductsDAO();


	    	b_y_or_n_jan = "true".equals(y_or_n_jan);
	    	System.out.println("JANコードの自動生成どうする");
	    	System.out.println(b_y_or_n_jan);

	    	if(b_y_or_n_jan==true){


	    		while(dup == false){
//	    			グループID用ランダム（13桁）
	    	        Random random = new Random();
	    	        int min = 0;
	    	        int max = 9;


//	    	        13桁の配列を生成
	    	 		for(int i=0; i < 13; i++){

//	    	        minからmaxまでの範囲の乱数を生成
	    	        int id_1 = random.nextInt((max - min) + 1) + min;

//	    			intからStringへ
	    	        String s_id_1 = Integer.toString(id_1);

	    	        jan_list.add(s_id_1);
	    	 		}


//					配列から文字列に
	    	        random_jan = String.join("",jan_list);
	    	        System.out.println(random_jan);

	    	        //
//	        		JANコードで検索後のデータ
	        		List<Products> pro_list1 = p_dao.search(random_jan);


//		    		既にこの商品は登録されていない
		    		if(pro_list1 == null || pro_list1.isEmpty()){
		    			dup = true;
		    		}

		    	}





	    	}





//	    	単体JANコード
	    	String jancode_1 = lis_solo[lis_solo.length - 1].trim();

	    	if(random_jan!=null){
	    		jancode_1 = random_jan;
	    	}


//	    	単体商品名
	    	String pro_name_1 = lis_solo[lis_solo.length - 2].trim();


	    	System.out.println("JANコード");
	    	System.out.println("あああ" + lis_solo[lis_solo.length - 1].trim() + "あああ");
	    	System.out.println("商品名");
	    	System.out.println("あああ" + lis_solo[lis_solo.length - 2].trim() + "あああ");





////    	1,JANコードの重複チェック




//    		商品の制限食材を登録用dao
    		ProductFoods p_f_dao = new ProductFoods();


//
//    		JANコードで検索後のデータ
    		List<Products> pro_list = p_dao.search(jancode_1);


    		System.out.println("dao後のデータ");
    		System.out.println(pro_list);


//    		System.out.println("確認");
//    		for(int i2 =lis_solo.length - 3 ; i2>=0 ; i2--){
//
//	    		System.out.println("あああ" + lis_solo[i2].trim() + "あああ");
//    		}






//	    		既にこの商品は登録されていない
	    		if(pro_list == null || pro_list.isEmpty()){



//	    			84639493

//    		重複していなかったら
//    		1,PRODUCTSのJANコード,NAMEに追加。is_commonはfalse
//    		2,PRODUCT_FOODS のPRODUCT_ID  	FOODS_ID （1対1、制限食材分）に追加



//	    			PRODUCTSに商品を登録
	    			int insert_dao = p_dao.insert(jancode_1,pro_name_1);
	    			System.out.println(insert_dao);
	    			System.out.println("登録完了");


	    			for(int i2 =lis_solo.length - 3 ; i2>=0 ; i2--){

	    	    		System.out.println("あああ" + lis_solo[i2].trim() + "あああ");





	    	    		int pro_food_dao = p_f_dao.insert(jancode_1,lis_solo[i2].trim());

	    	    		System.out.println("pro_food_daoの登録完了");
	    	    		System.out.println(pro_food_dao);


	    		}



	    	}else{


//			    重複していたら、
//			    1,PRODUCTS のis_commonをtrueにする

//		    			PRODUCTS のis_commonをtrueに
		    			int common_update = p_dao.updateStatusToTrue(jancode_1);
		    			System.out.println("既にこの商品は登録されています");
		    			System.out.println(common_update);


	    	}





//	    		共通
//	    		1,GROUP_PRODUCTS にJANコードとグループIDを追加




//	    		グループID：gru_id
//	    		JANコード：jancode_1

	    		GroupProductsDAO pg_dao = new GroupProductsDAO();


//	    		既にグループに登録されているかどうか
//	    		True：まだされていない	False：登録済み
	    		boolean group_pro_torf = pg_dao.searchByJ_G(jancode_1,gru_id);


	    		if(group_pro_torf){

//		    		GroupProductsに挿入dao
		    		boolean group_pro_dao = pg_dao.insert(jancode_1,gru_id);

		    		System.out.println("group_pro_daoの登録完了");
		    		System.out.println(group_pro_dao);
	    		}


	    		System.out.println("一通りの処理終了");


	    }



	    req.getRequestDispatcher("/stuff/foods_regist_comp.jsp").forward(req, resp);

	}



	    }










