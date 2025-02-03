package s_store;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.Stores;
import dao.StoresDAO;

@WebServlet(urlPatterns = { "/staff/store/change" })
@MultipartConfig
public class store_change_in extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// セッションからグループIDを取得
		HttpSession session = req.getSession();

		String se_g_id = "";
		se_g_id = (String) session.getAttribute("s_g_id");

		System.out.println(se_g_id);

//		店長権限がない場合飛ばされる
		boolean s_action = (boolean)session.getAttribute("s_action");



		if(s_action == false){

			req.getRequestDispatcher("/stuff/not_manager.jsp").forward(req, resp);
		}




//		// グループID仮置き
//		se_g_id = "101";
//		System.out.println("仮置き後");
//		System.out.println(se_g_id);







		// jspからグループIDを取得
		String jsp_gru_id = req.getParameter("group_id");

		System.out.println("グループID");
		System.out.println(jsp_gru_id);

		// jspから店舗IDを取得
		String jsp_store_id = req.getParameter("store_id");
		System.out.println("店舗ID");
		System.out.println(jsp_store_id);

//		if (se_g_id.equals(jsp_gru_id)) {

			StoresDAO dao = new StoresDAO();

			try {

				// 店舗情報取得DAO
				List<Stores> s_list = dao.searchBysId(jsp_store_id);

				System.out.println("daoの結果");
				System.out.println(s_list);




				for(Stores store : s_list){

					String s1 = store.getFigure1();
					System.out.println("写真１取り出した");
					System.out.println(s1);

					String s2 = store.getFigure2();
					System.out.println("写真2取り出した");
					System.out.println(s2);

					String s3 = store.getFigure3();
					System.out.println("写真3取り出した");
					System.out.println(s3);

					Time o_time = store.getOpeningTime();
					System.out.println("Time取り出した");
					System.out.println(o_time);

					Time c_time = store.getClosingTime();
					System.out.println("Time取り出した");
					System.out.println(c_time);

				}






				// 表示データリスト
				req.setAttribute("storesList", s_list);

				req.getRequestDispatcher("/stuff/store_info_change_1.jsp").forward(req, resp);

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				System.out.println("店舗情報取得DAOでエラー");
			}

//		} else {
			System.out.println("URLからの偽造確認");
//		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//		公開チェックボックスから取得
		String s_id = req.getParameter("store_id");
		System.out.println("第一回店舗ID");
		System.out.println(s_id);

//		公開チェックボックスから取得
		String s_action = req.getParameter("action");
		System.out.println(s_action);
//		(Boolean型)公開チェックボックス
		boolean b_action = false;


//		公開チェックをbooleanに変換
		b_action = "on".equals(s_action);



		System.out.println("公開チェックボタン↓");
		System.out.println(b_action);




//		<<<<<	ファイル保存




		// アップロードされたファイルそのものを取得
				// 以後パートと呼ぶ



//		List<Part> parts = (List<Part>) req.getParts();
//		System.out.println("fileのリストの表示");
//		System.out.println(parts);


//				Part part = req.getPart("file1");
//				System.out.println("fileの表示");
//				System.out.println(part);




			    // アップロードされたファイルを取得
			    List<Part> parts = (List<Part>) req.getParts();
			    System.out.println("fileのリストの表示");
			    System.out.println(parts);



				// 保存するファイル名を決定（今回は現在日時を経過ミリ秒換算したものとした）
				String savedFileName="";




//				DAOに保存するファイル名（最終）
				String dao_savedFileName1=null;
				String dao_savedFileName2=null;
				String dao_savedFileName3=null;



