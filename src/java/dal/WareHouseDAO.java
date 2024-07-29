/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author DELL DN
 */
public class WareHouseDAO extends DBContext {

    public boolean checkExistedSerialId(String serialNumber) {
        boolean checkExists = false;
        try {
            String sql = "SELECT COUNT(*) FROM [dbo].[Warehouse] WHERE SerialNumber = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, serialNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkExists = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return checkExists;
    }
    public void insertWarehouse(String serialNumber, int productId) {
        try {
            String sql = """
                     INSERT INTO [Warehouse] (SerialNumber, ProductId)
                     VALUES (?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, serialNumber);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WareHouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        WareHouseDAO w = new WareHouseDAO();
        w.insertWarehouse("LA120", 3);
    }
}
