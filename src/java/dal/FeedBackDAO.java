/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import java.beans.Statement;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.FeedbackOrderProduct;

/**
 *
 * @author admin
 */
public class FeedBackDAO extends DBContext {

    public void toggleFeedbackStatus(int id) {
        String sql = "UPDATE Feedback SET FeedbackStatus = CASE WHEN FeedbackStatus = 0 THEN 1 ELSE 0 END WHERE OrderId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkOrderIdInFeedback(int id) {
        String sql = "SELECT COUNT(*) FROM Feedback WHERE OrderId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean updateFeedbackImage(InputStream inputStream, int id) {
        String sql = "update Feedback set FeedbackImage = ? Where OrderId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBlob(1, inputStream);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    public boolean updateFeedbackDescription(String text, int orderId) {
        String sql = "update Feedback set FeedbackDescription = ? Where OrderId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, text);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            // Handle exception
        }
        return false;
    }

    public boolean updateFeedbackStarRate(int star, int id) {
        String sql = "update Feedback set StarRate = ? Where OrderId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, star);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    public String getFeedbackDescription(int orderId) {
        String sql = "SELECT FeedbackDescription FROM Feedback WHERE OrderId = ?";
        String feedbackDescription = null;
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                feedbackDescription = rs.getString("FeedbackDescription");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return feedbackDescription;
    }

    public int getTotalFeedback() {
        int totalFeedback = 0;
        String sql = "SELECT COUNT(*) AS TotalFeedback FROM Feedback";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalFeedback = rs.getInt("TotalFeedback");
            }
        } catch (SQLException ex) {
        }
        return totalFeedback;
    }
    public int getTotalFeedbackByProductId(String productId) {
        int totalFeedback = 0;
        String sql = "SELECT COUNT(*) AS TotalFeedback FROM Feedback WHERE ProductId = ? AND FeedbackStatus = 1";

        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalFeedback = rs.getInt("TotalFeedback");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalFeedback;
    }
    
    public int getSumStarByProductId(String productId) {
    int sumStar = 0;
    String sql = "SELECT SUM(StarRate) AS SumStar FROM Feedback WHERE ProductId = ? AND FeedbackStatus = 1";

    try {
        Connection con = DBContext();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            sumStar = rs.getInt("SumStar");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return sumStar;
}


    public float getAverageStarRate() {
        float averageStarRate = 0.0f;
        String sql = "SELECT AVG(CAST(StarRate AS DECIMAL(10, 2))) AS AverageStarRate FROM Feedback;";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                averageStarRate = rs.getFloat("AverageStarRate");
                averageStarRate = Math.round(averageStarRate * 100.0f) / 100.0f;
            }
        } catch (SQLException ex) {
        }
        return averageStarRate;
    }
    
    public List<FeedbackOrderProduct> getAllFeedbackByProductId(String productId) throws SQLException {
    List<FeedbackOrderProduct> list = new ArrayList<>();
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        Connection con = DBContext();
        String sql = "SELECT \n"
                + "    f.FeedbackId, \n"
                + "    f.UserName, \n"
                + "    f.UserId, \n"
                + "    f.RoleId, \n"
                + "    f.StarRate, \n"
                + "    f.FeedbackDescription, \n"
                + "    f.FeedbackImage, \n"
                + "    f.FeedbackDate, \n"
                + "    f.FeedbackStatus, \n"
                + "    f.OrderId, \n"
                + "    f.ProductId, \n"
                + "    o.OrderStatus, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription, \n"
                + "    p.ProductPrice, \n"
                + "    p.ProductQuantity, \n"
                + "    p.ProductBrand, \n"
                + "    p.ProductImage, \n"
                + "    p.ProductStatus, \n"
                + "    p.CreateBy, \n"
                + "    p.CreateDate, \n"
                + "    p.ModifyBy, \n"
                + "    p.ModifyDate \n"
                + "FROM \n"
                + "    Feedback f \n"
                + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                + "WHERE \n"
                + "    f.ProductId = ? AND FeedbackStatus = 1 \n"
                + "ORDER BY \n"
                + "    f.FeedbackId;";

        st = con.prepareStatement(sql);
        st.setString(1, productId);
        rs = st.executeQuery();

        while (rs.next()) {
            FeedbackOrderProduct feedbackDTO = new FeedbackOrderProduct();
            feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
            feedbackDTO.setUserName(rs.getString("UserName"));
            feedbackDTO.setUserId(rs.getInt("UserId"));
            feedbackDTO.setRoleId(rs.getInt("RoleId"));
            feedbackDTO.setStarRate(rs.getInt("StarRate"));
            feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
            feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
            feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
            feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
            feedbackDTO.setOrderId(rs.getInt("OrderId"));
            feedbackDTO.setProductId(rs.getInt("ProductId"));
            feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
            feedbackDTO.setProductName(rs.getString("ProductName"));
            feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
            feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
            feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
            feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
            feedbackDTO.setProductImage(rs.getString("ProductImage"));
            feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
            feedbackDTO.setCreateBy(rs.getString("CreateBy"));
            feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
            feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
            feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));
            list.add(feedbackDTO);
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


    public List<FeedbackOrderProduct> getAllFeedbackPaging(int pageIndex, int pageSize) throws SQLException {
        List<FeedbackOrderProduct> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    f.FeedbackId, \n"
                    + "    f.UserName, \n"
                    + "    f.UserId, \n"
                    + "    f.RoleId, \n"
                    + "    f.StarRate, \n"
                    + "    f.FeedbackDescription, \n"
                    + "    f.FeedbackImage, \n"
                    + "    f.FeedbackDate, \n"
                    + "    f.FeedbackStatus, \n"
                    + "    f.OrderId, \n"
                    + "    f.ProductId, \n"
                    + "    o.OrderStatus, \n"
                    + "    p.ProductName, \n"
                    + "    p.ProductDescription, \n"
                    + "    p.ProductPrice, \n"
                    + "    p.ProductQuantity, \n"
                    + "    p.ProductBrand, \n"
                    + "    p.ProductImage, \n"
                    + "    p.ProductStatus, \n"
                    + "    p.CreateBy, \n"
                    + "    p.CreateDate, \n"
                    + "    p.ModifyBy, \n"
                    + "    p.ModifyDate \n"
                    + "FROM \n"
                    + "    Feedback f \n"
                    + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                    + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                    + "ORDER BY \n"
                    + "    f.FeedbackId \n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            st = con.prepareStatement(sql);
            st.setInt(1, (pageIndex - 1) * pageSize);
            st.setInt(2, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                FeedbackOrderProduct feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));
                list.add(feedbackDTO);
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

    public List<FeedbackOrderProduct> getAllFeedbackSortedBy(String sortBy) throws SQLException {
        List<FeedbackOrderProduct> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        List<String> allowedSortColumns = Arrays.asList(
                "FeedbackId", "UserName", "UserId", "RoleId", "StarRate",
                "FeedbackDate", "FeedbackStatus", "OrderId", "ProductId", "OrderStatus",
                "ProductName", "ProductPrice", "ProductQuantity", "ProductBrand", "CreateDate", "ModifyDate"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext(); // Assuming DBContext() is the method to get a connection
            String sql = "SELECT \n"
                    + "    f.FeedbackId, \n"
                    + "    f.UserName, \n"
                    + "    f.UserId, \n"
                    + "    f.RoleId, \n"
                    + "    f.StarRate, \n"
                    + "    f.FeedbackDescription, \n"
                    + "    f.FeedbackImage, \n"
                    + "    f.FeedbackDate, \n"
                    + "    f.FeedbackStatus, \n"
                    + "    f.OrderId, \n"
                    + "    f.ProductId, \n"
                    + "    o.OrderStatus, \n"
                    + "    p.ProductName, \n"
                    + "    p.ProductDescription, \n"
                    + "    p.ProductPrice, \n"
                    + "    p.ProductQuantity, \n"
                    + "    p.ProductBrand, \n"
                    + "    p.ProductImage, \n"
                    + "    p.ProductStatus, \n"
                    + "    p.CreateBy, \n"
                    + "    p.CreateDate, \n"
                    + "    p.ModifyBy, \n"
                    + "    p.ModifyDate \n"
                    + "FROM \n"
                    + "    Feedback f \n"
                    + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                    + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                    + "ORDER BY " + sortBy;
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                FeedbackOrderProduct feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));

                list.add(feedbackDTO);
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

    public List<FeedbackOrderProduct> getAllFeedbackByCondition(String condition) throws SQLException {
        List<FeedbackOrderProduct> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext(); // Assuming DBContext() is the method to get a connection
            String sql = "SELECT \n"
                    + "    f.FeedbackId, \n"
                    + "    f.UserName, \n"
                    + "    f.UserId, \n"
                    + "    f.RoleId, \n"
                    + "    f.StarRate, \n"
                    + "    f.FeedbackDescription, \n"
                    + "    f.FeedbackImage, \n"
                    + "    f.FeedbackDate, \n"
                    + "    f.FeedbackStatus, \n"
                    + "    f.OrderId, \n"
                    + "    f.ProductId, \n"
                    + "    o.OrderStatus, \n"
                    + "    p.ProductName, \n"
                    + "    p.ProductDescription, \n"
                    + "    p.ProductPrice, \n"
                    + "    p.ProductQuantity, \n"
                    + "    p.ProductBrand, \n"
                    + "    p.ProductImage, \n"
                    + "    p.ProductStatus, \n"
                    + "    p.CreateBy, \n"
                    + "    p.CreateDate, \n"
                    + "    p.ModifyBy, \n"
                    + "    p.ModifyDate \n"
                    + "FROM \n"
                    + "    Feedback f \n"
                    + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                    + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                    + "WHERE " + condition;

            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                FeedbackOrderProduct feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));

                list.add(feedbackDTO);
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

    public List<FeedbackOrderProduct> getAllFeedbackByDate(Date startDate, Date endDate) throws SQLException {
        List<FeedbackOrderProduct> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext(); // Assuming DBContext() is the method to get a connection
            String sql = "SELECT f.FeedbackId, f.UserName, f.UserId, f.RoleId, f.StarRate, "
                    + "f.FeedbackDescription, f.FeedbackImage, f.FeedbackDate, f.FeedbackStatus, f.OrderId, "
                    + "f.ProductId, o.OrderStatus, p.ProductName, p.ProductDescription, p.ProductPrice, "
                    + "p.ProductQuantity, p.ProductBrand, p.ProductImage, p.ProductStatus, p.CreateBy, p.CreateDate, "
                    + "p.ModifyBy, p.ModifyDate "
                    + "FROM Feedback f "
                    + "INNER JOIN [Order] o ON f.OrderId = o.OrderID "
                    + "INNER JOIN Product p ON f.ProductId = p.ProductId "
                    + "WHERE f.FeedbackDate BETWEEN ? AND ?";
            st = con.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = st.executeQuery();

            while (rs.next()) {
                FeedbackOrderProduct feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));

                list.add(feedbackDTO);
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

    public byte[] getImageData(int id) {
        String sql = "select FeedbackImage from Feedback Where FeedbackId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("FeedbackImage");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public FeedbackOrderProduct getFeedbackByFeedbackId(int id) throws SQLException {
        FeedbackOrderProduct feedbackDTO = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    f.FeedbackId, \n"
                    + "    f.UserName, \n"
                    + "    f.UserId, \n"
                    + "    f.RoleId, \n"
                    + "    f.StarRate, \n"
                    + "    f.FeedbackDescription, \n"
                    + "    f.FeedbackImage, \n"
                    + "    f.FeedbackDate, \n"
                    + "    f.FeedbackStatus, \n"
                    + "    f.OrderId, \n"
                    + "    f.ProductId, \n"
                    + "    o.OrderStatus, \n"
                    + "    p.ProductName, \n"
                    + "    p.ProductDescription, \n"
                    + "    p.ProductPrice, \n"
                    + "    p.ProductQuantity, \n"
                    + "    p.ProductBrand, \n"
                    + "    p.ProductImage, \n"
                    + "    p.ProductStatus, \n"
                    + "    p.CreateBy, \n"
                    + "    p.CreateDate, \n"
                    + "    p.ModifyBy, \n"
                    + "    p.ModifyDate \n"
                    + "FROM \n"
                    + "    Feedback f \n"
                    + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                    + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                    + "WHERE \n"
                    + "    f.FeedbackId = ?";

            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));
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
        return feedbackDTO;
    }

    public FeedbackOrderProduct getFeedbackByOrderId(int id) throws SQLException {
        FeedbackOrderProduct feedbackDTO = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    f.FeedbackId, \n"
                    + "    f.UserName, \n"
                    + "    f.UserId, \n"
                    + "    f.RoleId, \n"
                    + "    f.StarRate, \n"
                    + "    f.FeedbackDescription, \n"
                    + "    f.FeedbackImage, \n"
                    + "    f.FeedbackDate, \n"
                    + "    f.FeedbackStatus, \n"
                    + "    f.OrderId, \n"
                    + "    f.ProductId, \n"
                    + "    o.OrderStatus, \n"
                    + "    p.ProductName, \n"
                    + "    p.ProductDescription, \n"
                    + "    p.ProductPrice, \n"
                    + "    p.ProductQuantity, \n"
                    + "    p.ProductBrand, \n"
                    + "    p.ProductImage, \n"
                    + "    p.ProductStatus, \n"
                    + "    p.CreateBy, \n"
                    + "    p.CreateDate, \n"
                    + "    p.ModifyBy, \n"
                    + "    p.ModifyDate \n"
                    + "FROM \n"
                    + "    Feedback f \n"
                    + "    JOIN [Order] o ON f.OrderId = o.OrderID \n"
                    + "    JOIN Product p ON f.ProductId = p.ProductId \n"
                    + "WHERE \n"
                    + "    f.OrderId = ?";

            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                feedbackDTO = new FeedbackOrderProduct();
                feedbackDTO.setFeedbackId(rs.getInt("FeedbackId"));
                feedbackDTO.setUserName(rs.getString("UserName"));
                feedbackDTO.setUserId(rs.getInt("UserId"));
                feedbackDTO.setRoleId(rs.getInt("RoleId"));
                feedbackDTO.setStarRate(rs.getInt("StarRate"));
                feedbackDTO.setFeedbackDescription(rs.getString("FeedbackDescription"));
                feedbackDTO.setFeedbackImage(rs.getBytes("FeedbackImage"));
                feedbackDTO.setFeedbackDate(rs.getDate("FeedbackDate"));
                feedbackDTO.setFeedbackStatus(rs.getBoolean("FeedbackStatus"));
                feedbackDTO.setOrderId(rs.getInt("OrderId"));
                feedbackDTO.setProductId(rs.getInt("ProductId"));
                feedbackDTO.setOrderStatus(rs.getBoolean("OrderStatus"));
                feedbackDTO.setProductName(rs.getString("ProductName"));
                feedbackDTO.setProductDescription(rs.getString("ProductDescription"));
                feedbackDTO.setProductPrice(rs.getDouble("ProductPrice"));
                feedbackDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                feedbackDTO.setProductBrand(rs.getString("ProductBrand"));
                feedbackDTO.setProductImage(rs.getString("ProductImage"));
                feedbackDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                feedbackDTO.setCreateBy(rs.getString("CreateBy"));
                feedbackDTO.setCreateDate(rs.getDate("CreateDate"));
                feedbackDTO.setModifyBy(rs.getString("ModifyBy"));
                feedbackDTO.setModifyDate(rs.getDate("ModifyDate"));
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
        return feedbackDTO;
    }

    public void insertFeedback(Feedback feedback) throws SQLException {
        PreparedStatement st = null;

        try {
            Connection con = DBContext();
            String sql = "INSERT INTO Feedback (UserName, UserId, RoleId, StarRate, FeedbackDescription, FeedbackImage, FeedbackDate, FeedbackStatus, OrderId, ProductId) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            st = con.prepareStatement(sql);
            st.setString(1, feedback.getUserName());
            st.setInt(2, feedback.getUserId());
            st.setInt(3, feedback.getRoleId());
            st.setInt(4, feedback.getStarRate());
            st.setString(5, feedback.getFeedbackDescription());
            st.setBytes(6, feedback.getFeedbackImage());
            st.setDate(7, new java.sql.Date(feedback.getFeedbackDate().getTime()));
            st.setBoolean(8, feedback.isFeedbackStatus());
            st.setInt(9, feedback.getOrderId());
            st.setInt(10, feedback.getProductId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public static void main(String[] args) {
        FeedBackDAO feedbackDAO = new FeedBackDAO();
        int list = feedbackDAO.getTotalFeedback();
        
        System.out.println(list);
    }

}
