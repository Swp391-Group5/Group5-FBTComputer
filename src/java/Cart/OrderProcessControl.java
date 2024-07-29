/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Cart;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Customer;

/**
 *
 * @author admin
 */
public class OrderProcessControl extends HttpServlet {
   
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
            out.println("<title>Servlet OrderProcessControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderProcessControl at " + request.getContextPath () + "</h1>");
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
       HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("customer");
        Admin ad = (Admin) session.getAttribute("admin");
        
        int userId = 0;
        int role = 0;
        String name = null;
        String phone = null;
        
        if (cus != null) {
            userId = cus.getCustomerId();
            role = cus.getRoleId();
            name = cus.getCustomerName();
            phone = cus.getCustomerPhoneNumber();
        } else if (ad != null) {
            userId = ad.getAdminId();
            role = ad.getRoleId();
            name = ad.getAdminName();
            phone = ad.getAdminPhoneNumber();
        }
        
               
        OrderDAO orderdao = new OrderDAO();
        int count = 0;
        String address = null;
        
        count = orderdao.getTimesOfCustomerId(userId);
        
        if (count > 0) {
            address = orderdao.getAddressOrder(userId);        
        }else {
            //address = cus.getCustomerAddress();
            if (cus != null) {
                address = cus.getCustomerAddress();
            } else if (ad != null) {
                address = ad.getAdminAddress();
            }
        }
        request.setAttribute("name", name);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.getRequestDispatcher("Checkout.jsp").forward(request, response);
    } 

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
        processRequest(request, response);
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
