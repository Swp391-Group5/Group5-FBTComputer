/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import model.Category;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;



/**
 *
 * @author admin
 */
@WebServlet(name="ExcelCategoryControl", urlPatterns={"/excel-cate"})
public class ExcelCategoryControl extends HttpServlet {
   
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
     
        // Lấy dữ liệu từ DAO
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> list = categoryDAO.getAllCategory();

        // Tạo Workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("Categories");

        // Tạo dòng tiêu đề
        Row headerRow = workSheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Status");

        // Điền dữ liệu vào các dòng
        int rowNum = 1;
        for (Category cate : list) {
            Row row = workSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cate.getCategoryId());
            row.createCell(1).setCellValue(cate.getCategoryName());
            row.createCell(2).setCellValue(cate.getCategoryStatus() == 1 ? "Show" : "Hidden");
        }

        // Đường dẫn nơi lưu file tạm thời
        String filePath = "C:\\Users\\admin\\Downloads\\chip\\";
        Random rn = new Random();
        int randomNum = rn.nextInt(Integer.MAX_VALUE - 1) + 1;
        String fileName = "categories-" + randomNum + ".xlsx";
        String fullPath = filePath + fileName;

        // Lưu file vào hệ thống tệp tin
        try (FileOutputStream fileOut = new FileOutputStream(fullPath)) {
            workbook.write(fileOut);
        }

        // Đóng Workbook
        workbook.close();

        // Đặt thông báo và chuyển tiếp đến trang quản lý danh mục
        request.setAttribute("mess", "Đã xuất file Excel thành công! File lưu tại: " + fullPath);
        request.getRequestDispatcher("list-cate").forward(request, response);
        
        
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
        processRequest(request, response);
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
