package utils;

import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Part;

public class ImageUploadUtil {
    private static final String API_KEY = "c47e98631c8e40908e9a4b53c9a0d6c7"; // API key của bạn

    public static String uploadImage(Part imagePart) throws IOException {
        String fileName = imagePart.getSubmittedFileName();

        // Tạo URL yêu cầu
        URL url = new URL("https://api.imgbb.com/1/upload?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=*****");

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {

            // Thêm tệp hình ảnh
            dos.writeBytes("--*****\r\n");
            dos.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\"" + fileName + "\"\r\n");
            dos.writeBytes("Content-Type: " + imagePart.getContentType() + "\r\n\r\n");

            // Đọc dữ liệu hình ảnh từ request và ghi vào body của request
            try (InputStream fileStream = imagePart.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileStream.read(buffer)) != -1) {
                    dos.write(buffer, 0, bytesRead);
                }
                dos.writeBytes("\r\n--*****--\r\n");
            }
        }

        // Đọc phản hồi từ API
        try (InputStream inputStream = conn.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Parse JSON để lấy URL
            JSONObject jsonResponse = new JSONObject(responseBuilder.toString());
            boolean success = jsonResponse.getBoolean("success");
            return success ? jsonResponse.getJSONObject("data").getString("url") : "Lỗi tải ảnh lên";
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 90646a4 (update login, register, and UI index)
