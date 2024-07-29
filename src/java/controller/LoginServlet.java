/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Cart.Cart;
import dal.AdminDAO;
import Utils.Encryptor;
import dal.CartDAO;
import dal.CustomerDAO;
import model.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;
import model.Product;

@WebServlet(name = "login", urlPatterns = {"/login"})

public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        //get email , pass from cookie
        Cookie arr[] = request.getCookies();
        for (Cookie o : arr) {
            if (o.getName().equals("u_Email")) {
                request.setAttribute("email", o.getValue());
            }
            if (o.getName().equals("u_Password")) {
                request.setAttribute("password", o.getValue());
            }

            if (o.getName().equals("r_reMem")) {
                request.setAttribute("remember_me", o.getValue());
            }
        }
        // set email , pass into login form
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String email = request.getParameter("login-email");
        String rawPassword = request.getParameter("login-password");
        String hashedPassword = Encryptor.toSHA1(rawPassword);
        String rememberMe = request.getParameter("remember_me");

        System.out.println("Email: " + email);
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);
        System.out.println("Remember Me: " + rememberMe);

        CustomerDAO customerDAO = new CustomerDAO();
        AdminDAO adminDAO = new AdminDAO();
        Customer customer = customerDAO.CustomerLogin(email, hashedPassword);
        Admin admin = adminDAO.AdminLogin(email, hashedPassword);
        try {

            // If either a customer or admin is found, set session attributes and cookies
            if (customer != null || admin != null) {
                Cart cart = new Cart();
                
                if (admin != null) {
                    setupSessionAndCart(session, admin.getAdminId(), admin.getRoleId(), cart);
                    session.setAttribute("admin", admin);
                    setCookies(response, email, rawPassword, rememberMe);
                    request.getRequestDispatcher("adminDashboard").forward(request, response);
                    return; // Return to prevent further code execution
                }

                if (customer != null) {
                    setupSessionAndCart(session, customer.getCustomerId(), customer.getRoleId(), cart);
                    session.setAttribute("customer", customer);
                    setCookies(response, email, rawPassword, rememberMe);
                    response.sendRedirect("home");
                    //request.getRequestDispatcher("home").forward(request, response);
                }
            } else {
                // If login fails, set error message and forward back to login page
                request.setAttribute("msg", "Email or password incorrect!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

//            if (admin != null) {
//                session.setAttribute("admin", admin);
//                setCookies(response, email, rawPassword, rememberMe);
//                request.getRequestDispatcher("adminDashboard").forward(request, response);
//            } else if (customer != null) {
//                session.setAttribute("customer", customer);
//                setCookies(response, email, rawPassword, rememberMe);
//                //                request.getRequestDispatcher("home").forward(request, response);
//                response.sendRedirect("home");
//            } else {
//                System.out.println("Login failed for email: " + email);
//                request.setAttribute("msg", "Email or password incorrect!");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "An error occurred. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void setCookies(HttpServletResponse response, String email, String password, String rememberMe) {
        Cookie emailCookie = new Cookie("u_Email", email);
        Cookie passwordCookie = new Cookie("u_Password", password);
        Cookie rememberMeCookie = new Cookie("r_reMem", rememberMe);
        int cookieMaxAge = (rememberMe != null) ? 60 * 60 * 24 * 30 * 3 : 0;
        emailCookie.setMaxAge(60 * 60 * 24 * 30 * 3);
        passwordCookie.setMaxAge(cookieMaxAge);
        rememberMeCookie.setMaxAge(cookieMaxAge);
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.addCookie(rememberMeCookie);
    }

// Helper method to set up session and cart
    private void setupSessionAndCart(HttpSession session, int userId, int roleId, Cart cart) {
        CartDAO cartDAO = new CartDAO();
        Cart dbCart = cartDAO.getCartByCustomerId(userId, roleId);
        List<Product> cartDetailList = cartDAO.getCartDetailsByCartId(dbCart.getCartId());
        cart.addItems(cartDetailList);
        session.setAttribute("cart", cart);
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
