 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Utils;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import dal.AdminDAO;
import dal.CustomerDAO;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import model.Order;
import model.OrderDetailDTO;
import model.OrderOrderHistory;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author hungp
 */
@WebServlet(name = "OrderPDFServlet", urlPatterns = {"/OrderPDFServlet"})
public class OrderPDFServlet extends HttpServlet {

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
            out.println("<title>Servlet OrderPDFServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderPDFServlet at " + request.getContextPath() + "</h1>");
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
        String orderIdStr = request.getParameter("orderId");

        OrderOrderHistory order = null;
        List<OrderDetailDTO> orderDetail = null;
        String detailTotalSale = null;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
            return;
        }
        order = orderDAO.getOrderByOrderId(orderId);
        try {
            orderDetail = orderDetailDAO.getAllOrderDetailByOrderId(orderId);
        } catch (SQLException ex) {
            Logger.getLogger(OrderPDFServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        detailTotalSale = df.format(orderDetailDAO.getTotalMoneyOrder(orderId));

        // Generate HTML content
        String htmlContent = generateHTMLContent(request, order, orderDetail, detailTotalSale);

        // Convert HTML to PDF and save it to a byte array
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, pdfOutputStream);
        byte[] pdfBytes = pdfOutputStream.toByteArray();
        String email = null;
        if ("Email".equalsIgnoreCase(action)) {
            if (order.getRoleId() == 1) {
                email = customerDAO.getCustomerEmail(order.getUserId());
            } else if (order.getRoleId() == 2) {
                email = adminDAO.getAdminEmail(order.getUserId());
            }
/////////////////////////////////////////////////////
//            String email = "furycap47@gmail.com";
            if (email != null) {
                sendEmailWithAttachment(email, pdfBytes, htmlContent);
            } else {
                response.sendRedirect(request.getContextPath() + "/orderManager?action=viewDetail&id=" + orderIdStr +"&error=Email_is_null");
            }
            // Redirect to a success page or show a success message
            response.sendRedirect(request.getContextPath() + "/orderManager?action=viewDetail&id=" + orderIdStr);
        } else if ("PDF".equalsIgnoreCase(action)) {
            // Set response headers and content type for PDF download
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"order.pdf\"");
            response.getOutputStream().write(pdfBytes);
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

    private void sendEmailWithAttachment(String email, byte[] pdfBytes, String htmlContent) {
        String to = email;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thongdnmhe176561@fpt.edu.vn", "babv cccv blbu tvqj");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("FBTComputer: Your Order Details");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(pdfBytes, "application/pdf");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("order.pdf");
            multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String generateHTMLContent(HttpServletRequest request, OrderOrderHistory order, List<OrderDetailDTO> orderDetail, String detailTotalSale) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html><head><title>Order PDF</title>");
        html.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; color: #000000; }");
        html.append(".container { width: 80%; margin: auto; color: #000000; }");
        html.append(".header, .footer { text-align: center; margin-bottom: 20px; }");
        html.append(".header { font-size: 24px; font-weight: bold; color: #000000; }");
        html.append(".footer { font-size: 14px; color: #888888; }");
        html.append(".text-right { text-align: right; color: #000000; }");
        html.append(".text-center { text-align: center; color: #000000; }");
        html.append(".info { color: #000000; }");
        html.append("h3 { color: #000000; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; color: #000000; }");
        html.append("th, td { border: 1px solid #dddddd; padding: 8px; }");
        html.append("th { background-color: #f2f2f2; color: #000000; }");
        html.append("</style>");
        html.append("</head><body>");

        html.append("<div class=\"container\">");
        html.append("<div class=\"row\">");
        html.append("<div class=\"col-sm-6\"><h1 class=\"header\">FBT COMPUTER</h1></div>");
        html.append("<div class=\"col-sm-6 text-right\">");
        html.append("<p>Address: Hoa Lac Hi-Tech Park, Hanoi, Vietnam</p>");
        html.append("<p>Telephone: 0123456789</p>");
        html.append("<p>Email: fbtcomputer@fpt.edu.vn</p>");
        html.append("</div></div>");
        html.append("<h2 class=\"text-center\">ORDER: #").append(order.getOrderId()).append("</h2>");
        html.append("<p class=\"text-right\">Date: ").append(order.getOrderDate());

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
            html.append("<tr><td colspan=\"5\" class=\"text-right border-0\"><strong>TOTAL:</strong></td>");
            html.append("<td class=\"border-0\">").append(detailTotalSale).append(" VNĐ</td>");
            html.append("</tr>");
        }

        html.append("</tbody></table>");
        html.append("<div class=\"footer\">Thank you for your purchase!</div>");
        html.append("</div>");
        html.append("</body></html>");

        return html.toString();
    }
