/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.AdminCustomerRoleView;
import static dal.DBContext.DBContext;
import Utils.Encryptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author hungp
 */
public class AdminCustomerRoleViewDAO extends DBContext {

    public boolean checkEmail(String email) {
        boolean checkRegister = false;
        try {
            String sql = "SELECT COUNT(*) FROM AdminCustomerRoleView WHERE Email = ?";
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

    public List<AdminCustomerRoleView> getAllPaging(int pageIndex, int pageSize) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView "
                    + "ORDER BY ID "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            st = con.prepareStatement(sql);
            st.setInt(1, (pageIndex - 1) * pageSize);
            st.setInt(2, pageSize);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public byte[] getImageData(int id, int roleId) {
        String sql = "select avatar from AdminCustomerRoleView Where id = ? AND RoleId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("avatar");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public List<AdminCustomerRoleView> getAllSortedBy(String sortBy) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        List<String> allowedSortColumns = Arrays.asList(
                "ID", "Type", "Name", "Age", "Email", "Password", "Gender", "Address", "City",
                "Avatar", "PhoneNumber", "DOB", "Status", "RoleId", "RoleName", "RoleStatus"
        );

        if (!allowedSortColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid column name for sorting: " + sortBy);
        }

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView ORDER BY " + sortBy;
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

//    public List<AdminCustomerRoleView> getAllByCondition(String condition, int pageIndex, int pageSize) throws SQLException {
//        List<AdminCustomerRoleView> list = new ArrayList<>();
//        PreparedStatement st = null;
//        ResultSet rs = null;
//
//        try {
//            Connection con = DBContext();
//            String sql = "SELECT * FROM AdminCustomerRoleView WHERE " + condition + " ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
//            st = con.prepareStatement(sql);
//            st.setInt(1, (pageIndex - 1) * pageSize);
//            st.setInt(2, pageSize);
//            rs = st.executeQuery();
//
//            while (rs.next()) {
//                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
//                        rs.getInt("ID"),
//                        rs.getString("Type"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Email"),
//                        rs.getString("Password"),
//                        rs.getBoolean("Gender"),
//                        rs.getString("Address"),
//                        rs.getString("City"),
//                        rs.getBytes("Avatar"),
//                        rs.getString("PhoneNumber"),
//                        rs.getDate("DOB"),
//                        rs.getBoolean("Status"),
//                        rs.getInt("RoleId"),
//                        rs.getString("RoleName"),
//                        rs.getBoolean("RoleStatus")
//                );
//                list.add(adminCustomerRoleView);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//        }
//        return list;
//    }
    public List<AdminCustomerRoleView> getAllByCondition(String condition) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE " + condition;
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public AdminCustomerRoleView userLogin(String email, String hashedPassword) throws SQLException {
        AdminCustomerRoleView adminCustomerRoleView = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE Email = ? AND Password = ?";
            st = con.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, hashedPassword);
            rs = st.executeQuery();

            if (rs.next()) {
                adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
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
        return adminCustomerRoleView;
    }

    public AdminCustomerRoleView getUserByIDAndRoleID(int id, int roleId) throws SQLException {
        AdminCustomerRoleView adminCustomerRoleView = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE ID = ? AND RoleId = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, roleId);
            rs = st.executeQuery();

            if (rs.next()) {
                adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
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
        return adminCustomerRoleView;
    }

    public int getTotalUser() throws SQLException {
        int totalUsers = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT COUNT(*) AS total FROM AdminCustomerRoleView";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();

            if (rs.next()) {
                totalUsers = rs.getInt("total");
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
        return totalUsers;
    }

    public List<AdminCustomerRoleView> getAllByRoleName(String roleName) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE RoleName LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, roleName);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public List<AdminCustomerRoleView> getAllByGender(int gender) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE Gender = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, gender);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public List<AdminCustomerRoleView> getAllByStatus(int status) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE Status = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, status);
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public List<AdminCustomerRoleView> getAllByNameSearch(String nameSearch) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE Name LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + nameSearch + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public List<AdminCustomerRoleView> getAllByEmailSearch(String emailSearch) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE Email LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + emailSearch + "%"); // Using '%' to match any substring
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public List<AdminCustomerRoleView> getAllByPhoneNumberSearch(String phoneNumberSearch) throws SQLException {
        List<AdminCustomerRoleView> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            Connection con = DBContext();
            String sql = "SELECT * FROM AdminCustomerRoleView WHERE PhoneNumber LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + phoneNumberSearch + "%"); // Using '%' to match any substring
            rs = st.executeQuery();

            while (rs.next()) {
                AdminCustomerRoleView adminCustomerRoleView = new AdminCustomerRoleView(
                        rs.getInt("ID"),
                        rs.getString("Type"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("Gender"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getBytes("Avatar"),
                        rs.getString("PhoneNumber"),
                        rs.getDate("DOB"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                list.add(adminCustomerRoleView);
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

    public String getNameByUserIDAndRoleID(int userID, int roleID) throws SQLException {
        String sql = "SELECT Name FROM AdminCustomerRoleView WHERE ID = ? AND RoleId = ?";
        PreparedStatement st = null;
        ResultSet rs = null;
        String name = null;

        try {
            Connection con = DBContext();
            st = con.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, roleID);
            rs = st.executeQuery();

            if (rs.next()) {
                name = rs.getString("Name");
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

        return name;
    }

    public static void main(String[] args) throws SQLException {
        AdminCustomerRoleViewDAO acrDAO = new AdminCustomerRoleViewDAO();
        byte[] getAll = acrDAO.getImageData(1, 2);
        System.out.println(getAll);
    }
}
