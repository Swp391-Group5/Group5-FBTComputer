
package dal;

import static dal.DBContext.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import model.Blog;
import model.CategoryBlog;

/**
 *
 * @author quanb
 */
public class BlogDAO extends DBContext {
    public Blog getFirstSlider(int categoryId) {
        Blog blog = null;
        String query = "SELECT TOP 1 b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE c.CategoryBlogId = ? "
                + "ORDER BY BlogId";

        try (Connection conn = DBContext(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setBlogTitle(rs.getString("BlogTitle"));
                    blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    blog.setBlogContent(rs.getString("BlogContent"));
                    blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    blog.setAdminId(rs.getInt("AdminId"));
                    blog.setBlogStatus(rs.getInt("BlogStatus"));
                    blog.setImg(rs.getString("img"));
                    blog.setUrl(rs.getString("url"));

                    // Tạo đối tượng CategoryBlog và gán cho Blog
                    CategoryBlog categoryBlog = new CategoryBlog();
                    categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                    categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                    blog.setCategoryBlog(categoryBlog);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blog;
    }
    public int getcountSlider() {
        String query = "SELECT COUNT(*) FROM Blog b \n"
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId \n"
                + "WHERE c.CategoryBlogId = 2";
        int count = 0;

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    public List<Blog> getAllSlidersByCategory(int categoryBlogId) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE c.CategoryBlogId = ? AND b.BlogStatus = 1";

        try (Connection conn = DBContext(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the categoryBlogId parameter in the query
            stmt.setInt(1, categoryBlogId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setBlogTitle(rs.getString("BlogTitle"));
                    blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    blog.setBlogContent(rs.getString("BlogContent"));
                    blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    blog.setAdminId(rs.getInt("AdminId"));
                    blog.setBlogStatus(rs.getInt("BlogStatus"));
                    blog.setImg(rs.getString("img"));
                    blog.setUrl(rs.getString("url"));

                    // Create and set the CategoryBlog object
                    CategoryBlog categoryBlog = new CategoryBlog();
                    categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                    categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                    blog.setCategoryBlog(categoryBlog);

                    blogs.add(blog);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    public List<Blog> getAllSlidersByCategory1() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE c.CategoryBlogId = 2 AND b.BlogStatus = 1";

        try (Connection conn = DBContext(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the categoryBlogId parameter in the query
            

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setBlogTitle(rs.getString("BlogTitle"));
                    blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    blog.setBlogContent(rs.getString("BlogContent"));
                    blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    blog.setAdminId(rs.getInt("AdminId"));
                    blog.setBlogStatus(rs.getInt("BlogStatus"));
                    blog.setImg(rs.getString("img"));
                    blog.setUrl(rs.getString("url"));

                    // Create and set the CategoryBlog object
                    CategoryBlog categoryBlog = new CategoryBlog();
                    categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                    categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                    blog.setCategoryBlog(categoryBlog);

                    blogs.add(blog);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogs;
    }
    public Blog getSlider(int blogId) {

        String query = "SELECT b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE BlogId = ?";

        try (Connection conn = DBContext(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the categoryBlogId parameter in the query
              stmt.setInt(1, blogId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Blog blog = new Blog();
                    blog.setBlogId(rs.getInt("BlogId"));
                    blog.setBlogTitle(rs.getString("BlogTitle"));
                    blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    blog.setBlogContent(rs.getString("BlogContent"));
                    blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    blog.setAdminId(rs.getInt("AdminId"));
                    blog.setBlogStatus(rs.getInt("BlogStatus"));
                    blog.setImg(rs.getString("img"));
                    blog.setUrl(rs.getString("url"));

                    // Create and set the CategoryBlog object
                    CategoryBlog categoryBlog = new CategoryBlog();
                    categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                    categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                    blog.setCategoryBlog(categoryBlog);

                    return blog;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Blog> getALLSlider_True_False() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE c.CategoryBlogId = 2";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setBlogTitle(rs.getString("BlogTitle"));
                blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                blog.setBlogContent(rs.getString("BlogContent"));
                blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                blog.setAdminId(rs.getInt("AdminId"));
                blog.setBlogStatus(rs.getInt("BlogStatus"));
                blog.setImg(rs.getString("img"));
                blog.setUrl(rs.getString("url"));

                // Tạo đối tượng CategoryBlog và gán cho Blog
                CategoryBlog categoryBlog = new CategoryBlog();
                categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                blog.setCategoryBlog(categoryBlog);

                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Blog getSliderDetailById(int blogIdParam) {
        Blog blog = null;
        String sql = "SELECT b.*, c.CategoryBlogId, c.CategoryBlogName, c.CategoryBlogStatus "
                + "FROM Blog b "
                + "JOIN CategoryBlog c ON b.CategoryBlogId = c.CategoryBlogId "
                + "WHERE b.BlogId = ? AND c.CategoryBlogId = 2";

        try (Connection conn = DBContext(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blogIdParam);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                blog = new Blog();
                blog.setBlogId(rs.getInt("BlogId"));
                blog.setBlogTitle(rs.getString("BlogTitle"));
                blog.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                blog.setBlogContent(rs.getString("BlogContent"));
                blog.setBlogThumbnail(rs.getString("BlogThumbnail"));
                blog.setAdminId(rs.getInt("AdminId"));
                blog.setBlogStatus(rs.getInt("BlogStatus"));
                blog.setImg(rs.getString("img"));
                blog.setUrl(rs.getString("url"));

                // Tạo đối tượng CategoryBlog và gán cho Blog
                CategoryBlog categoryBlog = new CategoryBlog();
                categoryBlog.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                categoryBlog.setCategoryBlogName(rs.getString("CategoryBlogName"));
                categoryBlog.setCategoryBlogStatus(rs.getBoolean("CategoryBlogStatus"));

                blog.setCategoryBlog(categoryBlog);
            }

        } catch (SQLException ex) {
            System.err.println("Error while fetching slider detail by ID: " + ex.getMessage());
        }

        return blog;
    }

    public void UpdateBlogById(int blogId, String blogTitle, String blogContent, String img, String url) {
        try {
            // Corrected SQL update statement
            String sql = "UPDATE Blog\n"
                    + "SET BlogTitle = ?,\n"
                    + "    BlogContent = ?,\n"
                    + "    img = ?,\n"
                    + "    url = ?\n"
                    + "WHERE BlogId = ?";  // Removed the extra comma before WHERE clause

            Connection connection = DBContext();
            PreparedStatement st = connection.prepareStatement(sql);

            // Set parameters for the PreparedStatement
            st.setString(1, blogTitle);
            st.setString(2, blogContent);
            st.setString(3, img);
            st.setString(4, url);  // Added this missing line to set the url parameter
            st.setInt(5, blogId);  // This line sets the BlogId for the WHERE clause
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateBlogAndCategoryStatus(int blogId, boolean blogStatus) {
        Connection connection = null;
        PreparedStatement updateBlogStmt = null;

        try {
            // Khởi tạo kết nối
            connection = DBContext();
            connection.setAutoCommit(false); // Bắt đầu giao dịch

            // SQL để cập nhật trạng thái Blog
            String updateBlogSql = "UPDATE Blog SET BlogStatus = ? WHERE BlogId = ?";
            updateBlogStmt = connection.prepareStatement(updateBlogSql);
            updateBlogStmt.setBoolean(1, blogStatus);
            updateBlogStmt.setInt(2, blogId);
            updateBlogStmt.executeUpdate();


            // Cam kết giao dịch nếu thành công
            connection.commit();

        } catch (SQLException ex) {
            // Rollback giao dịch nếu có lỗi
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            // Đóng các tài nguyên
            try {
                if (updateBlogStmt != null) {
                    updateBlogStmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addSlider(String blogTitle, String blogContent, String url, String img, int status, int adminId) {
        String sql = "INSERT INTO Blog (BlogTitle, BlogUpdateDate, BlogContent, AdminId, CategoryBlogId, BlogStatus, img, url) "
                + "VALUES (?, ?, ?, ?, 2, ?, ?, ?)";

        try (Connection connection = DBContext(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, blogTitle);
            stmt.setDate(2, new java.sql.Date(new Date().getTime())); // Lấy ngày hiện tại cho BlogUpdateDate
            stmt.setString(3, blogContent);
            stmt.setInt(4, adminId);
            stmt.setInt(5, status);
            stmt.setString(6, img); // Thêm thông tin hình ảnh
            stmt.setString(7, url);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public void delete(String blogId) {
        String sql = "delete Blog \n"
                + "where BlogId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, blogId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getTotalBlog() {
        int TotalBlog = 0;
        String sql = "SELECT COUNT(*) AS TotalBlog FROM Blog";

        try ( Connection con = DBContext();  
                PreparedStatement st = con.prepareStatement(sql);  
                ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                TotalBlog = rs.getInt("TotalBlog");
            }
        } catch (SQLException ex) {
        }
        return TotalBlog;
    }
    
    
    public List<Blog> getAllBlog() {
        try {
            String sql = "select * from Blog WHERE CategoryBlogId = 1";

            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Blog> blog = new ArrayList<>();
            while (rs.next()) {
                Blog p = new Blog();
                p.setBlogId(rs.getInt("BlogId"));
                p.setBlogTitle(rs.getString("BlogTitle"));
                p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                p.setBlogContent(rs.getString("BlogContent"));
                p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                p.setAdminId(rs.getInt("AdminId"));
                p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                p.setShortDesc(rs.getString("shortDesc"));
                p.setBlogStatus(rs.getInt("BlogStatus"));
                p.setImg(rs.getString("img"));
                p.setUrl(rs.getString("url"));
                
                
                

                blog.add(p);
            }
            return blog;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    public List<Blog> getAllBlogByStatus(int status, String keyword, int pageIndex) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM Blog where CategoryBlogId = 1 and BlogStatus = ? ";
        if (!keyword.isEmpty()) {
            sql += " AND BlogTitle LIKE ?";
        }
        sql += " ORDER BY BlogId OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY"; // Add ORDER BY clause

        try ( Connection con = DBContext();  PreparedStatement ps = con.prepareStatement(sql)) {

            int index = 1;
            ps.setInt(index++, status);
            if (!keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }
            ps.setInt(index++, (pageIndex - 1) * 6); // Calculate the offset

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog p = new Blog();
                    p.setBlogId(rs.getInt("BlogId"));
                    p.setBlogTitle(rs.getString("BlogTitle"));
                    p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    p.setBlogContent(rs.getString("BlogContent"));
                    p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    p.setAdminId(rs.getInt("AdminId"));
                    p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    p.setBlogStatus(rs.getInt("BlogStatus"));
                    p.setImg(rs.getString("img"));
                    p.setUrl(rs.getString("url"));
                    p.setShortDesc(rs.getString("ShortDesc"));
                    blogs.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Get blog by status: " + ex);
        }
        return blogs;
    }
    public List<Blog> getAllBlogByStatus1(int status, String title, int pageIndex, String adminName) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT Blog.*, Admin.AdminName FROM Blog "
                + "JOIN Admin ON Blog.AdminId = Admin.AdminId "
                + "WHERE BlogStatus = ? And CategoryBlogId = 1";

        if (!title.isEmpty()) {
            sql += " AND BlogTitle LIKE ?";
        }

        if (!adminName.isEmpty()) {
            sql += " AND Admin.AdminName LIKE ?";
        }

        sql += " ORDER BY BlogId OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";

        try ( Connection con = DBContext();  PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1;
            ps.setInt(index++, status);

            if (!title.isEmpty()) {
                ps.setString(index++, "%" + title + "%");
            }

            if (!adminName.isEmpty()) {
                ps.setString(index++, "%" + adminName + "%");
            }

            ps.setInt(index++, (pageIndex - 1) * 6);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog p = new Blog();
                    p.setBlogId(rs.getInt("BlogId"));
                    p.setBlogTitle(rs.getString("BlogTitle"));
                    p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    p.setBlogContent(rs.getString("BlogContent"));
                    p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    p.setAdminId(rs.getInt("AdminId"));
                    p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    p.setBlogStatus(rs.getInt("BlogStatus"));
                    p.setImg(rs.getString("img"));
                    p.setUrl(rs.getString("url"));
                    p.setShortDesc(rs.getString("ShortDesc"));
                    p.setAdminName(rs.getString("AdminName"));
                    blogs.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Get blog by status: " + ex);
        }
        return blogs;
    }
     public List<Blog> getBlogTop3(int status) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT TOP 3 * FROM Blog where CategoryBlogId = 1 and BlogStatus = ? Order by BlogId desc";
        try ( Connection con = DBContext();  PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 1;
            ps.setInt(index++, status);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Blog p = new Blog();
                    p.setBlogId(rs.getInt("BlogId"));
                    p.setBlogTitle(rs.getString("BlogTitle"));
                    p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                    p.setBlogContent(rs.getString("BlogContent"));
                    p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                    p.setAdminId(rs.getInt("AdminId"));
                    p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                    p.setBlogStatus(rs.getInt("BlogStatus"));
                    p.setImg(rs.getString("img"));
                    p.setUrl(rs.getString("url"));
                    p.setShortDesc(rs.getString("ShortDesc"));
                    blogs.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Get blog by status: " + ex);
        }
        return blogs;
    }
     public Blog getAllBlogById(int id) {
        try {
            String sql = "select * from Blog where BlogId = ?";

            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Blog p = new Blog();
                p.setBlogId(rs.getInt("BlogId"));
                p.setBlogTitle(rs.getString("BlogTitle"));
                p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                p.setBlogContent(rs.getString("BlogContent"));
                p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                p.setAdminId(rs.getInt("AdminId"));
                p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                p.setBlogStatus(rs.getInt("BlogStatus"));
                p.setShortDesc(rs.getString("ShortDesc"));
                p.setImg(rs.getString("img"));
                p.setUrl(rs.getString("url"));
                
                
                return p;
            }
        } catch (SQLException ex) {
            System.out.println("Get blog by status: " + ex);
        }
        return null;
    }
 public int getNumberBlog(String search) {
        String sql = "select count(*) from Blog WHERE BlogTitle LIKE ? ";
        try {
            Connection conn = DBContext();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("get all error; " + e);
        }
        return 0;
    }
  public int getNumberBlog1(String title, String adminName) {
        String sql = "SELECT COUNT(*) FROM Blog where CategoryBlogId = 1"
                + "JOIN Admin ON Blog.AdminId = Admin.AdminId "
                + "WHERE BlogTitle LIKE ?";

        if (!adminName.isEmpty()) {
            sql += " AND Admin.AdminName LIKE ?";
        }

        try ( Connection conn = DBContext();  PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            ps.setString(index++, "%" + title + "%");

            if (!adminName.isEmpty()) {
                ps.setString(index++, "%" + adminName + "%");
            }

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            System.out.println("get all error: " + e);
        }
        return 0;
    }
  
   public Blog getBlogById(int blogId) {
        try {
            String sql = "SELECT * FROM Blog WHERE BlogId = ? ";
            Connection con = DBContext(); // Giả sử đã có phương thức DBContext() để kết nối CSDL
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Blog p = new Blog();
                p.setBlogId(rs.getInt("BlogId"));
                p.setBlogTitle(rs.getString("BlogTitle"));
                p.setBlogUpdateDate(rs.getString("BlogUpdateDate"));
                p.setBlogContent(rs.getString("BlogContent"));
                p.setBlogThumbnail(rs.getString("BlogThumbnail"));
                p.setAdminId(rs.getInt("AdminId"));
                p.setCategoryBlogId(rs.getInt("CategoryBlogId"));
                p.setBlogStatus(rs.getInt("BlogStatus"));
                p.setShortDesc(rs.getString("ShortDesc"));
                p.setImg(rs.getString("img"));
                p.setUrl(rs.getString("url"));

                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
     public boolean createBlog(Blog blog) {
        try {
            String sql = "insert into Blog (BlogTitle, BlogUpdateDate, BlogContent, BlogThumbnail, AdminId, CategoryBlogId, BlogStatus, img, url, shortDesc) values (?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, blog.getBlogTitle());
            ps.setString(2, blog.getBlogUpdateDate());
            ps.setString(3, blog.getBlogContent());
            ps.setString(4, blog.getBlogThumbnail());
            ps.setInt(5, blog.getAdminId());
            ps.setInt(6, blog.getCategoryBlogId());
            ps.setInt(7, blog.getBlogStatus());
            ps.setString(8, blog.getImg());
            ps.setString(9, blog.getUrl());
            ps.setString(10, blog.getShortDesc());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            System.out.println("Create blog: " + ex);
        }
        return false;
    }
     
      public boolean updateBlog(Blog blog) {
        try {
            String sql = "update Blog set BlogTitle = ?, BlogUpdateDate = ?, BlogContent = ?, BlogThumbnail = ?, AdminId = ?, CategoryBlogId = ?, BlogStatus = ?, img = ?, url = ?, ShortDesc=? where BlogId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, blog.getBlogTitle());
            ps.setString(2, blog.getBlogUpdateDate());
            ps.setString(3, blog.getBlogContent());
            ps.setString(4, blog.getBlogThumbnail());
            ps.setInt(5, blog.getAdminId());
            ps.setInt(6, blog.getCategoryBlogId());
            ps.setInt(7, blog.getBlogStatus());
            ps.setString(8, blog.getImg());
            ps.setString(9, blog.getUrl());
            ps.setString(10, blog.getShortDesc());
            ps.setInt(11, blog.getBlogId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            System.out.println("Update blog: " + ex);
        }
        return false;
    }
       public boolean deleteBlog(int blogId) {
        try {
            String sql = "delete from Blog where BlogId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            System.out.println("Delete blog: " + ex);
        }
        return false;
    }
       
    public static void main(String[] args) {

    }
}