//    private String generateHTMLContent(HttpServletRequest request, OrderOrderHistory order, List<OrderDetailDTO> orderDetail, String detailTotalSale, String paymentMethod) {
//        StringBuilder html = new StringBuilder();
//        html.append("<!DOCTYPE html>");
//        html.append("<html><head><title>Order PDF</title>");
//        html.append("<meta charset=\"UTF-8\">");
//        html.append("<style>");
//        html.append("body { font-family: Arial, sans-serif; color: #000000; }");
//        html.append(".container { width: 80%; margin: auto; }");
//        html.append(".header, .footer { text-align: center; margin-bottom: 20px; }");
//        html.append(".header { font-size: 24px; font-weight: bold; }");
//        html.append(".footer { font-size: 14px; color: #888888; }");
//        html.append(".text-right { text-align: right; }");
//        html.append(".text-center { text-align: center; }");
//        html.append("div { color: #000000; }");
//        html.append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
//        html.append("th, td { border: 1px solid #dddddd; padding: 8px; }");
//        html.append("th { background-color: #f2f2f2; }");
//        html.append(".qr-center { text-align: center; }");
//        html.append(".qr-center img { max-width: 100%; height: auto; }");
//        html.append("</style>");
//        html.append("</head><body>");
//
//        html.append("<div class=\"container\">");
//        html.append("<div class=\"header\">FBT COMPUTER</div>");
//        html.append("<div class=\"text-right\">");
//        html.append("<p>Address: Hoa Lac Hi-Tech Park, Hanoi, Vietnam</p>");
//        html.append("<p>Telephone: 0123456789</p>");
//        html.append("<p>Email: @fpt.edu.vn</p>");
//        html.append("</div>");
//        html.append("<h2 class=\"text-center\">ORDER: #").append(order.getOrderId()).append("</h2>");
//        html.append("<p class=\"text-right\">Date: ").append(order.getOrderDate()).append("</p>");
//
//        html.append("<h3>CUSTOMER INFORMATION</h3>");
//        html.append("<div class=\"info\">");
//        html.append("<p>Customer Name: ").append(order.getName()).append("</p>");
//        html.append("<p>Phone: 0").append(order.getPhone()).append("</p>");
//        html.append("<p>Address: ").append(order.getAddress()).append("</p>");
//        html.append("</div>");
//
//        html.append("<h3>ORDER INFORMATION</h3>");
//        html.append("<table>");
//        html.append("<thead><tr>");
//        html.append("<th>#</th>");
//        html.append("<th>Serial Number</th>");
//        html.append("<th>Category</th>");
//        html.append("<th>Product Name</th>");
//        html.append("<th>Brand</th>");
//        html.append("<th>Price</th>");
//        html.append("</tr></thead><tbody>");
//
//        if (orderDetail != null) {
//            for (int i = 0; i < orderDetail.size(); i++) {
//                OrderDetailDTO detail = orderDetail.get(i);
//                html.append("<tr>");
//                html.append("<td>").append(i + 1).append("</td>");
//                html.append("<td>").append(detail.getSerialNumber()).append("</td>");
//                html.append("<td>").append(detail.getCategoryName()).append("</td>");
//                html.append("<td>").append(detail.getProductName()).append("</td>");
//                html.append("<td>").append(detail.getProductBrand()).append("</td>");
//                html.append("<td>").append(detail.getFormattedPrice()).append(" VNĐ</td>");
//                html.append("</tr>");
//            }
//            html.append("<tr><td colspan=\"5\" class=\"text-right\"><strong>TOTAL:</strong></td>");
//            html.append("<td>").append(detailTotalSale).append(" VNĐ</td>");
//            html.append("</tr>");
//        }
//
//        html.append("</tbody></table>");
//        html.append("<h2 class=\"text-center\">PAYMENT</h2>");
//        if ("QR".equalsIgnoreCase(paymentMethod)) {
//            html.append("<h2>Banking Guide:</h2>");
//            html.append("<h3>Method 1: Scan QR</h3>");
//            html.append("<div class=\"qr-center\">");
//            html.append("<img src=\"https://drive.google.com/uc?export=view&id=1s5NZaW90jz9MIGlh2CnJU1dL1arP03MQ\" alt=\"QR Code\">");
//            html.append("</div>");
//            html.append("<ul>");
//            html.append("<li><strong>Banking Details: ").append(order.getName()).append("_").append(order.getPhone()).append("_SUCCESSFUL</strong></li>");
//            html.append("</ul>");
//
//            html.append("<div class=\"text-center\"><h2>OR</h2></div>");
//
//            html.append("<h3>Method 2: Enter credentials manually</h3>");
//            html.append("<ul>");
//            html.append("<li><strong>Account Name: DO ANH QUAN</strong></li>");
//            html.append("<li><strong>Account Number: 0822 7847 86</strong></li>");
//            html.append("<li><strong>Bank Name: MB BANK</strong></li>");
//            html.append("<li><strong>Banking Details: ").append(order.getName()).append("_").append(order.getPhone()).append("_SUCCESSFUL</strong></li>");
//            html.append("</ul>");
//            html.append("<p><strong>If the payment is not sent within 24 hours, the order will be rejected. If you still want to place the order, please contact our customer support for assistance.</strong></p>");
//        } else if ("ATM".equalsIgnoreCase(paymentMethod)) {
//            html.append("<div>");
//            html.append("<h3>Thanks for your order. It has been received.</h3>");
//            html.append("<h4>Date: ").append(order.getOrderDate()).append("</h4>");
//            html.append("<h4>Total: ").append(detailTotalSale).append(" VNĐ</h4>");
//            html.append("<h4>Payment method: ").append(paymentMethod).append("</h4>");
//            html.append("<h4>Status: SUCCESSFUL</h4>");
//            html.append("</div>");
//        }
//
//        html.append("<div class=\"footer\">Thank you for your purchase! If you have any issues, please contact our customer support for assistance.</div>");
//        html.append("</div>");
//        html.append("</body></html>");
//
//        return html.toString();
//    }

}
