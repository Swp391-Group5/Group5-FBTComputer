/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import model.Admin;
import model.Customer;
import model.CustomerWithRole;
import model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author hungp
 */
public class CustomerDAO extends DBContext {

    public String getCustomerEmail(int id) {
        String email = null;
        String sql = "SELECT CustomerEmail FROM Customer WHERE CustomerID = ?";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("CustomerEmail");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return email;
    }

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
            customer.setCustomerAvatar(rs.getBytes("customerAvatar"));
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
            if (connection != null) {
                st = connection.prepareStatement("SELECT CustomerEmail FROM [Customer] WHERE CustomerId = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
            }
            if (rs != null && rs.next()) {
                return rs.getString("CustomerEmail");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer CustomerLogin(String customerEmail, String customerPassword) {
        Customer customer = null;
        try {
            String sql = "Select * from Customer where CustomerEmail= ? and CustomerPassword= ? and CustomerStatus = 1";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerEmail);
            ps.setString(2, customerPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getBytes("CustomerAvatar"));
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
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getBytes("CustomerAvatar"));
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

    public void signup(String customerName, String customerEmail, String customerPassword) {
        try {
            String sql = "INSERT INTO Customer (CustomerName, CustomerEmail, CustomerPassword, CustomerStatus, RoleId) VALUES (?, ?, ?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerEmail);
            ps.setString(3, customerPassword);
            ps.setBoolean(4, true);
            ps.setInt(5, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getBytes("CustomerAvatar"));
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

    public byte[] getImageData(int id) {
        String sql = "select CustomerAvatar from Customer Where Customerid = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("customerAvatar");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public boolean updateUserAvatar(InputStream inputStream, int id) {
        String sql = "update Customer set CustomerAvatar = ? Where CustomerId = ?";
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

    public List<CustomerWithRole> getAllCustomers() throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            st = con.prepareStatement("SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId;");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersByName(String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerName LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersByEmail(String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerEmail LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersByPhone(String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerPhoneNumber LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersByGender(int gender) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerGender = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, gender);
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersByStatus(int status) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerStatus = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, status);
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public List<CustomerWithRole> getCustomersSortBy(String sortBy) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        // List of allowed columns to sort by
        List<String> allowedSortColumns = Arrays.asList(
                "CustomerID", "CustomerName", "CustomerAge", "CustomerEmail", "CustomerPassword",
                "CustomerGender", "CustomerAddress", "CustomerCity", "CustomerAvatar",
                "CustomerDOB", "CustomerStatus", "RoleId", "RoleName", "RoleStatus"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "	C.CustomerPhoneNumber,\n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "ORDER BY C." + sortBy;  // dynamically appending the column name

            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getBytes("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
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

    public boolean deleteCustomerAndAddToAdmin(int customerId, boolean status, int roleid, Admin admin) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBContext();
            conn.setAutoCommit(false); // Start transaction

            // Retrieve customer details
            String queryGetCustomer = "SELECT * FROM Customer WHERE CustomerID = ?";
            ps = conn.prepareStatement(queryGetCustomer);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false; // Customer not found
            }

            // Set admin properties from customer details
            admin.setAdminName(rs.getString("CustomerName"));
            admin.setAdminAge(rs.getInt("CustomerAge"));
            admin.setAdminEmail(rs.getString("CustomerEmail"));
            admin.setAdminPassword(rs.getString("CustomerPassword"));
            admin.setAdminGender(rs.getBoolean("CustomerGender"));
            admin.setAdminAddress(rs.getString("CustomerAddress"));
            admin.setAdminCity(rs.getString("CustomerCity"));
            admin.setAdminAvatar(rs.getBytes("CustomerAvatar"));
            admin.setAdminPhoneNumber(rs.getString("CustomerPhoneNumber"));
            admin.setAdminDOB(rs.getDate("CustomerDOB"));
            admin.setAdminStatus(status);
            admin.setRoleId(2);

            // Delete customer
            String queryDeleteCustomer = "DELETE FROM Customer WHERE CustomerID = ?";
            ps = conn.prepareStatement(queryDeleteCustomer);
            ps.setInt(1, customerId);
            ps.executeUpdate();

            // Add customer as admin
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.addAdmin(conn, admin);

            conn.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public Customer getCustomerByIdAndRoleID(int id, int roleId) {
        try {
            String sql = "SELECT * FROM customer WHERE CustomerId = ? AND RoleID = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customerID"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setCustomerAge(rs.getInt("customerAge"));
                customer.setCustomerEmail(rs.getString("customerEmail"));
                customer.setCustomerPassword(rs.getString("customerPassword"));
                customer.setCustomerGender(rs.getBoolean("customerGender"));
                customer.setCustomerAddress(rs.getString("customerAddress"));
                customer.setCustomerCity(rs.getString("customerCity"));
                customer.setCustomerAvatar(rs.getBytes("customerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("customerStatus"));
                return customer;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomerStatus(int customerId, boolean status) {
        String sql = "UPDATE customer SET CustomerStatus = ? WHERE CustomerId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, status);
            ps.setInt(2, customerId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void addCustomer(Connection conn, Customer customer) throws SQLException {
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO Customer (CustomerName, CustomerAge, CustomerEmail, CustomerPassword, CustomerGender, CustomerAddress, CustomerCity, CustomerAvatar, CustomerPhoneNumber, CustomerDOB, CustomerStatus, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setInt(2, customer.getCustomerAge());
            ps.setString(3, customer.getCustomerEmail());
            ps.setString(4, customer.getCustomerPassword());
            ps.setBoolean(5, customer.isCustomerGender());
            ps.setString(6, customer.getCustomerAddress());
            ps.setString(7, customer.getCustomerCity());
            ps.setBytes(8, customer.getCustomerAvatar());
            ps.setString(9, customer.getCustomerPhoneNumber());
            ps.setDate(10, new java.sql.Date(customer.getCustomerDOB().getTime()));
            ps.setBoolean(11, customer.isCustomerStatus());
            ps.setInt(12, customer.getRoleId());

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void InsertNewCustomer(Customer customer) throws SQLException {
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO Customer (CustomerEmail, CustomerPassword, CustomerName, CustomerGender, CustomerPhoneNumber, CustomerDOB, CustomerAge, CustomerCity, CustomerAddress, RoleId, CustomerStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            ps = con.prepareStatement(query);
            ps.setString(1, customer.getCustomerEmail());
            ps.setString(2, customer.getCustomerPassword());
            ps.setString(3, customer.getCustomerName());
            ps.setBoolean(4, customer.isCustomerGender());
            ps.setString(5, customer.getCustomerPhoneNumber());
            ps.setDate(6, new java.sql.Date(customer.getCustomerDOB().getTime()));
            ps.setInt(7, customer.getCustomerAge());
            ps.setString(8, customer.getCustomerCity());
            ps.setString(9, customer.getCustomerAddress());
            ps.setInt(10, customer.getRoleId());
            ps.setBoolean(11, customer.isCustomerStatus());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int getTotalCustomer() {
        int totalCustomers = 0;
        String sql = "SELECT COUNT(*) AS TotalCustomers FROM Customer";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalCustomers = rs.getInt("TotalCustomers");
            }
        } catch (SQLException ex) {
        }
        return totalCustomers;
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

    public static void main(String[] args) throws SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        boolean email = customerDAO.checkEmail("dawdwadawdwa");
        System.out.println(email);
    }
}
