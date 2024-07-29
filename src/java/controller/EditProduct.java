
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import model.Admin;
import model.Category;
import model.Product;

@MultipartConfig()
@WebServlet(name = "EditProduct", urlPatterns = {"/edit-product"})
public class EditProduct extends HttpServlet {

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
            out.println("<title>Servlet EditProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProduct at " + request.getContextPath() + "</h1>");
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
        String row_id = request.getParameter("pid");
        int pid;
        try {
            pid = Integer.parseInt(row_id);
            ProductDAO pdao = new ProductDAO();
            Product list = pdao.getProductById(pid);
            CategoryDAO cdao = new CategoryDAO();
            List<Category> listCc = cdao.getAllCategory();
            request.setAttribute("listC", listCc);
            request.setAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
    }

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRS = "BACK";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Admin ad = (Admin) session.getAttribute("admin");
            ProductDAO pdao = new ProductDAO();
            int id = Integer.parseInt(request.getParameter("pid"));
            String productName = request.getParameter("name");
            String productDescription = request.getParameter("description");
            float productPrice = Float.parseFloat(request.getParameter("price"));
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            String productBrand = request.getParameter("brand");
            String img = "";

//            phần xử lý file ảnh 
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRS;
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            Collection<Part> parts = request.getParts();
            Product p = pdao.getProductById(id);
            for (Part part : parts) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()) {
                    img = fileName;
                    part.write(uploadFilePath + File.separator + fileName);
                } else {
                    img = p.getProductImage();
                }
            }

            String ModifyBy = ad.getAdminName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ModifyDate = sdf.format(new Date());

            pdao.updateProduct(productName, productDescription, productPrice, productQuantity, productBrand, img, ModifyBy, ModifyDate, productQuantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("manager-product");
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
