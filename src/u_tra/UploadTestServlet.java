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

        // 1. リクエストボディを読み込み (JSON想定)
        //    (音声データではなくテキストのみ送られてくる)
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestBody = sb.toString();
        System.out.println("Received body: " + requestBody);

        // 2. テキストを抽出 (JSONパースなどで取り出す)
        //    ここでは超シンプルに "text" フィールドだけ取り出す例。
        //    本格的には Jackson / org.json 等でパースしてください。
        //    例: {"text":"認識された文章です","language":"ja"}
        String text = parseTextFromJson(requestBody);
        // → 下の parseTextFromJson() は簡易実装サンプル

        System.out.println("Received text: " + text);

        // 3. 受け取ったテキストを保存・翻訳などの処理をここで実行
        //    例: AWS Translate を呼ぶ、DBに保存する、など

        // 4. レスポンスを返す
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().println("Received text length=" + text.length());
        System.out.println("=== UploadTestServlet.doPost end ===");
    }

    /**
     * 超簡易的な JSON から "text" フィールドを探す処理 (実運用ではライブラリ使用推奨)
     * 例: {"text":"こんにちは"}
     */
    private String parseTextFromJson(String json) {
        // 本来は Jackson や org.json などを使うのが安全
        // ここでは "text":"～" を抜き出すだけの簡易実装
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
