// UploadTestServlet.java

package u_tra;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uploadTest")
public class UploadTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POST /uploadTest
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=== UploadTestServlet.doPost start ===");

        // 1. リクエストボディ(JSON)を読み込む
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestBody = sb.toString();
        System.out.println("body: " + requestBody);


        // 2. レスポンス
        response.setContentType("text/plain; charset=UTF-8");
        System.out.println("=== UploadTestServlet.doPost end ===");
    }


}
