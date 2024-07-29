/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.BlogDAO;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Admin;
import model.Blog;
import model.Category;

/**
 *
 * @author HP
 */
@WebServlet(name = "blogs", urlPatterns = {"/blogs"})

public class BlogController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        BlogDAO blogDAO = new BlogDAO();
        CategoryDAO categoryDao = new CategoryDAO();
        switch (action) {
            case "view":
                try {
                int id = Integer.parseInt(request.getParameter("id"));
                Blog blog = blogDAO.getAllBlogById(id);
                if (blog != null) {
                    AdminDAO adminDao = new AdminDAO();
                    Category category = categoryDao.getCategoryById(id);
                    Admin admin = adminDao.getAdminById(blog.getAdminId());
                    request.setAttribute("admin", admin);
                    request.setAttribute("category", category);
                    request.setAttribute("blog", blog);
                    request.getRequestDispatcher("blog_detail_1_1.jsp").forward(request, response);
                } else {
                    response.sendRedirect("blogs");
                }
            } catch (Exception e) {
                response.sendRedirect("blogs");
            }

            break;
            default:
                String search = request.getParameter("search");
                if (search != null) {
                    search = search.trim();
                    if (!search.isEmpty()) {
                        search = search.replaceAll("\\s+\\S+", "");
                    } else {
                        search = null;
                    }
                }

                search = search != null ? search : "";
                int totalproduct = blogDAO.getNumberBlog(search);
                int numberPage = (int) Math.ceil((double) totalproduct / 6);
                AdminDAO adminDao = new AdminDAO();
                Map<Integer, String> adminNames = new HashMap<>();
                Map<Integer, String> categoryNames = new HashMap<>();
                BlogDAO blogDao = new BlogDAO();
                List<Blog> top3 = blogDAO.getBlogTop3(1);
                request.setAttribute("top3", top3);
                String currentPage = request.getParameter("index");
                int index = 1;
                if (currentPage == null) {
                    index = 1;
                } else {
                    try {
                        index = Integer.parseInt(currentPage);
                    } catch (Exception e) {
                        index = 1;
                    }
                }
                request.setAttribute("index", index);
                request.setAttribute("numberPage", numberPage);
                List<Blog> blogs = blogDao.getAllBlogByStatus(1, search, index);
                for (Blog blog : blogs) {
                    int adminId = blog.getAdminId();
                    int categoryId = blog.getCategoryBlogId();

                    if (!adminNames.containsKey(adminId)) {
                        adminNames.put(adminId, adminDao.getAdminById(adminId).getAdminName());
                    }

                    if (!categoryNames.containsKey(categoryId)) {
                        categoryNames.put(categoryId, categoryDao.getCategoryById(categoryId).getCategoryName());
                    }
                }
                request.setAttribute("adminNames", adminNames);
                request.setAttribute("categoryNames", categoryNames);
                request.setAttribute("blogs", blogs);
                request.getRequestDispatcher("blogs.jsp").forward(request, response);

        }
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
        processRequest(request, response);
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
