/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import static DAL.DBContext.DBContext;
import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author hungp
 */
public class CustomerDAO extends DBContext {
    
    public boolean checkEmail(String email) {
        boolean checkRegister = false;
        try {
            String sql = "SELECT COUNT(*) FROM customer WHERE customerEmail = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkRegister = true;
                }
            }
        } catch (SQLException ex) {
        }
        return checkRegister;
    }
    
    public Customer getCustomerById(int id) {
        try {
            String sql = "select * from customer where CustomerId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerID"));
            customer.setCustomerName(rs.getString("customerName"));
            customer.setCustomerAge(rs.getInt("customerAge"));
            customer.setCustomerEmail(rs.getString("customerEmail"));
            customer.setCustomerPassword(rs.getString("customerPassword"));
            customer.setCustomerGender(rs.getBoolean("customerGender"));
            customer.setCustomerAddress(rs.getString("customerAddress"));
            customer.setCustomerCity(rs.getString("customerCity"));
            customer.setCustomerAvatar(rs.getString("customerAvatar"));
            customer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
            customer.setCustomerDOB(rs.getDate("CustomerDOB"));
            customer.setCustomerStatus(rs.getBoolean("customerStatus"));
            return customer;

        } catch (SQLException ex) {
        }
        return null;

    }
    
    public void updateCustomer(Customer customer) {
        try {
            String sql = "update customer set CustomerName = ?, CustomerAge = ?, CustomerEmail = ?, CustomerGender = ?, CustomerAddress = ?, CustomerCity = ?, CustomerPhoneNumber = ?, CustomerDOB = ? Where CustomerID = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName()); 
            ps.setInt(2, customer.getCustomerAge()); 
            ps.setString(3, customer.getCustomerEmail()); 
            ps.setBoolean(4, customer.isCustomerGender()); 
            ps.setString(5, customer.getCustomerAddress());
            ps.setString(6, customer.getCustomerCity());
            ps.setString(7, customer.getCustomerPhoneNumber());
            ps.setDate(8, new java.sql.Date(customer.getCustomerDOB().getTime()));
            ps.setInt(9, customer.getCustomerId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }
    
    
    public String checkPasswordExist(int id) {
        try {
            String sql = "select CustomerPassword from customer where customerId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CustomerPassword");
            }
        } catch (SQLException ex) {
        }
        return null;

    }

    public void updateUserPassword(Customer customer) {

        try {
            String sql = "update customer set customerPassword = ? Where customerId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerPassword());
            ps.setInt(2, customer.getCustomerId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }
    
    public String getEmailIdByCustomerId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = DBContext();
        try {
            if(connection != null) {
                st = connection.prepareStatement("SELECT CustomerEmail FROM [Customer] WHERE CustomerId = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
            }
            if(rs!=null && rs.next()) {
                return rs.getString("CustomerEmail");
            }
        } catch(SQLException e) {
            System.out.println(e);
        } 
        return null;
    }
    
    public static void main(String[] args) {
        CustomerDAO CusDao = new CustomerDAO();
        String customer  = CusDao.getEmailIdByCustomerId(9);
        boolean check = CusDao.checkEmail(customer);
        System.out.println(check);
    }
}