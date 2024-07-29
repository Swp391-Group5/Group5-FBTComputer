/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author quanb
 */
@WebServlet(name = "CheckWarrantyServlet", urlPatterns = {"/CheckWarranty"})
public class CheckWarrantyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckWarrantyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckWarrantyServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            WarrantyDAO wdao = new WarrantyDAO();
            int orderHistoryDetailId = Integer.parseInt(request.getParameter("orderHistoryDetailId"));
            String serialnumber = request.getParameter("serialnumber");

            if (wdao.checkOrderHistoryDetail(orderHistoryDetailId, serialnumber)) {
                Date orderDate = wdao.getOrderDate(orderHistoryDetailId);
                if (orderDate != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(orderDate);
                    cal.add(Calendar.MONTH, 12);
                    Date warrantyExpiryDate = cal.getTime();
                    Date currentDate = new Date();

                    if (currentDate.after(warrantyExpiryDate)) {
                        request.setAttribute("error", "Đơn hàng đã hết hạn bảo hành.");
                        request.getRequestDispatcher("FindWarranty.jsp").forward(request, response);
                    } else {
                        request.setAttribute("orderHistoryDetailId", orderHistoryDetailId);
                        request.setAttribute("serialNumber", serialnumber);
                        request.getRequestDispatcher("Warranty.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Không tìm thấy ngày đặt hàng.");
                    request.getRequestDispatcher("FindWarranty.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Không tìm thấy sản phẩm.");
                request.getRequestDispatcher("FindWarranty.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
