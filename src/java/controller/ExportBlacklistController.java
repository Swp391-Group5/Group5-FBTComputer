package controller;

import dal.WarrantyDAO;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import model.ProductQuality;

@WebServlet(name = "ExportBlacklistController", urlPatterns = {"/export-blacklist"})
public class ExportBlacklistController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WarrantyDAO dao = new WarrantyDAO();
        List<ProductQuality> blacklist = dao.getBlacklist();

        // Path to save the Excel file
        String filePath = "C:\\Users\\admin\\Downloads\\chip\\";
        String fileName = "blacklist.xlsx";
        String fullPath = filePath + fileName;

        // Create Workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Blacklist");

        // Create Header Row
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product Name");
        headerRow.createCell(1).setCellValue("Warranty Count");
        headerRow.createCell(2).setCellValue("Warranty Ratio (%)");
        headerRow.createCell(3).setCellValue("Warranty Status");

        // Fill Data
        int rowNum = 1;
        for (ProductQuality pq : blacklist) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(pq.getProductName());
            row.createCell(1).setCellValue(pq.getWarrantyCount());
            row.createCell(2).setCellValue(pq.getWarrantyRatio());
            row.createCell(3).setCellValue(pq.getStatus());
        }

        // Save Workbook to File
        try (FileOutputStream fileOut = new FileOutputStream(fullPath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }

        // Set success message and redirect back to the product quality page
        request.setAttribute("mess", "Blacklist exported successfully! File saved at: " + fullPath);
        request.getRequestDispatcher("product-quality").forward(request, response);
    }
}
