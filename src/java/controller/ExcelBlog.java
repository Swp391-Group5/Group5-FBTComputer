

package controller;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.List;
import model.Blog;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
@WebServlet(name="ExcelBlog", urlPatterns={"/excel-blog"})
public class ExcelBlog extends HttpServlet {
   
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
            out.println("<title>Servlet ExcelBlog</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExcelBlog at " + request.getContextPath () + "</h1>");
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
        BlogDAO bdao = new BlogDAO();
        List<Blog> list = bdao.getAllSlidersByCategory1();
           // Đường dẫn nơi lưu file tạm thời
   String filePath = "C:\\Users\\admin\\Downloads\\chip\\"; // Thay đổi đường dẫn lưu file tùy vào hệ thống của bạn
    String fileName = "blogs.xlsx";
    String fullPath = filePath + fileName;

    // Tạo Workbook mới
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Blogs");

    // Tạo dòng tiêu đề
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Blog ID");
    headerRow.createCell(1).setCellValue("Title");
    headerRow.createCell(2).setCellValue("Content");
    headerRow.createCell(3).setCellValue("Image");
    headerRow.createCell(4).setCellValue("Link");
    headerRow.createCell(5).setCellValue("Status");

    // Điền dữ liệu vào các dòng
    int rowNum = 1;
    for (Blog blog : list) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(blog.getBlogId());
        row.createCell(1).setCellValue(blog.getBlogTitle());
        row.createCell(2).setCellValue(blog.getBlogContent());
        row.createCell(3).setCellValue(blog.getImg()); // Thay đổi tùy theo cách lấy ảnh
        row.createCell(4).setCellValue(blog.getUrl());
        row.createCell(5).setCellValue(blog.getBlogStatus()==1 ? "Show" : "Hidden");
    }

    // Lưu Workbook vào file
    try (FileOutputStream fileOut = new FileOutputStream(fullPath)) {
        workbook.write(fileOut);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        workbook.close();
    }

    // Đặt thông báo và chuyển tiếp đến trang quản lý danh mục
    request.setAttribute("mess", "Đã xuất file Excel danh sách blog thành công! File lưu tại: " + fullPath);
    request.getRequestDispatcher("slider-list").forward(request, response);
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
