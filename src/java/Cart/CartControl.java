/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Cart;

import dal.CartDAO;
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
import model.Admin;
import model.Admins;
import model.Customer;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class CartControl extends HttpServlet {

    CartDAO cartdao = new CartDAO();

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

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            ProductDAO productdao = new ProductDAO();
            HttpSession session = request.getSession(true);
            String id = request.getParameter("productId");
            String action = request.getParameter("action");
            String quantity = request.getParameter("productQuantity");
            Customer cus = (Customer) session.getAttribute("customer");
            Admin ad = (Admin) session.getAttribute("admin");

            String message = null;

            if (cus != null) {
                if (!(id == null && action == null)) {

                    // FOR ADD BUTTON
                    if (action != null && action.equalsIgnoreCase("add")) {
                        if (session.getAttribute("cart") == null) {
                            Cart c = new Cart();
                            Cart carts = cartdao.getCartByCustomerId(cus.getCustomerId(), cus.getRoleId());
                            List<Product> cartdetaillist = cartdao.getCartDetailsByCartId(carts.getCartId());
                            c.addItems(cartdetaillist);
                            session.setAttribute("cart", c);
                        }
                        Product p = productdao.getProductByProductId(id);
                        Cart c = (Cart) session.getAttribute("cart");

                        if ((p.getProductQuantity() != 0) && (c.getNumInCart(p) > p.getProductQuantity())) {
                            c.setNumInCart(p);
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
                            //session.setAttribute("message", "Cannot add more of this product. Stock limit overreached!");
                            message = "Cannot add more of this product. Stock limit overreached!";
                        } else if ((p.getProductQuantity() == 0)) {
                            c.remove(Integer.parseInt(id));

                            // Delete Cart Detail with ProductId. DB
                            cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                            // Update total cost in Cart. DB
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                            //session.setAttribute("message", "Outta stock!");
                            message = "Outta stock!";
                        } else {
                            c.adds(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage(), 1));
                            c.setCustomerId(cus.getCustomerId());
                            c.setRoleId(cus.getRoleId());

                            // just saved Cart if it dont be saved before
                            if (!c.isIsSaved() && !cartdao.checkExistedCart(cus.getCustomerId(), cus.getRoleId())) {
                                cartdao.insertCart(c);
                                c.setIsSaved(true);
                            }

                            if (!cartdao.checkExistedCartDetail(p.getProductId(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()))) {
                                cartdao.insertCartDetail(new CartDetail(p.getProductId(), p.getProductName(), p.getProductImage(), 1, p.getProductPrice()), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
                            } else {
                                cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());
                            }

                            // Saved totalcost in CART
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                            session.setAttribute("cart", c);
                        }
                    }

                    // For UPDATE INPUT
                    if (action != null && action.equalsIgnoreCase("update")) {
                        try {
                            Product p = productdao.getProductByProductId(id);
                            Cart c = (Cart) session.getAttribute("cart");
                            int quantities = Integer.parseInt(quantity);


                            if ((p.getProductQuantity() == 0)) {
                                c.remove(Integer.parseInt(id));

                                // Delete Cart Detail with ProductId. DB
                                cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                                // Update total cost in Cart. DB
                                cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                                message = "Outta stock!";
                            } else {
                                c.updateItem(p.getProductId(), quantities);

                                // Update the quantity of Cart Detail on DB.
                                cartdao.updateCartDetail(quantities, cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());

                                // Update total cost in Cart. DB
                                cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));


                                if (quantities > p.getProductQuantity()) {
                                    message = "Your input must be lower than quantity!";
                                }else if (quantities <= 0) {
                                    message = "Your input must be greater than 0!";
                                }else {
                                    response.sendRedirect("cartcheck");
                                }
                                session.setAttribute("cart", c);
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("message", "Quantity must be numbers");
                        }
                    }

                    // FOR MINUS BUTTON
                    if (action != null && action.equalsIgnoreCase("minus")) {
                        Product p = productdao.getProductByProductId(id);
                        Cart c = (Cart) session.getAttribute("cart");

                        if ((p.getProductQuantity() != 0) && (c.getNumInCart(p) > p.getProductQuantity())) {
                            c.setNumInCart(p);
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
                            //session.setAttribute("message", "Cannot reduce the quantity of this product futher!");
                            message = "Cannot reduce the quantity of this product futher!";
                        } else if ((p.getProductQuantity() == 0)) {
                            c.remove(Integer.parseInt(id));

                            // Delete Cart Detail with ProductId. DB
                            cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                            // Update total cost in Cart. DB
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                            //session.setAttribute("message", "Outta stock!");
                            message = "Outta stock!";
                        } else {
                            c.minus(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage()));

                            // Update CartDetail In DB.
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());
                            // Update total cost in Cart.
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
                            session.setAttribute("cart", c);
                        }

                    } // FOR DELETE BUTTON
                    else if (action != null && action.equalsIgnoreCase("delete")) {
                        Cart c = (Cart) session.getAttribute("cart");
                        c.remove(Integer.parseInt(id));

                        // Delete Cart Detail with ProductId. DB
                        cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                        // Update total cost in Cart. DB
                        cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

                        // Delete Cart if there isn't any products in Cart. DB
                        if (c.getItems().isEmpty()) {
                            cartdao.deleteCart(cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
                        }
                        session.setAttribute("cart", c);
                    }
                }
            } else if (ad != null) {
                if (!(id == null && action == null)) {

                    // FOR ADD BUTTON
                    if (action != null && action.equalsIgnoreCase("add")) {
                        if (session.getAttribute("cart") == null) {
                            Cart c = new Cart();
                            Cart carts = cartdao.getCartByCustomerId(ad.getAdminId(), ad.getRoleId());
                            List<Product> cartdetaillist = cartdao.getCartDetailsByCartId(carts.getCartId());
                            c.addItems(cartdetaillist);
                            session.setAttribute("cart", c);
                        }
                        Product p = productdao.getProductByProductId(id);
                        Cart c = (Cart) session.getAttribute("cart");

                        if ((p.getProductQuantity() != 0) && (c.getNumInCart(p) > p.getProductQuantity())) {
                            c.setNumInCart(p);
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()), p.getProductId());
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));
                            //session.setAttribute("message", "Cannot add more of this product. Stock limit overreached!");
                            message = "Cannot add more of this product. Stock limit overreached!";
                        } else if ((p.getProductQuantity() == 0)) {
                            c.remove(Integer.parseInt(id));

                            // Delete Cart Detail with ProductId. DB
                            cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                            // Update total cost in Cart. DB
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                            //session.setAttribute("message", "Outta stock!");
                            message = "Outta stock!";
                        } else {
                            c.adds(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage(), 1));
                            c.setCustomerId(ad.getAdminId());
                            c.setCustomerId(ad.getRoleId());

                            // just saved Cart if it dont be saved before
                            if (!c.isIsSaved() && !cartdao.checkExistedCart(ad.getAdminId(), ad.getRoleId())) {
                                cartdao.insertCart(c);
                                c.setIsSaved(true);
                            }

                            if (!cartdao.checkExistedCartDetail(p.getProductId(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()))) {
                                cartdao.insertCartDetail(new CartDetail(p.getProductId(), p.getProductName(), p.getProductImage(), 1, p.getProductPrice()), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));
                            } else {
                                cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()), p.getProductId());
                            }

                            // Saved totalcost in CART
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                            session.setAttribute("cart", c);
                        }
                    }

                    // For UPDATE INPUT
                    if (action != null && action.equalsIgnoreCase("update")) {
                        try {
                            Product p = productdao.getProductByProductId(id);
                            Cart c = (Cart) session.getAttribute("cart");
                            int quantities = Integer.parseInt(quantity);

                            if ((p.getProductQuantity() == 0)) {
                                c.remove(Integer.parseInt(id));

                                // Delete Cart Detail with ProductId. DB
                                cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                                // Update total cost in Cart. DB
                                cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                                message = "Outta stock!";
                            } else {
                                c.updateItem(p.getProductId(), quantities);

                                // Update the quantity of Cart Detail on DB.
                                cartdao.updateCartDetail(quantities, cartdao.getCartId(ad.getAdminId(),ad.getRoleId()), p.getProductId());

                                // Update total cost in Cart. DB
                                cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));


                                if (quantities > p.getProductQuantity()) {
                                    message = "Your input must be lower than quantity!";
                                }else if (quantities <= 0) {
                                    message = "Your input must be greater than 0!";
                                }else {
                                    response.sendRedirect("cartcheck");
                                }
                                session.setAttribute("cart", c);
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("message", "Quantity must be numbers");
                        }
                    }

                    // FOR MINUS BUTTON
                    if (action != null && action.equalsIgnoreCase("minus")) {
                        Product p = productdao.getProductByProductId(id);
                        Cart c = (Cart) session.getAttribute("cart");

                        if ((p.getProductQuantity() != 0) && (c.getNumInCart(p) > p.getProductQuantity())) {
                            c.setNumInCart(p);
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()), p.getProductId());
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));
                            //session.setAttribute("message", "Cannot reduce the quantity of this product futher!");
                            message = "Cannot reduce the quantity of this product futher!";
                        } else if ((p.getProductQuantity() == 0)) {
                            c.remove(Integer.parseInt(id));

                            // Delete Cart Detail with ProductId. DB
                            cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                            // Update total cost in Cart. DB
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                            //session.setAttribute("message", "Outta stock!");
                            message = "Outta stock!";
                        } else {
                            c.minus(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage()));

                            // Update CartDetail In DB.
                            cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()), p.getProductId());
                            // Update total cost in Cart.
                            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));
                            session.setAttribute("cart", c);
                        }

                    } // FOR DELETE BUTTON
                    else if (action != null && action.equalsIgnoreCase("delete")) {
                        Cart c = (Cart) session.getAttribute("cart");
                        c.remove(Integer.parseInt(id));

                        // Delete Cart Detail with ProductId. DB
                        cartdao.deleteCartDetail(Integer.parseInt(id), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                        // Update total cost in Cart. DB
                        cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));

                        // Delete Cart if there isn't any products in Cart. DB
                        if (c.getItems().isEmpty()) {
                            cartdao.deleteCart(cartdao.getCartId(ad.getAdminId(), ad.getRoleId()));
                        }
                        session.setAttribute("cart", c);
                    }
                }                
            }

            // Notification proceed
            if (message != null) {
                request.setAttribute("message", message);
            }
            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
