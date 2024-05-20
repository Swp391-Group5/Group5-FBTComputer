package dal;

import static dal.DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author DELL DN
 */
public class CustomerDAO extends DBContext {

    public Customer CustomerLogin(String customerEmail, String customerPassword) {
        Customer customer = null;
        try {
            String sql = "Select * from Customer where CustomerEmail= ? and CustomerPassword= ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerEmail);
            ps.setString(2, customerPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public Customer checkAccountExist(String email) {
        Customer customer = null;
        try {
            String sql = "select * from Customer where CustomerEmail = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public void signup(String customerName, String customerEmail, String customerPassword, String customerCity, String customerPhoneNumber) {
        try {
            String sql = "INSERT INTO Customer (CustomerName, CustomerEmail, CustomerPassword, CustomerCity, CustomerPhoneNumber, CustomerStatus, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerEmail);
            ps.setString(3, customerPassword);
            ps.setString(4, customerCity);
            ps.setString(5, customerPhoneNumber);
            ps.setBoolean(6, true); // Assuming that the CustomerStatus is set to true for a new customer
            ps.setInt(7, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkEmail(String customerEmail) {
        boolean checkRegister = false;
        try {
            String sql = "SELECT COUNT(*) FROM Customer WHERE CustomerEmail = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkRegister = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkRegister;
    }

    public List<Customer> searchByEmail(String customerEmail) {
        List<Customer> list = new ArrayList<>();
        if (customerEmail == null || customerEmail.isEmpty()) {
            return list;
        }
        try {
            String sql = "SELECT * FROM Customer WHERE CustomerEmail LIKE ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + customerEmail + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
                list.add(customer);
            }
            rs.close(); // Close ResultSet
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updatePasswordCustomer(String customerPassword, String customerEmail) {

        try {
            String sql = "update Customer set  CustomerPassword = ? Where CustomerEmail = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerPassword);
            ps.setString(2, customerEmail);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        String customerName = "John Doe";
        String customerEmail = "john.doe@example.com";
        String customerPassword = "password123";
        String customerCity = "New York";
        String customerPhoneNumber = "1234567890";
        dao.signup(customerName, customerEmail, customerPassword, customerCity, customerPhoneNumber);
    }
}
