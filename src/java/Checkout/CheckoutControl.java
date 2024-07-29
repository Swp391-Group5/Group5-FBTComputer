/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Checkout;

import Cart.Cart;
import com.vnpay.common.Config;
import dal.CartDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderHistoryDAO;
import dal.OrderHistoryDetailDAO;
import dal.ProductDAO;
import dal.WareHouseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import model.Admin;
import model.Admins;
import model.Customer;
import model.OrderHistoryDetail;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class CheckoutControl extends HttpServlet {

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
            out.println("<title>Servlet CheckoutControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    ProductDAO productdao = new ProductDAO();
    CartDAO cartdao = new CartDAO();

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

    }

    OrderHistoryDetailDAO ohdd = new OrderHistoryDetailDAO();
    OrderHistoryDAO ohd = new OrderHistoryDAO();
    WareHouseDAO whd = new WareHouseDAO();
    ProductDAO productDAO = new ProductDAO();
    OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
    private static final long serialVersionUID = 1L;

    private static final String HOME_PAGE = "home";
    private static final String CHECKOUT_PAGE = "Checkout.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ORDER_SUCCESS_MESSAGE = "Order successfully!";
    private static final String ORDER_FAIL_MESSAGE = "Order fail!";

    OrderHistoryDAO orderhistorydao = new OrderHistoryDAO();

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
        Customer cus = (Customer) session.getAttribute("customer");
        Admin ad = (Admin) session.getAttribute("admin");
        String address = request.getParameter("address");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        Cart cart = (Cart) session.getAttribute("cart");
        int orderHistoryId;
        int cartId;
        double totalQuantity = 0;

        double totalPrice = 0;

        if (cart != null) {
            totalPrice = cart.getAmount();
        }

        if (totalPrice == 0.0) {
            response.sendRedirect(HOME_PAGE);
            return;
        }

        try {
            int userId;
            int role;

            if (cus != null) {
                userId = cus.getCustomerId();
                role = cus.getRoleId();
            } else if (ad != null) {
                userId = ad.getAdminId();
                role = ad.getRoleId();
            } else {
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
                return;
            }

            // FORMAT DATE
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateInsert = dateFormat.format(date);

            // Getting the total of product's quantity.
            for (Product product : cart.getItems()) {
                totalQuantity += product.getNumberInCart();
            }

            // Getting the payment's option.
            String method = request.getParameter("bankCode");

            if (method.equalsIgnoreCase("VNBANK")) {

                // For VNPAY method
                String vnp_Version = "2.1.0";
                String vnp_Command = "pay";
                String orderType = "other";
                //long amount = Integer.parseInt(request.getParameter("amount")) * 100;
                // param 1
                long amount = (int) totalPrice * 100;
                //String bankCode = request.getParameter("bankCode");

                String vnp_TxnRef = Config.getRandomNumber(8);
                String vnp_IpAddr = Config.getIpAddress(request);

                String vnp_TmnCode = Config.vnp_TmnCode;

                Map<String, String> vnp_Params = new HashMap<>();
                vnp_Params.put("vnp_Version", vnp_Version);
                vnp_Params.put("vnp_Command", vnp_Command);
                vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
                vnp_Params.put("vnp_Amount", String.valueOf(amount));
                vnp_Params.put("vnp_CurrCode", "VND");

//                if (bankCode != null && !bankCode.isEmpty()) {
//                    vnp_Params.put("vnp_BankCode", bankCode);
//                }
                // param 2
                vnp_Params.put("vnp_BankCode", method);
                vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
                vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
                vnp_Params.put("vnp_OrderType", orderType);

                // param 3
                vnp_Params.put("vnp_Locale", "en");

//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
                vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
                vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

                Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String vnp_CreateDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

                cld.add(Calendar.MINUTE, 15);
                String vnp_ExpireDate = formatter.format(cld.getTime());
                vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

                List fieldNames = new ArrayList(vnp_Params.keySet());
                Collections.sort(fieldNames);
                StringBuilder hashData = new StringBuilder();
                StringBuilder query = new StringBuilder();
                Iterator itr = fieldNames.iterator();
                while (itr.hasNext()) {
                    String fieldName = (String) itr.next();
                    String fieldValue = (String) vnp_Params.get(fieldName);
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        //Build hash data
                        hashData.append(fieldName);
                        hashData.append('=');
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        if (itr.hasNext()) {
                            query.append('&');
                            hashData.append('&');
                        }
                    }
                }

                // Order Processing -- Start
                OrderDAO orderdao = new OrderDAO();
                OrderDetailDAO orderdetaildao = new OrderDetailDAO();
                ohd.insertOrderHistory(totalQuantity, dateInsert, name, phone, address, userId, role, totalPrice, "Succeed", "1", "ATM");
                orderHistoryId = ohd.getOrderHistoryId();
                ///
                // Hùng - Tự trừ sp trong product
                Map<Integer, Integer> productQuantities = orderHistoryDAO.getProductQuantities(orderHistoryId);
                for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
//                    System.out.println("ProductId: " + entry.getKey() + ", Quantity: " + entry.getValue());
                    int quantityFromProduct = productDAO.getProductQuantityByProductId(entry.getKey());
                    int quantityAfterSubtraction = quantityFromProduct - entry.getValue();
                    productDAO.UpdateProductQuantity(entry.getKey(), quantityAfterSubtraction);
                }
                ///
                String var = null;
                for (Product product : cart.getItems()) {
                    if (product.getNumberInCart() > 1) {
                        for (int i = 0; i < product.getNumberInCart(); i++) {
                            do {
                                var = generateSerialNumber(product);
                            } while (whd.checkExistedSerialId(var));
                            whd.insertWarehouse(var, product.getProductId());
                            ohdd.insertOrderHistoryDetail(var, product.getProductPrice(), orderHistoryId, product.getProductId());
                        }
                    } else {
                        var = generateSerialNumber(product);
                        while (whd.checkExistedSerialId(var)) {
                            var = generateSerialNumber(product);
                        }
                        whd.insertWarehouse(var, product.getProductId());
                        ohdd.insertOrderHistoryDetail(var, product.getProductPrice(), orderHistoryId, product.getProductId());
                    }
                }

                // Get OrderId
                orderdao.insertOrder(1, orderHistoryId);
                int orderId = orderdao.getOrderId();

                List<OrderHistoryDetail> orderHisList = ohdd.getOrderHistoryDetail(orderHistoryId);

                for (OrderHistoryDetail orderHistoryDetail : orderHisList) {
                    orderdetaildao.inserOrderDetail(orderId, orderHistoryId, orderHistoryDetail.getOrderHistoryDetailId());
                }

//                cartId = cartdao.getCartId(userId, role);
//                cartdao.deleteCartDetailAfterCheckout(cartId);
//                cartdao.deleteCart(cartId);
//
//                // Delete session successfully
//                session.removeAttribute("cart");
                // Order Processing -- End
                String queryUrl = query.toString();
                String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
                queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
                String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
//                com.google.gson.JsonObject job = new JsonObject();
//                job.addProperty("code", "00");
//                job.addProperty("message", "success");
//                job.addProperty("data", paymentUrl);
//                Gson gson = new Gson();
//                response.getWriter().write(gson.toJson(job));

                response.sendRedirect(paymentUrl);
            } else {
                // Order Processing -- Start
                OrderDAO orderdao = new OrderDAO();
                OrderDetailDAO orderdetaildao = new OrderDetailDAO();
                ohd.insertOrderHistory(totalQuantity, dateInsert, name, phone, address, userId, role, totalPrice, "Pending", "1", "QR");
                orderHistoryId = ohd.getOrderHistoryId();

                String var = null;
                for (Product product : cart.getItems()) {
                    if (product.getNumberInCart() > 1) {
                        for (int i = 0; i < product.getNumberInCart(); i++) {
                            do {
                                var = generateSerialNumber(product);
                            } while (whd.checkExistedSerialId(var));
                            whd.insertWarehouse(var, product.getProductId());
                            ohdd.insertOrderHistoryDetail(var, product.getProductPrice(), orderHistoryId, product.getProductId());
                        }
                    } else {
                        var = generateSerialNumber(product);
                        while (whd.checkExistedSerialId(var)) {
                            var = generateSerialNumber(product);
                        }
                        whd.insertWarehouse(var, product.getProductId());
                        ohdd.insertOrderHistoryDetail(var, product.getProductPrice(), orderHistoryId, product.getProductId());
                    }
                }

                // Get OrderId
                orderdao.insertOrder(1, orderHistoryId);
                int orderId = orderdao.getOrderId();

                List<OrderHistoryDetail> orderHisList = ohdd.getOrderHistoryDetail(orderHistoryId);

                for (OrderHistoryDetail orderHistoryDetail : orderHisList) {
                    orderdetaildao.inserOrderDetail(orderId, orderHistoryId, orderHistoryDetail.getOrderHistoryDetailId());
                }

//                cartId = cartdao.getCartId(userId, role);
//                cartdao.deleteCartDetailAfterCheckout(cartId);
//                cartdao.deleteCart(cartId);
                // Delete session successfully
//                session.removeAttribute("cart");
                // Order Processing -- End
                response.sendRedirect("checkoutqr?amount=" + totalPrice);
            }

        } catch (Exception e) {
            request.setAttribute("message", ORDER_FAIL_MESSAGE);
            request.getRequestDispatcher(CHECKOUT_PAGE).forward(request, response);
        }
    }

    private String generateSerialNumber(Product product) {
        String prefix = productdao.getCategoryName(product.getProductId()).substring(0, 2).toUpperCase();
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String number = String.format("%06d", randomNumber);

        return prefix + number;
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
