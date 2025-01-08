//店舗登録　入力確認コントローラ



package a_store;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Groups;
import dao.GroupsDAO;

@WebServlet(urlPatterns={"/admin/a_create_store_1"})
public class store_create_in extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupsDAO dao = new GroupsDAO();
        List<Groups> groupsList = null;
        try {


//        	グループID、グループ名取得する
            groupsList = dao.id_name();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(groupsList);

        req.setAttribute("groupsList", groupsList);
        req.getRequestDispatcher("store_create_1.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // POSTリクエストの処理

//		店舗名を取得
		String sto_name = req.getParameter("name");

//		電話番号を取得
		String sto_tel = req.getParameter("tel_num");

//		メールアドレスを取得
		String sto_mail = req.getParameter("mail");

//		住所
		String sto_address = req.getParameter("address");

//		グループIDとグループ名
		String gru_id_name = req.getParameter("groups_list");
		String[] gru_id_name_List =  gru_id_name.split(",");

		String gru_id = gru_id_name_List[0];
		String gru_name = gru_id_name_List[1];

//		確認画面表示用(グループ)
		String gru_st = gru_id + "：" + gru_name;

//		System.out.println(sto_name);
//		System.out.println(sto_tel);
//		System.out.println(sto_mail);
//		System.out.println(sto_address);
//		System.out.println(gru_id_name);
//
//		System.out.println(gru_id);
//		System.out.println(gru_name);
//
//		System.out.println(gru_st);

//		for(String s:gru_id_name_List){
//			System.out.println(s);
//		}

//		System.out.println(gru_id_name_List);


//		確認画面表示用
		req.setAttribute("sto_name",sto_name);
		req.setAttribute("sto_tel",sto_tel);
		req.setAttribute("sto_mail",sto_mail);
		req.setAttribute("sto_address",sto_address);
		req.setAttribute("gru_id_name",gru_id_name);

//		グループ登録用（id）gru_id
		req.setAttribute("gru_id",gru_id);

//		グループ表示用
		req.setAttribute("gru_st",gru_st);


		req.getRequestDispatcher("store_create_2.jsp").forward(req, resp);



    }

}