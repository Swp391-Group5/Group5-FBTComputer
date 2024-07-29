/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminDAO;
import dal.CustomerDAO;
import dal.FeedBackDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import model.Admin;
import model.Customer;
import model.Order;
import model.OrderDetailDTO;
import model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Feedback;
import model.FeedbackOrderProduct;
import model.FilterOrderDetail;
import model.OrderOrderHistory;

/**
 *
 * @author hungp
 */
@WebServlet(name = "myOrderServlet", urlPatterns = {"/myOrder"})
public class MyOrderServlet extends HttpServlet {

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
        List<String> badWords = Arrays.asList(
                "arse", "arsehead", "arsehole", "ass", "ass hole", "asshole",
                "bastard", "bitch", "bloody", "bollocks", "brotherfucker", "bugger", "bullshit",
                "child-fucker", "Christ on a bike", "Christ on a cracker", "cock", "cocksucker", "crap", "cunt",
                "dammit", "damn", "damned", "damn it", "dick", "dick-head", "dickhead", "dumb ass", "dumb-ass", "dumbass", "dyke",
                "father-fucker", "fatherfucker", "frigger", "fuck", "fucker", "fucking",
                "god dammit", "god damn", "goddammit", "God damn", "goddam", "Goddamn", "goddamned", "goddammit", "godsdamn",
                "hell", "holy shit", "horseshit", "in shit",
                "jack-ass", "jackarse", "jackass", "Jesus Christ", "Jesus fuck", "Jesus H. Christ", "Jesus Harold Christ",
                "Jesus, Mary and Joseph", "Jesus wept",
                "kike",
                "mother fucker", "mother-fucker", "motherfucker",
                "nigga", "nigra",
                "pigfucker", "piss", "prick", "pussy",
                "shit", "shit ass", "shite", "sibling fucker", "sisterfuck", "sisterfucker", "slut", "son of a whore",
                "son of a bitch", "spastic", "sweet Jesus",
                "twat", "wanker", "địt", "lồn", "đĩ", "đụ", "chó", "mẹ", "mệnh", "ngu", "súc vật", "mông", "đít"
        );
        HttpSession session = request.getSession();
        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        OrderDAO orderDAO = new OrderDAO();
        FeedBackDAO feedbackDAO = new FeedBackDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ProductDAO productDAO = new ProductDAO();
        Customer sessionCustomer = null;
        Admin sessionAdmin = null;
        List<OrderOrderHistory> listOrder = null;
        List<OrderDetailDTO> orderDetail = null;
        String action = request.getParameter("action");
        request.setAttribute("listOfFilter", FilterOrderDetail.values());
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            sessionCustomer = (Customer) session.getAttribute("customer");
            if (sessionCustomer == null && sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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
                if (sessionAdmin != null) {
                    if (sortBy == null && filterBy == null) {
                        request.setAttribute("main", "main");
                        try {
                            count = orderDAO.getTotalOrderByUserIdAndRoleId(sessionAdmin.getAdminId(), sessionAdmin.getRoleId());
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            listOrder = orderDAO.getAllOrderPagingByUserIdAndRoleId(sessionAdmin.getAdminId(), sessionAdmin.getRoleId(), index, pageSize);
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (sortBy != null) {
                        try {
                            listOrder = orderDAO.getAllByUserIdAndRoleIdSortedBy(sessionAdmin.getAdminId(), sessionAdmin.getRoleId(), sortBy);
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (filterBy != null) {
                        if (filterBy.equals("Pending")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionAdmin.getAdminId(), sessionAdmin.getRoleId(), "isSuccess LIKE 'Pending'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (filterBy.equals("Succeed")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionAdmin.getAdminId(), sessionAdmin.getRoleId(), "isSuccess LIKE 'Succeed'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (filterBy.equals("Failed")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionAdmin.getAdminId(), sessionAdmin.getRoleId(), "isSuccess LIKE 'Failed'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    request.setAttribute("listOrder", listOrder);
                }
                if (sessionCustomer != null) {
                    request.setAttribute("main", "main");
                    if (sortBy == null) {
                        try {
                            count = orderDAO.getTotalOrderByUserIdAndRoleId(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId());
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            listOrder = orderDAO.getAllOrderPagingByUserIdAndRoleId(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId(), index, pageSize);
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (sortBy != null) {
                        try {
                            listOrder = orderDAO.getAllByUserIdAndRoleIdSortedBy(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId(), sortBy);
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (filterBy != null) {
                        if (filterBy.equals("Pending")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId(), "isSuccess LIKE 'Pending'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (filterBy.equals("Succeed")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId(), "isSuccess LIKE 'Succeed'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (filterBy.equals("Failed")) {
                            try {
                                listOrder = orderDAO.getAllByUserIdAndRoleIdByCondition(sessionCustomer.getCustomerId(), sessionCustomer.getRoleId(), "isSuccess LIKE 'Failed'");
                            } catch (SQLException ex) {
                                Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    request.setAttribute("listOrder", listOrder);
                }
                int num = (int) Math.ceil((double) count / pageSize);
                int start = (index - 1) * pageSize + 1;
                int end = Math.min(index * pageSize, count);

                request.setAttribute("totalProducts", count);
                request.setAttribute("start", start);
                request.setAttribute("num", num);
                request.setAttribute("end", end);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.getRequestDispatcher("myOrder.jsp").forward(request, response);
            }
            case "searchView" -> {
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");

                if (startDateStr != null && endDateStr != null) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = format.parse(startDateStr);
                        Date endDate = format.parse(endDateStr);
                        if (sessionAdmin != null) {
                            listOrder = orderDAO.getAllByDateUserIdRoleId(startDate, endDate, sessionAdmin.getAdminId(), sessionAdmin.getRoleId());
                            request.setAttribute("listOrder", listOrder);
                        }
                        if (sessionCustomer != null) {
                            listOrder = orderDAO.getAllByDateUserIdRoleId(startDate, endDate, sessionCustomer.getCustomerId(), sessionCustomer.getRoleId());
                            request.setAttribute("listOrder", listOrder);
                        }
                    } catch (ParseException | SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                        return;
                    }
                    request.getRequestDispatcher("myOrder.jsp").forward(request, response);
                }
            }
            case "viewDetail" -> {
                String filter = request.getParameter("filter");
                String sortBy = request.getParameter("sortBy");
                String orderIdStr = request.getParameter("id");
                int orderId;
                try {
                    orderId = Integer.parseInt(orderIdStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }

                if (filter == null && sortBy == null) {
                    request.setAttribute("main", "main");
                    try {
                        count = orderDetailDAO.getTotalOrderDetailByOrderIdPaging(orderId);
                    } catch (SQLException ex) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + ex);

                    }

                    try {
                        orderDetail = orderDetailDAO.getAllOrderDetailByOrderIdPaging(orderId, index, pageSize);
                    } catch (SQLException ex) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + ex);

                    }
                } else if (filter != null) {
                    try {
                        orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("CategoryName LIKE '%" + filter + "%'", orderId);
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                        return;
                    }
                } else if (sortBy != null) {
                    try {
                        orderDetail = orderDetailDAO.getAllOrderDetailsSortedByAndOrderId(sortBy, orderId);
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                        return;
                    }
                }
                int num = (int) Math.ceil((double) count / pageSize);
                int start = (index - 1) * pageSize + 1;
                int end = Math.min(index * pageSize, count);

                request.setAttribute("totalProducts", count);
                request.setAttribute("start", start);
                request.setAttribute("num", num);
                request.setAttribute("end", end);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);

                request.setAttribute("orderDetail", orderDetail);
                request.setAttribute("orderId", orderId);
                request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
            }
            case "searchViewDetail" -> {
                String orderIdStr = request.getParameter("id");
                String query = request.getParameter("query");
                String criteria = request.getParameter("criteria");
                int orderId;
                try {
                    orderId = Integer.parseInt(orderIdStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                switch (criteria) {
                    case "Serial" -> {
                        try {
                            orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("SerialNumber LIKE '%" + query + "%'", orderId);
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "Category" -> {
                        try {
                            orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("CategoryName LIKE '%" + query + "%'", orderId);
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "ProductName" -> {
                        try {
                            orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("ProductName LIKE '%" + query + "%'", orderId);
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "Brand" -> {
                        try {
                            orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("ProductBrand LIKE '%" + query + "%'", orderId);
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "Price" -> {
                        try {
                            orderDetail = orderDetailDAO.getAllOrderDetailByConditionAndOrderId("Price LIKE '%" + query + "%'", orderId);
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    default ->
                        throw new AssertionError();
                }
                request.setAttribute("orderDetail", orderDetail);
                request.setAttribute("orderId", orderId);
                request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
            }
            case "viewProductDetail" -> {
                String productIdStr = request.getParameter("id");
                Product product = productDAO.getProductByProductId(productIdStr);
                request.setAttribute("product", product);
                request.getRequestDispatcher("orderProductDetail.jsp").forward(request, response);
            }
            case "leaveFeedback0" -> {
                String orderIdStr = request.getParameter("id");
                int orderId;
                FeedbackOrderProduct feedbackOrderProduct = null;
                try {
                    orderId = Integer.parseInt(orderIdStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                try {
                    feedbackOrderProduct = feedbackDAO.getFeedbackByOrderId(orderId);
                } catch (SQLException ex) {
                    Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("orderId", orderIdStr);
                request.setAttribute("feedbackOrderProduct", feedbackOrderProduct);
                request.getRequestDispatcher("leaveFeedback.jsp").forward(request, response);
            }
            case "leaveFeedback" -> {
                String orderIdStr = request.getParameter("id");
                String starStr = request.getParameter("rating");
                String comment = request.getParameter("comment");
                int orderId;
                int star;
                try {
                    orderId = Integer.parseInt(orderIdStr);
                    star = Integer.parseInt(starStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                if (star == 0 || comment.length() == 0 || containsBadWords(comment, badWords)) {
                    String error = null;
                    if (containsBadWords(comment, badWords)) {
                        error = "Your comment contains inappropriate language.";
                    }
                    if (comment.length() == 0) {
                        error = "Please leave a feedback on the product";
                    }
                    if (star == 0) {
                        error = "Please rate the product from 1 - 5 star(s)";
                    }
                    response.sendRedirect("myOrder?action=leaveFeedback0&id=" + orderId + "&error=" + error);
                    return;
                }
                boolean feedbackExist = feedbackDAO.checkOrderIdInFeedback(orderId);
                if (feedbackExist) {
                    feedbackDAO.updateFeedbackStarRate(star, orderId);
                    feedbackDAO.updateFeedbackDescription(comment, orderId);
                } else {
                    try {
                        orderDetail = orderDetailDAO.getAllOrderDetailByOrderId(orderId);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for (OrderDetailDTO detail : orderDetail) {
                        Feedback feedback = new Feedback();
                        feedback.setOrderId(orderId);
                        feedback.setProductId(detail.getProductId());
                        feedback.setStarRate(star);
                        feedback.setFeedbackDescription(comment);
                        feedback.setUserId(detail.getUserId());
                        feedback.setUserName(detail.getName());
                        feedback.setRoleId(detail.getRoleId());
                        feedback.setFeedbackDate(new Date());
                        feedback.setFeedbackStatus(true);
                        try {
                            feedbackDAO.insertFeedback(feedback);
                        } catch (SQLException ex) {
                            Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                response.sendRedirect("myOrder");

            }

            default -> {
                throw new AssertionError();
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

    private boolean containsBadWords(String comment, List<String> badWords) {
        if (comment == null || comment.isEmpty()) {
            return false;
        }

        // Join the bad words list into a regex pattern with word boundaries
        String patternString = "\\b(" + String.join("|", badWords) + ")\\b";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(comment);

        return matcher.find();
    }
}
