package controller;

import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Admin;
import model.Warranty;

@WebServlet(name = "ManageWarrantyServlet", urlPatterns = {"/ManageWarranty"})
public class ManageWarrantyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageWarrantyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageWarrantyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

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

        WarrantyDAO wdao = new WarrantyDAO();
        String action = request.getParameter("action");

        if (action == null) {
            action = "view";
        }
        switch (action) {
            case "view" -> {
                List<Warranty> list = wdao.pagingWarranty(index, pageSize);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.setAttribute("listW", list);
                request.getRequestDispatcher("ManageWarranty.jsp").forward(request, response);
            }

            case "editStatus" -> {
                WarrantyDAO warrantyDAO = new WarrantyDAO();
                String type = request.getParameter("type");
                String warrantyIdStr = request.getParameter("id");
                String value = request.getParameter("value");
                int warrantyId = 0;
                try {
                    warrantyId = Integer.parseInt(warrantyIdStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                Warranty warranty = warrantyDAO.getWarrantyByWarrantyID(warrantyId);
                if (type.equals("successStatus")) {
                    Admin ad = (Admin) session.getAttribute("admin");
                    int id = ad.getAdminId();
                    String name = ad.getAdminName();
                    wdao.updateSuccessStatus1(warrantyId, value, id, name);
                    if (value.equals("Pass")) {
                        sendWarrantyEmail(request, response, warrantyId, name, generatePassEmailContent(warranty, name));
                        return;
                    } else if (value.equals("Fail")) {
                        sendWarrantyEmail(request, response, warrantyId, name, generateFailEmailContent(warranty, name));
                        return;
                    }
                }

                List<Warranty> list = wdao.pagingWarranty(index, pageSize);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.setAttribute("listW", list);
                request.getRequestDispatcher("ManageWarranty.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String generatePassEmailContent(Warranty warranty, String adminName) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
                + ".container { max-width: 600px; margin: 0 auto; padding: 20px; }"
                + ".header { background-color: #007bff; color: white; padding: 10px; text-align: center; }"
                + ".content { background-color: #f8f9fa; padding: 20px; }"
                + ".footer { background-color: #007bff; color: white; text-align: center; padding: 10px; }"
                + "a { color: #007bff; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<div class='header'>"
                + "<h1>Product Warranty Confirmation</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Dear " + warranty.getUserName() + ",</p>"
                + "<p>Thank you for trusting and choosing our products. We would like to confirm that your warranty request has been received and processed. Below is the detailed information about your warranty claim:</p>"
                + "<p><strong>Warranty claim information:</strong></p>"
                + "<p><strong>Warranty claim ID:</strong> " + warranty.getWarrantyId() + "</p>"
                + "<p><strong>Product name:</strong> " + warranty.getProductName() + "</p>"
                + "<p><strong>Customer name:</strong> " + warranty.getUserName() + "</p>"
                + "<p><strong>Name of handling staff:</strong> " + adminName + "</p>"
                + "<p><strong>Contact phone number:</strong> " + warranty.getPhoneNumber() + "</p>"
                + "<p>We will check and process your warranty request as soon as possible. If you need more information or have any questions, please contact us via phone number <a href='tel:0822784786'>0822784786</a> or email <a href='mailto:anhquand1511@gmail.com'>anhquand1511@gmail.com</a>.</p>"
                + "<p>Sincerely thank you for your cooperation.</p>"
                + "<p>Best regards,</p>"
                + "<p>" + adminName + "</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; FBT Computer</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private String generateFailEmailContent(Warranty warranty, String adminName) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css'>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; }"
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
                + "<h1>Product Warranty Not Accepted: " + warranty.getProductName() + "</h1>"
                + "</div>"
                + "<div class='content'>"
                + "<p>Dear " + warranty.getUserName() + ",</p>"
                + "<p>Thank you for trusting and choosing our products. We would like to inform that your warranty request has been received and processed. However, after inspection, we regret to inform you that your warranty claim was not accepted.</p>"
                + "<p>If you need more information or have any questions, please contact us via phone number <a href='tel:0822784786'>0822784786</a> or email <a href='mailto:anhquand1511@gmail.com'>anhquand1511@gmail.com</a>.</p>"
                + "<p>Sincerely thank you for your cooperation.</p>"
                + "<p>Best regards,</p>"
                + "<p>" + adminName + "</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>&copy; FBT Computer</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

    private void sendWarrantyEmail(HttpServletRequest request, HttpServletResponse response, int warrantyId, String adminName, String emailContent)
            throws ServletException, IOException {
        WarrantyDAO warrantyDAO = new WarrantyDAO();
        Warranty warranty = warrantyDAO.getWarrantyByWarrantyID(warrantyId);
        String to = warranty.getEmail();

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
            message.setSubject("Product Warranty Confirmation: " + warranty.getProductName());

            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Message sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/ManageWarranty?action=view");
    }
}
