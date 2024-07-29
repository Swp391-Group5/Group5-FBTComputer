/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Utils;

import dal.AdminDAO;
import dal.CustomerDAO;
import model.Admin;
import model.Customer;
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
@WebServlet(name = "SendPasswordEmail", urlPatterns = {"/sendPasswordEmail"})
public class SendPasswordEmail extends HttpServlet {

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
            out.println("<title>Servlet SendPasswordEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendPasswordEmail at " + request.getContextPath() + "</h1>");
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

        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        Customer sessionCustomer = null;
        Admin sessionAdmin = null;
        String email = request.getParameter("email");
        String accountName = request.getParameter("accountName");
        String genderStr = request.getParameter("gender");
        String phoneStr = request.getParameter("phone");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String roleStr = request.getParameter("role");
        String statusStr = request.getParameter("status");
        RequestDispatcher dispatcher = null;
        String password = null;
        HttpSession mySession = request.getSession();
        String roleEmail = null;
        try {
            sessionAdmin = (Admin) mySession.getAttribute("admin");
            sessionCustomer = (Customer) mySession.getAttribute("customer");
            if (sessionCustomer == null && sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        if (email != null || !email.equals("")) {
            try {
                password = PasswordCreator.createRandomPassword();
            } catch (NullPointerException e) {
                response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
            }

            String to = email;// change accordingly
            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("thongdnmhe176561@fpt.edu.vn", "babv cccv blbu tvqj");
                    //Put email id and password here
                }
            });
            if (roleStr.equals("1")) {
                roleEmail = "Customer";
            } else if (roleStr.equals("2")) {
                roleEmail = "Admin";
            }
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));// change accordingly///////////////////
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("FBTComputer: OTP Code to verify account");
                String htmlContent = "<!DOCTYPE html>"
                        + "<html>"
                        + "<body style=\"font-family: Arial, sans-serif;\">"
                        + "<div style=\"max-width: 600px; margin: auto; padding: 20px; border: 1px solid #cccccc;\">"
                        + "<h2 style=\"color: #333333;\">FBTComputer</h2>"
                        + "<p style=\"font-size: 16px; color: #555555;\">Hello, <strong>"+ accountName +"</strong></p>"
                        + "<p style=\"font-size: 16px; color: #555555;\">We are pleased to inform you that an FBTComputer account has been created for you.</p>"
                        + "<p style=\"font-size: 16px; color: #555555;\">Your account details are as follows:</p>"
                        + "<p style=\"font-size: 16px; color: #555555;\"><strong>Email:</strong> " + email + "</p>"
                        + "<p style=\"font-size: 16px; color: #555555;\"><strong>Password:</strong> " + password + "</p>"
                        + "<p style=\"font-size: 16px; color: #555555;\">This is a <strong>" + roleEmail + "</strong> account. </p>"
                        + "<p style=\"font-size: 16px; color: #555555;\">Please use these credentials to log in and access your account.</p>"
                        + "<p style=\"font-size: 16px; color: #555555;\">If you have any questions, feel free to contact our support team.</p>"
                        + "<br>"
                        + "<p style=\"font-size: 16px; color: #555555;\">Thank you,<br>FBTComputer Team</p>"
                        + "</div>"
                        + "</body>"
                        + "</html>";

                message.setContent(htmlContent, "text/html; charset=utf-8");
                // send message
                Transport.send(message);
                System.out.println("message sent successfully");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("password", password);
            request.setAttribute("email", email);
            request.setAttribute("accountName", accountName );
            request.setAttribute("gender", genderStr);
            request.setAttribute("phone", phoneStr);
            request.setAttribute("dateOfBirth", dateOfBirthStr);
            request.setAttribute("city", city);
            request.setAttribute("address", address);
            request.setAttribute("role", roleStr);
            request.setAttribute("status", statusStr);
            dispatcher = request.getRequestDispatcher("/userManager?action=add");
            dispatcher.forward(request, response);
        }
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
