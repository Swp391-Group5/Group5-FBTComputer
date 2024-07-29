/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Checkout;

import Cart.Cart;
import dal.CartDAO;
import dal.OrderHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Admin;
import model.Admins;
import model.Customer;
import model.OrderHistory;

/**
 *
 * @author DELL DN
 */
@WebServlet(name = "CheckoutQRControls", urlPatterns = {"/checkoutqr"})
public class CheckoutQRControls extends HttpServlet {

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
            out.println("<title>Servlet CheckoutQRControls</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutQRControls at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static final String EMAIL = "thongdnmhe176561@fpt.edu.vn";
    private static final String PASSWORD = "babv cccv blbu tvqj";
    CartDAO cartdao = new CartDAO();
    OrderHistoryDAO ohd = new OrderHistoryDAO();

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
        String amount = request.getParameter("amount");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("customer");
        Admin admin = (Admin) session.getAttribute("admin");
        String email = null;
        int cartId;

        int userId = 0;
        int role = 0;
        if (customer != null) {
            email = customer.getCustomerEmail();
            userId = customer.getCustomerId();
            role = customer.getRoleId();
        } else if (admin != null) {
            email = admin.getAdminEmail();
            userId = admin.getAdminId();
            role = admin.getRoleId();
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        cartId = cartdao.getCartId(userId, role);
        cartdao.deleteCartDetailAfterCheckout(cartId);
        cartdao.deleteCart(cartId);

        // Delete session successfully
        session.removeAttribute("cart");

        // Get all the customer's information.
        int orderHisId = ohd.getOrderHistoryId();
        OrderHistory orderHis = ohd.getOrderHisByOrderHisId(orderHisId);

        String totalMoney = orderHis.getFormattedTotalMoney();
        String nameCustomer = orderHis.getName();
        String phoneNumber = orderHis.getPhone();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("FBTComputer: Order confirmation to verify QR payment");

            StringBuilder html = new StringBuilder();
            html.append("<!DOCTYPE html>");
            html.append("<html>");
            html.append("<head>");
            html.append("<style>");
            html.append("body { font-family: Arial, sans-serif; background-color: #ffffff; }");
            html.append(".container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #cccccc; background-color: #ffffff; }");
            html.append(".m-auto { display: block; margin: 20px auto; }");
            html.append("h2 { color: #007bff; }");
            html.append("p, li { font-size: 16px; color: #333333; }");
            html.append(".footer { font-size: 16px; color: #333333; text-align: center; margin-top: 20px; }");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");

            html.append("<div class=\"container\">");
            html.append("<img class=\"m-auto\" src=\"https://drive.google.com/uc?export=view&id=1s5NZaW90jz9MIGlh2CnJU1dL1arP03MQ\" alt=\"QR Code\" style=\"width: 100px; height: 100px;\">");
            html.append("<h2>Banking Guide</h2>");
            html.append("<ul>");
            html.append("<li>Account Name: DO ANH QUAN</li>");
            html.append("<li>Account Number: 0822 7847 86</li>");
            html.append("<li>Bank Name: MB BANK</li>");
            html.append("<li>Transfer Amount: ").append(totalMoney).append("VNĐ</li>");
            html.append("<li>Banking Details: ").append(nameCustomer).append("_").append(phoneNumber).append("</li>");
            html.append("<li>Payment is accepted within 24 hours, no support for transactions after!</li>");
            html.append("</ul>");
            html.append("<p>If you have any issues, please contact our customer support for assistance by following the phone number - 0865024982</p>");
            html.append("<div class=\"footer\">Thank you for your purchase!</div>");
            html.append("</div>");

            html.append("</body></html>");

            message.setContent(html.toString(), "text/html");

            Transport.send(message);
            System.out.println("Message sent successfully");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        //orderhistorydao.insertOrderHistory(orderId, "Pending", "1", "QR");
        request.setAttribute(
                "cardType", "QR");
        //request.setAttribute("orderId", orderId);
        request.getRequestDispatcher(
                "CheckoutSuccess.jsp").forward(request, response);

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
