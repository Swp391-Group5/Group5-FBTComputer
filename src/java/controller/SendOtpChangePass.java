/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import Utils.*;
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
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

/**
 *
 * @author DELL DN
 */
@WebServlet(name = "sendOtp", urlPatterns = {"/sendOtp"})

public class SendOtpChangePass extends HttpServlet {

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
        doPost(request, response);
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
        doPost(request, response);
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
        RequestDispatcher dispatcher = null;
        int otpvalue = 0;
        HttpSession mySession = request.getSession();
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
        // For forgotpassword only
        if (sessionCustomer != null && customerDAO.checkEmail(email) == false) {
            request.setAttribute("Notexisted", "This email doesn't exist!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
        if (sessionAdmin != null && adminDAO.checkEmail(email) == false) {
            request.setAttribute("Notexisted", "This email doesn't exist!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }

        if (email != null || !email.equals("")) {
            // sending otp
            Random rand = new Random();
            otpvalue = rand.nextInt(1255650);

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
            // compose message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));// change accordingly///////////////////
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("FBTComputer: OTP Code to verify account");
                String htmlContent = "<!DOCTYPE html>"
                        + "<html>"
                        + "<body style=\"font-family: Arial, sans-serif; background-color: #ffffff;\">"
                        + "<div style=\"max-width: 600px; margin: auto; padding: 20px; border: 1px solid #cccccc; background-color: #ffffff;\">"
                        + "<h2 style=\"color: #007bff;\">FBTComputer</h2>"
                        + "<p style=\"font-size: 16px; color: #333333;\">Hello,</p>"
                        + "<p style=\"font-size: 16px; color: #333333;\">Your OTP code to verify your account is:</p>"
                        + "<h1 style=\"font-size: 24px; color: #007bff; text-align: center;\">" + otpvalue + "</h1>"
                        + "<p style=\"font-size: 16px; color: #333333;\">Please use this code to complete your verification process.</p>"
                        + "<p style=\"font-size: 16px; color: #333333;\">If you did not request this code, please ignore this email.</p>"
                        + "<br>"
                        + "<p style=\"font-size: 16px; color: #333333;\">Thank you,<br>FBTComputer Team</p>"
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
            String action = request.getParameter("action");
            dispatcher = request.getRequestDispatcher("EnterOtpChangePass.jsp");
            request.setAttribute("message", "An OTP code is sent to your email: " + email);
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", email);
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
