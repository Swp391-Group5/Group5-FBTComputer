/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderHistoryDetail;

/**
 *
 * @author DELL DN
 */
public class OrderHistoryDetailDAO extends DBContext {


    public List<OrderHistoryDetail> getOrderHistoryDetail(int orderHistoryId) {
        List<OrderHistoryDetail> details = new ArrayList<>();
        try {
            String sql = "SELECT OrderHistoryDetailId, SerialNumber, Price, ProductId FROM OrderHistoryDetail WHERE OrderHistoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderHistoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderHistoryDetailId = rs.getInt("OrderHistoryDetailId");
                String serialNumber = rs.getString("SerialNumber");
                double price = rs.getDouble("Price");
                int productId = rs.getInt("ProductId");
                //details.add(new OrderHistoryDetailDAO(id, serialNumber, price, productId));
                details.add(new OrderHistoryDetail(orderHistoryDetailId, serialNumber, price, productId));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return details;
    }

    public void insertOrderHistoryDetail(String serialNumber, double price, int orderHistoryId, int productId) {
        try {
            String sql = """
                     INSERT INTO [OrderHistoryDetail] (SerialNumber, Price, OrderHistoryId, ProductId)
                     VALUES (?, ?, ?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, serialNumber);
            ps.setDouble(2, price);
            ps.setInt(3, orderHistoryId);
            ps.setInt(4, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        OrderHistoryDetailDAO o = new OrderHistoryDetailDAO();
        List<OrderHistoryDetail> list = o.getOrderHistoryDetail(4);
        System.out.println(list);
    }
}
