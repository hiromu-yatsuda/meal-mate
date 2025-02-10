package u_accounts;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import bean.Languages;
import dao.LanguageDAO;
import dao.MemberAccountsDAO;

@WebServlet(urlPatterns={"/user/create_user_1"})
public class user_create_accounts_regist extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
        ) throws ServletException, IOException {

    	  HttpSession session = req.getSession();
          String error = (String) session.getAttribute("u_ac_error");
          System.out.println("getのエラーメッセージ");
          System.out.println(error);

          session.removeAttribute("u_ac_error"); // エラーメッセージをクリア
          System.out.println("getのエラーメッセージ");
          System.out.println(error);
          req.setAttribute("u_ac_error", error); // エラーメッセージを設定
          LanguageDAO l_dao = new LanguageDAO();

          try {
              List<Languages> lang_list = l_dao.all();
              System.out.println(lang_list);

              req.setAttribute("u_ac_error", error); // エラーメッセージを設定
              req.setAttribute("languageList", lang_list);

          } catch (Exception e) {
              e.printStackTrace();
              System.out.println("languageDAOでエラー起きてる");
          }

          req.getRequestDispatcher("/user/create_user_1.jsp")
          .forward(req, resp);
      }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp
        ) throws ServletException, IOException {

    	HttpSession session = req.getSession();
    	// 名前
        String u_name = req.getParameter("name");

        // メールアドレス
        String u_email = req.getParameter("email");

        // パスワード
        String u_pass = req.getParameter("pass");
        // パスワードのハッシュ化
        String hash_pass = BCrypt.hashpw(u_pass, BCrypt.gensalt());

        // 言語
        String u_langId = req.getParameter("language_list");
        int u_int_langId = Integer.parseInt(u_langId);

        // jspから取り出したデータ
        System.out.println("jspから取り出したデータ");
        System.out.println(u_name);
        System.out.println(u_email);
        System.out.println(u_pass);
        System.out.println(u_int_langId);

        // DAO
        MemberAccountsDAO u_dao = new MemberAccountsDAO();

        try {
            // email重複判定用DAO
            boolean b_dup_email = u_dao.searchByEmail(u_email);
            System.out.println("email重複判定：false=重複しない");
            System.out.println(b_dup_email);

            // email重複している
            if (b_dup_email == true) {
                System.out.println("重複してる！");
                String error = ("既にこのメールアドレスは登録されています");
                session.setAttribute("u_ac_error", error);
                // このページのリロード
                resp.sendRedirect(req.getContextPath() + "/user/create_user_1");
                return; // 処理を終了
            }

            // 重複チェック用 true=重複
            boolean id_dup_TorF = true;

            // IDの引数
            String a_id = "";
            // ID生成→重複チェック
            while (id_dup_TorF == true) {
                Random random = new Random();
                int min = 0;
                int max = 9;

                // minからmaxまでの範囲の乱数を生成
                // 先頭1文字目
                int id_1 = random.nextInt((max - min) + 1) + min;

                // 2文字目
                int id_2 = random.nextInt((max - min) + 1) + min;

                // 3文字目
                int id_3 = random.nextInt((max - min) + 1) + min;

                // 4文字目
                int id_4 = random.nextInt((max - min) + 1) + min;

                // 5文字目
                int id_5 = random.nextInt((max - min) + 1) + min;

                // 6文字目
                int id_6 = random.nextInt((max - min) + 1) + min;

                // intからStringへ
                String s_id_1 = Integer.toString(id_1);
                String s_id_2 = Integer.toString(id_2);
                String s_id_3 = Integer.toString(id_3);
                String s_id_4 = Integer.toString(id_4);
                String s_id_5 = Integer.toString(id_5);
                String s_id_6 = Integer.toString(id_6);

                a_id = s_id_1 + s_id_2 + s_id_3 + s_id_4 + s_id_5 + s_id_6;

                System.out.println("ID生成");
                System.out.println(a_id);

                id_dup_TorF = u_dao.id_dup(a_id);
                System.out.println("ID重複チェック");
                System.out.println(id_dup_TorF);
            }

            try {
                // 登録
                boolean b_insert = u_dao.insert(a_id, u_name, u_email, hash_pass, u_int_langId);
                System.out.println(b_insert);






                // ログインページ
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("登録DAOでエラー");


                String error = ("既にこのメールアドレスは登録されています");
                session.setAttribute("u_ac_error", error);
                // このページのリロード
                resp.sendRedirect(req.getContextPath() + "/user/create_user_1");
                return; // 処理を終了
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("email重複判定用DAOでエラー");

            String error = ("既にこのメールアドレスは登録されています");
            session.setAttribute("u_ac_error", error);
            // このページのリロード
            resp.sendRedirect(req.getContextPath() + "/user/create_user_1");
            return; // 処理を終了
        }
    }
}