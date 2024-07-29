package controller;

import dal.WarrantyDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductQuality;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet(name="ProductQualityServlet", urlPatterns={"/product-quality"})
public class ProductQualityServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        WarrantyDAO wdao = new WarrantyDAO();
        List<ProductQuality> productQualityList = wdao.getProductQualityList();

        request.setAttribute("productQualityList", productQualityList);
        request.getRequestDispatcher("product-quality.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("export".equals(action)) {
            exportBlacklistToExcel(response);
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    private void exportBlacklistToExcel(HttpServletResponse response) throws IOException {
        WarrantyDAO wdao = new WarrantyDAO();
        List<ProductQuality> blacklist = wdao.getBlacklist();

        // Tạo Workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Blacklist");

        // Tạo dòng tiêu đề
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User Name");
        headerRow.createCell(1).setCellValue("Warranty Count");
        headerRow.createCell(2).setCellValue("Warranty Ratio (%)");

        // Điền dữ liệu vào các dòng
        int rowNum = 1;
        for (ProductQuality pq : blacklist) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pq.getProductName());
            row.createCell(1).setCellValue(pq.getWarrantyCount());
            row.createCell(2).setCellValue(pq.getWarrantyRatio());
        }

        // Thiết lập kiểu nội dung và tên file xuất ra
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=blacklist.xlsx");

        // Xuất Workbook ra response output stream
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
        workbook.close();
    }

    @Override
    public String getServletInfo() {
        return "Product Quality Assessment Servlet";
    }
}
