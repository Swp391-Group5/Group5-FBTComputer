/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedBackDAO;
import model.Admin;
import model.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author hungp
 */
@WebServlet(name = "uploadImageFeedback", urlPatterns = {"/uploadImageFeedback"})
@MultipartConfig
//chỉ định servlet sẽ nhận dữ liệu là một file
public class UploadImageFeedback extends HttpServlet {

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
        HttpSession session = request.getSession();
        InputStream inputStream = null;
        FeedBackDAO feedbackDAO = new FeedBackDAO();
        String error = null;
        try {
            String orderIdStr = request.getParameter("id");
            int orderId;
            try {
                orderId = Integer.parseInt(orderIdStr);
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                return;
            }
            Part filePart = request.getPart("feedbackInput");
            if (filePart != null) {
                String contentType = filePart.getContentType();
                long fileSize = filePart.getSize();
                if (contentType.startsWith("image/") && fileSize > 0) {
                    inputStream = filePart.getInputStream();
                } else {
                    error = "Please upload a valid image file.";
                    response.sendRedirect("myOrder?action=leaveFeedback0&id=" + orderId + "&error=" + error);
                    return;
                }
            } else {
                error = "File part is missing.";
                response.sendRedirect("myOrder?action=leaveFeedback0&id=" + orderId + "&error=" + error);
                return;
            }

            Customer customer = (Customer) session.getAttribute("customer");
            Admin admin = (Admin) session.getAttribute("admin");
            if (customer == null && admin == null) {
                session.setAttribute("error", "User not logged in");
                response.sendRedirect("login");
                return;
            }
            feedbackDAO.updateFeedbackImage(inputStream, orderId);
            response.sendRedirect("myOrder?action=leaveFeedback0&id=" + orderId);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "An error occurred. Please try again.");
            request.getRequestDispatcher("myOrder").forward(request, response);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//

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
        processRequest(request, response);
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
