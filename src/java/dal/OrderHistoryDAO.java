package dal;

import static dal.DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import model.OrderHistory;

/**
 *
 *
 */
public class OrderHistoryDAO extends DBContext {

    public void updateSuccessStatus(int orderHistoryId, String newValue) {
        String sql = "UPDATE OrderHistory SET isSuccess = ? WHERE orderHistoryId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newValue);
            ps.setInt(2, orderHistoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void toggleOrderHistoryStatus(int orderHistoryId) {
        String sql = "UPDATE OrderHistory SET OrderHistoryStatus = CASE WHEN OrderHistoryStatus = 0 THEN 1 ELSE 0 END WHERE orderHistoryId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderHistoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getPaymentMethodByOrderID(int orderId) {
        String sql = "SELECT PaymentMethod FROM OrderHistory WHERE OrderId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("PaymentMethod");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insertOrderHistory(double quantity, String orderDate, String name, String phone, String address,
            int userId, int roleId, double totalMoney, String isSuccess, String orderHisStatus, String payment) {
        try {
            String sql = """
                     INSERT INTO OrderHistory (Quantity, OrderDate, Name, Phone, Address, UserId, RoleId, TotalMoney, IsSuccess, OrderHistoryStatus, PaymentMethod)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, quantity);
            ps.setString(2, orderDate);
            ps.setString(3, name);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setInt(6, userId);
            ps.setInt(7, roleId);
            ps.setDouble(8, totalMoney);
            ps.setString(9, isSuccess);
            ps.setString(10, orderHisStatus);
            ps.setString(11, payment);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getOrderHistoryId() {
        try {
            String sql = "SELECT TOP(1) OrderHistoryID FROM [OrderHistory] ORDER BY OrderHistoryID DESC";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderHistoryID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public OrderHistory getOrderHisByOrderHisId(int orderHisId) {
        OrderHistory orderHistory = null;
        try {
            String sql = "SELECT Quantity, OrderDate, Name, Phone, Address, UserId, RoleId, TotalMoney, IsSuccess, OrderHistoryStatus, PaymentMethod FROM OrderHistory WHERE OrderHistoryID = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderHisId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderHistory = new OrderHistory();
                orderHistory.setQuantity(rs.getInt("Quantity"));
                orderHistory.setOrderDate(rs.getString("OrderDate"));
                orderHistory.setName(rs.getString("Name"));
                orderHistory.setPhone(rs.getString("Phone"));
                orderHistory.setAddress(rs.getString("Address"));
                orderHistory.setUserId(rs.getInt("UserId"));
                orderHistory.setRoleId(rs.getInt("RoleId"));
                orderHistory.setTotalMoney(rs.getDouble("TotalMoney"));
                orderHistory.setIsSuccess(rs.getString("IsSuccess"));
                orderHistory.setOrderHisStatus(rs.getString("OrderHistoryStatus"));
                orderHistory.setPaymentMethod(rs.getString("PaymentMethod"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderHistory;
    }

    public void updateIsSuccessById(int userId, int roleId) {
        try {
            String sql = """
                     UPDATE OrderHistory
                     SET IsSuccess = ?
                     WHERE UserId = ? AND RoleId = ?""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "Succeed");
            ps.setInt(2, userId);
            ps.setInt(3, roleId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTimesOfUserId(int id, int roleId) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS OrderCount FROM dbo.[OrderHistory] WHERE UserId = ? AND RoleId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("OrderCount");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public String getAddressOrder(int id, int roleId) {
        String address = null;
        try {
            String sql = "SELECT TOP 1 Address FROM dbo.[OrderHistory] WHERE UserId = ? AND RoleId = ? ORDER BY OrderHistoryId DESC";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = rs.getString("Address");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return address;
    }

    public Map<Integer, Integer> getProductQuantities(int orderHistoryId) {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        String sql = "SELECT ProductId, COUNT(*) AS quantity FROM OrderHistoryDetail WHERE OrderHistoryId = ? GROUP BY ProductId";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderHistoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductId");
                    int quantity = rs.getInt("quantity");
                    productQuantities.put(productId, quantity);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productQuantities;
    }

    public static void main(String[] args) {
        OrderHistoryDAO dao = new OrderHistoryDAO();
        Map<Integer, Integer> productQuantities = dao.getProductQuantities(11);
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            System.out.println("ProductId: " + entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }
}
