package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.BlogDAO;
import dal.CategoryDAO;
import dal.CustomerDAO;
import dal.FeedBackDAO;
import dal.OrderDAO;
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
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Blog;
import model.Category;
import model.Customer;
import model.Order;
import model.Product;
import model.ProductOrder;

/**
 *
 * @author hungp
 */
@WebServlet(urlPatterns = {"/adminDashboard"})
public class AdminDashboard extends HttpServlet {

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
        Admin sessionAdmin = null;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String action = request.getParameter("action");
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            if (sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        Product product = null;
        Order order = null;
        Customer customer = null;
        Category category = null;
        Blog blog = null;

        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        FeedBackDAO feedbackDAO = new FeedBackDAO();
        BlogDAO blogDAO = new BlogDAO();
        int totalProduct = 0;
        int totalOrder = 0;
        String totalSale = null;
        int totalCustomer = 0;
        int totalCategory = 0;
        int totalBlog = 0;
        int totalFeedback = 0;
        float averageStar = 0;
        totalProduct = productDAO.getTotalProduct();
        totalOrder = orderDAO.getTotalQuantityOrder();
        totalSale = df.format(orderDAO.getTotalMoneyOrder());
        totalCustomer = customerDAO.getTotalCustomer();
        totalCategory = categoryDAO.getTotalCategory();
        totalBlog = blogDAO.getTotalBlog();
        totalFeedback = feedbackDAO.getTotalFeedback();
        averageStar = feedbackDAO.getAverageStarRate();

        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("totalSale", totalSale);
        request.setAttribute("totalCustomer", totalCustomer);
        request.setAttribute("totalCategory", totalCategory);
        request.setAttribute("totalBlog", totalBlog);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("averageStar", averageStar);

        //Monthly Rev
        String yearMonthRevStr = request.getParameter("yearMonth");
        int yearMonth = (yearMonthRevStr == null ? 2024 : Integer.parseInt(yearMonthRevStr));
        double totalMoneyMonth1 = orderDAO.totalMoneyMonth(1, yearMonth);
        double totalMoneyMonth2 = orderDAO.totalMoneyMonth(2, yearMonth);
        double totalMoneyMonth3 = orderDAO.totalMoneyMonth(3, yearMonth);
        double totalMoneyMonth4 = orderDAO.totalMoneyMonth(4, yearMonth);
        double totalMoneyMonth5 = orderDAO.totalMoneyMonth(5, yearMonth);
        double totalMoneyMonth6 = orderDAO.totalMoneyMonth(6, yearMonth);
        double totalMoneyMonth7 = orderDAO.totalMoneyMonth(7, yearMonth);
        double totalMoneyMonth8 = orderDAO.totalMoneyMonth(8, yearMonth);
        double totalMoneyMonth9 = orderDAO.totalMoneyMonth(9, yearMonth);
        double totalMoneyMonth10 = orderDAO.totalMoneyMonth(10, yearMonth);
        double totalMoneyMonth11 = orderDAO.totalMoneyMonth(11, yearMonth);
        double totalMoneyMonth12 = orderDAO.totalMoneyMonth(12, yearMonth);

        request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
        request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
        request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
        request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
        request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
        request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
        request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
        request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
        request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
        request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
        request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
        request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);
        request.setAttribute("yearMonth", yearMonth);

        //Chart
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        int startDay = startOfWeek.getDayOfMonth();
        int endDay = endOfWeek.getDayOfMonth();
        int monthValue = startOfWeek.getMonthValue();

        //Weekly Rev
        String yearRevStr = request.getParameter("yearRev");
        String monthRevStr = request.getParameter("monthRev");
        String fromRevStr = request.getParameter("fromRev");
        String toRevSTr = request.getParameter("toRev");
        int year = (yearRevStr == null ? 2024 : Integer.parseInt(yearRevStr));
        int month = (monthRevStr == null ? monthValue : Integer.parseInt(monthRevStr));
        int from = (fromRevStr == null ? startDay : Integer.parseInt(fromRevStr));
        int to = (toRevSTr == null ? endDay : Integer.parseInt(toRevSTr));
        LocalDate startDate = LocalDate.of(year, month, from);

