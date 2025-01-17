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
        System.out.println("Received body: " + requestBody);

        // 2. "text" フィールドを簡易的に抜き出し
        String text = parseTextFromJson(requestBody);
        System.out.println("Received text: " + text);

        // 3. ここで翻訳やDB保存など行う場合はご自由に
        //    例: AWS Translateにかける、テーブルにINSERTする...

        // 4. レスポンス
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().println("Received text length=" + text.length());
        System.out.println("=== UploadTestServlet.doPost end ===");
    }

    /**
     * シンプルに "text":"..." を抜き出すだけ (本番はJackson等推奨)
     */
    private String parseTextFromJson(String json) {
        String key = "\"text\":\"";
        int start = json.indexOf(key);
        if (start < 0) {
            return "";
        }
        start += key.length();
        int end = json.indexOf("\"", start);
        if (end < 0) {
            return "";
        }
        return json.substring(start, end);
    }
}
