/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.CustomerDAO;
import Model.Customer;
import Utils.Encryptor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hungp
 */
@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

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
        CustomerDAO userDAO = new CustomerDAO();
        String action = request.getParameter("action");
        Customer customer;
        try {
            //edit
            customer = userDAO.getCustomerById(2);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
            return;
        }

        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view" -> {
                // set attribute cua list
                request.setAttribute("customer", customer);
                request.setAttribute("action", "view");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            case "editInfo0" -> {
                request.setAttribute("customer", customer);
                request.setAttribute("action", "editInfo");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            case "editPassword0" -> {
                request.setAttribute("action", "editPassword");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
            case "editInfo" -> {
//                String AccountIDStr = request.getParameter("AccountID");
//                String Email = request.getParameter("Email");
                String accountName = request.getParameter("accountName");
                String genderStr = request.getParameter("gender");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String phoneStr = request.getParameter("phone");
                String dateOfBirthStr = request.getParameter("dateOfBirth");

                try {
//                    int AccountID = Integer.parseInt(AccountIDStr);
//                    int Phone = Integer.parseInt(PhoneStr);

                    boolean gender = genderStr.equals("Female");

                    // Use LocalDate for simpler date handling
                    LocalDate DateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    
                    // Convert LocalDate to java.sql.Date
                    java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(DateOfBirth);
                    
                    // Calculate age using Period
                    int age = Period.between(DateOfBirth, LocalDate.now()).getYears();
                    
                    
                    if (!(phoneStr.length() == 10 && phoneStr.charAt(0) == '0') || age < 18 || age > 60) {
                        String errorMessage = null;
                        if (!(phoneStr.length() == 10 && phoneStr.charAt(0) == '0')) {
                            errorMessage = "Phone number must start with a zero digit and have exactly 10 digits.";
                            request.setAttribute("errorMessagePhone", errorMessage);
                        }
                        if (age < 18) {
                            errorMessage = "You must be at least 18 years old.";
                            request.setAttribute("errorMessageDateOfBirth", errorMessage);
                        }
                        if (age > 60) {
                            errorMessage = "You must be under 60 years old.";
                            request.setAttribute("errorMessageDateOfBirth", errorMessage);
                        }
                        request.setAttribute("customer", customer);
                        request.setAttribute("action", "editInfo");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                        return;
                    }

                    // Set all attributes after successful parsing and validation
                    // Set the below to database
                    try {
                        // vẫn phải truyền id vào vì UserDAO cần id để biết thằng nào cần thay đổi
                        Customer updateCustomer = new Customer(customer.getCustomerId(), accountName, age ,customer.getCustomerEmail(), gender, address, city, phoneStr, sqlDateOfBirth);
                        userDAO.updateCustomer(updateCustomer);
                        response.sendRedirect(request.getContextPath() + "/profile");
                    } catch (Exception e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                        return;
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                    return;
                }
            }
            case "editPassword" -> {
                String existPassword = "";
                try {
                    //existPassword already encrypted
                    existPassword = userDAO.checkPasswordExist(customer.getCustomerId());
                } catch (NullPointerException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                    return;
                }
                
                String currentPassword = request.getParameter("currentPassword");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String changePasswordSuccessMessage = null;
                String errorCurrentPassword = null;
                String errorNewPassword = null;
                
                //encrypt currentpassword
                String encryptedCurrentPassword = Encryptor.SHA256Encryption(currentPassword);
                //encrypt newpassword
                String encryptedPassword = Encryptor.SHA256Encryption(password);
                
                if (password.length() < 8 || 
                        !Character.isUpperCase(password.charAt(0)) || 
                        !password.matches(".*[^a-zA-Z0-9].*")) {
                    //.* means "zero or more of any character, including blank". Giống % trong SQL. 
                    // Có cả đầu và cuối vì nó cho phép bất kì chữ nào được xuất hiện trước hoặc sau.
                    //^a-zA-Z0-9 means not a-z, A-Z, 0-9
                    //.*[!].*: 1234: false, 12!34: true
                    errorNewPassword = "Password must be at least 8 characters long, start with a capital letter, and contain at least one special character.";
                } else if (encryptedPassword.equals(existPassword)) {
                    errorNewPassword = "Password must not match current password.";
                } else if (!password.equals(confirmPassword)) {
                    errorNewPassword = "Passwords do not match.";
                } else if (!encryptedCurrentPassword.equals(existPassword)) {
                    errorCurrentPassword = "Current password is incorrect.";
                } else {
                    changePasswordSuccessMessage = "Password changed successfully.";
                    Customer updateCustomer = new Customer(customer.getCustomerId(), encryptedPassword);
                    userDAO.updateUserPassword(updateCustomer);
                }

                request.setAttribute("errorCurrentPassword", errorCurrentPassword);
                request.setAttribute("errorNewPassword", errorNewPassword);
                request.setAttribute("changePasswordSuccessMessage", changePasswordSuccessMessage);
                request.setAttribute("action", "editPassword");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
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
}
