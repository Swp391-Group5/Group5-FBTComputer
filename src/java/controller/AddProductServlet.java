/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
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
import java.util.List;
import model.Admin;
import model.Category;
import model.Product;

/**
 *
 * @author admin
 */
@MultipartConfig()
@WebServlet(name = "AddProductServlet", urlPatterns = {"/add"})
public class AddProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR1 = "UPLOAD_IMAGE1";

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ProductDAO pdao = new ProductDAO();
            CategoryDAO cdao = new CategoryDAO();
            List<String> brands = pdao.getAllBrands();
            List<Category> listCc = cdao.getAllCategory();
            request.setAttribute("listC", listCc);
            request.setAttribute("brands", brands);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Admin ad = (Admin) session.getAttribute("admin");
            String productName = request.getParameter("name");
            String productDescription = request.getParameter("description");
            String productPriceStr = request.getParameter("price");
            productPriceStr = productPriceStr.replaceAll("[^\\d.]", "");
            double productPrice = Double.parseDouble(productPriceStr);
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            String productBrand = request.getParameter("brand");
            String img = "";
            // phần xử lý file ảnh
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR1;
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

                            request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                            return;
                        }
                    } else {
                        // Nếu phần mở rộng không hợp lệ, có thể in thông báo lỗi và không lưu file
                        request.setAttribute("error", "Chỉ cho phép chọn tệp JPG, JPEG hoặc PNG.");
                        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                        return;
                    }
                }
            }

            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            

            String createBy = ad.getAdminName();
            // Lấy ngày hiện tại và định dạng nó
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createDate = sdf.format(new Date());

            // Gọi phương thức insertProduct trong DAO
            ProductDAO productDAO = new ProductDAO();

            productDAO.insertProduct(productName, productDescription, productPrice, productQuantity, productBrand, img, createBy, createDate, categoryId);

        } catch (Exception e) {
            e.printStackTrace();

        }
        // Chuyển hướng sau khi thêm sản phẩm
        response.sendRedirect("manager-product"); // Điều chỉnh đường dẫn theo trang của bạn
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
