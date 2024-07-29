/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Utils.Upload;
import dal.AdminDAO;
import dal.BlogDAO;
import dal.CategoryDAO;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Admin;
import model.Blog;
import model.Category;

/**
 *
 * @author HP
 */


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)

@WebServlet(name = "managerBlog", urlPatterns = {"/managerBlog"})

public class AdminManagerBlogController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminManagerBlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagerBlogController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Admin sessionAdmin = null;
        try {
            sessionAdmin = (Admin) session.getAttribute("admin");
            if (sessionAdmin == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        AdminDAO adminDao = new AdminDAO();
        CategoryDAO categoryDao = new CategoryDAO();
        BlogDAO blogDAO = new BlogDAO();
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "add":
                List<Category> categories = categoryDao.getAllCategory();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/admin/addNewBlog.jsp").forward(request, response);
                break;
            case "edit":
                try {
                int blogId = Integer.parseInt(request.getParameter("id"));
                Blog blog = blogDAO.getBlogById(blogId);
                if (blog == null) {
                    response.sendRedirect("managerBlog?error=Blog not found.");
                    return;
                }
                List<Category> categoriesEdit = categoryDao.getAllCategory();
                request.setAttribute("blog", blog);
                request.setAttribute("categories", categoriesEdit);
                request.getRequestDispatcher("/admin/editBlog.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("managerBlog?error=Id is not valid.");
            }
            break;
            case "view":
                try {
                int blogId = Integer.parseInt(request.getParameter("id"));
                Blog blog = blogDAO.getBlogById(blogId);
                if (blog == null) {
                    response.sendRedirect("managerBlog?error=Blog not found.");
                    return;
                }
                Category category = categoryDao.getCategoryById(blog.getCategoryBlogId());
                Admin admin = adminDao.getAdminById(blog.getAdminId());
                request.setAttribute("blog", blog);
                request.setAttribute("category", category);
                request.setAttribute("admin", admin);
                request.getRequestDispatcher("/admin/viewBlog.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("managerBlog?error=Id is not valid.");
            }
            break;
            case "delete":
                try {
                int blogId = Integer.parseInt(request.getParameter("id"));
                Blog blog = blogDAO.getBlogById(blogId);
                if (blog == null) {
                    response.sendRedirect("managerBlog?error=Blog not found.");
                    return;
                }
                boolean isDelete = blogDAO.deleteBlog(blogId);
                if (isDelete) {
                    response.sendRedirect("managerBlog?success=Delete successfully");
                } else {
                    response.sendRedirect("managerBlog?error=Delete fail");
                }
            } catch (Exception e) {
                response.sendRedirect("managerBlog?error=Id is not valid.");
            }
            break;
            default:

                String searchTitle = request.getParameter("searchTitle");
                if (searchTitle != null) {
                    searchTitle = searchTitle.trim();
                    if (!searchTitle.isEmpty()) {
                        searchTitle = searchTitle.replaceAll("\\s+\\S+", "");
                    } else {
                        searchTitle = null;
                    }
                }
                searchTitle = (searchTitle != null) ? searchTitle : "";

                String adminName = request.getParameter("adminName");
                adminName = (adminName != null) ? adminName : "";

                //AdminDAO adminDao = new AdminDAO();
                List<Admin> adminList = adminDao.getAllAdmin(); 

                int totalProduct = blogDAO.getNumberBlog1(searchTitle, adminName); 
                int numberPage = (int) Math.ceil((double) totalProduct / 2);

                String currentPage = request.getParameter("index");
                int index = 1;
                if (currentPage == null) {
                    index = 1;
                } else {
                    try {
                        index = Integer.parseInt(currentPage);
                    } catch (Exception e) {
                        index = 1;
                    }
                }

                BlogDAO blogDao = new BlogDAO();
                request.setAttribute("index", index);
                request.setAttribute("numberPage", numberPage);

                List<Blog> blogList = blogDao.getAllBlogByStatus1(1, searchTitle, index, adminName); 
                Map<Integer, String> adminNames = new HashMap<>();
                Map<Integer, String> categoryNames = new HashMap<>();

                //CategoryDAO categoryDao = new CategoryDAO();

                for (Blog blog : blogList) {
                    int adminId = blog.getAdminId();
                    int categoryId = blog.getCategoryBlogId();

                    if (!adminNames.containsKey(adminId)) {
                        adminNames.put(adminId, adminDao.getAdminById(adminId).getAdminName());
                    }

                    if (!categoryNames.containsKey(categoryId)) {
                        categoryNames.put(categoryId, categoryDao.getCategoryById(categoryId).getCategoryName());
                    }
                }

                request.setAttribute("adminList", adminList);
                request.setAttribute("adminNames", adminNames);
                request.setAttribute("categoryNames", categoryNames);
                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("/admin/listBlog.jsp").forward(request, response);

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "add":
                this.addNew(request, response);
                break;
            case "edit":
                this.edit(request, response);
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String pathProduct = "./blogs/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            Upload upload = new Upload();
            HttpSession session = request.getSession();
            Admin sessionAdmin = (Admin) session.getAttribute("admin");
            if (sessionAdmin == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            int blogId = Integer.parseInt(request.getParameter("blogid"));
            String title = request.getParameter("blogtitle");
            String content = request.getParameter("blogcontent");
            String shortDesc = request.getParameter("shortDesc");
            int adminIdStr = sessionAdmin.getAdminId();
            String categoryBlogIdStr = request.getParameter("category");
            String statusStr = request.getParameter("blogstatus");
            String url = request.getParameter("url");
            Part mainImgParth = request.getPart("blogthumbnail");
            String thumbnailName = upload.uploadImg(mainImgParth, uploadPath);
            String thumbnail = pathProduct + thumbnailName;
            Part mainImgParthImg = request.getPart("img");
            String imgName = upload.uploadImg(mainImgParthImg, uploadPath);
            String img = pathProduct + imgName;
            if (thumbnailName == null) {
                thumbnail = request.getParameter("oldThumbnail");
            }
            if (imgName == null) {
                img = request.getParameter("oldImg");
            }
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = currentDate.format(formatter);
            if (blogId <= 0) {
                response.sendRedirect("managerBlog?error=Invalid blog ID.");
                return;
            }

            BlogDAO blogDAO = new BlogDAO();
            Blog blog = blogDAO.getBlogById(blogId);

            if (blog == null) {
                response.sendRedirect("managerBlog?error=Blog not found.");
                return;
            }

            List<String> errors = new ArrayList<>();

            if (title == null || title.trim().isEmpty()) {
                errors.add("Title is required.");
            }

            if (shortDesc == null | shortDesc.trim().isEmpty()) {
                errors.add("Short description is required.");
            }

            if (content == null || content.trim().isEmpty()) {
                errors.add("Content is required.");
            }

            if (thumbnail == null || thumbnail.trim().isEmpty()) {
                errors.add("Thumbnail URL is required.");
            }

            int categoryBlogId = -1;
            try {
                categoryBlogId = Integer.parseInt(categoryBlogIdStr);
            } catch (NumberFormatException e) {
                errors.add("Category ID must be a valid number.");
            }

            int status = -1;
            try {
                status = Integer.parseInt(statusStr);
                if (status != 0 && status != 1) {
                    errors.add("Invalid status value.");
                }
            } catch (NumberFormatException e) {
                errors.add("Status must be either 0 (Hidden) or 1 (Active).");
            }

            if (img == null || img.trim().isEmpty()) {
                errors.add("Image URL is required.");
            }

            if (url == null || url.trim().isEmpty()) {
                errors.add("URL is required.");
            }
            if (!errors.isEmpty()) {
                CategoryDAO categoryDao = new CategoryDAO();
                request.setAttribute("errors", errors);
                List<Category> categories = categoryDao.getAllCategory();
                request.setAttribute("blog", blog);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/admin/editBlog.jsp").forward(request, response);
                return;
            }

            Blog blogNew = new Blog();
            blogNew.setBlogId(blogId);
            blogNew.setBlogTitle(title);
            blogNew.setBlogUpdateDate(date);
            blogNew.setBlogContent(content);
            blogNew.setBlogThumbnail(thumbnail);
            blogNew.setAdminId(adminIdStr);
            blogNew.setCategoryBlogId(categoryBlogId);
            blogNew.setBlogStatus(status);
            blogNew.setShortDesc(shortDesc);
            blogNew.setImg(img);
            blogNew.setUrl(url);
            boolean isEdit = blogDAO.updateBlog(blogNew);
            if (isEdit) {
                response.sendRedirect("managerBlog?success=Edit successfully");
            } else {
                CategoryDAO categoryDao = new CategoryDAO();
                request.setAttribute("errors", "Edit fail.");
                List<Category> categories = categoryDao.getAllCategory();
                request.setAttribute("blog", blog);
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/admin/editBlog.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("managerBlog?error=Invalid blog ID.");
            System.out.println("Invalid blog ID: " + e.getMessage());
        } catch (IOException | ServletException e) {
            response.sendRedirect("managerBlog?error=Edit blog error.");
            System.out.println("Edit blog error: " + e.getMessage());
        }
    }

    private void addNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String pathProduct = "./blogs/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            Upload upload = new Upload();
            HttpSession session = request.getSession();
            Admin sessionAdmin = null;
            try {
                sessionAdmin = (Admin) session.getAttribute("admin");
                if (sessionAdmin == null) {
                    throw new Exception();
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            String title = request.getParameter("blogtitle");
            String content = request.getParameter("blogcontent");
            int adminIdStr = sessionAdmin.getAdminId();
            String categoryBlogIdStr = request.getParameter("category");
            String statusStr = request.getParameter("blogstatus");
            String url = request.getParameter("url");
            String shortDesc = request.getParameter("shortDesc");
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = currentDate.format(formatter);
            List<String> errors = new ArrayList<>();
            Part mainImgParth = request.getPart("blogthumbnail");
            String thumbnailName = upload.uploadImg(mainImgParth, uploadPath);
            String thumbnail = pathProduct + thumbnailName;
            Part mainImgParthImg = request.getPart("img");
            String imgName = upload.uploadImg(mainImgParthImg, uploadPath);
            String img = pathProduct + imgName;

            if (title == null || title.trim().isEmpty()) {
                errors.add("Title is required.");
            }

            if (content == null || content.trim().isEmpty()) {
                errors.add("Content is required.");
            }

            if (thumbnailName == null || thumbnailName.trim().isEmpty()) {
                errors.add("Thumbnail URL is required.");
            }

            if (shortDesc == null | shortDesc.trim().isEmpty()) {
                errors.add("Short description is required.");
            }

            int categoryBlogId = -1;
            try {
                categoryBlogId = Integer.parseInt(categoryBlogIdStr);
            } catch (NumberFormatException e) {
                errors.add("Category ID must be a valid number.");
            }

            int status = -1;
            try {
                status = Integer.parseInt(statusStr);
                if (status != 0 && status != 1) {
                    errors.add("Invalid status value.");
                }
            } catch (NumberFormatException e) {
                errors.add("Status must be either 0 (Hidden) or 1 (Active).");
            }

            if (imgName == null || imgName.trim().isEmpty()) {
                errors.add("Image URL is required.");
            }

            if (url == null || url.trim().isEmpty()) {
                errors.add("URL is required.");
            }
            if (!errors.isEmpty()) {
                CategoryDAO categoryDao = new CategoryDAO();
                request.setAttribute("errors", errors);
                List<Category> categories = categoryDao.getAllCategory();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/admin/addNewBlog.jsp").forward(request, response);
                return;
            }

            Blog blog = new Blog();
            blog.setBlogTitle(title);
            blog.setBlogUpdateDate(date);
            blog.setBlogContent(content);
            blog.setBlogThumbnail(thumbnail);
            blog.setAdminId(adminIdStr);
            blog.setCategoryBlogId(categoryBlogId);
            blog.setBlogStatus(status);
            blog.setShortDesc(shortDesc);
            blog.setImg(img);
            blog.setUrl(url);

            BlogDAO blogDAO = new BlogDAO();
            boolean isAdd = blogDAO.createBlog(blog);
            if (isAdd) {
                response.sendRedirect("managerBlog?success=Add new successfully");
            } else {
                response.sendRedirect("managerBlog?error=Add new fail.");
            }
        } catch (Exception e) {
            response.sendRedirect("managerBlog?error=Add new fail.");
            System.out.println("Add new error: " + e);
        }
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
