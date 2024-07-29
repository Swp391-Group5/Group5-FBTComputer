/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Utils;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import dal.WarrantyDAO;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import model.Warranty;

/**
 *
 * @author hungp
 */
@WebServlet(name = "WarrantyPDFServlet", urlPatterns = {"/WarrantyPDFServlet"})
public class WarrantyPDFServlet extends HttpServlet {

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
            out.println("<title>Servlet OrderPDFServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderPDFServlet at " + request.getContextPath() + "</h1>");
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
        WarrantyDAO warrantyDAO = new WarrantyDAO();
        String warrantyIdStr = request.getParameter("warrantyId");
        int warrantyId = 0;
        try {
            warrantyId = Integer.parseInt(warrantyIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Warranty warranty = warrantyDAO.getWarrantyByWarrantyID(warrantyId);

        // Generate HTML content
        String htmlContent = generateHTMLContent(request, warranty);

        // Convert HTML to PDF and save it to a byte array
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, pdfOutputStream);
        byte[] pdfBytes = pdfOutputStream.toByteArray();

        // Set response headers and content type for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"warranty.pdf\"");
        response.getOutputStream().write(pdfBytes);
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

    private String generateHTMLContent(HttpServletRequest request, Warranty warranty) {
        StringBuilder html = new StringBuilder();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        html.append("<!DOCTYPE html>");
        html.append("<html><head><title>Warranty PDF</title>");
        html.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\">");
        html.append("<style>");
        html.append("body { font-family: 'Times New Roman', Times, serif; color: #000000; }");
        html.append(".container { width: 80%; margin: auto; color: #000000; }");
        html.append(".header{ text-align: center; margin-bottom: 20px; }");
        html.append(".footer { text-align: left; margin-bottom: 20px; }");
        html.append(".header { font-size: 24px; font-weight: bold; color: #000000; }");
        html.append(".footer { font-size: 14px; color: #888888; }");
        html.append(".text-right { text-align: right; color: #000000; }");
        html.append(".text-center { text-align: center; color: #000000; }");
        html.append(".info { color: #000000; }");
        html.append("h3 { color: #000000; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; color: #000000; }");
        html.append("th, td { border: 1px solid #dddddd; padding: 8px; }");
        html.append("th { background-color: #f2f2f2; color: #000000; }");
        html.append("</style>");
        html.append("</head><body>");

        html.append("<div class=\"container\">");
        html.append("<div class=\"row\">");
        html.append("<div class=\"col-sm-12 text-center\"><h1 class=\"header\">WARRANTY</h1></div>");
        html.append("<div class=\"col-sm-12 text-right\">");
        html.append("<p>Date: ").append(formattedDate).append("</p>");
        html.append("</div></div>");

        html.append("<h3>USER INFORMATION</h3>");
        html.append("<div class=\"info\">");
        html.append("<p>User Name: ").append(warranty.getUserName()).append("</p>");
        html.append("<p>Phone: ").append(warranty.getPhoneNumber()).append("</p>");
        html.append("</div>");

        html.append("<h3>RECIPIENT INFORMATION</h3>");
        html.append("<div class=\"info\">");
        html.append("<p>Admin name:").append(warranty.getAdminName()).append("</p>");
        html.append("<p>Admin ID:").append(warranty.getAdminID()).append("</p>");
        html.append("</div>");

        html.append("<h3>RETURN DATE:</h3>");
        html.append("<div class=\"info\">");
        html.append("<p>Expected announcement after 1 week</p>");
        html.append("</div>");

        html.append("<table>");
        html.append("<thead><tr>");
        html.append("<th>N</th>");
        html.append("<th>SerialNumber</th>");
        html.append("<th>ProductNumber</th>");
        html.append("<th>Product Condition</th>");
        html.append("<th>Product Image</th>");
        html.append("<th>Date Warranty</th>");
        html.append("<th>Note</th>");
        html.append("</tr></thead><tbody>");

        html.append("<tr>");
        html.append("<td>1</td>");
        html.append("<td>").append(warranty.getSerialNumber()).append("</td>");
        html.append("<td>").append(warranty.getProductName()).append("</td>");
        html.append("<td>").append(warranty.getCauseError()).append("</td>");
        html.append("<td>").append("<img style=\"width: 100%\" src=\"").append(convertImageToBase64(request.getServletContext().getRealPath("/UPLOAD_IMAGE/" + warranty.getWarrantyImage()))).append("\" alt=\"").append(warranty.getProductName()).append("\">").append("</td>");

        html.append("<td>12</td>"); // Assuming a fixed value for the example
        html.append("<td>Trong thời hạn</td>"); // Assuming a fixed value for the example
        html.append("</tr>");

        html.append("</tbody></table>");
        html.append("<div class=\"row\">");
        html.append("<div class=\"col-sm-6 text-left\">Customer</div>");
        html.append("<div class=\"col-sm-6 text-right\">Receptionist</div>");
        html.append("</div>");
        html.append("<div class=\"row\">");
        html.append("<div class=\"col-sm-12 text-left\"><br><br><br><br><br>Sign and Write Fullname</div>");
        html.append("</div>");
        html.append("</div>");
        html.append("</body></html>");

        return html.toString();
    }

    private String convertImageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return "data:image/png;base64," + base64Image;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
