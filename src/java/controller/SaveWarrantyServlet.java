/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import model.Customer;

/**
 *
 * @author quanb
 */
@MultipartConfig()
@WebServlet(name = "SaveWarrantyServlet", urlPatterns = {"/SaveWarranty"})
public class SaveWarrantyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveWarrantyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveWarrantyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR2 = "UPLOAD_IMAGE2";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("customer");
            int orderHistoryDetailId = Integer.parseInt(request.getParameter("orderHistoryDetailId"));            
            String serialNumber = request.getParameter("serialNumber");
            String causeError = request.getParameter("textInput");
            String phoneNumber = request.getParameter("phoneNumber");
            String productName = request.getParameter("productname");
            String email = request.getParameter("email");
            String img = "";
            // phần xử lý file ảnh
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR2;
            File uploadDir = new File(uploadFilePath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if (part.getName().equals("fileInput")) {
                    long fileSize = part.getSize();
                    String fileName = extractFileName(part);
                    String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

                    // Kiểm tra phần mở rộng tệp
                    if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
                        // Kiểm tra kích thước tệp
                        if (fileSize > 0 && fileSize <= 1 * 1024 * 1024) {
                            img = fileName;
                            part.write(uploadFilePath + File.separator + fileName);
                        } else {
                            if (fileSize <= 0) {
                                request.setAttribute("error", "File không có dung lượng hoặc là tệp trống.");
                            } else if (fileSize > 1 * 1024 * 1024) {
                                request.setAttribute("error", "File quá lớn, vui lòng chọn file nhỏ hơn hoặc bằng 1MB.");
                            }

                            request.getRequestDispatcher("Warranty.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        // Nếu phần mở rộng không hợp lệ, có thể in thông báo lỗi và không lưu file
                        request.setAttribute("error", "Chỉ cho phép chọn tệp JPG, JPEG hoặc PNG.");
                        request.getRequestDispatcher("Warranty.jsp").forward(request, response);
                        return;
                    }
                }
            }
            String userName = cus.getCustomerName();
            int userID = cus.getCustomerId();
            // Lấy ngày hiện tại và định dạng nó
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String PeriodDate = sdf.format(new Date());            
            WarrantyDAO wdao = new WarrantyDAO();
                        wdao.saveWarranty(productName, img, PeriodDate, orderHistoryDetailId, userName, userID, phoneNumber, email, serialNumber, causeError);
            
        } catch (Exception e) {
        }
        response.sendRedirect("home");
    }
     private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
