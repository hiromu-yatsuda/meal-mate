<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/stuffbase.jsp">
    <c:param name="title">従業員 - 商品登録_確認</c:param>
    <c:param name="body">

        <header>
            <%@ include file="../stuffnav.jsp" %>
        </header>

        <h1>商品登録確認</h1>
        <div class="form-create" id="stuff_reg">

            <!-- 固定の商品情報 -->



            <!-- 動的な商品情報 -->

                <%
                ArrayList<ArrayList<String>> nextFullList = (ArrayList<ArrayList<String>>) request.getAttribute("next_full_list");


				int pro_food_count = 0;

                for (ArrayList<String> mini_nextFullList : nextFullList) {
                    out.println("<table border='1'>");

                    for (int i = mini_nextFullList.size() - 1; i >= 0; --i) {
                        int ii = mini_nextFullList.size() - 1;

                        if (i == ii) {
                            out.println("<tr>");
                            out.println("<td>JANコード</td>");
                            out.println("<td>" + mini_nextFullList.get(i) + "</td>");
                            out.println("</tr>");
                        } else if (i == ii - 1) {
                            out.println("<tr>");
                            out.println("<td>商品名</td>");
                            out.println("<td>" + mini_nextFullList.get(i) + "</td>");
                            out.println("</tr>");
                            out.println("<tr>");
                            out.println("<td>原材料</td>");
                        } else if (i == 0) {
                            out.println("<td>" + mini_nextFullList.get(i) + "</td>");
                            out.println("</tr>");
                        } else {

                        	if(pro_food_count==5){

                        		out.println("<td>" + mini_nextFullList.get(i) + "</td>");
                        		out.println("</tr>");
                        		out.println("<tr>");

                        		out.println("<td></td>");

                        		pro_food_count=0;
                        	}else{


                            out.println("<td>" + mini_nextFullList.get(i) + "</td>");
                            pro_food_count++;
                        	}
                        }
                    }

                    out.println("</table>");
                    out.println("<br/>");
                }
                %>


            <p>上記の商品を登録しますか？</p>



<form action=""></form>

            <!-- ボタン -->
            <div id="decision" class="button-group">
                <button type="button" id="confirmed">作成</button>
                <button type="button" id="cancel">キャンセル</button>
            </div>

        </div>

    </c:param>
</c:import>