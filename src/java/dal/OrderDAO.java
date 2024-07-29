/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import model.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import model.OrderOrderHistory;
import java.sql.PreparedStatement;

/**
 *
 * @author hungp
 */
public class OrderDAO extends DBContext {

    // new
    public void insertOrder(int status, int orderHisId) {
        try {
            String sql = """
                     INSERT INTO [Order] (OrderStatus, OrderHistoryId)
                     VALUES (?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, orderHisId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int getOrderId() {
        try {
            String sql = "SELECT TOP(1) OrderID FROM [Order] ORDER BY OrderID DESC";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderID");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public List<OrderOrderHistory> getAllOrderPagingByUserIdAndRoleId(int userId, int roleId, int pageIndex, int pageSize) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT o.OrderID, o.OrderStatus, oh.OrderHistoryID, oh.Quantity, oh.OrderDate, oh.[Name], oh.Phone, "
                    + "oh.[Address], oh.UserId, oh.RoleId, oh.TotalMoney, oh.IsSuccess, oh.OrderHistoryStatus, oh.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE oh.UserId = ? AND oh.RoleId = ? "
                    + "ORDER BY o.OrderID "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            st.setInt(3, (pageIndex - 1) * pageSize);
            st.setInt(4, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                list.add(orderOrderHistory);
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

    public int getTotalOrderByUserIdAndRoleId(int userId, int roleId) throws SQLException {
        int totalOrders = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS TotalOrders "
                    + "FROM [Order] o "
                    + "JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE oh.UserId = ? AND oh.RoleId = ? AND oh.OrderHistoryStatus = 1";
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            rs = st.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
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
        return totalOrders;
    }

    public int getTotalQuantityOrder() {
        int totalQuantity = 0;
        String sql = "SELECT SUM(Quantity) AS TotalQuantity FROM [OrderHistory]";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalQuantity = rs.getInt("TotalQuantity");
            }
        } catch (SQLException ex) {
        }
        return totalQuantity;
    }

    public OrderOrderHistory getOrderByOrderId(int orderId) {
        OrderOrderHistory orderOrderHistory = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT o.OrderID, o.OrderStatus, oh.OrderHistoryID, oh.Quantity, oh.OrderDate, oh.[Name], oh.Phone, "
                    + "oh.[Address], oh.UserId, oh.RoleId, oh.TotalMoney, oh.IsSuccess, oh.OrderHistoryStatus, oh.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE o.OrderID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, orderId);
            rs = st.executeQuery();

            if (rs.next()) {
                orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderOrderHistory;
    }

    public OrderOrderHistory getOrderByOrderHistoryId(int orderHistoryId) {
        OrderOrderHistory orderOrderHistory = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT o.OrderID, o.OrderStatus, oh.OrderHistoryID, oh.Quantity, oh.OrderDate, oh.[Name], oh.Phone, "
                    + "oh.[Address], oh.UserId, oh.RoleId, oh.TotalMoney, oh.IsSuccess, oh.OrderHistoryStatus, oh.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE oh.OrderHistoryID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, orderHistoryId);
            rs = st.executeQuery();

            if (rs.next()) {
                orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderOrderHistory;
    }

    public float getTotalMoneyOrder() {
        float totalMoney = 0;
        String sql = "SELECT SUM(TotalMoney) AS TotalMoney FROM [OrderHistory]";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalMoney = rs.getFloat("TotalMoney");
            }
        } catch (SQLException ex) {
        }
        return totalMoney;
    }

    public double totalMoneyMonth(int month, int year) {
        double totalMoney = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT SUM(TotalMoney) AS TotalMoney FROM [OrderHistory] WHERE MONTH(OrderDate) = ? AND YEAR(OrderDate) = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, month);
            st.setInt(2, year);
            rs = st.executeQuery();

            if (rs.next()) {
                totalMoney = rs.getDouble("TotalMoney");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return totalMoney;
    }

    public double totalMoneyWeek(int day, int from, int to, int year, int month) {
        double totalMoney = 0;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "";

        if (from > to) {
            sql = "SELECT SUM(TotalMoney) AS TotalMoney "
                    + "FROM [OrderHistory] "
                    + "WHERE ((DAY([OrderDate]) >= ? AND MONTH([OrderDate]) = ?) OR (DAY([OrderDate]) <= ? AND MONTH([OrderDate]) = ?)) "
                    + "AND YEAR([OrderDate]) = ? AND DATEPART(dw, [OrderDate]) = ?";
        } else {
            sql = "SELECT SUM(TotalMoney) AS TotalMoney "
                    + "FROM [OrderHistory] "
                    + "WHERE DAY([OrderDate]) BETWEEN ? AND ? "
                    + "AND MONTH([OrderDate]) = ? "
                    + "AND YEAR([OrderDate]) = ? "
                    + "AND DATEPART(dw, [OrderDate]) = ?";
        }

        try {
            Connection con = DBContext(); // Assuming DBContext() provides the connection
            st = con.prepareStatement(sql);

            if (from > to) {
                st.setInt(1, from);
                st.setInt(2, month);
                st.setInt(3, to);
                st.setInt(4, (month + 1));
                st.setInt(5, year);
                st.setInt(6, day);
            } else {
                st.setInt(1, from);
                st.setInt(2, to);
                st.setInt(3, month);
                st.setInt(4, year);
                st.setInt(5, day);
            }

            rs = st.executeQuery();

            if (rs.next()) {
                totalMoney = rs.getDouble("TotalMoney");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return totalMoney;
    }

    public int totalOrderWeek(int day, int from, int to, int year, int month) {
        int totalOrder = 0;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "";

        if (from > to) {
            sql = "SELECT Count(OrderHistoryID) AS OrderID "
                    + "FROM [OrderHistory] "
                    + "WHERE ((DAY([OrderDate]) >= ? AND MONTH([OrderDate]) = ?) OR (DAY([OrderDate]) <= ? AND MONTH([OrderDate]) = ?)) "
                    + "AND YEAR([OrderDate]) = ? AND DATEPART(dw, [OrderDate]) = ?";
        } else {
            sql = "SELECT Count(OrderHistoryID) AS OrderID "
                    + "FROM [OrderHistory] "
                    + "WHERE DAY([OrderDate]) BETWEEN ? AND ? "
                    + "AND MONTH([OrderDate]) = ? "
                    + "AND YEAR([OrderDate]) = ? "
                    + "AND DATEPART(dw, [OrderDate]) = ?";
        }

        try {
            Connection con = DBContext(); // Assuming DBContext() provides the connection
            st = con.prepareStatement(sql);

            if (from > to) {
                st.setInt(1, from);
                st.setInt(2, month);
                st.setInt(3, to);
                st.setInt(4, (month + 1));
                st.setInt(5, year);
                st.setInt(6, day);
            } else {
                st.setInt(1, from);
                st.setInt(2, to);
                st.setInt(3, month);
                st.setInt(4, year);
                st.setInt(5, day);
            }

            rs = st.executeQuery();

            if (rs.next()) {
                totalOrder = rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return totalOrder;
    }

    public List<OrderOrderHistory> getAllByUserIdAndRoleIdSortedBy(int userId, int roleId, String sortBy) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        List<String> allowedSortColumns = Arrays.asList(
                "OrderID", "OrderDate", "Quantity", "UserId", "RoleId", "TotalMoney",
                "OrderStatus", "Name", "Phone", "Address", "OrderHistoryID", "IsSuccess", "OrderHistoryStatus"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext(); // Assuming DBContext.getConnection() is the method to get a connection
            String sql = "SELECT o.OrderID, o.OrderStatus, oh.OrderHistoryID, oh.Quantity, oh.OrderDate, oh.[Name], oh.Phone, "
                    + "oh.[Address], oh.UserId, oh.RoleId, oh.TotalMoney, oh.IsSuccess, oh.OrderHistoryStatus, oh.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE oh.UserId = ? AND oh.RoleId = ? AND oh.OrderHistoryStatus = 1 "
                    + "ORDER BY " + sortBy;
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                list.add(orderOrderHistory);
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

    public List<OrderOrderHistory> getAllByUserIdAndRoleIdByCondition(int userId, int roleId, String condition) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext(); // Assuming DBContext.getConnection() is the method to get a connection
            String sql = "SELECT o.OrderID, o.OrderStatus, oh.OrderHistoryID, oh.Quantity, oh.OrderDate, oh.[Name], oh.Phone, "
                    + "oh.[Address], oh.UserId, oh.RoleId, oh.TotalMoney, oh.IsSuccess, oh.OrderHistoryStatus, oh.PaymentMethod "
                    + "FROM [Order] o "
                    + "INNER JOIN OrderHistory oh ON o.OrderHistoryId = oh.OrderHistoryId "
                    + "WHERE oh.UserId = ? AND oh.RoleId = ? AND oh.OrderHistoryStatus = 1 AND " + condition;
            st = con.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, roleId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryID"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                list.add(orderOrderHistory);
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

    public List<OrderOrderHistory> getAllByDateUserIdRoleId(Date startDate, Date endDate, int userId, int roleId) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT oh.OrderHistoryId, oh.OrderDate, oh.Quantity, oh.UserId, oh.RoleId, oh.TotalMoney, "
                    + "oh.OrderHistoryStatus, oh.[Name], oh.Phone, oh.[Address], oh.IsSuccess, oh.PaymentMethod, "
                    + "o.OrderID, o.OrderStatus "
                    + "FROM OrderHistory oh "
                    + "INNER JOIN [Order] o ON oh.OrderHistoryId = o.OrderHistoryId "
                    + "WHERE oh.OrderDate BETWEEN ? AND ? AND oh.UserId = ? AND oh.RoleId = ?";
            st = con.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            st.setInt(3, userId);
            st.setInt(4, roleId);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderOrderHistory = new OrderOrderHistory();
                orderOrderHistory.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderOrderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderOrderHistory.setQuantity(rs.getInt("Quantity"));
                orderOrderHistory.setUserId(rs.getInt("UserId"));
                orderOrderHistory.setRoleId(rs.getInt("RoleId"));
                orderOrderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderOrderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderOrderHistory.setName(rs.getString("Name"));
                orderOrderHistory.setPhone(rs.getString("Phone"));
                orderOrderHistory.setAddress(rs.getString("Address"));
                orderOrderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderOrderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                orderOrderHistory.setOrderId(rs.getInt("OrderID"));
                orderOrderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                list.add(orderOrderHistory);
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

    public List<OrderOrderHistory> getAllOrderHistoryPaging(int pageIndex, int pageSize) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT oh.OrderHistoryId, oh.OrderDate, oh.Quantity, oh.UserId, oh.RoleId, oh.TotalMoney, "
                    + "oh.OrderHistoryStatus, oh.[Name], oh.Phone, oh.[Address], oh.IsSuccess, oh.PaymentMethod, "
                    + "o.OrderID, o.OrderStatus "
                    + "FROM OrderHistory oh "
                    + "INNER JOIN [Order] o ON oh.OrderHistoryId = o.OrderHistoryId "
                    + "ORDER BY oh.OrderHistoryId "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            st = con.prepareStatement(sql);
            st.setInt(1, (pageIndex - 1) * pageSize);
            st.setInt(2, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderhistory = new OrderOrderHistory();
                orderhistory.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderhistory.setOrderDate(rs.getDate("OrderDate"));
                orderhistory.setQuantity(rs.getInt("Quantity"));
                orderhistory.setUserId(rs.getInt("UserId"));
                orderhistory.setRoleId(rs.getInt("RoleId"));
                orderhistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderhistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderhistory.setName(rs.getString("Name"));
                orderhistory.setPhone(rs.getString("Phone"));
                orderhistory.setAddress(rs.getString("Address"));
                orderhistory.setIsSuccess(rs.getString("IsSuccess"));
                orderhistory.setPaymentMethod(rs.getString("PaymentMethod"));
                orderhistory.setOrderId(rs.getInt("OrderID"));
                orderhistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                list.add(orderhistory);
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

    public int getTotalOrders() throws SQLException {
        int totalOrders = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS TotalOrders FROM [OrderHistory]";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
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
        return totalOrders;
    }

    public List<OrderOrderHistory> getAllBySortedBy(String sortBy) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        List<String> allowedSortColumns = Arrays.asList(
                "OrderID", "OrderDate", "Quantity", "UserId", "RoleId", "TotalMoney",
                "OrderStatus", "Name", "Phone", "Address", "OrderHistoryId", "IsSuccess", "OrderHistoryStatus"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext(); // Assuming DBContext.getConnection() is the method to get a connection
            String sql = "SELECT oh.OrderHistoryId, oh.OrderDate, oh.Quantity, oh.UserId, oh.RoleId, oh.TotalMoney, "
                    + "oh.OrderHistoryStatus, oh.[Name], oh.Phone, oh.[Address], oh.IsSuccess, oh.PaymentMethod, "
                    + "o.OrderID, o.OrderStatus "
                    + "FROM OrderHistory oh "
                    + "INNER JOIN [Order] o ON oh.OrderHistoryId = o.OrderHistoryId "
                    + "ORDER BY " + sortBy;
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderHistory = new OrderOrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderHistory.setQuantity(rs.getInt("Quantity"));
                orderHistory.setUserId(rs.getInt("UserId"));
                orderHistory.setRoleId(rs.getInt("RoleId"));
                orderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderHistory.setName(rs.getString("Name"));
                orderHistory.setPhone(rs.getString("Phone"));
                orderHistory.setAddress(rs.getString("Address"));
                orderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                orderHistory.setOrderId(rs.getInt("OrderID"));
                orderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                list.add(orderHistory);
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

    public List<OrderOrderHistory> getAllByDate(Date startDate, Date endDate) throws SQLException {
        List<OrderOrderHistory> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext(); // Assuming DBContext.getConnection() is the method to get a connection
            String sql = "SELECT oh.OrderHistoryId, oh.OrderDate, oh.Quantity, oh.UserId, oh.RoleId, oh.TotalMoney, "
                    + "oh.OrderHistoryStatus, oh.[Name], oh.Phone, oh.[Address], oh.IsSuccess, oh.PaymentMethod, "
                    + "o.OrderID, o.OrderStatus "
                    + "FROM OrderHistory oh "
                    + "INNER JOIN [Order] o ON oh.OrderHistoryId = o.OrderHistoryId "
                    + "WHERE oh.OrderDate BETWEEN ? AND ?";
            st = con.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = st.executeQuery();

            while (rs.next()) {
                OrderOrderHistory orderHistory = new OrderOrderHistory();
                orderHistory.setOrderHistoryId(rs.getInt("OrderHistoryId"));
                orderHistory.setOrderDate(rs.getDate("OrderDate"));
                orderHistory.setQuantity(rs.getInt("Quantity"));
                orderHistory.setUserId(rs.getInt("UserId"));
                orderHistory.setRoleId(rs.getInt("RoleId"));
                orderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderHistory.setOrderHistoryStatus(rs.getBoolean("OrderHistoryStatus"));
                orderHistory.setName(rs.getString("Name"));
                orderHistory.setPhone(rs.getString("Phone"));
                orderHistory.setAddress(rs.getString("Address"));
                orderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
                orderHistory.setOrderId(rs.getInt("OrderID"));
                orderHistory.setOrderStatus(rs.getBoolean("OrderStatus"));
                list.add(orderHistory);
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

    public List<Order> getAllOrderPagingAdmin(int adminId, int pageIndex, int pageSize) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM [Order] "
                    + "WHERE AdminId = ? "
                    + "ORDER BY OrderID "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            st = con.prepareStatement(sql);
            st.setInt(1, adminId);
            st.setInt(2, (pageIndex - 1) * pageSize);
            st.setInt(3, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getDate("OrderDate"),
                        rs.getInt("Quantity"),
                        rs.getInt("CustomerId"),
                        rs.getInt("AdminId"),
                        rs.getString("name"),
                        rs.getFloat("TotalMoney")
                );
                list.add(order);
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

    public List<Order> getAllOrderPagingCustomer(int customerId, int pageIndex, int pageSize) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM [Order] "
                    + "WHERE CustomerId = ? "
                    + "ORDER BY OrderID "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            st.setInt(2, (pageIndex - 1) * pageSize);
            st.setInt(3, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getDate("OrderDate"),
                        rs.getInt("Quantity"),
                        rs.getInt("CustomerId"),
                        rs.getInt("AdminId"),
                        rs.getString("name"),
                        rs.getFloat("TotalMoney")
                );
                list.add(order);
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

    public int getTotalOrderByCustomerId(int customerId) throws SQLException {
        int totalOrders = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS TotalOrders FROM [Order] WHERE CustomerId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, customerId);
            rs = st.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
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
        return totalOrders;
    }
//  public int getTimesOfCustomerId(int id) {
//        int count = 0;
//        try {
//            String sql = "SELECT COUNT(*) AS OrderCount FROM dbo.[Order] WHERE CustomerId = ?";
//            Connection con = DBContext();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt("OrderCount");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return count;
//    }

    public int getTimesOfCustomerId(int id) {
        int count = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS OrderCount FROM dbo.[Order] WHERE CustomerId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                count = rs.getInt("OrderCount");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public String getAddressOrder(int id) {
        String address = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT TOP 1 Address FROM dbo.[Order] WHERE CustomerId = ? ORDER BY OrderId DESC";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                address = rs.getString("Address");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return address;

    }

    public int getTotalOrderByAdminId(int adminId) throws SQLException {
        int totalOrders = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS TotalOrders FROM [Order] WHERE AdminId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, adminId);
            rs = st.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("TotalOrders");
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
        return totalOrders;
    }

    public static void main(String[] args) throws SQLException {
        OrderDAO orderDao = new OrderDAO();
//        int list = (int) orderDao.getTotalMoneyOrder();
//        System.out.println(list);
        OrderOrderHistory list = orderDao.getOrderByOrderHistoryId(15);
        System.out.println(list);
    }
}
