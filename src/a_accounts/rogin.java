package a_accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.AdminDAO;

@WebServlet(urlPatterns={"/admin/rogin"})
public class rogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		idを取得

//    	mnbv
    	String ac_id = req.getParameter("id");


//		パスワードを取得
//		aaaa
    	String ac_pass = req.getParameter("pass");

		System.out.println(ac_id);
		System.out.println(ac_pass);

		HttpSession session = req.getSession();


//		DAO
		AdminDAO dao = new AdminDAO();
//        List<AdminAccounts> ad_ac = null;


        try {


        	String ad_pass = dao.serch(ac_id);
        	System.out.println(ad_pass);
//        	System.out.println(ad_ac[0]);

        	boolean isPasswordCorrect = BCrypt.checkpw(ac_pass, ad_pass);
        	System.out.println(isPasswordCorrect);

        	if(isPasswordCorrect==true){

//        		ログイン成功→アカウント名セッションへ
        		session.setAttribute("a_name", ac_id);

        	}


        } catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("daoでエラー");
		}







		String se_name = (String) session.getAttribute("rog_name");
		System.out.println("セッション");
		System.out.println(se_name);


		req.getRequestDispatcher("a_top.jsp").forward(req, resp);

    }

}



//	ID：001
//	パスワード：qdp0i946xr