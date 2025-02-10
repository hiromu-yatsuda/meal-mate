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

import org.mindrot.jbcrypt.BCrypt;

import dao.AdminDAO;


@WebServlet(urlPatterns={"/admin/create_accounts_1"})
public class accounts_create extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	req.getRequestDispatcher("a_accounts_create.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		idを取得
		String ac_id = req.getParameter("id");

//		パスワードを取得
//		String ac_pass = req.getParameter("pass");

//		パスワード2を取得
//		String ac_pass2 = req.getParameter("pass2");


		// パスワードを取得してハッシュ化

//		String hash_pass = BCrypt.hashpw(ac_pass, BCrypt.gensalt());


//		boolean isPasswordCorrect = BCrypt.checkpw(ac_pass2, hash_pass);




//	    ランダムパスワードを生成

//	    36字
	    List<String> pass_sel_list = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8","9","0","q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"));

//	    生成されたパスワードのリスト
	    List<String> pass_list = new ArrayList<>();

        Random random2 = new Random();
        int min2 = 0;
        int max2 = 35;

        for (int i = 0; i < 10; i++){
// 		minからmaxまでの範囲の乱数を生成
//      listの配列から

        	int pass_list_num = random2.nextInt((max2 - min2) + 1) + min2;

        	String pass_1 = pass_sel_list.get(pass_list_num);

        	pass_list.add(pass_1);

        }

        String password = String.join("", pass_list);
        System.out.println("ランダムパスワード");
        System.out.println(password);

//        パスワードのハッシュ化
        String hash_pass2 = BCrypt.hashpw(password, BCrypt.gensalt());

        System.out.println("ランダムパスワードハッシュ後");
		System.out.println(hash_pass2);


		System.out.println("ID");
		System.out.println(ac_id);
//		System.out.println("パスワード");
//		System.out.println(ac_pass);
//		System.out.println("ハッシュ後");
//		System.out.println(hash_pass);

//		System.out.println("パス識別");
//		System.out.println(isPasswordCorrect);

//		DAO
		AdminDAO dao = new AdminDAO();

		int in_dao;
		try {
			in_dao = dao.insert(ac_id,hash_pass2);

			System.out.println(in_dao);

			System.out.println("================================");

			System.out.println("");
			System.out.println("password");

			System.out.println(password);

			System.out.println("");

			System.out.println("================================");

			String password_success = ("※今回のみ表示されます。password:" + password);
			req.setAttribute("admin_accounts_password_success",password_success );
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック

			e.printStackTrace();
			String error = ("このIDは既に使用されています");
			req.setAttribute("create_accounts_1_error",error );
			System.out.println("daoでエラー");
			req.getRequestDispatcher("/admin/a_accounts_create.jsp").forward(req, resp);
		}



		req.getRequestDispatcher("a_rogin.jsp").forward(req, resp);


    }

}
