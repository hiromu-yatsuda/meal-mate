package u_tra; // パッケージはプロジェクトに合わせて

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadTest")
@MultipartConfig
public class UploadTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POST /uploadTest
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("=== UploadTestServlet.doPost start ===");

        // 1. まずはパラメータ確認
        Part filePart = request.getPart("file"); // クライアント側で formData.append("file", ...) したキー
        if (filePart == null) {
            System.out.println("filePart is null. no file received");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File part not found");
            return;
        }

        // 2. ファイル名を取得
        String originalFileName = getFileName(filePart); // 例: "recorded.wav"
        if (originalFileName == null || originalFileName.isEmpty()) {
            originalFileName = "unknown.wav";
        }


     // ファイル名をユニークにする例：タイムスタンプ + 元の拡張子
        String extension = "";
        int dotPos = originalFileName.lastIndexOf('.');
        if (dotPos >= 0) {
            extension = originalFileName.substring(dotPos); // .wav 等
        }
        String uniqueFileName = System.currentTimeMillis() + extension;

        System.out.println("fileName = " + originalFileName);

        // 3. 保存先のディレクトリを取得 (webapp/upload に保存する例)
        String uploadDir = getServletContext().getRealPath("/upload");
        System.out.println("uploadDir = " + uploadDir);

        // ※ getRealPath() が null の場合は Tomcat がWARを展開していない可能性あり
        //   → とりあえず OS上のパスを直書きして確認してみるのも手。
        if (uploadDir == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "uploadDir is null");
            return;
        }

        // ディレクトリがなければ作る
        Files.createDirectories(Paths.get(uploadDir));

        // 4. ファイルを保存
        try (InputStream is = filePart.getInputStream()) {
            Files.copy(is, Paths.get(uploadDir, uniqueFileName));
        }

        // 5. 成功レスポンスを返す
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().println("File uploaded: " + uniqueFileName);
        System.out.println("=== UploadTestServlet.doPost end ===");
    }

    // Content-Dispositionからファイル名を抜くメソッド
    private String getFileName(Part part) {
        String cd = part.getHeader("Content-Disposition");
        if (cd != null) {
            for (String token : cd.split(";")) {
                if (token.trim().startsWith("filename")) {
                    return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }
}
