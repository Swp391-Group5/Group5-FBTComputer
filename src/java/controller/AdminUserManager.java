/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AdminCustomerRoleViewDAO;
import dal.AdminDAO;
import dal.CustomerDAO;
import model.Admin;
import model.AdminCustomerRoleView;
import model.City;
import model.Customer;
import model.Filter;
import model.Roles;
import model.SortBy;
import Utils.Encryptor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author hungp
 */
@WebServlet(name = "AdminUserManager", urlPatterns = {"/userManager"})
public class AdminUserManager extends HttpServlet {

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
        AdminCustomerRoleViewDAO acrDAO = new AdminCustomerRoleViewDAO();
        Customer customer = null;
        Admin admin = null;
        Admin sessionAdmin = null;
        List<AdminCustomerRoleView> listOfACR = null;
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            session.setAttribute("loggedInAdminId", sessionAdmin.getAdminId());
            if (sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
        request.setAttribute("listOfRoles", Roles.values());
        request.setAttribute("listOfFilter", Filter.values());
        request.setAttribute("listOfSortBy", SortBy.values());
        request.setAttribute("listOfCity", City.values());
        String indexParam = request.getParameter("index");
        int index = 1;
        try {
            if (indexParam != null && !indexParam.isEmpty()) {
                index = Integer.parseInt(indexParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Get the page size from the request, defaulting to 5 if not provided or invalid
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
        switch (action) {
            case "view" -> {
                String filter = request.getParameter("filter");
                String sortBy = request.getParameter("sortBy");

                if (filter == null && sortBy == null) {
                    try {
                        count = acrDAO.getTotalUser();
                        listOfACR = acrDAO.getAllPaging(index, pageSize);
                        request.setAttribute("main", "main");
                    } catch (SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                        return;
                    }
                } else if (filter != null) {
                    switch (filter) {
                        case "Customer" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("RoleName LIKE '%Customer%'");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Admin" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("RoleName LIKE '%Admin%'");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Male" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("Gender = 0");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Female" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("Gender = 1");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Inactived" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("Status = 0");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Actived" -> {
                            try {
                                listOfACR = acrDAO.getAllByCondition("Status = 1");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        default -> {
                        }
                    }
                } else if (sortBy != null) {
                    switch (sortBy) {
                        case "ID" -> {
                            try {
                                listOfACR = acrDAO.getAllSortedBy("ID");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Fullname" -> {
                            try {
                                listOfACR = acrDAO.getAllSortedBy("Name");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Gender" -> {
                            try {
                                listOfACR = acrDAO.getAllSortedBy("Gender");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Email" -> {
                            try {
                                listOfACR = acrDAO.getAllSortedBy("Email");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        case "Mobile" -> {
                            try {
                                listOfACR = acrDAO.getAllSortedBy("PhoneNumber");
                            } catch (SQLException e) {
                                response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                                return;
                            }
                        }
                        default -> {
                        }
                    }
                }
                int num = (int) Math.ceil((double) count / pageSize);
                int start = (index - 1) * pageSize + 1;
                int end = Math.min(index * pageSize, count);
                request.setAttribute("start", start);
                request.setAttribute("num", num);
                request.setAttribute("end", end);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute("currentPage", index);
                request.setAttribute("totalProducts", count);
                request.setAttribute("listOfACR", listOfACR);
                request.getRequestDispatcher("/admin/userManager.jsp").forward(request, response);
            }
            case "search" -> {
                String query = request.getParameter("query");
                String criteria = request.getParameter("criteria");
                switch (criteria) {
                    case "Username" -> {
                        try {
                            listOfACR = acrDAO.getAllByCondition("Name LIKE" + "'%" + query + "%'");
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "Email" -> {
                        try {
                            listOfACR = acrDAO.getAllByCondition("Email LIKE" + "'%" + query + "%'");
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    case "Phone Number" -> {
                        try {
                            listOfACR = acrDAO.getAllByCondition("PhoneNumber LIKE" + "'%" + query + "%'");
                        } catch (SQLException e) {
                            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                            return;
                        }
                    }
                    default ->
                        throw new AssertionError();
                }
                request.setAttribute("listOfACR", listOfACR);
                request.getRequestDispatcher("/admin/userManager.jsp").forward(request, response);
            }
            case "add0" -> {
                request.setAttribute("action", "add");
                request.getRequestDispatcher("/admin/addOrUpdateUser.jsp").forward(request, response);
            }
            case "update0" -> {
                String userIdStr = request.getParameter("id");
                String roleIdStr = request.getParameter("roleId");
                int userId;
                int roleId;
                AdminCustomerRoleView acrUser;
                try {
                    userId = Integer.parseInt(userIdStr);
                    roleId = Integer.parseInt(roleIdStr);
                    acrUser = acrDAO.getUserByIDAndRoleID(userId, roleId);
                } catch (SQLException | NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                request.setAttribute("acrUser", acrUser);
                request.setAttribute("action", "update");
                request.getRequestDispatcher("/admin/addOrUpdateUser.jsp").forward(request, response);
            }
            case "add" -> {
                String email = (String) request.getAttribute("email");
                String password = (String) request.getAttribute("password");
                String accountName = (String) request.getAttribute("accountName");
                String genderStr = (String) request.getAttribute("gender");
                String phoneStr = (String) request.getAttribute("phone");
                String dateOfBirthStr = (String) request.getAttribute("dateOfBirth");
                String city = (String) request.getAttribute("city");
                String address = (String) request.getAttribute("address");
                String roleStr = (String) request.getAttribute("role");
                String statusStr = (String) request.getAttribute("status");
                try {
                    int role;
                    boolean gender = genderStr.equals("Female");
                    boolean status = statusStr.equals("1");
                    LocalDate dateOfBirth;
                    try {
                        role = Integer.parseInt(roleStr);
                    } catch (NumberFormatException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                        return;
                    }
                    try {
                        dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        request.setAttribute("errorMessageDateOfBirth", "Invalid date format.");
                        request.setAttribute("action", "add");
                        request.getRequestDispatcher("/admin/addOrUpdateUser.jsp").forward(request, response);
                        return;
                    }

                    java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(dateOfBirth);
                    int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
                    AdminCustomerRoleView acrError = new AdminCustomerRoleView(accountName, email, password, gender, address, city, phoneStr, sqlDateOfBirth, status, role);
                    request.setAttribute("acrError", acrError);

                    boolean hasErrors = false;
                    String errorMessageDateOfBirth = null;
                    String errorMessagePhone = null;
                    String errorEmailExist = null;

                    if (!(phoneStr.length() == 10 && phoneStr.charAt(0) == '0')) {
                        errorMessagePhone = "Phone number must start with a zero digit and have exactly 10 digits.";
                        hasErrors = true;
                    }

                    if (age < 18) {
                        errorMessageDateOfBirth = "You must be at least 18 years old.";
                        hasErrors = true;
                    } else if (age > 60) {
                        errorMessageDateOfBirth = "You must be under 60 years old.";
                        hasErrors = true;
                    }

                    if (acrDAO.checkEmail(email)) {
                        errorEmailExist = "Email already exists.";
                        hasErrors = true;
                    }

                    if (hasErrors) {
                        request.setAttribute("errorEmailExist", errorEmailExist);
                        request.setAttribute("errorMessagePhone", errorMessagePhone);
                        request.setAttribute("errorMessageDateOfBirth", errorMessageDateOfBirth);
                        request.setAttribute("action", "add");
                        request.getRequestDispatcher("/admin/addOrUpdateUser.jsp").forward(request, response);
                        return;
                    }

                    try {
                        if (role == 1) {
                            Customer updateCustomer = new Customer(accountName, age, email, Encryptor.toSHA1(password), gender, address, city, phoneStr, sqlDateOfBirth, status, role);
                            customerDAO.InsertNewCustomer(updateCustomer);
                        } else if (role == 2) {
                            Admin updateAdmin = new Admin(accountName, age, email, Encryptor.toSHA1(password), gender, address, city, phoneStr, sqlDateOfBirth, status, role);
                            adminDAO.InsertNewAdmin(updateAdmin);
                        }
                        response.sendRedirect(request.getContextPath() + "/userManager");
                    } catch (IOException | SQLException e) {
                        response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                    }
                } catch (ServletException | IOException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp?error=" + e);
                }
            }

            case "update" -> {
                String userIdStr = request.getParameter("id");
                String roleIdStr = request.getParameter("role");
                String roleNameStr = request.getParameter("roleName");
                String statusStr = request.getParameter("status");
                int userId;
                int roleId;
                boolean status = statusStr.equals("1");
                try {
                    userId = Integer.parseInt(userIdStr);
                    roleId = Integer.parseInt(roleIdStr);
                    customer = customerDAO.getCustomerByIdAndRoleID(userId, roleId);
                    admin = adminDAO.getAdminByIdAndRoleID(userId, roleId);

                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
                try {
                    if (roleNameStr.equals("Customer")) {
                        if (customer != null && roleId == 1) {
                            customerDAO.updateCustomerStatus(userId, status);
                        } else if (roleId == 2) {
                            Admin updatedAdmin = new Admin();
                            customerDAO.deleteCustomerAndAddToAdmin(userId, status, roleId, updatedAdmin);
                        }
                    }
                    if (roleNameStr.equals("Admin")) {
                        if (admin != null && roleId == 2) {
                            adminDAO.updateAdminStatus(userId, status);
                        } else if (roleId == 1) {
                            Customer updatedCustomer = new Customer();
                            adminDAO.deleteAdminAndAddToCustomer(userId, status, roleId, updatedCustomer);
                        }
                    }
                    response.sendRedirect(request.getContextPath() + "/userManager");
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath() + "/error/error.jsp");
                    return;
                }
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
