/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import model.OrderDetailDTO;
import model.Product;
import model.Specification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hungp
 */
public class OrderDetailDAO extends DBContext {

    public List<OrderDetailDTO> getAllOrderDetailByOrderIdPaging(int orderId, int pageIndex, int pageSize) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT DISTINCT\n"
                    + "    od.OrderId, \n"
                    + "    od.OrderDetailId, \n"
                    + "    oh.OrderHistoryId, \n"
                    + "    oh.Quantity, \n"
                    + "    oh.OrderDate, \n"
                    + "    oh.Name, \n"
                    + "    oh.Phone, \n"
                    + "    oh.Address, \n"
                    + "    oh.UserId, \n"
                    + "    oh.RoleId, \n"
                    + "    oh.TotalMoney, \n"
                    + "    oh.IsSuccess, \n"
                    + "    oh.OrderHistoryStatus, \n"
                    + "    oh.PaymentMethod, \n"
                    + "    ohd.OrderHistoryDetailId, \n"
                    + "    ohd.SerialNumber, \n"
                    + "    ohd.Price, \n"
                    + "    p.ProductId, \n"
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
                    + "    p.ModifyDate, \n"
                    + "    p.CategoryId, \n"
                    + "    p.AdminId, \n"
                    + "    c.CategoryName, \n"
                    + "    c.CategoryStatus \n"
                    + "FROM \n"
                    + "    OrderDetail od \n"
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId \n"
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId \n"
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId \n"
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId \n"
                    + "WHERE \n"
                    + "    od.OrderId = ?\n"
                    + "ORDER BY \n"
                    + "    od.OrderDetailId\n"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, (pageIndex - 1) * pageSize);
            st.setInt(3, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetailDTO.setOrderId(rs.getInt("OrderId"));
                orderDetailDTO.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderDetailDTO.setQuantity(rs.getInt("Quantity"));
                orderDetailDTO.setOrderDate(rs.getDate("OrderDate"));
                orderDetailDTO.setName(rs.getString("Name"));
                orderDetailDTO.setPhone(rs.getString("Phone"));
                orderDetailDTO.setAddress(rs.getString("Address"));
                orderDetailDTO.setUserId(rs.getInt("UserId"));
                orderDetailDTO.setRoleId(rs.getInt("RoleId"));
                orderDetailDTO.setTotalMoney(rs.getFloat("TotalMoney"));
                orderDetailDTO.setIsSuccess(rs.getString("IsSuccess"));
                orderDetailDTO.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderDetailDTO.setPaymentMethod(rs.getString("PaymentMethod"));
                orderDetailDTO.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                orderDetailDTO.setSerialNumber(rs.getString("SerialNumber"));
                orderDetailDTO.setPrice(rs.getFloat("Price"));
                orderDetailDTO.setProductId(rs.getInt("ProductId"));
                orderDetailDTO.setProductName(rs.getString("ProductName"));
                orderDetailDTO.setProductDescription(rs.getString("ProductDescription"));
                orderDetailDTO.setProductPrice(rs.getFloat("ProductPrice"));
                orderDetailDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                orderDetailDTO.setProductBrand(rs.getString("ProductBrand"));
                orderDetailDTO.setProductImage(rs.getString("ProductImage"));
                orderDetailDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                orderDetailDTO.setCreateBy(rs.getString("CreateBy"));
                orderDetailDTO.setCreateDate(rs.getDate("CreateDate"));
                orderDetailDTO.setModifyBy(rs.getString("ModifyBy"));
                orderDetailDTO.setModifyDate(rs.getDate("ModifyDate"));
                orderDetailDTO.setCategoryId(rs.getInt("CategoryId"));
                orderDetailDTO.setAdminId(rs.getInt("AdminId"));
                orderDetailDTO.setCategoryName(rs.getString("CategoryName"));
                orderDetailDTO.setCategoryStatus(rs.getBoolean("CategoryStatus"));

                list.add(orderDetailDTO);
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

    public List<OrderDetailDTO> getAllOrderDetailByOrderId(int orderId) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT DISTINCT\n"
                    + "    od.OrderDetailId, \n"
                    + "    od.OrderId, \n"
                    + "    oh.OrderHistoryId, \n"
                    + "    oh.Quantity, \n"
                    + "    oh.OrderDate, \n"
                    + "    oh.Name, \n"
                    + "    oh.Phone, \n"
                    + "    oh.Address, \n"
                    + "    oh.UserId, \n"
                    + "    oh.RoleId, \n"
                    + "    oh.TotalMoney, \n"
                    + "    oh.IsSuccess, \n"
                    + "    oh.OrderHistoryStatus, \n"
                    + "    oh.PaymentMethod, \n"
                    + "    ohd.OrderHistoryDetailId, \n"
                    + "    ohd.SerialNumber, \n"
                    + "    ohd.Price, \n"
                    + "    p.ProductId, \n"
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
                    + "    p.ModifyDate, \n"
                    + "    p.CategoryId, \n"
                    + "    p.AdminId, \n"
                    + "    c.CategoryName, \n"
                    + "    c.CategoryStatus \n"
                    + "FROM \n"
                    + "    OrderDetail od \n"
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId \n"
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId \n"
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId \n"
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId \n"
                    + "WHERE \n"
                    + "    od.OrderId = ?\n"
                    + "ORDER BY \n"
                    + "    od.OrderDetailId;";

            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetailDTO.setOrderId(rs.getInt("OrderId"));
                orderDetailDTO.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderDetailDTO.setQuantity(rs.getInt("Quantity"));
                orderDetailDTO.setOrderDate(rs.getDate("OrderDate"));
                orderDetailDTO.setName(rs.getString("Name"));
                orderDetailDTO.setPhone(rs.getString("Phone"));
                orderDetailDTO.setAddress(rs.getString("Address"));
                orderDetailDTO.setUserId(rs.getInt("UserId"));
                orderDetailDTO.setRoleId(rs.getInt("RoleId"));
                orderDetailDTO.setTotalMoney(rs.getFloat("TotalMoney"));
                orderDetailDTO.setIsSuccess(rs.getString("IsSuccess"));
                orderDetailDTO.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderDetailDTO.setPaymentMethod(rs.getString("PaymentMethod"));
                orderDetailDTO.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                orderDetailDTO.setSerialNumber(rs.getString("SerialNumber"));
                orderDetailDTO.setPrice(rs.getFloat("Price"));
                orderDetailDTO.setProductId(rs.getInt("ProductId"));
                orderDetailDTO.setProductName(rs.getString("ProductName"));
                orderDetailDTO.setProductDescription(rs.getString("ProductDescription"));
                orderDetailDTO.setProductPrice(rs.getFloat("ProductPrice"));
                orderDetailDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                orderDetailDTO.setProductBrand(rs.getString("ProductBrand"));
                orderDetailDTO.setProductImage(rs.getString("ProductImage"));
                orderDetailDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                orderDetailDTO.setCreateBy(rs.getString("CreateBy"));
                orderDetailDTO.setCreateDate(rs.getDate("CreateDate"));
                orderDetailDTO.setModifyBy(rs.getString("ModifyBy"));
                orderDetailDTO.setModifyDate(rs.getDate("ModifyDate"));
                orderDetailDTO.setCategoryId(rs.getInt("CategoryId"));
                orderDetailDTO.setAdminId(rs.getInt("AdminId"));
                orderDetailDTO.setCategoryName(rs.getString("CategoryName"));
                orderDetailDTO.setCategoryStatus(rs.getBoolean("CategoryStatus"));

                list.add(orderDetailDTO);
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

    public List<OrderDetailDTO> getAllOrderDetailByOrderHistoryId(int orderHistoryId) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT DISTINCT\n"
                    + "    od.OrderDetailId, \n"
                    + "    od.OrderId, \n"
                    + "    oh.OrderHistoryId, \n"
                    + "    oh.Quantity, \n"
                    + "    oh.OrderDate, \n"
                    + "    oh.Name, \n"
                    + "    oh.Phone, \n"
                    + "    oh.Address, \n"
                    + "    oh.UserId, \n"
                    + "    oh.RoleId, \n"
                    + "    oh.TotalMoney, \n"
                    + "    oh.IsSuccess, \n"
                    + "    oh.OrderHistoryStatus, \n"
                    + "    oh.PaymentMethod, \n"
                    + "    ohd.OrderHistoryDetailId, \n"
                    + "    ohd.SerialNumber, \n"
                    + "    ohd.Price, \n"
                    + "    p.ProductId, \n"
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
                    + "    p.ModifyDate, \n"
                    + "    p.CategoryId, \n"
                    + "    p.AdminId, \n"
                    + "    c.CategoryName, \n"
                    + "    c.CategoryStatus \n"
                    + "FROM \n"
                    + "    OrderDetail od \n"
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId \n"
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId \n"
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId \n"
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId \n"
                    + "WHERE \n"
                    + "    oh.OrderHistoryId = ?\n"
                    + "ORDER BY \n"
                    + "    od.OrderDetailId;";

            st = con.prepareStatement(sql);
            st.setInt(1, orderHistoryId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetailDTO.setOrderId(rs.getInt("OrderId"));
                orderDetailDTO.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderDetailDTO.setQuantity(rs.getInt("Quantity"));
                orderDetailDTO.setOrderDate(rs.getDate("OrderDate"));
                orderDetailDTO.setName(rs.getString("Name"));
                orderDetailDTO.setPhone(rs.getString("Phone"));
                orderDetailDTO.setAddress(rs.getString("Address"));
                orderDetailDTO.setUserId(rs.getInt("UserId"));
                orderDetailDTO.setRoleId(rs.getInt("RoleId"));
                orderDetailDTO.setTotalMoney(rs.getFloat("TotalMoney"));
                orderDetailDTO.setIsSuccess(rs.getString("IsSuccess"));
                orderDetailDTO.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderDetailDTO.setPaymentMethod(rs.getString("PaymentMethod"));
                orderDetailDTO.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                orderDetailDTO.setSerialNumber(rs.getString("SerialNumber"));
                orderDetailDTO.setPrice(rs.getFloat("Price"));
                orderDetailDTO.setProductId(rs.getInt("ProductId"));
                orderDetailDTO.setProductName(rs.getString("ProductName"));
                orderDetailDTO.setProductDescription(rs.getString("ProductDescription"));
                orderDetailDTO.setProductPrice(rs.getFloat("ProductPrice"));
                orderDetailDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                orderDetailDTO.setProductBrand(rs.getString("ProductBrand"));
                orderDetailDTO.setProductImage(rs.getString("ProductImage"));
                orderDetailDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                orderDetailDTO.setCreateBy(rs.getString("CreateBy"));
                orderDetailDTO.setCreateDate(rs.getDate("CreateDate"));
                orderDetailDTO.setModifyBy(rs.getString("ModifyBy"));
                orderDetailDTO.setModifyDate(rs.getDate("ModifyDate"));
                orderDetailDTO.setCategoryId(rs.getInt("CategoryId"));
                orderDetailDTO.setAdminId(rs.getInt("AdminId"));
                orderDetailDTO.setCategoryName(rs.getString("CategoryName"));
                orderDetailDTO.setCategoryStatus(rs.getBoolean("CategoryStatus"));

                list.add(orderDetailDTO);
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

    public int getTotalOrderDetailByOrderIdPaging(int orderId) throws SQLException {
        int total = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS Total "
                    + "FROM ( "
                    + "    SELECT DISTINCT od.OrderDetailId "
                    + "    FROM OrderDetail od "
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId "
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId "
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId "
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId "
                    + "    WHERE od.OrderId = ? "
                    + ") AS UniqueOrderDetails";

            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
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
        return total;
    }

    public List<OrderDetailDTO> getAllOrderDetailByConditionAndOrderId(String condition, int orderId) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT DISTINCT\n"
                    + "    od.OrderDetailId, \n"
                    + "    od.OrderId, \n"
                    + "    oh.OrderHistoryId, \n"
                    + "    oh.Quantity, \n"
                    + "    oh.OrderDate, \n"
                    + "    oh.Name, \n"
                    + "    oh.Phone, \n"
                    + "    oh.Address, \n"
                    + "    oh.UserId, \n"
                    + "    oh.RoleId, \n"
                    + "    oh.TotalMoney, \n"
                    + "    oh.IsSuccess, \n"
                    + "    oh.OrderHistoryStatus, \n"
                    + "    oh.PaymentMethod, \n"
                    + "    ohd.OrderHistoryDetailId, \n"
                    + "    ohd.SerialNumber, \n"
                    + "    ohd.Price, \n"
                    + "    p.ProductId, \n"
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
                    + "    p.ModifyDate, \n"
                    + "    p.CategoryId, \n"
                    + "    p.AdminId, \n"
                    + "    c.CategoryName, \n"
                    + "    c.CategoryStatus \n"
                    + "FROM \n"
                    + "    OrderDetail od \n"
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId \n"
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId \n"
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId \n"
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId \n"
                    + "WHERE " + condition + " AND od.OrderId = ?";

            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetailDTO.setOrderId(rs.getInt("OrderId"));
                orderDetailDTO.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderDetailDTO.setQuantity(rs.getInt("Quantity"));
                orderDetailDTO.setOrderDate(rs.getDate("OrderDate"));
                orderDetailDTO.setName(rs.getString("Name"));
                orderDetailDTO.setPhone(rs.getString("Phone"));
                orderDetailDTO.setAddress(rs.getString("Address"));
                orderDetailDTO.setUserId(rs.getInt("UserId"));
                orderDetailDTO.setRoleId(rs.getInt("RoleId"));
                orderDetailDTO.setTotalMoney(rs.getFloat("TotalMoney"));
                orderDetailDTO.setIsSuccess(rs.getString("IsSuccess"));
                orderDetailDTO.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderDetailDTO.setPaymentMethod(rs.getString("PaymentMethod"));
                orderDetailDTO.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                orderDetailDTO.setSerialNumber(rs.getString("SerialNumber"));
                orderDetailDTO.setPrice(rs.getFloat("Price"));
                orderDetailDTO.setProductId(rs.getInt("ProductId"));
                orderDetailDTO.setProductName(rs.getString("ProductName"));
                orderDetailDTO.setProductDescription(rs.getString("ProductDescription"));
                orderDetailDTO.setProductPrice(rs.getFloat("ProductPrice"));
                orderDetailDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                orderDetailDTO.setProductBrand(rs.getString("ProductBrand"));
                orderDetailDTO.setProductImage(rs.getString("ProductImage"));
                orderDetailDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                orderDetailDTO.setCreateBy(rs.getString("CreateBy"));
                orderDetailDTO.setCreateDate(rs.getDate("CreateDate"));
                orderDetailDTO.setModifyBy(rs.getString("ModifyBy"));
                orderDetailDTO.setModifyDate(rs.getDate("ModifyDate"));
                orderDetailDTO.setCategoryId(rs.getInt("CategoryId"));
                orderDetailDTO.setAdminId(rs.getInt("AdminId"));
                orderDetailDTO.setCategoryName(rs.getString("CategoryName"));
                orderDetailDTO.setCategoryStatus(rs.getBoolean("CategoryStatus"));

                list.add(orderDetailDTO);
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

    public List<OrderDetailDTO> getAllOrderDetailsSortedByAndOrderId(String sortBy, int orderId) throws SQLException {
        List<OrderDetailDTO> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT DISTINCT\n"
                    + "    od.OrderDetailId, \n"
                    + "    od.OrderId, \n"
                    + "    oh.OrderHistoryId, \n"
                    + "    oh.Quantity, \n"
                    + "    oh.OrderDate, \n"
                    + "    oh.Name, \n"
                    + "    oh.Phone, \n"
                    + "    oh.Address, \n"
                    + "    oh.UserId, \n"
                    + "    oh.RoleId, \n"
                    + "    oh.TotalMoney, \n"
                    + "    oh.IsSuccess, \n"
                    + "    oh.OrderHistoryStatus, \n"
                    + "    oh.PaymentMethod, \n"
                    + "    ohd.OrderHistoryDetailId, \n"
                    + "    ohd.SerialNumber, \n"
                    + "    ohd.Price, \n"
                    + "    p.ProductId, \n"
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
                    + "    p.ModifyDate, \n"
                    + "    p.CategoryId, \n"
                    + "    p.AdminId, \n"
                    + "    c.CategoryName, \n"
                    + "    c.CategoryStatus \n"
                    + "FROM \n"
                    + "    OrderDetail od \n"
                    + "    JOIN OrderHistory oh ON od.OrderHistoryId = oh.OrderHistoryId \n"
                    + "    JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId \n"
                    + "    JOIN Product p ON ohd.ProductId = p.ProductId \n"
                    + "    JOIN Category c ON p.CategoryId = c.CategoryId \n"
                    + "WHERE od.OrderId = ? \n"
                    + "ORDER BY " + sortBy;

            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                orderDetailDTO.setOrderDetailId(rs.getInt("OrderDetailId"));
                orderDetailDTO.setOrderId(rs.getInt("OrderId"));
                orderDetailDTO.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderDetailDTO.setQuantity(rs.getInt("Quantity"));
                orderDetailDTO.setOrderDate(rs.getDate("OrderDate"));
                orderDetailDTO.setName(rs.getString("Name"));
                orderDetailDTO.setPhone(rs.getString("Phone"));
                orderDetailDTO.setAddress(rs.getString("Address"));
                orderDetailDTO.setUserId(rs.getInt("UserId"));
                orderDetailDTO.setRoleId(rs.getInt("RoleId"));
                orderDetailDTO.setTotalMoney(rs.getFloat("TotalMoney"));
                orderDetailDTO.setIsSuccess(rs.getString("IsSuccess"));
                orderDetailDTO.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderDetailDTO.setPaymentMethod(rs.getString("PaymentMethod"));
                orderDetailDTO.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                orderDetailDTO.setSerialNumber(rs.getString("SerialNumber"));
                orderDetailDTO.setPrice(rs.getFloat("Price"));
                orderDetailDTO.setProductId(rs.getInt("ProductId"));
                orderDetailDTO.setProductName(rs.getString("ProductName"));
                orderDetailDTO.setProductDescription(rs.getString("ProductDescription"));
                orderDetailDTO.setProductPrice(rs.getFloat("ProductPrice"));
                orderDetailDTO.setProductQuantity(rs.getInt("ProductQuantity"));
                orderDetailDTO.setProductBrand(rs.getString("ProductBrand"));
                orderDetailDTO.setProductImage(rs.getString("ProductImage"));
                orderDetailDTO.setProductStatus(rs.getBoolean("ProductStatus"));
                orderDetailDTO.setCreateBy(rs.getString("CreateBy"));
                orderDetailDTO.setCreateDate(rs.getDate("CreateDate"));
                orderDetailDTO.setModifyBy(rs.getString("ModifyBy"));
                orderDetailDTO.setModifyDate(rs.getDate("ModifyDate"));
                orderDetailDTO.setCategoryId(rs.getInt("CategoryId"));
                orderDetailDTO.setAdminId(rs.getInt("AdminId"));
                orderDetailDTO.setCategoryName(rs.getString("CategoryName"));
                orderDetailDTO.setCategoryStatus(rs.getBoolean("CategoryStatus"));

                list.add(orderDetailDTO);
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

    public Map<Integer, Integer> getTop5HottestItems() {
        Map<Integer, Integer> productCountMap = new HashMap<>();
        try {
            String sql = "SELECT TOP 5 ProductID, COUNT(*) AS ProductCount "
                    + "FROM OrderHistoryDetail "
                    + "GROUP BY ProductID "
                    + "ORDER BY ProductCount DESC";
            Connection con = DBContext(); // Assuming DBContext() returns a valid connection
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                int productCount = rs.getInt("ProductCount");
                productCountMap.put(productId, productCount);
            }
            return productCountMap;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public float getTotalMoneyOrder(int orderId) {
        float totalMoney = 0;
        String sql = "SELECT SUM(ohd.Price) AS TotalMoney "
                + "FROM OrderDetail od "
                + "JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId "
                + "WHERE od.OrderId = ?";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, orderId);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalMoney = rs.getFloat("TotalMoney");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalMoney;
    }

    public float getTotalMoneyOrderByOrderHistoryId(int orderId) {
        float totalMoney = 0;
        String sql = "SELECT SUM(ohd.Price) AS TotalMoney "
                + "FROM OrderDetail od "
                + "JOIN OrderHistoryDetail ohd ON od.OrderHistoryDetailId = ohd.OrderHistoryDetailId "
                + "WHERE od.OrderHistoryId = ?";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, orderId);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    totalMoney = rs.getFloat("TotalMoney");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalMoney;
    }

    public void inserOrderDetail(int orderId, int orderHistoryId, int orderHistoryDetailId) {
        try {
            String sql = """
                     INSERT INTO [OrderDetail] (OrderId, OrderHistoryId, OrderHistoryDetailId)
                     VALUES (?, ?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, orderHistoryId);
            ps.setInt(3, orderHistoryDetailId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        float list = orderDetailDAO.getTotalMoneyOrderByOrderHistoryId(15);
        System.out.println(list);
    }
}
