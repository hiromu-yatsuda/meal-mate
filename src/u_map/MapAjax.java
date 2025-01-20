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

        StringBuilder json = new StringBuilder("{");
        StringBuilder storeCode = new StringBuilder();
        StringBuilder storeName = new StringBuilder();
        StringBuilder groups = new StringBuilder();
        StringBuilder groupCode = new StringBuilder();
        StringBuilder phoneNum = new StringBuilder();
        StringBuilder latitude = new StringBuilder();
        StringBuilder longitude = new StringBuilder();
        StringBuilder openingTime = new StringBuilder();
        StringBuilder closingTime = new StringBuilder();
        StringBuilder avgAmountLow = new StringBuilder();
        StringBuilder avgAmountHigh = new StringBuilder();
        StringBuilder figure1 = new StringBuilder();
        StringBuilder figure2 = new StringBuilder();
        StringBuilder figure3 = new StringBuilder();
        StringBuilder isActive = new StringBuilder();

//        String string = "{\"latitude\": [\"32.789884\", \"32.639884\", \"36.160657\"], \"longitude\": [\"130.987154\", \"131.087154\", \"139.244206\"], \"storeName\": [\"AAA\", \"BBB\", \"CCC\"]}";

//        out.print(string);

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
