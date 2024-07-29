/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedBackDAO;
import model.Admin;
import model.Customer;
import model.FeedbackOrderProduct;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AdminCustomerRoleView;
import model.Feedback;

/**
 *
 * @author hungp
 */
@WebServlet(name = "AdminFeedbackManager", urlPatterns = {"/adminFeedbackManager"})
public class AdminFeedbackManager extends HttpServlet {

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
        String action = request.getParameter("action");
        Admin sessionAdmin = null;
        Customer sessionCustomer = null;
        FeedbackOrderProduct feedbackOrderProduct = null;
        List<FeedbackOrderProduct> listFeedback = null;
        FeedBackDAO feedbackDAO = new FeedBackDAO();
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            sessionCustomer = (Customer) session.getAttribute("customer");
            if (sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        String indexParam = request.getParameter("index");
        int index = 1;
        try {
            if (indexParam != null && !indexParam.isEmpty()) {
                index = Integer.parseInt(indexParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String pageSizeParam = request.getParameter("pageSize");
        int pageSize = 5;
        try {
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int count = 0;

        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view" -> {
                String sortBy = request.getParameter("sortBy");
                String filterBy = request.getParameter("filterBy");
                if (filterBy == null && sortBy == null) {
                    request.setAttribute("main", "main");
                    count = feedbackDAO.getTotalFeedback();
                    try {
                        listFeedback = feedbackDAO.getAllFeedbackPaging(index, pageSize);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminFeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (sortBy != null) {
                    try {
                        listFeedback = feedbackDAO.getAllFeedbackSortedBy(sortBy);
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminFeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (filterBy != null) {
                    try {
                        listFeedback = feedbackDAO.getAllFeedbackByCondition("StarRate = " + filterBy);
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                        return;
                    }
                    if (filterBy.equals("True")) {
                        try {
                            listFeedback = feedbackDAO.getAllFeedbackByCondition("FeedbackStatus = 1");
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminFeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (filterBy.equals("False")) {
                        try {
                            listFeedback = feedbackDAO.getAllFeedbackByCondition("FeedbackStatus = 0");
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminFeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                request.setAttribute("listFeedback", listFeedback);
                int num = (int) Math.ceil((double) count / pageSize);
                int start = (index - 1) * pageSize + 1;
                int end = Math.min(index * pageSize, count);

                request.setAttribute("totalFeedbacks", count);
                request.setAttribute("start", start);
                request.setAttribute("num", num);
                request.setAttribute("end", end);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.setAttribute("filterBy", filterBy);
                request.setAttribute("sortBy", sortBy);
                request.getRequestDispatcher("/admin/feedbackManager.jsp").forward(request, response);

            }
            case "searchDate" -> {
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");
                if (startDateStr != null && endDateStr != null) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = format.parse(startDateStr);
                        Date endDate = format.parse(endDateStr);
                        listFeedback = feedbackDAO.getAllFeedbackByDate(startDate, endDate);
                        request.setAttribute("listFeedback", listFeedback);
                    } catch (ParseException | SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                        return;
                    }
                    request.setAttribute("startDateStr", startDateStr);
                    request.setAttribute("endDateStr", endDateStr);
                    request.getRequestDispatcher("/admin/feedbackManager.jsp").forward(request, response);
                }
            }
            case "searchKeyword" -> {
                String query = request.getParameter("query");
                String criteria = request.getParameter("criteria");
                switch (criteria) {
                    case "Username" -> {
                        try {
                            listFeedback = feedbackDAO.getAllFeedbackByCondition("UserName LIKE '%" + query + "%'");
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "ProductName" -> {
                        try {
                            listFeedback = feedbackDAO.getAllFeedbackByCondition("ProductName LIKE '%" + query + "%'");
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    default ->
                        throw new AssertionError();
                }
                request.setAttribute("listFeedback", listFeedback);
                request.setAttribute("query", query);
                request.setAttribute("criteria", criteria);
                request.getRequestDispatcher("/admin/feedbackManager.jsp").forward(request, response);
            }
            case "viewDetail" -> {
                String feedbackIdStr = request.getParameter("id");
                int feedbackId;
                try {
                    feedbackId = Integer.parseInt(feedbackIdStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                try {
                    feedbackOrderProduct = feedbackDAO.getFeedbackByFeedbackId(feedbackId);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminFeedbackManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("feedbackOrderProduct", feedbackOrderProduct);
                request.getRequestDispatcher("/admin/feedbackManagerDetail.jsp").forward(request, response);
            }
            case "editStatus" -> {
                String type = request.getParameter("type");
                String orderFeedbackStr = request.getParameter("id");
                String sortBy = request.getParameter("sortBy");
                String filterby = request.getParameter("filterby");
                String startDateStr = request.getParameter("startDateStr");
                String endDateStr = request.getParameter("endDateStr");
                String criteria = request.getParameter("criteria");
                String query = request.getParameter("query");
                int orderFeedback = 0;
                try {
                    orderFeedback = Integer.parseInt(orderFeedbackStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (type.equals("feedbackStatus")) {
                    feedbackDAO.toggleFeedbackStatus(orderFeedback);
                }

                if (!"none".equals(criteria) || !"none".equals(query)) {
                    criteria = URLEncoder.encode(criteria, "UTF-8");
                    query = URLEncoder.encode(query, "UTF-8");
                    response.sendRedirect("adminFeedbackManager?action=searchKeyword&query=" + query + "&criteria=" + criteria);
                    return;
                }

                if (!"none".equals(startDateStr) || !"none".equals(endDateStr)) {
                    startDateStr = URLEncoder.encode(startDateStr, "UTF-8");
                    endDateStr = URLEncoder.encode(endDateStr, "UTF-8");
                    response.sendRedirect("adminFeedbackManager?action=searchDate&startDate=" + startDateStr + "&endDate=" + endDateStr);
                    return;
                }

                if (!"none".equals(sortBy)) {
                    sortBy = URLEncoder.encode(sortBy, "UTF-8");
                    response.sendRedirect("adminFeedbackManager?action=view&sortBy=" + sortBy);
                    return;
                }

                if (!"none".equals(filterby)) {
                    sortBy = URLEncoder.encode(sortBy, "UTF-8");
                    response.sendRedirect("adminFeedbackManager?action=view&filterby=" + filterby);
                    return;
                }

                response.sendRedirect("orderManager");
            }
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
