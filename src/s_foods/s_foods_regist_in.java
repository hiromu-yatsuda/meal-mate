package s_foods;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Products;
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

//	    グループID
	    String gru_id = req.getParameter("gru_id");


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




//	    	単体JANコード
	    	String jancode_1 = lis_solo[lis_solo.length - 1].trim();

//	    	単体商品名
	    	String pro_name_1 = lis_solo[lis_solo.length - 2].trim();


	    	System.out.println("JANコード");
	    	System.out.println("あああ" + lis_solo[lis_solo.length - 1].trim() + "あああ");
	    	System.out.println("商品名");
	    	System.out.println("あああ" + lis_solo[lis_solo.length - 2].trim() + "あああ");

	    	for(int i2 =lis_solo.length - 3 ; i2>=0 ; i2--){



	    		System.out.println("あああ" + lis_solo[i2].trim() + "あああ");
////	    	1,JANコードの重複チェック

//	    		dao
	    		ProductsDAO p_dao = new ProductsDAO();
//
//	    		JANコードで検索語のデータ
	    		List<Products> pro_list = p_dao.search(jancode_1);


//	    		既にこの商品は登録されている
	    		if(pro_list!=null){

//		    重複していたら、
//		    1,PRODUCTS のis_commonをtrueにする

//	    			PRODUCTS のis_commonをtrueに
	    			int common_update = p_dao.updateStatusToTrue(jancode_1);
	    			System.out.println("既にこの商品は登録されています");
	    			System.out.println(common_update);

	    		}else{

//    		重複していなかったら
//    		1,PRODUCTSのJANコード,NAMEに追加。is_commonはfalse
//    		2,PRODUCT_FOODS のPRODUCT_ID  	FOODS_ID （1対1、制限食材分）に追加

//	    			PRODUCTSに商品を登録
	    			int insert_dao = p_dao.insert(jancode_1,pro_name_1);
	    			System.out.println(insert_dao);
	    			System.out.println("登録完了");





	    		}







//	    		共通
//	    		1,GROUP_PRODUCTS にJANコードとグループIDを追加







	    	}





	    }






//	    System.out.println(b1);








//	    ArrayList next_full_list2 = (ArrayList) m;
//	    ArrayList<ArrayList<String>> next_full_list = (ArrayList<ArrayList<String>>) m;
//	    System.out.println(next_full_list.get(0));

//	    System.out.println(next_full_list2);



//	    String a8 =(String)req.getAttribute("re_next_full_list");
//	    System.out.println(a8);

//
//
//	    if (next_full_list != null) {
//            for (ArrayList<String> subList : next_full_list) {
//            	System.out.println(subList);
//
//            	for (String item : subList) {
//                    System.out.println(item);
//                }
//            }
//        }

//	    String[] aa = a2.split("], [");
//	    String[] aa = a2.split("");
//	    System.out.println("本命１");
//	    System.out.println(aa);



//	    List<String[]> pp = new ArrayList<>();
//	    String[] pp = req.getParameterValues("re_next_full_list[]");
//
////	    		req.getParameterValues("re_next_full_list[][]");
//
//
//	    System.out.println("うわあああ");
//    	System.out.println(pp);
//
//
//
//    	if(pp!=null){
//
//    	}
//
//
//
//
//    	String[] p3;
//
//    	if(pp!=null){
//    		for(String a : pp){
//
//
//
//
//
//    				System.out.println("ぎいやあああ");
////					力技タイム
//
//
//    				a = a.replace(",", "v(o_o)v");
//    				a = a.replace("],", ",");
//    				System.out.println("aaaaaaaaaaaaa");
//    				System.out.println(a);
//
//    				int n = a.length() - 2;
//
//
//    				String ppp = a.substring(2, n);
//    				System.out.println(ppp);
//
//
//
//    				String[] p1 = ppp.split(",");
//    				System.out.println(p1);
//
//    				for(String p2:p1){
//
//    					System.out.println("p2はじめ");
//    					System.out.println(p2);
//
//    					p2 = p2.replace( "v(o_o)v",",");
//
//    					 p3 = p2.split(",");
//
//    					System.out.println("p22222    p3");
//    					System.out.println(p2);
//    					System.out.println("p2    p3333");
//    					System.out.println(p3);
//
//
//    				}
//
//
//
//
//    			System.out.println("いうわあああ");
//    	    	System.out.println(a);
//    		}
//    	}
//
//
//
////    	for(String[] p5:p3){
////
////    	}
//
//

//      jspへ持って行って申し分ない整っているリスト用
//      ArrayList<ArrayList<String>> ii = new ArrayList<ArrayList <String>>();
//	    ii = a2;



//if(regist_list!=null){
//
//
//    for(ArrayList<String> mini_regist_list:regist_list){
//    	System.out.println("[１段階]ここやで");
//    	System.out.println(mini_regist_list);
//    }
}





//	    List<String[]> aa = new ArrayList<>();
//	    for(int i=0 ; ; i++){
//	    	String[] aaa =req.getParameterValues("re_next_full_list[" + i + "][]");
//
//
//	        if (aaa == null) break;
//	        aa.add(aaa);
//	    	System.out.println("これ「aa」");
//	    	System.out.println(aaa);
//
//
//	    }



//    	System.out.println("新ページregist_list");
//    	System.out.println(aa);

//
//for(String a : regist_list){
//	System.out.println("データ：" + a);
//
//}



	    }










