/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import model.Admin;
import model.AdminWithRole;
import model.Customer;
import model.Role;
import Utils.Encryptor;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admins;

/**
 *
 * @author hungp
 */
public class AdminDAO extends DBContext {

    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT AdminId, AdminName FROM Admin";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("AdminId"));
                admin.setAdminName(rs.getString("AdminName"));
                admins.add(admin);
            }
        } catch (SQLException ex) {
            System.out.println("Get all admins error: " + ex);
        }
        return admins;
    }

    public String getAdminEmail(int id) {
        String email = null;
        String sql = "SELECT AdminEmail FROM Admin WHERE AdminID = ?";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("AdminEmail");
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
            String sql = "SELECT COUNT(*) FROM admin WHERE adminEmail = ?";
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

    public Admin AdminLogin(String adminEmail, String adminPassword) {
        Admin admin = null;
        try {
            String sql = "Select * from Admin where AdminEmail= ? and AdminPassword= ? and AdminStatus = 1";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminEmail);
            ps.setString(2, adminPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("AdminID"));
                admin.setAdminName(rs.getString("AdminName"));
                admin.setAdminAge(rs.getInt("AdminAge"));
                admin.setAdminEmail(rs.getString("AdminEmail"));
                admin.setAdminPassword(rs.getString("AdminPassword"));
                admin.setAdminGender(rs.getBoolean("AdminGender"));
                admin.setAdminAddress(rs.getString("AdminAddress"));
                admin.setAdminCity(rs.getString("AdminCity"));
                admin.setAdminAvatar(rs.getBytes("AdminAvatar"));
                admin.setAdminPhoneNumber(rs.getString("AdminPhoneNum"));
                admin.setAdminDOB(rs.getDate("AdminDOB"));
                admin.setAdminStatus(rs.getBoolean("AdminStatus"));
                admin.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
        }
        return admin;
    }

    public Admin getAdminById(int id) {
        try {
            String sql = "select * from admin where AdminId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("adminID"));
            admin.setAdminName(rs.getString("adminName"));
            admin.setAdminAge(rs.getInt("adminAge"));
            admin.setAdminEmail(rs.getString("adminEmail"));
            admin.setAdminPassword(rs.getString("adminPassword"));
            admin.setAdminGender(rs.getBoolean("adminGender"));
            admin.setAdminAddress(rs.getString("adminAddress"));
            admin.setAdminCity(rs.getString("adminCity"));
            admin.setAdminAvatar(rs.getBytes("adminAvatar"));
            admin.setAdminPhoneNumber(rs.getString("adminPhoneNum"));
            admin.setAdminDOB(rs.getDate("AdminDOB"));
            admin.setAdminStatus(rs.getBoolean("adminStatus"));
            return admin;

        } catch (SQLException ex) {
        }
        return null;

    }

    public void updateAdmin(Admin admin) {
        try {
            String sql = "update admin set AdminName = ?, AdminAge = ?, AdminEmail = ?, AdminGender = ?, AdminAddress = ?, AdminCity = ?, AdminPhoneNum = ?, AdminDOB = ? Where AdminID = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getAdminName());
            ps.setInt(2, admin.getAdminAge());
            ps.setString(3, admin.getAdminEmail());
            ps.setBoolean(4, admin.isAdminGender());
            ps.setString(5, admin.getAdminAddress());
            ps.setString(6, admin.getAdminCity());
            ps.setString(7, admin.getAdminPhoneNumber());
            ps.setDate(8, new java.sql.Date(admin.getAdminDOB().getTime()));
            ps.setInt(9, admin.getAdminId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }

    public String checkPasswordExist(int id) {
        try {
            String sql = "select AdminPassword from admin where adminId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("AdminPassword");
            }
        } catch (SQLException ex) {
        }
        return null;

    }

    public void updateUserPassword(Admin admin) {

        try {
            String sql = "update admin set adminPassword = ? Where adminId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin.getAdminPassword());
            ps.setInt(2, admin.getAdminId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }

    public byte[] getImageData(int id) {
        String sql = "select AdminAvatar from Admin Where Adminid = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("adminAvatar");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public boolean updateUserAvatar(InputStream inputStream, int id) {
        String sql = "update Admin set AdminAvatar = ? Where AdminId = ?";
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

    public List<AdminWithRole> getAllAdmins() throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            st = con.prepareStatement("SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId;");
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsByName(String searchQuery) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    A.AdminName LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsByEmail(String searchQuery) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    A.AdminEmail LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsByPhone(String searchQuery) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    A.AdminPhoneNum LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsByGender(int gender) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    A.AdminGender = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, gender);
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsByStatus(int status) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    A.AdminStatus = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, status);
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public List<AdminWithRole> getAdminsSortBy(String sortBy) throws SQLException {
        List<AdminWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        // List of allowed columns to sort by
        List<String> allowedSortColumns = Arrays.asList(
                "AdminID", "AdminName", "AdminAge", "AdminEmail", "AdminPassword",
                "AdminGender", "AdminAddress", "AdminCity", "AdminAvatar",
                "AdminPhoneNum", "AdminDOB", "AdminStatus", "RoleId", "RoleName", "RoleStatus"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    A.AdminID, \n"
                    + "    A.AdminName, \n"
                    + "    A.AdminAge, \n"
                    + "    A.AdminEmail, \n"
                    + "    A.AdminPassword, \n"
                    + "    A.AdminGender, \n"
                    + "    A.AdminAddress, \n"
                    + "    A.AdminCity, \n"
                    + "    A.AdminAvatar, \n"
                    + "    A.AdminPhoneNum, \n"
                    + "    A.AdminDOB, \n"
                    + "    A.AdminStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Admin A\n"
                    + "JOIN \n"
                    + "    [Role] R ON A.RoleId = R.RoleId\n"
                    + "ORDER BY\n"
                    + "    A." + sortBy;  // dynamically appending the column name

            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getInt("AdminID"),
                        rs.getString("AdminName"),
                        rs.getInt("AdminAge"),
                        rs.getString("AdminEmail"),
                        rs.getString("AdminPassword"),
                        rs.getBoolean("AdminGender"),
                        rs.getString("AdminAddress"),
                        rs.getString("AdminCity"),
                        rs.getBytes("AdminAvatar"),
                        rs.getString("AdminPhoneNum"),
                        rs.getDate("AdminDOB"),
                        rs.getBoolean("AdminStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                AdminWithRole adminWithRole = new AdminWithRole(admin, role);
                list.add(adminWithRole);
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

    public boolean deleteAdminAndAddToCustomer(int adminId, boolean status, int roleid, Customer customer) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBContext();
            conn.setAutoCommit(false); // Start transaction

            // Retrieve admin details
            String queryGetAdmin = "SELECT * FROM Admin WHERE AdminID = ?";
            ps = conn.prepareStatement(queryGetAdmin);
            ps.setInt(1, adminId);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false; // Admin not found
            }

            // Set customer properties from admin details
            customer.setCustomerName(rs.getString("AdminName"));
            customer.setCustomerAge(rs.getInt("AdminAge"));
            customer.setCustomerEmail(rs.getString("AdminEmail"));
            customer.setCustomerPassword(rs.getString("AdminPassword"));
            customer.setCustomerGender(rs.getBoolean("AdminGender"));
            customer.setCustomerAddress(rs.getString("AdminAddress"));
            customer.setCustomerCity(rs.getString("AdminCity"));
            customer.setCustomerAvatar(rs.getBytes("AdminAvatar"));
            customer.setCustomerPhoneNumber(rs.getString("AdminPhoneNum"));
            customer.setCustomerDOB(rs.getDate("AdminDOB"));
            customer.setCustomerStatus(status);
            customer.setRoleId(roleid);

            // Delete admin
            String queryDeleteAdmin = "DELETE FROM Admin WHERE AdminID = ?";
            ps = conn.prepareStatement(queryDeleteAdmin);
            ps.setInt(1, adminId);
            ps.executeUpdate();

            // Add admin as customer
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.addCustomer(conn, customer);

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
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Admin getAdminByIdAndRoleID(int id, int roleId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Admin WHERE AdminId = ? AND RoleID = ?";
            con = DBContext();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setAdminName(rs.getString("adminName"));
                admin.setAdminAge(rs.getInt("adminAge"));
                admin.setAdminEmail(rs.getString("adminEmail"));
                admin.setAdminPassword(rs.getString("adminPassword"));
                admin.setAdminGender(rs.getBoolean("adminGender"));
                admin.setAdminAddress(rs.getString("adminAddress"));
                admin.setAdminCity(rs.getString("adminCity"));
                admin.setAdminAvatar(rs.getBytes("adminAvatar"));
                admin.setAdminPhoneNumber(rs.getString("adminPhoneNum"));
                admin.setAdminDOB(rs.getDate("adminDOB"));
                admin.setAdminStatus(rs.getBoolean("adminStatus"));
                admin.setRoleId(rs.getInt("roleId"));
                return admin;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean updateAdminStatus(int adminId, boolean status) {
        String sql = "UPDATE Admin SET AdminStatus = ? WHERE AdminID = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, status);
            ps.setInt(2, adminId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void addAdmin(Connection conn, Admin admin) throws SQLException {
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO Admin (AdminName, AdminAge, AdminEmail, AdminPassword, AdminGender, AdminAddress, AdminCity, AdminAvatar, AdminPhoneNum, AdminDOB, AdminStatus, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, admin.getAdminName());
            ps.setInt(2, admin.getAdminAge());
            ps.setString(3, admin.getAdminEmail());
            ps.setString(4, admin.getAdminPassword());
            ps.setBoolean(5, admin.isAdminGender());
            ps.setString(6, admin.getAdminAddress());
            ps.setString(7, admin.getAdminCity());
            ps.setBytes(8, admin.getAdminAvatar());
            ps.setString(9, admin.getAdminPhoneNumber());
            ps.setDate(10, new java.sql.Date(admin.getAdminDOB().getTime()));
            ps.setBoolean(11, admin.isAdminStatus());
            ps.setInt(12, admin.getRoleId());

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void InsertNewAdmin(Admin admin) throws SQLException {
        PreparedStatement ps = null;

        try {
            String query = "INSERT INTO Admin (AdminEmail, AdminPassword, AdminName, AdminGender, AdminPhoneNum, AdminDOB, AdminAge ,AdminCity, AdminAddress, RoleId, AdminStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            ps = con.prepareStatement(query);
            ps.setString(1, admin.getAdminEmail());
            ps.setString(2, admin.getAdminPassword());
            ps.setString(3, admin.getAdminName());
            ps.setBoolean(4, admin.isAdminGender());
            ps.setString(5, admin.getAdminPhoneNumber());
            ps.setDate(6, new java.sql.Date(admin.getAdminDOB().getTime()));
            ps.setInt(7, admin.getAdminAge());
            ps.setString(8, admin.getAdminCity());
            ps.setString(9, admin.getAdminAddress());
            ps.setInt(10, admin.getRoleId());
            ps.setBoolean(11, admin.isAdminStatus());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Admins AdminLogin1(String email, String password) {
        Admins admin = null;
        try {
            String sql = "Select * from Admin where AdminEmail= ? and AdminPassword= ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admins();
                admin.setAdminId(rs.getInt("AdminId"));
                admin.setAdminName(rs.getString("AdminName"));
                admin.setAdminAge(rs.getInt("AdminAge"));
                admin.setAdminEmail(rs.getString("AdminEmail"));
                admin.setAdminPassword(rs.getString("AdminPassword"));
                admin.setAdminGender(rs.getBoolean("AdminGender"));
                admin.setAdminAddress(rs.getString("AdminAddress"));
                admin.setAdminCity(rs.getString("AdminCity"));
                admin.setAdminAvatar(rs.getString("AdminAvatar"));
                admin.setAdminPhoneNumber(rs.getString("AdminPhoneNumber"));
                admin.setAdminDOB(rs.getDate("AdminDOB"));
                admin.setAdminStatus(rs.getBoolean("AdminStatus"));
                admin.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public static void main(String[] args) {
        AdminDAO admin = new AdminDAO();
        String c = admin.getAdminEmail(2);

        System.out.println(c);
    }
}
