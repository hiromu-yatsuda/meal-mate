//	グループ作成登録コントローラ


package a_group;


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


@WebServlet(urlPatterns={"/admin/a_create_group_1/regist"})

public class group_create_regist extends HttpServlet {
	@Override

//	入力画面表示
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp
		) throws ServletException, IOException {



	}
	@Override
	protected void doPost(
	        HttpServletRequest req,
	        HttpServletResponse resp
	    ) throws ServletException, IOException {


System.out.print("登録コントローラまで");


		HttpSession session = req.getSession();


	    String g_name = (String) session.getAttribute("g_name");
	    String g_tel = (String) session.getAttribute("g_tel");
	    String g_mail = (String) session.getAttribute("g_mail");
//	    String user_name = (String) session.getAttribute("u_name");

//		店長権限
	    boolean b_perm = true;

//	    ハッシュ化したパスワード
	    String hash_pass = null;

	    System.out.print("セッション");
	    System.out.print(g_name);
	    System.out.print(g_tel);
	    System.out.print(g_mail);
//	    System.out.print(user_name);


//	DAO
    GroupsDAO dao=new GroupsDAO();
	GroupAccountsDAO ac_dao = new GroupAccountsDAO();

	try {

//		メールアドレスの重複チェック
		boolean mail_dup = ac_dao.searchByEmail(g_mail);
		System.out.println("メールアドレス重複チェックの結果");
		System.out.println(mail_dup);

//		重複していた場合→ページのリロード
		if(mail_dup==true){

			String error_staff_create_regist = ("このメールアドレスは既に使用されています");
			session.setAttribute("error_group_create_regist",error_staff_create_regist );
			System.out.println("メールアドレスが重複していた");
		    resp.sendRedirect(req.getContextPath() + "/admin/a_create_group_1");
		    return; // リダイレクト後に処理を終了

		}



//	    36字
	    List<String> pass_sel_list = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","0","q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"));

//	    生成されたパスワードのリスト
	    List<String> pass_list = new ArrayList<>();

        Random random2 = new Random();
        int min2 = 0;
        int max2 = 35;

        for (int i = 0; i < 8; i++){
// 		minからmaxまでの範囲の乱数を生成
//      listの配列から

        	int pass_list_num = random2.nextInt((max2 - min2) + 1) + min2;

        	String pass_1 = pass_sel_list.get(pass_list_num);

        	pass_list.add(pass_1);

        }

        String password = String.join("", pass_list);
        System.out.println("パスワード");
        System.out.println(password);

//        パスワードのハッシュ化
        hash_pass = BCrypt.hashpw(password, BCrypt.gensalt());


//        ハッシュ後のパスワードとハッシュ前のパスワードが一致するかの確認
        boolean isPasswordCorrect = BCrypt.checkpw(password, hash_pass);
        System.out.println("ハッシュ化の確認");
        System.out.println(isPasswordCorrect);


        session.setAttribute("group_accounts_password",password );





	} catch (Exception e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}


    try {

//  重複チェック用
    Boolean dup = false;

//  グループID用
    String s_id = "";


//	グループIDランダム生成→重複チェック
    while(dup == false){

//		グループID用ランダム（001～999）
        Random random = new Random();
        int min = 0;
        int max = 9;

// 		minからmaxまでの範囲の乱数を生成
//      先頭1文字目
        int id_1 = random.nextInt((max - min) + 1) + min;

//      2文字目
        int id_2 = random.nextInt((max - min) + 1) + min;

//      3文字目
        int id_3 = random.nextInt((max - min) + 1) + min;

//		intからStringへ
        String s_id_1 = Integer.toString(id_1);
        String s_id_2 = Integer.toString(id_2);
        String s_id_3 = Integer.toString(id_3);

        s_id = s_id_1 + s_id_2 + s_id_3;


//		重複チェック
        dup=dao.duplication(s_id);

        System.out.println(s_id);
        System.out.println(dup);

    }







//	登録
//  重複チェック用 true=重複
    Boolean id_dup = true;

//  スタッフID用
    String a_id = "";

//    ID生成→重複チェック
    while(id_dup == true){
//    for (int i = 0; i < 5; i++){

        Random random = new Random();
        int min = 0;
        int max = 9;

// 		minからmaxまでの範囲の乱数を生成
//      先頭1文字目
        int id_1 = random.nextInt((max - min) + 1) + min;

//      2文字目
        int id_2 = random.nextInt((max - min) + 1) + min;

//      3文字目
        int id_3 = random.nextInt((max - min) + 1) + min;

//	      4文字目
	    int id_4 = random.nextInt((max - min) + 1) + min;

//		5文字目
	    int id_5 = random.nextInt((max - min) + 1) + min;

//		6文字目
	    int id_6 = random.nextInt((max - min) + 1) + min;

//		intからStringへ
        String s_id_1 = Integer.toString(id_1);
        String s_id_2 = Integer.toString(id_2);
        String s_id_3 = Integer.toString(id_3);
        String s_id_4 = Integer.toString(id_4);
        String s_id_5 = Integer.toString(id_5);
        String s_id_6 = Integer.toString(id_6);

        a_id = s_id_1 + s_id_2 + s_id_3 + s_id_4 + s_id_5 + s_id_6;
//        a_id = "000010";

        System.out.println("ID生成");
        System.out.println(a_id);


        try{


//			        IDの重複チェック データが入る
	        GroupAccounts id_dao_dup = ac_dao.searchById(a_id);

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








////	登録用
//    reg_list.add(s_id);
//    reg_list.add(g_tel);
//    reg_list.add(g_mail);
//    reg_list.add(g_name);
    Groups group = new Groups(s_id,g_tel,g_mail,g_name);


//    DAOから登録
    int line = dao.insert(group);

    System.out.println("DAO後");
    System.out.println(line);


//    アカウントID：a_id
//    名前：g_name
//    パスワード：hash_pass
//    グループID：s_id
//    メールアドレス：g_mail
//    店長権限：b_perm


//    代表（店長権限持ち従業員アカウント）登録
    Boolean s_ac_insert = ac_dao.insert(a_id,g_name,g_mail,s_id,hash_pass,b_perm);
    System.out.println(s_ac_insert);



 // 次の登録完了サーブレットにリダイレクト
    resp.sendRedirect(req.getContextPath() + "/admin/a_create_group_1/regist/comp");




    //        確認用
//    System.out.println("重複");
//    System.out.println(fot);

    }catch (Exception e){
    	 System.out.println("エラーおきてるで");

    	 String error = ("正しい情報を入力してください");
			req.setAttribute("error_group_create_regist",error );
    	 req.getRequestDispatcher("/admin/group_create_1.jsp")
 		.forward(req, resp);
    }

////    System.out.println("id：" + dup);
//    System.out.println("name：" + g_name);
//    System.out.println("tel：" + g_tel);
//    System.out.println("mail：" + g_mail);








	}

}
