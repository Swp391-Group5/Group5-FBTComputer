/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
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
import java.util.Collection;
import java.util.Date;
import model.Admin;
import model.Blog;

/**
 *
 * @author admin
 */
@MultipartConfig()
@WebServlet(name = "AddSliderControler", urlPatterns = {"/add-slider"})
public class AddSliderControler extends HttpServlet {

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
            out.println("<title>Servlet AddSliderControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSliderControler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "UPLOAD_IMAGE";

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

        request.getRequestDispatcher("MKTAddSlider.jsp").forward(request, response);
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
        BlogDAO bdao = new BlogDAO();
        String sliderTitle = request.getParameter("slider_title");
        String sliderContent = request.getParameter("sliderc"); // Assuming this is the content field
        String backlink = request.getParameter("backlink");
        int status = (request.getParameter("status") == null || request.getParameter("status").isEmpty()) ? 0 : Integer.parseInt(request.getParameter("status")); // Đọc trạng thái radio button
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("login");
            return; // Add a return statement to exit the method if admin is null
        }
        int adminId = admin.getAdminId(); // Now safe to access admin object

        java.sql.Date updateDate = new java.sql.Date(new Date().getTime());

        String img = "";

        // phần xử lý file ảnh
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            String fileName = part.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                img = fileName;
                part.write(uploadFilePath + File.separator + fileName);
            }
        }

        boolean result = bdao.addSlider(sliderTitle, sliderContent, backlink, img, status, adminId);
        if (result) {
            request.getRequestDispatcher("slider-list").forward(request, response);
        } else {
            // Xử lý lỗi
            response.getWriter().println("Failed to add slider.");
        }

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
