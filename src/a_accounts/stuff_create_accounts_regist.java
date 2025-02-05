package a_accounts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import bean.GroupAccounts;
import bean.Groups;
import dao.GroupAccountsDAO;
import dao.GroupsDAO;

@WebServlet(urlPatterns={"/admin/stuff/create_stuff_1"})
public class stuff_create_accounts_regist extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {

		GroupsDAO dao = new GroupsDAO();
        List<Groups> groupsList = null;
        try {


//        	グループID、グループ名取得する
            groupsList = dao.id_name();
        } catch (Exception e) {
            e.printStackTrace();
        }


        HttpSession session = req.getSession();
        String error_staff_create_regist = (String) session.getAttribute("error_staff_create_regist");


        System.out.println(groupsList);

        req.setAttribute("groupsList", groupsList);
        req.setAttribute("error", error_staff_create_regist);



		req.getRequestDispatcher("/admin/s_create_stuff_1.jsp")
		.forward(req, resp);
	}

	@Override
	protected void doPost(
	        HttpServletRequest req,
	        HttpServletResponse resp
	    ) throws ServletException, IOException {


//		名前を取得
		String s_name = req.getParameter("s_name");

//		メールアドレスを取得
		String s_mail = req.getParameter("mail");

//		店長権限を取得
		String s_perm = req.getParameter("manager_permission");


//		ログインしているアカウントのセッションからグループIDを取得
		HttpSession session = req.getSession();
//		String g_id = (String) session.getAttribute("g_id");


//		グループID
		String g_id = req.getParameter("groups_list");


//		エラーメッセージをリセット
		String error_staff_create_regist = null;
		session.setAttribute("error_staff_create_regist",error_staff_create_regist );





		System.out.println("グループID");
		System.out.println(g_id);



		System.out.println("入力データ");
		System.out.println(s_name);
		System.out.println(s_mail);
		System.out.println(s_perm);

		GroupAccountsDAO dao = new GroupAccountsDAO();



		try {

			boolean mail_dup = dao.searchByEmail(s_mail);
			System.out.println("メールアドレス重複チェックの結果");
			System.out.println(mail_dup);


//			重複していた場合→ページのリロード
			if(mail_dup==true){

				error_staff_create_regist = ("このメールアドレスは既に使用されています");
				session.setAttribute("error_staff_create_regist",error_staff_create_regist );
				System.out.println("メールアドレスが重複していた");
			    resp.sendRedirect(req.getContextPath() + "/admin/stuff/create_stuff_1");
			    return; // リダイレクト後に処理を終了
			} else {


//				登録
			//  重複チェック用 true=重複
			    Boolean id_dup = true;

			//  スタッフID用
			    String a_id = "";

//			    ID生成→重複チェック
			    while(id_dup == true){
//			    for (int i = 0; i < 5; i++){

			        Random random = new Random();
			        int min = 0;
			        int max = 9;

//			 		minからmaxまでの範囲の乱数を生成
//			      先頭1文字目
			        int id_1 = random.nextInt((max - min) + 1) + min;

//			      2文字目
			        int id_2 = random.nextInt((max - min) + 1) + min;

//			      3文字目
			        int id_3 = random.nextInt((max - min) + 1) + min;

//				      4文字目
				    int id_4 = random.nextInt((max - min) + 1) + min;

//					5文字目
				    int id_5 = random.nextInt((max - min) + 1) + min;

//					6文字目
				    int id_6 = random.nextInt((max - min) + 1) + min;

//					intからStringへ
			        String s_id_1 = Integer.toString(id_1);
			        String s_id_2 = Integer.toString(id_2);
			        String s_id_3 = Integer.toString(id_3);
			        String s_id_4 = Integer.toString(id_4);
			        String s_id_5 = Integer.toString(id_5);
			        String s_id_6 = Integer.toString(id_6);

			        a_id = s_id_1 + s_id_2 + s_id_3 + s_id_4 + s_id_5 + s_id_6;
//			        a_id = "000010";

			        System.out.println("ID生成");
			        System.out.println(a_id);


			        try{


	//			        IDの重複チェック データが入る
				        GroupAccounts id_dao_dup = dao.searchById(a_id);

				        System.out.println("ID重複チェックの結果");
				        System.out.println(id_dao_dup);

				        if(id_dao_dup==null){
				        	id_dup = false;
				        }


			        } catch (Exception e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
						System.out.println("idの重複チェックDAOエラー");
					}



			    }

//				店長権限
			    boolean b_perm = "on".equals(s_perm);

//			    if(s_perm=="on"){
//			    	b_perm = true;
//			    }

			    System.out.println("店長権限");
		        System.out.println(b_perm);


//			    ランダムパスワードを生成

//			    36字
			    List<String> pass_sel_list = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","0","q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"));

//			    生成されたパスワードのリスト
			    List<String> pass_list = new ArrayList<>();

		        Random random2 = new Random();
		        int min2 = 0;
		        int max2 = 35;

		        for (int i = 0; i < 8; i++){
//		 		minからmaxまでの範囲の乱数を生成
//		      listの配列から

		        	int pass_list_num = random2.nextInt((max2 - min2) + 1) + min2;

		        	String pass_1 = pass_sel_list.get(pass_list_num);

		        	pass_list.add(pass_1);

		        }

		        String password = String.join("", pass_list);
		        System.out.println("パスワード");
		        System.out.println(password);

//		        パスワードのハッシュ化
		        String hash_pass = BCrypt.hashpw(password, BCrypt.gensalt());


//		        ハッシュ後のパスワードとハッシュ前のパスワードが一致するかの確認
		        boolean isPasswordCorrect = BCrypt.checkpw(password, hash_pass);
		        System.out.println("ハッシュ化の確認");
		        System.out.println(isPasswordCorrect);


//		        DAOに登録
//		        ID：a_id
//		        名前：s_name
//		        グループID：g_id
//		        メールアドレス：s_mail
//		        パスワード：hash_pass
//		        店長権限：b_perm

		        try{

		        	Boolean b_insert = dao.insert(a_id, s_name, s_mail, g_id, hash_pass, b_perm);
		        	System.out.println("登録結果Boolean");
			        System.out.println(b_insert);




		        } catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					System.out.println("DAO登録エラー");
					System.out.println("名前またはメールアドレスが無効か長すぎます。");
					error_staff_create_regist = ("名前またはメールアドレスが無効か長すぎます。");
					session.setAttribute("error_staff_create_regist",error_staff_create_regist );
				    resp.sendRedirect(req.getContextPath() + "/admin/stuff/create_stuff_1");
				    return; // リダイレクト後に処理を終了
				}





			}



		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("メールアドレスの重複チェックDAOエラー");
		}


        // 次の登録完了サーブレットにリダイレクト
        resp.sendRedirect(req.getContextPath() + "/admin/stuff/create_stuff_1/comp");
//		req.getRequestDispatcher("a_top.jsp").forward(req, resp);



//		 // 次の登録完了サーブレットにリダイレクト
//	    resp.sendRedirect(req.getContextPath() + "/admin/a_create_group_1/regist/comp");
	}



}
