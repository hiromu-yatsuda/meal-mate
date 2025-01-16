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
		se_g_id = (String) session.getAttribute("g_id");

		System.out.println(se_g_id);






		// グループID仮置き
		se_g_id = "001";
		System.out.println("仮置き後");
		System.out.println(se_g_id);







		// jspからグループIDを取得
		String jsp_gru_id = req.getParameter("group_id");
		System.out.println(jsp_gru_id);

		// jspから店舗IDを取得
		String jsp_store_id = req.getParameter("store_id");
		System.out.println("店舗ID");
		System.out.println(jsp_store_id);

		if (se_g_id.equals(jsp_gru_id)) {

			StoresDAO dao = new StoresDAO();

			try {

				// 店舗情報取得DAO
				List<Stores> s_list = dao.searchBysId(jsp_store_id);

				System.out.println("daoの結果");
				System.out.println(s_list);

				// 表示データリスト
				req.setAttribute("storesList", s_list);

				req.getRequestDispatcher("/stuff/store_info_change_1.jsp").forward(req, resp);

			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				System.out.println("店舗情報取得DAOでエラー");
			}

		} else {
			System.out.println("URLからの偽造確認");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//		公開チェックボックスから取得
		String s_id = req.getParameter("id");
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


				Part part = req.getPart("file1");
				System.out.println("fileの表示");
				System.out.println(part);




				// パートのファイル名を取得
				String originalFileName = part.getSubmittedFileName();




				// ファイル名から拡張子を取得
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));



				// 保存するファイル名を決定（今回は現在日時を経過ミリ秒換算したものとした）
				String savedFileName = Long.toString(new Date().getTime()) + ext;

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


//				ファイル保存	>>>>>







		//		電話番号を取得
		String tel_num = req.getParameter("tel_num");


		if(tel_num==null || tel_num.isEmpty() ){
			System.out.println("営業終了時間空");
			tel_num = null;
		}

		System.out.println(tel_num);



//		メールアドレスを取得
		String mail = req.getParameter("mail");


		if(mail==null || mail.isEmpty() ){
			System.out.println("メールアドレス空");
			mail = null;
		}

		System.out.println(mail);


//		営業開始時間
		String o_time = req.getParameter("time1");


		if(o_time==null || o_time.isEmpty() ){
			System.out.println("営業終了時間空");
			o_time = null;

		}
		System.out.println(o_time);




//		営業終了時間
		String c_time = req.getParameter("time2");
//		LocalTime l_o_time = LocalTime.parse(o_time);



		if(c_time==null || c_time.isEmpty() ){
			System.out.println("営業終了時間空");
			c_time =null;
		}
		System.out.println(c_time);


		int a = 1;

		int b = 2;

		String aa = "1";
		String aa2 = "1";
		String aa3= "1";

		Time pa = Time.valueOf(o_time + ":00");
		Time pa2 = Time.valueOf(c_time+ ":00");

		StoresDAO dao = new StoresDAO();
		String a11 = "000001";


		try {
			int update_dao = dao.update(a11,pa,pa2,a,b,aa,aa2,savedFileName,b_action);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}













		req.getRequestDispatcher("/stuff/store_info_change_2.jsp").forward(req, resp);



	}

}
