/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductQuality;
import model.Warranty;

/**
 *
 * @author quanb
 */
public class WarrantyDAO extends DBContext {
    public List<ProductQuality> getProductQualityList() {
        List<ProductQuality> list = new ArrayList<>();
        String query = "SELECT ProductName, UserName, COUNT(*) AS WarrantyCount, "
                     + "(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Warranty)) AS WarrantyRatio, "
                     + "CASE WHEN COUNT(*) >= 3 THEN 'Blacklist' ELSE 'Normal' END AS Status "
                     + "FROM Warranty "
                     + "GROUP BY ProductName, UserName";
        try (Connection conn = DBContext();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductQuality pq = new ProductQuality();
                pq.setProductName(rs.getString("ProductName"));
                pq.setUserName(rs.getString("UserName"));
                pq.setWarrantyCount(rs.getInt("WarrantyCount"));
                pq.setWarrantyRatio((float) rs.getDouble("WarrantyRatio"));
                pq.setStatus(rs.getString("Status"));
                list.add(pq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to fetch the blacklist
    public List<ProductQuality> getBlacklist() {
        List<ProductQuality> list = new ArrayList<>();
        String query = "SELECT ProductName, UserName, "
             + "COUNT(*) AS WarrantyCount, "
             + "(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Warranty)) AS WarrantyRatio, "
             + "CASE WHEN COUNT(*) >= 3 THEN 'Blacklist' ELSE 'Normal' END AS Status "
             + "FROM Warranty "
             + "GROUP BY ProductName, UserName "
             + "HAVING COUNT(*) >= 3;";

        try (Connection conn = DBContext();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductQuality pq = new ProductQuality();
                pq.setProductName(rs.getString("ProductName"));
                pq.setUserName(rs.getString("UserName"));
                pq.setWarrantyCount(rs.getInt("WarrantyCount"));
                pq.setWarrantyRatio((float) rs.getDouble("WarrantyRatio"));
                pq.setStatus(rs.getString("Status"));
                list.add(pq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Date getOrderDate(int orderHistoryDetailId) throws SQLException, ParseException {
        String sql = "SELECT oh.orderDate FROM OrderHistory oh JOIN OrderHistoryDetail ohd ON oh.orderHistoryId = ohd.orderHistoryId WHERE ohd.orderHistoryDetailId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderHistoryDetailId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String orderDateString = rs.getString("orderDate");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày của bạn
                    return new Date(formatter.parse(orderDateString).getTime());
                }
            }
        }
        return null;
    }
    public void updateSuccessStatus1(int id, String newValue, int adminid, String adminName) {
        String sql = "UPDATE Warranty SET isSucsess = ?, AdminID = ?, AdminName = ? WHERE WarrantyId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newValue);
            ps.setInt(2, adminid);
            ps.setString(3, adminName);        
            ps.setInt(4, id);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void updateSuccessStatus(int id, String newValue) {
        String sql = "UPDATE Warranty SET isSucsess = ? WHERE WarrantyId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newValue);           
            ps.setInt(2, id);   
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkOrderHistoryDetail(int orderHistoryDetailId, String serialNum) {
        boolean exists = false;
        try {
            String sql = "SELECT * FROM OrderHistoryDetail WHERE OrderHistoryDetailId = ? AND SerialNumber = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderHistoryDetailId);
            ps.setString(2, serialNum);
            ResultSet rs = ps.executeQuery();
            exists = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void saveWarranty(String productName, String warrantyImage, String warrantyPeriod, int orderHistoryDetailId, String username, int userID, String phoneNumber, String email, String serialNumber, String causeError) {
        String sql = "INSERT INTO Warranty (ProductName, WarrantyImage, WarrantyPeriod, OrderHistoryDetailId, UserName, UserID, isSucsess, PhoneNumber, Email, SerialNumber, ErrorBy) VALUES (?, ?, ?, ?, ?, ?, 'Wait', ?, ?, ?, ?)";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setString(2, warrantyImage);
            ps.setString(3, warrantyPeriod);
            ps.setInt(4, orderHistoryDetailId);
            ps.setString(5, username);
            ps.setInt(6, userID);
            ps.setString(7, phoneNumber);
            ps.setString(8, email);
            ps.setString(9, serialNumber);
            ps.setString(10, causeError);
            ps.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    public List<Warranty> pagingWarranty(int pageIndex, int pageSize) {
        List<Warranty> list = new ArrayList<>();
        String sql = "Select * from Warranty \n"
                + "order by WarrantyId\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            // Calculate the offset
            int offset = (pageIndex - 1) * pageSize;
            // Set the parameters for offset and fetch
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Warranty warranty = new Warranty();
                warranty.setWarrantyId(rs.getInt("WarrantyId"));
                warranty.setProductName(rs.getString("ProductName"));
                warranty.setWarrantyImage(rs.getString("WarrantyImage"));
                warranty.setWarrantyPeriod(rs.getString("WarrantyPeriod"));
                warranty.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
                warranty.setAdminID(rs.getInt("AdminId"));
                warranty.setIsSucsess(rs.getString("IsSucsess"));
                warranty.setUserID(rs.getInt("UserID"));
                warranty.setUserName(rs.getString("UserName"));
                warranty.setAdminName(rs.getString("AdminName"));
                warranty.setPhoneNumber(rs.getString("PhoneNumber"));
                warranty.setEmail(rs.getString("Email"));
                warranty.setSerialNumber(rs.getString("SerialNumber"));
                warranty.setCauseError(rs.getString("ErrorBy"));
                list.add(warranty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Warranty getWarrantyByWarrantyID(int id) {
  Warranty warranty = null;
  String sql = "Select * from Warranty where WarrantyId = ?";
  try {
    Connection con = DBContext();
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      warranty = new Warranty();
      warranty.setWarrantyId(rs.getInt("WarrantyId"));
      warranty.setProductName(rs.getString("ProductName"));
      warranty.setWarrantyImage(rs.getString("WarrantyImage"));
      warranty.setWarrantyPeriod(rs.getString("WarrantyPeriod"));
      warranty.setOrderHistoryDetailId(rs.getInt("OrderHistoryDetailId"));
      warranty.setAdminID(rs.getInt("AdminId"));
      warranty.setIsSucsess(rs.getString("IsSucsess"));
      warranty.setUserID(rs.getInt("UserID"));
      warranty.setUserName(rs.getString("UserName"));
      warranty.setAdminName(rs.getString("AdminName"));
      warranty.setPhoneNumber(rs.getString("PhoneNumber"));
      warranty.setEmail(rs.getString("Email"));
      warranty.setSerialNumber(rs.getString("SerialNumber"));
      warranty.setCauseError(rs.getString("ErrorBy"));
    }
  } catch (SQLException ex) {
    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
  }
  return warranty;
}

    public static void main(String[] args) {
        String productName = "Product A";
        String serialNumber = "SE1771";
        String causeError = "Ngu";
        String warrantyImage = "image_url_here";
        String warrantyPeriod = "1968/10/10";
        String email= "quanbarca2003@gmail.com";
        int orderHistoryDetailId = 1;
        int userID = 3;
        String phoneNumber = "01010203045";
        String userName = "Do Anh Quan";
        WarrantyDAO wdao = new WarrantyDAO();
        wdao.saveWarranty(productName, warrantyImage, warrantyPeriod, orderHistoryDetailId, userName, userID, phoneNumber, email, serialNumber, causeError);
    }
//    public static void main(String[] args) {
//        WarrantyDAO dao = new WarrantyDAO();
//        int pageIndex = 1; // trang đầu tiên
//        int pageSize = 3;  // số lượng bản ghi mỗi trang
//
//        List<Warranty> warranties = dao.pagingWarranty(pageIndex, pageSize);
//
//        for (Warranty warranty : warranties) {
//            System.out.println("Warranty ID: " + warranty.getWarrantyId());
//            System.out.println("Product Name: " + warranty.getProductName());
//            System.out.println("Warranty Image: " + warranty.getWarrantyImage());
//            System.out.println("Warranty Period: " + warranty.getWarrantyPeriod());
//            System.out.println("Order History Detail ID: " + warranty.getOrderHistoryDetailId());
//            System.out.println("Admin ID: " + warranty.getAdminID());
//            System.out.println("Is Success: " + warranty.getIsSucsess());
//            System.out.println("User ID: " + warranty.getUserID());
//            System.out.println("User Name: " + warranty.getUserName());
//            System.out.println("--------------------------------");
//        }
//    }
}
