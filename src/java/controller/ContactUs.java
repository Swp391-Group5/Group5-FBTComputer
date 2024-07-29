/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

/**
 *
 * @author admin
 */
@WebServlet(name = "ContactUs", urlPatterns = {"/SendEmail"})
public class ContactUs extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContactUs</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContactUs at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    String name, subject, email, msg;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        name = request.getParameter("name");
        email = request.getParameter("email");
        subject = request.getParameter("subject");
        msg = request.getParameter("message");
//        // Lấy giá trị của reCAPTCHA từ request
//        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
//
//        // Gửi yêu cầu xác thực reCAPTCHA lên Google
//        boolean valid = verifyRecaptcha(gRecaptchaResponse);
//
//        if (valid) {
//            // Xử lý gửi email và các hoạt động khác ở đây
//            // Ví dụ: gửi email, lưu vào cơ sở dữ liệu, v.v.
//            // Đoạn mã này tạm thời không xử lý gửi email mà chỉ là ví dụ đơn giản
//
//            out.println("reCAPTCHA verified, proceed to send email or other operations.");
//        } else {
//            // Xử lý khi reCAPTCHA không hợp lệ
//
//            out.println("reCAPTCHA verification failed. Please try again.");
//        }

        final String username = "bachdndhe176090@fpt.edu.vn";//your email id
        final String password = "fezh vosn zlfl gprh";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
            message.setSubject("Contact Details");

            // Construct email content
            StringBuilder content = new StringBuilder();
            content.append("<div style=\"font-family: Arial, sans-serif; line-height: 1.5;\">");
            content.append("<p>Someone just submitted your form:</p>");
            content.append("<table style=\"width: 100%; border-collapse: collapse; margin: 20px 0;\">");
            content.append("<thead><tr>");
            content.append("<th style=\"background-color: #0000ff; color: white; text-align: left; padding: 8px; border: 1px solid #ddd;\">Name</th>");
            content.append("<th style=\"background-color: #0000ff; color: white; text-align: left; padding: 8px; border: 1px solid #ddd;\">Value</th>");
            content.append("</tr></thead><tbody>");
            content.append("<tr><td style=\"border: 1px solid #ddd; padding: 8px;\">Name</td><td style=\"border: 1px solid #ddd; padding: 8px;\">").append(name).append("</td></tr>");
            content.append("<tr><td style=\"border: 1px solid #ddd; padding: 8px;\">Email</td><td style=\"border: 1px solid #ddd; padding: 8px;\">").append(email).append("</td></tr>");
            content.append("<tr><td style=\"border: 1px solid #ddd; padding: 8px;\">Subject</td><td style=\"border: 1px solid #ddd; padding: 8px;\">").append(subject).append("</td></tr>");
            content.append("<tr><td style=\"border: 1px solid #ddd; padding: 8px;\">Message</td><td style=\"border: 1px solid #ddd; padding: 8px;\">").append(msg).append("</td></tr>");
            content.append("</tbody></table>");
            content.append("</div>");

            // Create the email body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(content.toString(), "text/html");

            // Create multipart object for email
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            // Set content of message and send
            message.setContent(multipart);

            Transport.send(message);

            // Response to user
            out.println("<center><h2 style='color:green;'>Email Sent Successfully.</h2>");
            out.println("Thank you " + name + ", your message has been submitted to us.</center>");

        } catch (Exception e) {
            out.println("<center><h2 style='color:red;'>Error occurred while sending email.</h2>");
            out.println("Please try again later.</center>");
            e.printStackTrace(out); // Print stack trace for debugging
        }
    }
    // Phương thức kiểm tra xác thực reCAPTCHA

//    private boolean verifyRecaptcha(String gRecaptchaResponse) {
//        String secretKey = "6Lce-_wpAAAAALe_YJT0ytdKKTp_8f5eAoUjCif1";
//        try {
//            // Kết nối với Google để xác thực
//            String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + gRecaptchaResponse;
//            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
//
//            Gson gson = new Gson();
//            Map<String, Object> map = new HashMap<>();
//            map = gson.fromJson(json, map.getClass());
//            return (Boolean) map.get("success");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
