/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;
import model.ProductImange;
import model.ProductOrder;
import model.Specification;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    public Product getProductByProductId(String productId) {
        Product product = null;
        try {
            String sql = "SELECT p.*, s.Color, s.Size, s.Weight, s.Manufacturer, pi.ProductImageId, pi.Image "
                    + "FROM Product p "
                    + "LEFT JOIN Specification s ON p.ProductId = s.ProductId "
                    + "LEFT JOIN ProductImage pi ON p.ProductId = pi.ProductId "
                    + "WHERE p.ProductId = ?";

            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("productImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getString("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getString("ModifyDate"));
                product.setImage1(rs.getString("Image1"));
                product.setImage2(rs.getString("Image2"));
                product.setImage3(rs.getString("Image3"));
                Specification spec = new Specification();
                spec.setColor(rs.getString("Color"));
                spec.setSize(rs.getString("Size"));
                spec.setWeight(rs.getString("Weight"));
                spec.setManufacturer(rs.getString("Manufacturer"));
                product.setSpecification(spec);

                List<ProductImange> productImages = new ArrayList<>();
                do {
                    int imageId = rs.getInt("ProductImageId");
                    if (imageId > 0) {
                        ProductImange image = new ProductImange();
                        image.setProductImageId(imageId);
                        image.setProductId(rs.getInt("ProductId"));
                        image.setImage(rs.getString("Image"));
                        productImages.add(image);
                    }
                } while (rs.next());
                product.setProductImanges(productImages);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    // get all product
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product p\n"
                + "INNER JOIN Category c\n"
                + "ON p.CategoryId = c.CategoryId\n"
                + "where productStatus = 1";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

            }

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getDouble("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getString("ProductBrand"),
                        rs.getString("ProductImage"),
                        rs.getBoolean("ProductStatus"),
                        rs.getString("CreateBy"),
                        rs.getString("CreateDate"),
                        rs.getString("ModifyBy"),
                        rs.getString("ModifyDate")
                );
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    // get Product by Id
    public Product getProductById(int productId) {
        try {
            String sql = "SELECT p.ProductId, p.ProductName, p.ProductDescription, \n"
                    + "p.ProductPrice, p.ProductQuantity, p.ProductBrand, p.ProductImage, p.ProductStatus, \n"
                    + "p.CreateBy, p.CreateDate, p.ModifyBy, p.ModifyDate, c.CategoryId FROM Product p \n"
                    + "inner join Category c\n"
                    + "on p.CategoryId = c.CategoryId\n"
                    + "WHERE ProductId = ?";

            Connection con = DBContext(); // Assuming DBContext() returns a valid database connection
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getFloat("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getString("ProductBrand"),
                        rs.getString("ProductImage"),
                        rs.getBoolean("ProductStatus"),
                        rs.getString("CreateBy"),
                        rs.getString("CreateDate"),
                        rs.getString("ModifyBy"),
                        rs.getString("ModifyDate")
                );

                return product;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error retrieving product", ex);
        }
        return null;
    }

    public void updateProduct(String productName, String productDescription, float productPrice,
            int productQuantity, String productBrand, String productImage,
            String modifyBy, String modifyDate, int productId) {

        String sql = "UPDATE Product SET ProductName = ?, ProductDescription = ?, ProductPrice = ?, ProductQuantity = ?, "
                + "ProductBrand = ?, ProductImage = ?, ModifyBy = ?, ModifyDate = ?"
                + "WHERE ProductId = ?";

        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, productName);
            ps.setString(2, productDescription);
            ps.setFloat(3, productPrice);
            ps.setInt(4, productQuantity);
            ps.setString(5, productBrand);
            ps.setString(6, productImage);
            ps.setString(7, modifyBy);
            ps.setString(8, modifyDate);
            ps.setInt(9, productId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error updating product", ex);
        }
    }

    // count number product
    public int getTotalProduct() {
        try {
            String sql = "select count (*) from Product p \n"
                    + "join Category c\n"
                    + "on p.CategoryId = c.CategoryId\n"
                    + "Where c.CategoryStatus = 1 AND p.ProductStatus =1";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // search product bản chuẩn
    public List<Product> searchAndPagingProductByName2(String productName, int productCategory, float priceMin, float priceMax, int sort, String ProductBrand, int index, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "select p.ProductId, p.ProductName, p.ProductPrice, p.ProductDescription,\n"
                + "p.ProductImage, p.ProductStatus, p.CreateBy, p.CreateDate, p.ProductQuantity,\n"
                + "p.ProductBrand, p.ModifyBy, p.ModifyDate, c.CategoryID, c.CategoryName from Product p\n"
                + "inner join Category c\n"
                + "on p.CategoryId = c.CategoryId where 1 = 1 AND c.CategoryStatus = 1 AND p.ProductStatus = 1 \n";
        if (productName != null) {
            sql += " and p.ProductName like'%" + productName + "%'";
        }

        if (productCategory != 0) {
            sql += " and p.CategoryId = " + productCategory;
        }
        if (priceMin != 0 && priceMax != 0) {
            sql += "AND p.ProductPrice BETWEEN" + priceMin + "and" + priceMax;
        }
//        } else if (priceMin > 0) {
//            sql += "AND p.ProductPrice >= ? ";
//        } else if (priceMax > 0) {
//            sql += "AND p.ProductPrice <= ? ";
//        }

        if (ProductBrand != null) {
            sql += " and p.ProductBrand like '%" + ProductBrand + "%'";
        }
        switch (sort) {
            case 1:
                sql += "ORDER BY p.ProductPrice";
                break;
            case 2:
                sql += "ORDER BY p.ProductPrice DESC";
                break;
            case 3:
                sql += "ORDER BY p.ProductName";
                break;
            case 4:
                sql += " ORDER BY p.ProductName DESC ";
                break;
            default:
                sql += " ORDER BY p.ProductId "; // Default sorting
        }

        sql += " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Tính toán offset
            int offset = (index - 1) * pageSize;

            // Đặt các tham số cho câu lệnh SQL
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getString("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getString("ModifyDate"));
                    product.setProductImage(rs.getString("ProductImage"));
                    Category category = new Category();
                    category.setCategoryId(rs.getInt("categoryId"));
                    category.setCategoryName(rs.getString("categoryName"));
                    product.setCategory(category);
                    products.add(product);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    // lấy size
    public int getSize(String productName, int productCategory, float priceMin, float priceMax, int sort, String ProductBrand) {
        int count = 0;
        String sql = "select p.ProductId, p.ProductName, p.ProductPrice, p.ProductDescription,\n"
                + "p.ProductImage, p.ProductStatus, p.CreateBy, p.CreateDate, p.ProductQuantity,\n"
                + "p.ProductBrand, p.ModifyBy, p.ModifyDate, c.CategoryID, c.CategoryName from Product p\n"
                + "inner join Category c\n"
                + "on p.CategoryId = c.CategoryId where 1 = 1 AND c.CategoryStatus = 1 AND p.ProductStatus = 1 \n";
        if (productName != null) {
            sql += " and p.ProductName like'%" + productName + "%' ";
        }

        if (productCategory != 0) {
            sql += " and p.CategoryId = " + productCategory;
        }
        if (priceMin > 0 && priceMax > 0) {
            sql += " AND p.ProductPrice BETWEEN ? AND ?";

        } else if (priceMin > 0) {
            sql += " AND p.ProductPrice >= ?";

        } else if (priceMax > 0) {
            sql += " AND p.ProductPrice <= ?";

        }
        if (ProductBrand != null) {
            sql += " and p.ProductBrand like '%" + ProductBrand + "%'";
        }

        switch (sort) {
            case 1:
                sql += "ORDER BY p.ProductPrice";
                break;
            case 2:
                sql += "ORDER BY p.ProductPrice DESC";
                break;
            case 3:
                sql += "ORDER BY p.ProductName";
                break;
            case 4:
                sql += "ORDER BY p.ProductName DESC ";
                break;
            default:
                sql += "ORDER BY p.ProductId"; // Default sorting
        }
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Tính toán offset

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    count++;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    // dua ra tung trang 1
    public List<Product> getListByPage(List<Product> list,
            int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));

        }
        return arr;

    }

    // pagging new version
    public List<Product> pagingProduct(int pageIndex, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * \n"
                + "FROM Product p \n"
                + "JOIN Category c ON p.CategoryId = c.CategoryId\n"
                + "WHERE c.CategoryStatus = 1 AND p.ProductStatus = 1 \n"
                + "ORDER BY ProductId\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Tính toán offset
            int offset = (pageIndex - 1) * pageSize;

            // Đặt các tham số cho offset và fetch
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductImage(rs.getString("ProductImage"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getString("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getString("ModifyDate"));
                    product.setCategoryId(rs.getInt("CategoryId"));

                    list.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // lấy ra top 2 sản phẩm tương tự
    public List<Product> getTop2SimilarProducts(String productId) {
        List<Product> similarProducts = new ArrayList<>();
        String sql = "SELECT TOP 2 "
                + "ProductId, "
                + "ProductName, "
                + "ProductDescription, "
                + "ProductPrice, "
                + "ProductQuantity, "
                + "ProductBrand, "
                + "ProductImage, "
                + "ProductStatus, "
                + "CreateBy, "
                + "CreateDate, "
                + "ModifyBy, "
                + "ModifyDate, "
                + "CategoryId "
                + "FROM Product "
                + "WHERE CategoryId = ( "
                + "    SELECT CategoryId "
                + "    FROM Product "
                + "    WHERE ProductId = ? "
                + ") "
                + "AND ProductId != ?";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, productId);
            ps.setString(2, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductImage(rs.getString("ProductImage"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getString("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getString("ModifyDate"));
                    product.setCategoryId(rs.getInt("CategoryId"));

                    similarProducts.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return similarProducts;
    }

    public boolean updateProductStatus(int productId, boolean productStatus) {
        String sql = "UPDATE Product SET ProductStatus = ? WHERE ProductId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, productStatus);
            ps.setInt(2, productId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Product> getManageProduct() {
        try {
            String sql = "select p.ProductId, p.ProductName, p.ProductPrice, p.ProductDescription,\n"
                    + "p.ProductImage, p.ProductStatus, p.CreateBy, p.CreateDate, p.ProductQuantity,\n"
                    + "p.ProductBrand, p.ModifyBy, p.ModifyDate, c.CategoryID, c.CategoryName from Product p\n"
                    + "inner join Category c\n"
                    + "on p.CategoryId = c.CategoryId\n";

            Connection con = DBContext(); // Assuming DBContext() returns a valid database connection
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getString("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getString("ModifyDate"));
                product.setProductImage(rs.getString("ProductImage"));
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                product.setCategory(category);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertProduct(String productName, String productDescription, double productPrice, int productQuantity,
            String productBrand, String productImage,
            String createBy, String createDate, int categoryId) {
        String sql = "INSERT INTO Product (ProductName, ProductDescription, ProductPrice, ProductQuantity, ProductBrand, "
                + "ProductImage,  CreateBy, CreateDate, CategoryId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, productName);
            ps.setString(2, productDescription);
            ps.setDouble(3, productPrice);
            ps.setInt(4, productQuantity);
            ps.setString(5, productBrand);
            ps.setString(6, productImage);
            ps.setString(7, createBy);
            ps.setString(8, createDate);
            ps.setInt(9, categoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(String productName, String productDescription, double productPrice,
            int productQuantity, String productBrand, String productImage,
            String modifyBy, String modifyDate, int categoryId, int productId) {
        String sql = "UPDATE Product SET ProductName = ?, ProductDescription = ?, ProductPrice = ?, ProductQuantity = ?, "
                + "ProductBrand = ?, ProductImage = ?, ModifyBy = ?, ModifyDate = ?, CategoryId = ? "
                + "WHERE ProductId = ?";

        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, productName);
            ps.setString(2, productDescription);
            ps.setDouble(3, productPrice);
            ps.setInt(4, productQuantity);
            ps.setString(5, productBrand);
            ps.setString(6, productImage);
            ps.setString(7, modifyBy);
            ps.setString(8, modifyDate);
            ps.setInt(9, categoryId);
            ps.setInt(10, productId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating product failed, no rows affected.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error updating product", ex);
        }
    }

    public List<String> getAllBrands() {
        List<String> brands = new ArrayList<>();
        String query = "SELECT DISTINCT ProductBrand FROM Product"; // Giả sử tên bảng là Product
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                brands.add(rs.getString("productBrand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    public Product getProductById1(int productId) {
        try {
            String sql = "SELECT p.ProductId, p.ProductName, p.ProductDescription, \n"
                    + "p.ProductPrice, p.ProductQuantity, p.ProductBrand, p.ProductImage, p.ProductStatus, \n"
                    + "p.CreateBy, p.CreateDate, p.ModifyBy, p.ModifyDate FROM Product p \n"
                    + "WHERE ProductId = ?";

            Connection con = DBContext(); // Assuming DBContext() returns a valid database connection
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getDouble("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getString("ProductBrand"),
                        rs.getString("ProductImage"),
                        rs.getBoolean("ProductStatus"),
                        rs.getString("CreateBy"),
                        rs.getString("CreateDate"),
                        rs.getString("ModifyBy"),
                        rs.getString("ModifyDate")
                );

                return product;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error retrieving product", ex);
        }
        return null;
    }
    public List<Product> pagingProduct1(int pageIndex, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductId, p.ProductName, p.ProductPrice, p.ProductDescription, "
                + "p.ProductImage, p.ProductStatus, p.CreateBy, p.CreateDate, p.ProductQuantity, "
                + "p.ProductBrand, p.ModifyBy, p.ModifyDate, c.CategoryId, c.CategoryName "
                + "FROM Product p "
                + "INNER JOIN Category c ON p.CategoryId = c.CategoryId "                
                + "ORDER BY p.ProductId "
                + "OFFSET ? ROWS "
                + "FETCH NEXT ? ROWS ONLY";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Calculate the offset
            int offset = (pageIndex - 1) * pageSize;

            // Set the parameters for offset and fetch
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductImage(rs.getString("ProductImage"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getString("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getString("ModifyDate"));
                    Category category = new Category();
                    category.setCategoryId(rs.getInt("categoryId"));
                    category.setCategoryName(rs.getString("categoryName"));
                    product.setCategory(category);
                    list.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public int getProductQuantityByProductId(int productId) {
        int quantity = 0;
        String sql = "SELECT ProductQuantity AS quantity FROM Product WHERE ProductId = ?";
        try (Connection con = DBContext(); // Assuming DBContext has a method to get the connection
                 PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }

    public List<ProductOrder> getTop5ProductsByOrderCount() throws SQLException {
        List<ProductOrder> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT TOP 5 "
                    + "    P.ProductId, "
                    + "    P.ProductName, "
                    + "    P.ProductDescription, "
                    + "    P.ProductPrice, "
                    + "    P.ProductQuantity, "
                    + "    P.ProductBrand, "
                    + "    P.ProductImage, "
                    + "    P.ProductStatus, "
                    + "    P.CreateBy, "
                    + "    P.CreateDate, "
                    + "    P.ModifyBy, "
                    + "    P.ModifyDate, "
                    + "    P.CategoryId, "
                    + "    P.AdminID, "
                    + "    COUNT(*) AS ProductCount "
                    + "FROM OrderHistoryDetail OD "
                    + "JOIN Product P ON OD.ProductID = P.ProductId "
                    + "GROUP BY P.ProductId, P.ProductName, P.ProductDescription, P.ProductPrice, P.ProductQuantity, P.ProductBrand, "
                    + "         P.ProductImage, P.ProductStatus, P.CreateBy, P.CreateDate, P.ModifyBy, P.ModifyDate, P.CategoryId, P.AdminID "
                    + "ORDER BY ProductCount DESC";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                ProductOrder productDTO = new ProductOrder(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getFloat("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getString("ProductBrand"),
                        rs.getString("ProductImage"), // Assuming this field is a String
                        rs.getBoolean("ProductStatus"),
                        rs.getString("CreateBy"),
                        rs.getDate("CreateDate"),
                        rs.getString("ModifyBy"),
                        rs.getDate("ModifyDate"),
                        rs.getInt("CategoryId"),
                        rs.getInt("AdminID"),
                        rs.getInt("ProductCount")
                );
                list.add(productDTO);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }

    public void UpdateProductQuantity(int productId, int productQuantity) {
        try {
            String sql = "UPDATE dbo.Product SET ProductQuantity = ? WHERE ProductId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productQuantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Object ex = null;
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCategoryName(int productId) {
        String categoryName = null;
        try {
            String sql = "SELECT c.CategoryName FROM Product p JOIN Category c ON p.CategoryId = c.CategoryId WHERE p.ProductId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoryName = rs.getString("CategoryName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryName;
    }

    public static void main(String[] args) {
    ProductDAO productDAO = new ProductDAO();
    List<String> brands = productDAO.getAllBrands();
    
    // In ra danh sách các brand
    System.out.println("Danh sách các brand:");
    for (String brand : brands) {
        System.out.println(brand);
    }
}

}
