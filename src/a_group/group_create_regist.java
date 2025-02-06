//	グループ作成登録コントローラ


package a_group;


import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Groups;
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


	    System.out.print("セッション");
	    System.out.print(g_name);
	    System.out.print(g_tel);
	    System.out.print(g_mail);
//	    System.out.print(user_name);


//	DAO
    GroupsDAO dao=new GroupsDAO();

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



 // 次の登録完了サーブレットにリダイレクト
    resp.sendRedirect(req.getContextPath() + "/admin/a_create_group_1/regist/comp");




    //        確認用
//    System.out.println("重複");
//    System.out.println(fot);

    }catch (Exception e){
    	 System.out.println("エラーおきてるで");

    	 String error = ("正しい情報を入力してください");
			req.setAttribute("error",error );
    	 req.getRequestDispatcher("/admin/group_create_1.jsp")
 		.forward(req, resp);
    }

////    System.out.println("id：" + dup);
//    System.out.println("name：" + g_name);
//    System.out.println("tel：" + g_tel);
//    System.out.println("mail：" + g_mail);








	}

}
