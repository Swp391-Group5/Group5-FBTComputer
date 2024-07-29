/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Utils.OrderPDFServlet;
import dal.AdminDAO;
import dal.CustomerDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderHistoryDAO;
import dal.ProductDAO;
import model.Admin;
import model.FilterOrderDetail;
import model.Order;
import model.OrderDetailDTO;
import model.OrderOrderHistory;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import dal.CustomerDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.mail.Session;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

/**
 *
 * @author hungp
 */
@WebServlet(name = "orderManager", urlPatterns = {"/orderManager"})

public class AdminOrderManager extends HttpServlet {

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
        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
        OrderOrderHistory order = null;
        List<OrderOrderHistory> listOrder = null;
        List<OrderDetailDTO> orderDetail = null;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String action = request.getParameter("action");
        request.setAttribute("listOfFilter", FilterOrderDetail.values());
        Admin sessionAdmin = null;
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
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
        String totalSale = null;
        String detailTotalSale = null;
        //
        String paymentMethod = null;
        //
        totalSale = df.format(orderDAO.getTotalMoneyOrder());
        request.setAttribute("totalSale", totalSale);
        switch (action) {
            case "view" -> {
                String sortBy = request.getParameter("sortBy");
                if (sortBy == null) {
                    request.setAttribute("main", "main");
                    try {
                        count = orderDAO.getTotalOrders();
                    } catch (SQLException ex) {
                        Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        listOrder = orderDAO.getAllOrderHistoryPaging(index, pageSize);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (sortBy != null) {
                    try {
                        listOrder = orderDAO.getAllBySortedBy(sortBy);
                    } catch (SQLException ex) {
                        Logger.getLogger(MyOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                request.setAttribute("listOrder", listOrder);
                int num = (int) Math.ceil((double) count / pageSize);
                int start = (index - 1) * pageSize + 1;
                int end = Math.min(index * pageSize, count);

                request.setAttribute("totalProducts", count);
                request.setAttribute("start", start);
                request.setAttribute("num", num);
                request.setAttribute("end", end);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.setAttribute("sortBy", sortBy);
                request.getRequestDispatcher("/admin/orderManager.jsp").forward(request, response);

            }
            case "searchView" -> {
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");
                if (startDateStr != null && endDateStr != null) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = format.parse(startDateStr);
                        Date endDate = format.parse(endDateStr);
                        listOrder = orderDAO.getAllByDate(startDate, endDate);
                        request.setAttribute("listOrder", listOrder);
                    } catch (ParseException | SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                        return;
                    }
                    request.setAttribute("startDateStr", startDateStr);
                    request.setAttribute("endDateStr", endDateStr);
                    request.getRequestDispatcher("/admin/orderManager.jsp").forward(request, response);
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
                order = orderDAO.getOrderByOrderId(orderId);
                detailTotalSale = df.format(orderDetailDAO.getTotalMoneyOrder(orderId));
                paymentMethod = orderHistoryDAO.getPaymentMethodByOrderID(orderId);
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

                request.setAttribute("order", order);
                request.setAttribute("orderDetail", orderDetail);
                request.setAttribute("orderId", orderId);
                request.setAttribute("detailTotalSale", detailTotalSale);
                //
                request.setAttribute("paymentMethod", paymentMethod);
                //
                request.getRequestDispatcher("/admin/orderManagerDetail.jsp").forward(request, response);
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
                order = orderDAO.getOrderByOrderId(orderId);
                detailTotalSale = df.format(orderDetailDAO.getTotalMoneyOrder(orderId));
                paymentMethod = orderHistoryDAO.getPaymentMethodByOrderID(orderId);
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
                request.setAttribute("detailTotalSale", detailTotalSale);
                request.setAttribute("order", order);
                request.setAttribute("orderDetail", orderDetail);
                request.setAttribute("orderId", orderId);
                //
                request.setAttribute("paymentMethod", paymentMethod);
                //
                request.getRequestDispatcher("/admin/orderManagerDetail.jsp").forward(request, response);
            }
            case "editStatus" -> {
                String type = request.getParameter("type");
                String orderHistoryIdStr = request.getParameter("id");
                String sortBy = request.getParameter("sortBy");
                String startDateStr = request.getParameter("startDateStr");
                String endDateStr = request.getParameter("endDateStr");
                String time = request.getParameter("times");
                Boolean times = Boolean.valueOf(time);
                String quantity = request.getParameter("quantity");

                int orderHistoryId = 0;
                try {
                    orderHistoryId = Integer.parseInt(orderHistoryIdStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                String email = null;
                order = orderDAO.getOrderByOrderHistoryId(orderHistoryId);
                try {
                    orderDetail = orderDetailDAO.getAllOrderDetailByOrderHistoryId(orderHistoryId);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderPDFServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                detailTotalSale = df.format(orderDetailDAO.getTotalMoneyOrderByOrderHistoryId(orderHistoryId));
                if (order.getRoleId() == 1) {
                    email = customerDAO.getCustomerEmail(order.getUserId());
                } else if (order.getRoleId() == 2) {
                    email = adminDAO.getAdminEmail(order.getUserId());
                }
                if (type.equals("successStatus")) {
                    String value = request.getParameter("value");
                    orderHistoryDAO.updateSuccessStatus(orderHistoryId, value);
                    if (value.equals("Succeed")) {
                        sendWarrantyEmail(request, response, email, generateSucceedEmailContent(order, orderDetail, detailTotalSale), "Order Confirmation:");
                        Map<Integer, Integer> productQuantities = orderHistoryDAO.getProductQuantities(orderHistoryId);
                        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
//                            System.out.println("ProductId: " + entry.getKey() + ", Quantity: " + entry.getValue());
                            int quantityFromProduct = productDAO.getProductQuantityByProductId(entry.getKey());
                            int quantityAfterSubtraction = quantityFromProduct - entry.getValue();
                            productDAO.UpdateProductQuantity(entry.getKey(), quantityAfterSubtraction);
                        }
                    } else if (value.equals("Failed")) {
                        sendWarrantyEmail(request, response, email, generateFailedEmailContent(order), "Order Process Failed");
                        orderHistoryDAO.toggleOrderHistoryStatus(orderHistoryId);
                    }
                } else if (type.equals("orderStatus")) {
                    orderHistoryDAO.toggleOrderHistoryStatus(orderHistoryId);
                }

                if (!"none".equals(startDateStr) || !"none".equals(endDateStr)) {
                    startDateStr = URLEncoder.encode(startDateStr, "UTF-8");
                    endDateStr = URLEncoder.encode(endDateStr, "UTF-8");
                    response.sendRedirect("orderManager?action=searchView&startDate=" + startDateStr + "&endDate=" + endDateStr);
                    return;
                }

                if (!"none".equals(sortBy)) {
                    sortBy = URLEncoder.encode(sortBy, "UTF-8");
                    response.sendRedirect("orderManager?action=view&sortBy=" + sortBy);
                    return;
                }

//                response.sendRedirect(request.getContextPath() + "/orderManager?action=view");
            }
            default -> {
                throw new AssertionError();
            }
        }
    }

    ProductDAO productdao = new ProductDAO();
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

    private String generateSucceedEmailContent(OrderOrderHistory order, List<OrderDetailDTO> orderDetail, String detailTotalSale) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html><head><title>Order PDF</title>");
        html.append("<meta charset=\"UTF-8\">");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; color: #000000; }");
        html.append(".container { width: 80%; margin: auto; }");
        html.append(".header, .footer { text-align: center; margin-bottom: 20px; }");
        html.append(".header { font-size: 24px; font-weight: bold; }");
        html.append(".footer { font-size: 14px; color: #888888; }");
        html.append(".text-right { text-align: right; }");
        html.append(".text-center { text-align: center; }");
        html.append("div { color: #000000; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
        html.append("th, td { border: 1px solid #dddddd; padding: 8px; }");
        html.append("th { background-color: #f2f2f2; }");
        html.append(".qr-center { text-align: center; }");
        html.append(".qr-center img { max-width: 100%; height: auto; }");
        html.append("</style>");
        html.append("</head><body>");

        html.append("<div class=\"container\">");
        html.append("<div class=\"header\">FBT COMPUTER</div>");
        html.append("<div class=\"text-right\">");
        html.append("<p>Address: Hoa Lac Hi-Tech Park, Hanoi, Vietnam</p>");
        html.append("<p>Telephone: 0123456789</p>");
        html.append("<p>Email: fbtcomputer@fpt.edu.vn</p>");
        html.append("</div>");
        html.append("<h2 class=\"text-center\">ORDER: #").append(order.getOrderId()).append("</h2>");
        html.append("<p class=\"text-right\">Date: ").append(order.getOrderDate()).append("</p>");

        html.append("<div>");
        html.append("<div style=\"text-align: center;\">");
        html.append("<img style=\"width: 10%;\" src=\"https://drive.google.com/uc?export=view&id=1PenAYFvrUpfxfmW8LWk9tQaa2VqyfG64\" alt=\"tick\">");
        html.append("</div>");
        html.append("<h2 class=\"text-center\">Thanks for your order. It has been received.</h2>");
        html.append("<h4>Date: ").append(order.getOrderDate()).append("</h4>");
        html.append("<h4>Total: ").append(detailTotalSale).append(" VNĐ</h4>");
        html.append("<h4>Payment method: QR</h4>");
        html.append("<h4>Status: SUCCESSFUL</h4>");
        html.append("</div>");

        html.append("<h3>CUSTOMER INFORMATION</h3>");
        html.append("<div class=\"info\">");
        html.append("<p>Customer Name: ").append(order.getName()).append("</p>");
        html.append("<p>Phone: 0").append(order.getPhone()).append("</p>");
        html.append("<p>Address: ").append(order.getAddress()).append("</p>");
        html.append("</div>");

        html.append("<h3>ORDER INFORMATION</h3>");
        html.append("<table>");
        html.append("<thead><tr>");
        html.append("<th>#</th>");
        html.append("<th>Serial Number</th>");
        html.append("<th>Category</th>");
        html.append("<th>Product Name</th>");
        html.append("<th>Brand</th>");
        html.append("<th>Price</th>");
        html.append("</tr></thead><tbody>");

        if (orderDetail != null) {
            for (int i = 0; i < orderDetail.size(); i++) {
                OrderDetailDTO detail = orderDetail.get(i);
                html.append("<tr>");
                html.append("<td>").append(i + 1).append("</td>");
                html.append("<td>").append(detail.getSerialNumber()).append("</td>");
                html.append("<td>").append(detail.getCategoryName()).append("</td>");
                html.append("<td>").append(detail.getProductName()).append("</td>");
                html.append("<td>").append(detail.getProductBrand()).append("</td>");
                html.append("<td>").append(detail.getFormattedPrice()).append(" VNĐ</td>");
                html.append("</tr>");
            }
            html.append("<tr><td colspan=\"5\" class=\"text-right\"><strong>TOTAL:</strong></td>");
            html.append("<td>").append(detailTotalSale).append(" VNĐ</td>");
            html.append("</tr>");
        }

        html.append("</tbody></table>");

        html.append("<div class=\"footer\">Thank you for your purchase! If you have any issues, please contact our customer support for assistance.</div>");
        html.append("</div>");
        html.append("</body></html>");

        return html.toString();
    }

    private String generateFailedEmailContent(OrderOrderHistory order) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; color: #000000; }"
                + ".container { max-width: 600px; margin: 0 auto; padding: 20px; }"
                + ".header { background-color: #dc3545; color: white; padding: 10px; text-align: center; }"
                + ".content { background-color: #f8f9fa; padding: 20px; }"
                + ".footer { background-color: #dc3545; color: white; text-align: center; padding: 10px; }"
                + "a { color: #007bff; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>"
                + "<h1>ORDER FAILED</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Dear <strong>" + order.getName() + "</strong>,</p>"
                + "<p>Thank you for trusting and choosing our products. We would like to inform that your order has been <strong>REJECTED</strong> because <strong>the payment was not received a 3-day-period</strong>.</p>"
                + "<p>If you need more information or have any questions, please contact us via phone number <a href='tel:0822784786'>0822784786</a> or email <a href='mailto:anhquand1511@gmail.com'>anhquand1511@gmail.com</a>.</p>"
                + "<p>Sincerely thank you for your cooperation.</p>"
                + "<p>Best regards,</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; FBT Computer</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private void sendWarrantyEmail(HttpServletRequest request, HttpServletResponse response, String email, String emailContent, String subject)
            throws ServletException, IOException {
        String to = email;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thongdnmhe176561@fpt.edu.vn", "babv cccv blbu tvqj");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thongdnmhe176561@fpt.edu.vn")); // change accordingly
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
//            message.setSubject("Product Warranty Confirmation: " + warranty.getProductName());

            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Message sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/orderManager?action=view");
    }
}
