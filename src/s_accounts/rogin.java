package s_accounts;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import bean.GroupAccounts;
import dao.GroupAccountsDAO;

@WebServlet(urlPatterns={"/stuff/rogin"})
public class rogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    	HttpSession session = req.getSession();
        String error = null;
        session.setAttribute("u_ac_error", error);


    	req.getRequestDispatcher("/stuff/s_rogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		メールアドレスを取得
		String ac_mail = req.getParameter("mail");

//		パスワードを取得
		String ac_pass = req.getParameter("pass");

		System.out.println(ac_pass);

//		DAOGroup
		GroupAccountsDAO dao = new GroupAccountsDAO();
		try {
			List<GroupAccounts> accounts_list_dao = dao.list_seachByEmail(ac_mail);
			System.out.println("DAOの結果");
			System.out.println(accounts_list_dao);



//			メールアドレスがない（間違っている）
			if(accounts_list_dao == null || accounts_list_dao.isEmpty()){

				System.out.println("ログイン失敗");
				String error = ("メールアドレスまたはパスワードが間違っています");
				req.setAttribute("error",error );
//				ログインページへ
				req.getRequestDispatcher("/stuff/s_rogin.jsp").forward(req, resp);
			}



//			DAOから取り出す
//			パスワード
			String dao_pass = "";
//			グループID
			String dao_g_id = "";
//			名前
			String dao_name = "";

//			店長権限
			boolean dao_action = false;

			// パスワードを取り出す
		    for (GroupAccounts account : accounts_list_dao) {
		    	dao_pass = account.getPassword();
		        System.out.println("Password: " + dao_pass);

		    	dao_g_id = account.getGroupCode();
		        System.out.println("GroupID: " + dao_g_id);

		    	dao_name = account.getName();
		        System.out.println("Name: " + dao_name);

		        dao_action= account.isAdmin();
		        System.out.println("Action: " + dao_action);
		    }


//		    パスワード判別
		    boolean isPasswordCorrect = BCrypt.checkpw(ac_pass, dao_pass);

		    System.out.println("パスワード判別");
		    System.out.println(isPasswordCorrect);


//			パスワードが間違っている
		    if(isPasswordCorrect==false){


		    	System.out.println("ログイン失敗");

				String error = ("メールアドレスまたはパスワードが間違っています");
				req.setAttribute("error",error );

		    	//				ログインページへ
				req.getRequestDispatcher("/stuff/s_rogin.jsp").forward(req, resp);

		    }else{




//    		ログイン成功→セッションへ
		    	System.out.println("ログイン成功");

		    	HttpSession session = req.getSession();
//		    名前
    		session.setAttribute("s_name", dao_name);

//		    グループID
    		session.setAttribute("s_g_id", dao_g_id);

//    		店長権限
    		session.setAttribute("s_action", dao_action);
//    		dao_action


//    		セッション確認
    		Object se_name = session.getAttribute("s_name");
    		String st_name = se_name.toString();
    		System.out.println("セッション名前");
    		System.out.println(st_name);
    		Object se_g_id = session.getAttribute("s_g_id");
    		String st_g_id = se_g_id.toString();
    		System.out.println("セッショングループID");
    		System.out.println(st_g_id);


    		req.getRequestDispatcher("top.jsp").forward(req, resp);


		    }



		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("DAOでエラーが発生している");

			String error = ("メールアドレスまたはパスワードが間違っています");
			req.setAttribute("error",error );

	    	//				ログインページへ
			req.getRequestDispatcher("/stuff/s_rogin.jsp").forward(req, resp);


		}


//		パスワード判別
//		boolean isPasswordCorrect = BCrypt.checkpw(ac_pass2, hash_pass);


//		req.getRequestDispatcher("a_top.jsp").forward(req, resp);


    }

}




//	お試しアカウント
//	メールアドレス	qqqqq@qqqq.qqq
//	パスワード		xt7wpuem