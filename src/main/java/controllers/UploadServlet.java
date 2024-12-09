package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import utils.ImageUploadUtil;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy file từ request
            Part imagePart = request.getPart("image");
            
            String imageUrl = ImageUploadUtil.uploadImage(imagePart);
            // Đưa URL ảnh lên JSP (hoặc trang khác)
            request.setAttribute("imageUrl", imageUrl);
            
            // Lấy giá trị của trường ẩn "redirect"
            String redirect = request.getParameter("redirect");

            if (redirect != null && redirect.equals("candidate/create/profile")) {
                request.getRequestDispatcher(redirect).forward(request, response);
            }
            else {
            	request.getRequestDispatcher("/testUrl.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
