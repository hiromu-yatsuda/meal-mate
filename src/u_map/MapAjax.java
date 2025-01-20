package u_map;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Stores;
import dao.StoresDAO;
import tool.CommonServlet;

@WebServlet(urlPatterns={"/user/map/ajax"})
public class MapAjax extends CommonServlet {

    @Override
    protected void get(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        StoresDAO sDao = new StoresDAO();
        List<Stores> stores = sDao.all();

        String string = "{\"latitude\": [\"32.789884\", \"32.639884\", \"36.160657\"], \"longitude\": [\"130.987154\", \"131.087154\", \"139.244206\"], \"storeName\": [\"AAA\", \"BBB\", \"CCC\"]}";

        out.print(string);

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        resp.setContentType("application/json");
//        PrintWriter out = resp.getWriter();
//        StoresDAO sDao = new StoresDAO();
//        List<Stores> stores = sDao.all();
//
//        String string = "{\"storeName\": [\"AAA\", \"BBB\", \"CCC\"], latitude: [\"123\", \"456\", \"789\"], longitude: [\"123\", \"456\", \"789\"]}";
//
//        out.print(string.toString());
//        System.out.println("called");

    }

}
