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
import java.util.List;
import model.Admin;
import model.Customer;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class CartProcess extends HttpServlet {

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
            out.println("<title>Servlet CartProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartProcess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    CartDAO cartdao = new CartDAO();
    ProductDAO productdao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        String id = request.getParameter("productId");
        Customer cus = (Customer) session.getAttribute("customer");
        //Admin ad = (Admin) session.getAttribute("admin");
        Product p = productdao.getProductByProductId(id);
        //Cart c = (Cart) session.getAttribute("cart");

        // Trường hợp admin
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin != null) {
            if (session.getAttribute("cart") == null) {
                Cart c = new Cart();
                Cart carts = cartdao.getCartByCustomerId(admin.getAdminId(), admin.getRoleId());
                List<Product> cartdetaillist = cartdao.getCartDetailsByCartId(carts.getCartId());
                c.addItems(cartdetaillist);
                session.setAttribute("cart", c);
            }

            Cart c = (Cart) session.getAttribute("cart");
            c.adds(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage(), 1));
            c.setCustomerId(admin.getAdminId());
            c.setRoleId(admin.getRoleId());

            // Chỉ lưu Cart nếu nó chưa được lưu trước đó
            if (!c.isIsSaved() && !cartdao.checkExistedCart(admin.getAdminId(), admin.getRoleId())) {
                cartdao.insertCart(c);
                c.setIsSaved(true);
            }

            if (!cartdao.checkExistedCartDetail(p.getProductId(), cartdao.getCartId(admin.getAdminId(), admin.getRoleId()))) {
                cartdao.insertCartDetail(new CartDetail(p.getProductId(), p.getProductName(), p.getProductImage(), 1, p.getProductPrice()), cartdao.getCartId(admin.getAdminId(), admin.getRoleId()));
            } else {
                cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(admin.getAdminId(), admin.getRoleId()), p.getProductId());
            }

            // Lưu tổng chi phí trong CART
            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(admin.getAdminId(), admin.getRoleId()));

            String cartItemCount = String.valueOf(c.getItems().size());
            session.setAttribute("cart", c);
            response.sendRedirect("home");
            return;
        }

        // Trường hợp customer
        //Customer cus = (Customer) session.getAttribute("customer");
        if (cus != null) {

            if (session.getAttribute("cart") == null) {
                Cart c = new Cart();
                Cart carts = cartdao.getCartByCustomerId(cus.getCustomerId(), cus.getRoleId());
                List<Product> cartdetaillist = cartdao.getCartDetailsByCartId(carts.getCartId());
                c.addItems(cartdetaillist);
                session.setAttribute("cart", c);
            }

            Cart c = (Cart) session.getAttribute("cart");
            c.adds(new Product(p.getProductId(), p.getProductName(), (float) p.getProductPrice(), p.getProductQuantity(), p.getProductImage(), 1));
            c.setCustomerId(cus.getCustomerId());
            c.setRoleId(cus.getRoleId());

            // Chỉ lưu Cart nếu nó chưa được lưu trước đó
            if (!c.isIsSaved() && !cartdao.checkExistedCart(cus.getCustomerId(), cus.getRoleId())) {
                cartdao.insertCart(c);
                c.setIsSaved(true);
            }

            if (!cartdao.checkExistedCartDetail(p.getProductId(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()))) {
                cartdao.insertCartDetail(new CartDetail(p.getProductId(), p.getProductName(), p.getProductImage(), 1, p.getProductPrice()), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));
            } else {
                cartdao.updateCartDetail(c.getNumInCart(p), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()), p.getProductId());
            }

            // Lưu tổng chi phí trong CART
            cartdao.updateCartTotalCost(c.getAmount(), cartdao.getCartId(cus.getCustomerId(), cus.getRoleId()));

            String cartItemCount = String.valueOf(c.getItems().size());
            session.setAttribute("cart", c);
            response.sendRedirect("home");
        } else {
            // Xử lý trường hợp không tìm thấy khách hàng hoặc quản trị viên
            response.sendRedirect("login.jsp");
        }

//            out.println("""
//                                        <span id="content" class="position-absolute bg-secondary rounded-pill d-flex align-items-center justify-content-center text-dark px-1 cart-item-count"> 
//                                            """ + cartItemCount + """
//                                        </span>""");
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
