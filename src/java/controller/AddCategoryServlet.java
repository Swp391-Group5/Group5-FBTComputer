/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/add-cate"})
public class AddCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet AddCategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCategoryServlet at " + request.getContextPath() + "</h1>");
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
    String name = request.getParameter("cname");
    String status_raw = request.getParameter("cs");

    CategoryDAO cdao = new CategoryDAO();
    
      // Kiểm tra xem name có được nhập vào không
    if (name == null || name.trim().isEmpty()) {
        request.setAttribute("error1", "Please input category name.");
        request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
        return;
    }
    try {
        int categoryStatus = Integer.parseInt(status_raw);

        // Check if the category name already exists
        Category existingCategory = cdao.checkNameCate(name);
        if (existingCategory != null) {
            request.setAttribute("error", "Category name '" + name + "' already exists.");
            request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
            return;
        }

        // Insert the new category without manually setting the ID
        Category newCategory = new Category(name, categoryStatus);
        cdao.insertCategory(newCategory);

        // Redirect to the category list or a success page
        request.getRequestDispatcher("list-cate").forward(request, response);
    } catch (NumberFormatException e) {
        request.setAttribute("error", "Status must be an integer number.");
        request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
    } catch (Exception e) {
        request.setAttribute("error", "An error occurred while adding the category.");
        request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
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