//				DAOに


				// ファイルインデックスを管理する変数
				int fileIndex = 0;

				for (Part part : parts) {
				    if (part.getName().equals("file1[]")) {
				        // パートのファイル名を取得
				        String originalFileName = part.getSubmittedFileName();
				        if (originalFileName == null || originalFileName.isEmpty()) {
				            continue;
				        }





//			    for (Part parttt : parts) {
//			        if (part.getName().equals("file1[]")) {
//			            // パートのファイル名を取得
//			            String originalFileName = part.getSubmittedFileName();
//			            if (originalFileName == null || originalFileName.isEmpty()) {
//			                continue;
//			            }




				// パートのファイル名を取得
//				String originalFileName = part.getSubmittedFileName();




				// ファイル名から拡張子を取得
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));



				// 保存するファイル名を決定（今回は現在日時を経過ミリ秒換算したものとした）
				savedFileName = Long.toString(new Date().getTime()) + fileIndex + ext;


				 System.out.println("iがなにか");
				 System.out.println(fileIndex);


			        if (fileIndex == 0) {
			            dao_savedFileName1 = savedFileName;
			        } else if (fileIndex == 1) {
			            dao_savedFileName2 = savedFileName;
			        } else if (fileIndex == 2) {
			            dao_savedFileName3 = savedFileName;
			        }










				/*
				 * あらかじめ「環境変数」にアップロードディレクトリの物理パスを設定し
				 * 該当ディレクトリを作成しておくこと
				 * 変数：UPLOAD_DIR
				 * 値：C:/Users/admin/git/meal-mate/WebContent/img/shop
				 */
				// ファイルをアップロードするディレクトリの物理パスを指定
				String uploadDir = System.getenv("UPLOAD_DIR");


				// ファイル保存先のフルパスを設定
				String filePath = uploadDir + "/" + savedFileName;

				File save = new File(filePath);

				// ファイル保存先の物理パスを取得
				String savePath = save.getAbsolutePath();
				System.out.println(savePath);

				// ファイルを保存
				part.write(savePath);



		        fileIndex++; // ファイルインデックスをインクリメント
			        }}


//				ファイル保存	>>>>>







		//		電話番号を取得
		String tel_num = req.getParameter("tel_num");


		if(tel_num==null || tel_num.isEmpty() ){
			System.out.println("営業終了時間空");
			tel_num = null;
		}

		System.out.println(tel_num);



////		メールアドレスを取得
//		String mail = req.getParameter("mail");
//
//
//		if(mail==null || mail.isEmpty() ){
//			System.out.println("メールアドレス空");
//			mail = null;
//		}
//
//		System.out.println(mail);


//		営業開始時間
		String o_time = req.getParameter("time1");

		Time t_o_time = null;


		if(o_time.equals("00:00:00")){
			t_o_time = Time.valueOf(o_time);

		}else{

			if(o_time==null || o_time.isEmpty() ){
				System.out.println("営業終了時間空");
				o_time = null;

			}else{
				t_o_time = Time.valueOf(o_time + ":00");
			}

		}



		System.out.println(o_time);




//		営業終了時間
		String c_time = req.getParameter("time2");

		Time t_c_time = null;
//		LocalTime l_o_time = LocalTime.parse(o_time);

		if(c_time.equals("00:00:00")){


			t_c_time= Time.valueOf(c_time);


		}else{





		if(c_time==null || c_time.isEmpty() ){
			System.out.println("営業終了時間空");
			c_time =null;
		}else{
			t_c_time = Time.valueOf(c_time + ":00");
		}

		}
		System.out.println(c_time);




//		平均利用額（上）
		int dao_amount_high = 0;
		String amount_high = req.getParameter("amount1");

		if(amount_high==null || amount_high.isEmpty() ){
			System.out.println("平均利用額（上）空");

		}else{
			dao_amount_high = Integer.parseInt(amount_high);
		}
		System.out.println(dao_amount_high);



//		平均利用額（下）
		int dao_amount_low=0;
		String amount_low = req.getParameter("amount2");

		if(amount_low==null || amount_low.isEmpty() ){
			System.out.println("平均利用額（上）空");

		}else{
			dao_amount_low = Integer.parseInt(amount_high);
		}
		System.out.println(dao_amount_low);







		StoresDAO dao = new StoresDAO();


		System.out.println("最終ファイル名");
		System.out.println(savedFileName);

		System.out.println("dao最終ファイル名");
		System.out.println(dao_savedFileName1);
		System.out.println(dao_savedFileName2);
		System.out.println(dao_savedFileName3);

		try {

			if(dao_savedFileName1==null && dao_savedFileName2 == null && dao_savedFileName3 == null ){

				int noPhoto_update_dao = dao.noPhoto_update(s_id,t_o_time,t_c_time,dao_amount_high,dao_amount_low,b_action);

				System.out.println("noP_DAOの結果");
				System.out.println(noPhoto_update_dao);

			}else{

				int update_dao = dao.update(s_id,t_o_time,t_c_time,dao_amount_high,dao_amount_low,dao_savedFileName1,dao_savedFileName2,dao_savedFileName3,b_action);

				System.out.println("DAOの結果");
				System.out.println(update_dao);


			}






		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}













		req.getRequestDispatcher("/stuff/store_info_change_2.jsp").forward(req, resp);



	}

}
