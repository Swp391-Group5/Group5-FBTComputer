/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Customer;
import jakarta.servlet.annotation.WebServlet;

/**
 *
 * @author DELL DN
 */
@WebServlet(name = "register", urlPatterns = {"/register"})

public class RegisterServlets extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet RegisterServlets</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlets at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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

    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{}\\[\\]|:;\"'<>,.?/]).{8,}$";
    private static final String VIETNAMESE_PHONE_NUMBER_PATTERN = "^(\\+?84|0)([3|5|7|8|9])+([0-9]{8})$";    
    
    
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int otpvalue = 0;
        HttpSession mySession = request.getSession();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");
        String country = request.getParameter("country");
        String contact = request.getParameter("contact");

        // Password pattern.
        Pattern pass_pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher pass_matcher = pass_pattern.matcher(pass);
        boolean isMatchPass = pass_matcher.matches();

        // Phone number pattern.
        Pattern phone_pattern = Pattern.compile(VIETNAMESE_PHONE_NUMBER_PATTERN);
        Matcher phone_matcher = phone_pattern.matcher(contact);
        boolean isMatchPhone = phone_matcher.matches();

        if (!pass.equals(re_pass)) {
            request.setAttribute("erro", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if (isMatchPass == false) {
            request.setAttribute("erro", "Passwords must be at least 8 characters long and include uppercase, lowercase, digit, and special character");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        if (isMatchPhone == false) {
            request.setAttribute("erro", "Phone number must start with +84 or 0, followed by the first "
                    + "digit being 3, 5, 7, 8, or 9, and the remaining 8 digits must be numbers.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            CustomerDAO customerdao = new CustomerDAO();
            Customer u = customerdao.checkAccountExist(email);
            if (u == null) {

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
                        return new PasswordAuthentication("thongdnmhe176561@fpt.edu.vn", "babv cccv blbu tvqj");// Put your email
                        // id and
                        // password here
                    }
                });
                // compose message
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(email));// change accordingly
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("Hello");
                    message.setText("your OTP is: " + otpvalue);
                    // send message
                    Transport.send(message);
                    System.out.println("message sent successfully");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

                request.setAttribute("message", "OTP is sent to your email id");
                //request.setAttribute("connection", con);
                mySession.setAttribute("name", name);
                mySession.setAttribute("pass", pass);
                mySession.setAttribute("country", country);
                mySession.setAttribute("contact", contact);
                mySession.setAttribute("email", email);
                mySession.setAttribute("otp", otpvalue);
                request.getRequestDispatcher("EnterOtpRegister.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Email already exists");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