        int weekNumberRev = (yearRevStr == null || monthRevStr == null || fromRevStr == null || toRevSTr == null
                ? currentDate.get(WeekFields.of(Locale.getDefault()).weekOfYear())
                : startDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));

        double totalMoney1 = orderDAO.totalMoneyWeek(1, from, to, year, month);
        double totalMoney2 = orderDAO.totalMoneyWeek(2, from, to, year, month);
        double totalMoney3 = orderDAO.totalMoneyWeek(3, from, to, year, month);
        double totalMoney4 = orderDAO.totalMoneyWeek(4, from, to, year, month);
        double totalMoney5 = orderDAO.totalMoneyWeek(5, from, to, year, month);
        double totalMoney6 = orderDAO.totalMoneyWeek(6, from, to, year, month);
        double totalMoney7 = orderDAO.totalMoneyWeek(7, from, to, year, month);

        request.setAttribute("weekNumberRev", weekNumberRev);
        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney1", totalMoney1);
        request.setAttribute("totalMoney2", totalMoney2);
        request.setAttribute("totalMoney3", totalMoney3);
        request.setAttribute("totalMoney4", totalMoney4);
        request.setAttribute("totalMoney5", totalMoney5);
        request.setAttribute("totalMoney6", totalMoney6);
        request.setAttribute("totalMoney7", totalMoney7);
        request.setAttribute("year", year);

        //Weekly Order
        String yearOrderStr = request.getParameter("year");
        String monthOrderStr = request.getParameter("month");
        String fromOrderStr = request.getParameter("from");
        String toOrderStr = request.getParameter("to");
        int yearOrder = (yearOrderStr == null ? 2024 : Integer.parseInt(yearOrderStr));
        int monthOrder = (monthOrderStr == null ? monthValue : Integer.parseInt(monthOrderStr));
        int fromOrder = (fromOrderStr == null ? startDay : Integer.parseInt(fromOrderStr));
        int toOrder = (toOrderStr == null ? endDay : Integer.parseInt(toOrderStr));

        int weekNumberOrder = (yearOrderStr == null || monthOrderStr == null || fromOrderStr == null || toOrderStr == null
                ? currentDate.get(WeekFields.of(Locale.getDefault()).weekOfYear())
                : startDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));

        double totalOrder1 = orderDAO.totalOrderWeek(1, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder2 = orderDAO.totalOrderWeek(2, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder3 = orderDAO.totalOrderWeek(3, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder4 = orderDAO.totalOrderWeek(4, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder5 = orderDAO.totalOrderWeek(5, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder6 = orderDAO.totalOrderWeek(6, fromOrder, toOrder, yearOrder, monthOrder);
        double totalOrder7 = orderDAO.totalOrderWeek(7, fromOrder, toOrder, yearOrder, monthOrder);

        request.setAttribute("weekNumberOrder", weekNumberOrder);
        request.setAttribute("totalOrder1", totalOrder1);
        request.setAttribute("totalOrder2", totalOrder2);
        request.setAttribute("totalOrder3", totalOrder3);
        request.setAttribute("totalOrder4", totalOrder4);
        request.setAttribute("totalOrder5", totalOrder5);
        request.setAttribute("totalOrder6", totalOrder6);
        request.setAttribute("totalOrder7", totalOrder7);
        request.setAttribute("yearOrder", yearOrder);

        //Top 5 Product
        List<ProductOrder> productTop5 = null;
        try {
            productTop5 = productDAO.getTop5ProductsByOrderCount();
        } catch (SQLException ex) {
        }

        request.setAttribute("productTop5", productTop5);

        request.getRequestDispatcher("/admin/adminDashboard.jsp").forward(request, response);

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
