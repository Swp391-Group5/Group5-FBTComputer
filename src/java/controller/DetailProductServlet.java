/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FeedBackDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FeedbackOrderProduct;
import model.Product;

/**
 *
 * @author admin
 */
@WebServlet(name = "DetailProductServlet", urlPatterns = {"/detail"})
public class DetailProductServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProductServlet at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");
        ProductDAO pdao = new ProductDAO();

// Lưu productId vào danh sách sản phẩm đã xem trong session
        HttpSession session = request.getSession();
        List<String> viewedProductIds = (List<String>) session.getAttribute("viewedProductIds");
        FeedBackDAO feedBackDAO = new FeedBackDAO();
        int totalFeedback = 0;
        int sumStar = 0;
        List<FeedbackOrderProduct> feedback = null;
        if (viewedProductIds == null) {
            viewedProductIds = new ArrayList<>();
        }

// Thêm productId vào danh sách sản phẩm đã xem nếu chưa có
        if (!viewedProductIds.contains(productId)) {
            viewedProductIds.add(0, productId); // Thêm vào đầu danh sách để đảm bảo sản phẩm mới nhất xuất hiện đầu tiên
            session.setAttribute("viewedProductIds", viewedProductIds);

            // Giới hạn số lượng sản phẩm đã xem thành 5 sản phẩm mới nhất
            if (viewedProductIds.size() > 6) {
                viewedProductIds = viewedProductIds.subList(0, 6);
                session.setAttribute("viewedProductIds", viewedProductIds);
            }
        }

// Tạo một danh sách để lưu thông tin chi tiết các sản phẩm đã xem
        List<Product> viewedProductDetails = new ArrayList<>();

// Lặp qua từng ID sản phẩm đã xem và lấy thông tin chi tiết
        for (String viewedProductId : viewedProductIds) {
            try {
                Product viewedProduct = pdao.getProductByProductId(viewedProductId);
                if (viewedProduct != null) {
                    viewedProductDetails.add(viewedProduct);
                }
            } catch (Exception e) {
                // Xử lý ngoại lệ nếu có lỗi khi lấy thông tin sản phẩm
                e.printStackTrace(); // Hoặc xử lý theo cách phù hợp với ứng dụng của bạn
            }
        }

// Lấy thông tin chi tiết sản phẩm hiện tại
        Product p = null;
        try {
            p = pdao.getProductByProductId(productId);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi khi lấy thông tin sản phẩm
            e.printStackTrace(); // Hoặc xử lý theo cách phù hợp với ứng dụng của bạn
        }

// Lấy danh sách sản phẩm tương tự của sản phẩm hiện tại
        List<Product> similarProducts = null;
        try {
            similarProducts = pdao.getTop2SimilarProducts(productId);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi khi lấy danh sách sản phẩm tương tự
            e.printStackTrace(); // Hoặc xử lý theo cách phù hợp với ứng dụng của bạn
        }

        try {
            feedback = feedBackDAO.getAllFeedbackByProductId(productId);
        } catch (SQLException ex) {
            Logger.getLogger(DetailProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        totalFeedback = feedBackDAO.getTotalFeedbackByProductId(productId);
        sumStar = feedBackDAO.getSumStarByProductId(productId);
// Đặt các thuộc tính vào request
        request.setAttribute("viewedProductDetails", viewedProductDetails);
        request.setAttribute("similarProducts", similarProducts);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("sumStar", sumStar);
        request.setAttribute("detail", p);
        request.setAttribute("feedback", feedback);

// Chuyển hướng đến trang detailsP.jsp để hiển thị thông tin sản phẩm
        request.getRequestDispatcher("detailsP.jsp").forward(request, response);

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
