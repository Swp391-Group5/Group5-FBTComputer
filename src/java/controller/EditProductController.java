/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.util.Collection;
import model.Admin;
import model.Category;
import model.Product;

/**
 *
 * @author quanb
 */
@MultipartConfig()
@WebServlet(name = "EditProductController", urlPatterns = {"/editproduct"})
public class EditProductController extends HttpServlet {

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
            out.println("<title>Servlet EditProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String row_id = request.getParameter("pid");
        int pid;
        try {
            pid = Integer.parseInt(row_id);
            ProductDAO pdao = new ProductDAO();
            Product list = pdao.getProductById(pid);
            CategoryDAO cdao = new CategoryDAO();
            List<Category> listCc = cdao.getAllCategory();
            List<String> brands = pdao.getAllBrands();
            request.setAttribute("brands", brands);
            request.setAttribute("listC", listCc);
            request.setAttribute("list", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("updateproduct.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            Admin ad = (Admin) session.getAttribute("admin");
            ProductDAO pdao = new ProductDAO();
            int id = Integer.parseInt(request.getParameter("pid"));
            String productName = request.getParameter("name");
            String productDescription = request.getParameter("description");
            String productPriceStr = request.getParameter("price");
            productPriceStr = productPriceStr.replaceAll("[^\\d.]", "");
            double productPrice = Double.parseDouble(productPriceStr);
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            String productBrand = request.getParameter("brand");
            Product p = pdao.getProductById1(id);
            String img = p.getProductImage();
            String newImgName = "";
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

                    if (fileName != null && !fileName.isEmpty()) {
                        // Kiểm tra phần mở rộng tệp
                        if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
                            // Kiểm tra kích thước tệp
                            if (fileSize > 0 && fileSize <= 1 * 1024 * 1024) {
                                newImgName = fileName;
                                part.write(uploadFilePath + File.separator + fileName);
                            } else {
                                if (fileSize <= 0) {
                                    request.setAttribute("error", "File không có dung lượng hoặc là tệp trống.");
                                } else if (fileSize > 1 * 1024 * 1024) {
                                    request.setAttribute("error", "File quá lớn, vui lòng chọn file nhỏ hơn hoặc bằng 10MB.");
                                }
                                request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                                return;
                            }
                        } else {
                            // Nếu phần mở rộng không hợp lệ, có thể in thông báo lỗi và không lưu file
                            request.setAttribute("error", "Chỉ cho phép chọn tệp JPG, JPEG hoặc PNG.");
                            request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
                            return;
                        }
                    }
                }
            }

            if (newImgName != null && !newImgName.isEmpty()) {
                img = newImgName;
            }

            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            String ModifyBy = ad.getAdminName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ModifyDate = sdf.format(new Date());

            pdao.updateProduct(productName, productDescription, productPrice, productQuantity, productBrand, img, ModifyBy, ModifyDate, categoryId, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("manager-product");
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
