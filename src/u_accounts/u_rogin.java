package u_accounts;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import bean.MemberAccounts;
import dao.MemberAccountsDAO;

@WebServlet(urlPatterns={"/user/rogin"})
public class u_rogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    	req.getRequestDispatcher("/user/u_rogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    	//メールアドレスを取得
    	String u_mail = req.getParameter("email");



    	//パスワードを取得
    	String u_pass = req.getParameter("pass");


    	System.out.println("メールアドレス");
    	System.out.println(u_mail);
    	System.out.println("パスワード");
    	System.out.println(u_pass);


    	MemberAccountsDAO a_dao = new MemberAccountsDAO();

    	try {



			List<MemberAccounts> acc_list = a_dao.list_searchByEmail(u_mail);


//			DAOリストからデータを個別に取り出す。

//			ID
			String dao_id = "";

//			name
			String dao_name = "";

//			pass
			String dao_pass = "";


			for(MemberAccounts account : acc_list){

				dao_id = account.getId();
				dao_name = account.getName();
				dao_pass = account.getPassword();

				System.out.println("ID:" + dao_id);
				System.out.println("NAME:" + dao_name);
				System.out.println("PASS:" + dao_pass);
			}


//			パスワード判別
		    boolean isPasswordCorrect = BCrypt.checkpw(u_pass, dao_pass);

		    System.out.println("パスワード判別");
		    System.out.println(isPasswordCorrect);



//			パスワードが間違っている
		    if(isPasswordCorrect==false){

		    	System.out.println("ログイン失敗");

//				ログインページへ
				req.getRequestDispatcher("/user/u_rogin.jsp").forward(req, resp);




		    }else{
//		    	ログイン成功
//				セッションへ
//				HttpSession session = req.getSession();


		    	boolean up_success = a_dao.up_last_log(u_mail);
		    	System.out.println("Last_loginを更新");
		    	System.out.println(up_success);





//				IDをセッションへ
				req.setAttribute("user_id",dao_id );


//				top画面へ
				req.getRequestDispatcher("/user/top.jsp").forward(req, resp);


		    }



    	} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

	    	System.out.println("email検索DAOでエラー");
		}








    }

}
