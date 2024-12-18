package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

import utils.ImageUploadUtil;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	response.setContentType("text/html;charset=UTF - 8");
        	request.setCharacterEncoding("UTF-8");
        	System.out.println("UploadServlet");
            // Lấy file từ request
            Part imagePart = request.getPart("image");
            
            String imageUrl = ImageUploadUtil.uploadImage(imagePart);
            System.out.println("imageUrl: " + imageUrl);
            // Đưa URL ảnh lên JSP (hoặc trang khác)
            request.setAttribute("imageUrl", imageUrl);
            
            // Lấy giá trị của trường ẩn "redirect"
            String redirect = request.getParameter("redirect");

            if (redirect != null) {
            	String f_url = "/" + redirect;
            	System.out.println(request.getContextPath() + f_url);
            	request.getRequestDispatcher(f_url).forward(request, response);
//            	request.getRequestDispatcher(request.getContextPath() + "/candidate/create/profile").forward(request, response);
            }
            else {
            	throw new Exception("Image upload error");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()); // 500
        }
    }
}