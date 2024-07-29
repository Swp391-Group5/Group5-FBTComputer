/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BlogDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.SlideDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Blog;
import model.Category;
import model.Product;
import model.Slider;

/**
 *
 * @author admin
 */
public class HomeControl extends HttpServlet {

//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet HomeControl</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet HomeControl at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private BlogDAO blogDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        blogDAO = new BlogDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // getparam
        String minPrice_raw = request.getParameter("minRange");
        String maxPrice_raw = request.getParameter("maxRange");

        String sortBy_raw = request.getParameter("sortBy");
        String categoryId_raw = request.getParameter("categoryId");
        String ProductBrand = request.getParameter("brand");
        String productName = request.getParameter("productName");

        int categoryId = 0, sortBy = 0;
        int maxPrice = 0;
        int minPrice = 0;

        // Pagination parameters
        int index = 1;
        int pageSize = 5; // thay đổi page size ở đay

        try {

            maxPrice = (maxPrice_raw == null || maxPrice_raw.isEmpty()) ? 0 : Integer.parseInt(maxPrice_raw);
            minPrice = (minPrice_raw == null || minPrice_raw.isEmpty()) ? 0 : Integer.parseInt(minPrice_raw);
            categoryId = (categoryId_raw == null || categoryId_raw.isEmpty()) ? 0 : Integer.parseInt(categoryId_raw);
            sortBy = (sortBy_raw == null || sortBy_raw.isEmpty()) ? 0 : Integer.parseInt(sortBy_raw);
            String indexParam = request.getParameter("index");
            if (indexParam != null && !indexParam.isEmpty()) {
                index = Integer.parseInt(indexParam);
            }

            String pageSizeParam = request.getParameter("pageSize");
            if (pageSizeParam != null && !pageSizeParam.isEmpty()) {
                pageSize = Integer.parseInt(pageSizeParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Get total product count
        //int count = productDAO.getTotalProduct();
        int total_size = productDAO.getSize(productName, categoryId, minPrice, minPrice, sortBy, ProductBrand);

        // Calculate number of pages
        int num = (int) Math.ceil((double) total_size / pageSize);

        // Adjust current page index if out of bounds
        if (index > num) {
            index = num;
        }

        // Retrieve paginated list of products
        List<Product> productList = productDAO.searchAndPagingProductByName2(productName, categoryId, maxPrice, minPrice, sortBy, ProductBrand, index, pageSize);

        // Calculate start and end indices for current page
        int start = (index - 1) * pageSize + 1;
        int end = Math.min(index * pageSize, total_size);

        // Retrieve slider information
        int categoryBlogId = 2; // Example categoryBlogId, replace with your desired category ID
        Blog sliderFirst = blogDAO.getFirstSlider(categoryBlogId);
        session.setAttribute("sliderFirst", sliderFirst);

        int totalSlider = blogDAO.getcountSlider();
        session.setAttribute("totalSlider", totalSlider);

        List<Blog> listSliderHomePageAll = blogDAO.getAllSlidersByCategory(categoryBlogId);
        session.setAttribute("listSlider_HomePageAll", listSliderHomePageAll);

        // Set attributes for JSP
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("listP", productList);
        request.setAttribute("num", num);
        request.setAttribute("currentPage", index);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        //đẩy lại dữ liệu lên 
        request.setAttribute("maxPrice", (long) maxPrice);
        request.setAttribute("minPrice", (long) minPrice);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("brand", ProductBrand);
        request.setAttribute("productName", productName);
        request.setAttribute("totalSize", total_size);
        request.setAttribute("productName", productName);

        // Retrieve categories
        List<Category> categoryList = categoryDAO.getAllCategory();
        request.setAttribute("listC", categoryList);

        // Forward to JSP
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

}

/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
