/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Cart;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class CartCheck extends HttpServlet {

    ProductDAO productdao = new ProductDAO();

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

            HttpSession session = request.getSession();
            Cart c = (Cart) session.getAttribute("cart");

            // For quantity is equal to 0
            List<Integer> list = new ArrayList<>();
            // For quantity is less than its number in cart
            List<Integer> lists = new ArrayList<>();

            // For containing String
            List<String> lis = new ArrayList<>();

            for (Product product : c.getItems()) {
                if (product.getProductQuantity() == 0) {
                    list.add(product.getProductId());
                }
                if (product.getNumberInCart() > product.getProductQuantity()) {
                    lists.add(product.getProductId());
                }
            }

            if (list.isEmpty() && lists.isEmpty()) {
                response.sendRedirect("orderprocess");
            } else {
                if (!list.isEmpty()) {
                    for (Integer productId : list) {
                        c.remove(productId);
                    }
                    request.setAttribute("message", "Something went wrong because several products are outta stock!");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
                if (!lists.isEmpty()) {

                    // Set the number products in cart to its quantity.
                    for (int i = 0; i < lists.size(); i++) {
                        int index = lists.get(i);
                        c.setNumInCart(productdao.getProductByProductId(String.valueOf(index)));
                    }

                    // Display to alert.
                    lis.add("Stock limit overreached with id = ");

                    if (lists.size() == 1) {
                        // If there's only one element in the list
                        lis.add(lists.get(0).toString());
                    } else {
                        // If there are multiple elements in the list
                        for (int i = 0; i < lists.size(); i++) {
                            if (i == lists.size() - 1) {
                                // Last index, no comma
                                lis.add(lists.get(i).toString());
                            } else {
                                // All other indices, add a comma
                                lis.add(lists.get(i).toString() + ", ");
                            }
                        }
                    }

                    request.setAttribute("message", lis);
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
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
