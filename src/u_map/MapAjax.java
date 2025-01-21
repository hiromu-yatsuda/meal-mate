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

        StringBuilder json = new StringBuilder();
        StringBuilder storeCode = new StringBuilder("\"storeCode\": [");
        // 店舗名
        StringBuilder storeName = new StringBuilder("\"storeName\": [");
        StringBuilder groups = new StringBuilder("\"groups\": [");
        StringBuilder groupCode = new StringBuilder("\"groupCode\": [");
        // 電話番号
        StringBuilder phoneNum = new StringBuilder("\"phoneNum\": [");
        // 緯度
        StringBuilder latitude = new StringBuilder("\"latitude\": [");
        // 経度
        StringBuilder longitude = new StringBuilder("\"longitude\": [");
        // 営業開始時刻
        StringBuilder openingTime = new StringBuilder("\"openingTime\": [");
        // 営業終了時刻
        StringBuilder closingTime = new StringBuilder("\"closingTime\": [");
        // 平均使用金額(低)
        StringBuilder avgAmountLow = new StringBuilder("\"avgAmountLow\": [");
        // 平均使用金額(高)
        StringBuilder avgAmountHigh = new StringBuilder("\"avgAmountHigh\": [");
        // 画像
        StringBuilder figure1 = new StringBuilder("\"figure1\": [");
        StringBuilder figure2 = new StringBuilder("\"figure2\": [");
        StringBuilder figure3 = new StringBuilder("\"figure3\": [");
        StringBuilder isActive = new StringBuilder("\"isActive\": [");

        for (Stores s: stores) {
            storeCode.append("\"" + s.getStoreCode() + "\", ");
            storeName.append("\"" + s.getName() + "\", ");
            groups.append("\"" + s.getGroup_Code() + "\", ");
            groupCode.append("\"" + s.getGroup_Code() + "\", ");
            phoneNum.append("\"" + s.getPhoneNum() + "\", ");
            latitude.append("\"" + s.getLatitude() + "\", ");
            longitude.append("\"" + s.getLongitude() + "\", ");
            openingTime.append("\"" + s.getOpeningTime() + "\", ");
            closingTime.append("\"" + s.getClosingTime() + "\", ");
            avgAmountLow.append("\"" + s.getAvg_amount_low() + "\", ");
            avgAmountHigh.append("\"" + s.getAvg_amount_high() + "\", ");
            figure1.append("\"" + s.getFigure1() + "\", ");
            figure2.append("\"" + s.getFigure2() + "\", ");
            figure3.append("\"" + s.getFigure3() + "\", ");
            isActive.append("\"" + s.isActive() + "\", ");
        }

        json.append("{");
        json.append(storeCode.delete(storeCode.length()-2, storeCode.length()) + "], ");
        json.append(storeName.delete(storeName.length()-2, storeName.length()) + "], ");
        json.append(groups.delete(groups.length()-2, groups.length()) + "], ");
        json.append(groupCode.delete(groupCode.length()-2, groupCode.length()) + "], ");
        json.append(phoneNum.delete(phoneNum.length()-2, phoneNum.length()) + "], ");
        json.append(latitude.delete(latitude.length()-2, latitude.length()) + "], ");
        json.append(longitude.delete(longitude.length()-2, longitude.length()) + "], ");
        json.append(openingTime.delete(openingTime.length()-2, openingTime.length()) + "], ");
        json.append(closingTime.delete(closingTime.length()-2, closingTime.length()) + "], ");
        json.append(avgAmountLow.delete(avgAmountLow.length()-2, avgAmountLow.length()) + "], ");
        json.append(avgAmountHigh.delete(avgAmountHigh.length()-2, avgAmountHigh.length()) + "], ");
        json.append(figure1.delete(figure1.length()-2, figure1.length()) + "], ");
        json.append(figure2.delete(figure2.length()-2, figure2.length()) + "], ");
        json.append(figure3.delete(figure3.length()-2, figure3.length()) + "], ");
        json.append(isActive.delete(isActive.length()-2, isActive.length()) + "]");
        json.append("}");

        out.print(json.toString());

    }

    @Override
    protected void post(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }

}