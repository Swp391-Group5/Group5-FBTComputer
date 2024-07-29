/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import model.Admin;
import model.Customer;
import Utils.Encryptor;
import dal.AdminDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Customer sessionCustomer = null;
        Admin sessionAdmin = null;
        Customer customer = null;
        Admin admin = null;
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            sessionCustomer = (Customer) session.getAttribute("customer");
            if (sessionCustomer == null && sessionAdmin == null) {
                throw new Exception();
            }

            if (sessionAdmin != null) {
                admin = adminDAO.getAdminById(sessionAdmin.getAdminId());
            }

            if (sessionCustomer != null) {
                customer = customerDAO.getCustomerById(sessionCustomer.getCustomerId());
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("customer", sessionCustomer);
        request.setAttribute("admin", sessionAdmin);

        if (action == null) {
            action = "view";
        }
        switch (action) {
            case "view" -> {
                // set attribute cua list
                String error = (String) request.getAttribute("error");
                request.setAttribute("error", error);

                if (customer != null) {
                    request.setAttribute("customerAvatar", customer.getCustomerAvatar());
                }
                if (admin != null) {
                    request.setAttribute("adminAvatar", admin.getAdminAvatar());
                }

                request.setAttribute("action", "view");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            case "editInfo0" -> {

                if (customer != null) {
                    request.setAttribute("customerAvatar", customer.getCustomerAvatar());
                }
                if (admin != null) {
                    request.setAttribute("adminAvatar", admin.getAdminAvatar());
                }
                request.setAttribute("action", "editInfo");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            case "editPassword0" -> {
                request.setAttribute("action", "editPassword");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
            case "editInfo" -> {
                String accountName = request.getParameter("accountName");
                String genderStr = request.getParameter("gender");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String phoneStr = request.getParameter("phone");
                String dateOfBirthStr = request.getParameter("dateOfBirth");

                try {

                    boolean gender = genderStr.equals("Female");

                    LocalDate dateOfBirth;

                    try {
                        dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        request.setAttribute("errorMessageDateOfBirth", "Invalid date format.");

//                        if (customer != null) {
//                            request.setAttribute("customerAvatar", customer.getCustomerAvatar());
//                        }
//                        if (admin != null) {
//                            request.setAttribute("adminAvatar", admin.getAdminAvatar());
//                        }
                        request.setAttribute("action", "editInfo");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                        return;
                    }
                    java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(dateOfBirth);
                    int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
                    if (!(phoneStr.length() == 10 && phoneStr.charAt(0) == '0') || age < 18 || age > 60) {
                        String errorMessageDateOfBirth = null;
                        String errorMessagePhone = null;
                        if (!(phoneStr.length() == 10 && phoneStr.charAt(0) == '0')) {
                            errorMessagePhone = "Phone number must start with a zero digit and have exactly 10 digits.";
                            request.setAttribute("errorMessagePhone", errorMessagePhone);
                        }
                        if (age < 18) {
                            errorMessageDateOfBirth = "You must be at least 18 years old.";
                        }
                        if (age >= 60) {
                            errorMessageDateOfBirth = "You must be under 60 years old.";
                        }
                        request.setAttribute("errorMessageDateOfBirth", errorMessageDateOfBirth);

//                        if (customer != null) {
//                            request.setAttribute("customerAvatar", customer.getCustomerAvatar());
//                        }
//                        if (admin != null) {
//                            request.setAttribute("adminAvatar", admin.getAdminAvatar());
//                        }
                        request.setAttribute("action", "editInfo");
                        request.getRequestDispatcher("profile.jsp").forward(request, response);
                        return;
                    }

                    try {
                        if (customer != null) {
                            Customer updateCustomer = new Customer(sessionCustomer.getCustomerId(), accountName, age, sessionCustomer.getCustomerEmail(), gender, address, city, phoneStr, sqlDateOfBirth);
                            customerDAO.updateCustomer(updateCustomer);
                            session.setAttribute("customer", updateCustomer);
                        }
                        if (admin != null) {
                            Admin updateAdmin = new Admin(sessionAdmin.getAdminId(), accountName, age, sessionAdmin.getAdminEmail(), gender, address, city, phoneStr, sqlDateOfBirth);
                            adminDAO.updateAdmin(updateAdmin);
                            session.setAttribute("admin", updateAdmin);
                        }
                        response.sendRedirect(request.getContextPath() + "/profile");
                    } catch (IOException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + "1" + e);
                        return;
                    }
                } catch (ServletException | IOException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + "2" + e);
                    return;
                }
            }

            case "editPassword" -> {
                String existPassword = "";
                try {
                    if (customer != null) {
                        existPassword = customerDAO.checkPasswordExist(sessionCustomer.getCustomerId());
                    }
                    if (admin != null) {
                        existPassword = adminDAO.checkPasswordExist(sessionAdmin.getAdminId());
                    }

                } catch (NullPointerException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                    return;
                }

                String currentPassword = request.getParameter("currentPassword");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");
                String changePasswordSuccessMessage = null;
                String errorMessage = null;

                //encrypt currentpassword
                String encryptedCurrentPassword = Encryptor.toSHA1(currentPassword);
                //encrypt newpassword
                String encryptedPassword = Encryptor.toSHA1(password);

                if (password.length() < 8
                        || !password.matches(".*[A-Z].*")
                        || !password.matches(".*[^a-zA-Z0-9].*")) {
                    errorMessage = "Password must be at least 8 characters long, contains at least one capital letter and one special character.";
                } else if (encryptedPassword.equals(existPassword)) {
                    errorMessage = "Password must not match current password.";
                } else if (!password.equals(confirmPassword)) {
                    errorMessage = "Passwords do not match.";
                } else if (!encryptedCurrentPassword.equals(existPassword)) {
                    errorMessage = "Current password is incorrect.";
                } else {
                    changePasswordSuccessMessage = "Password changed successfully.";
                    if (customer != null) {
                        Customer updateCustomer = new Customer(sessionCustomer.getCustomerId(), encryptedPassword);
                        customerDAO.updateUserPassword(updateCustomer);
                    }
                    if (admin != null) {
                        Admin updateAdmin = new Admin(sessionAdmin.getAdminId(), encryptedPassword);
                        adminDAO.updateUserPassword(updateAdmin);
                    }
                    session.removeAttribute("customer");
                    session.removeAttribute("admin");
                }
                request.setAttribute("errorMessage", errorMessage);
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
