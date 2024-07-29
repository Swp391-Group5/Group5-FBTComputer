/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

public class CategoryDAO extends DBContext {
    // get all category

    public boolean updateCategoryStatus(int categoryId, boolean categoryStatus) {
        String sql = "UPDATE Category SET CategoryStatus = ? WHERE CategoryId = ?";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, categoryStatus);
            ps.setInt(2, categoryId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "Select * from Category";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setCategoryStatus(rs.getInt("categoryStatus"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // get category by id
    public Category getCategoryById(int categoryId) {

        try {
            String sql = "Select * from Category Where CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//                c = new Category();
//                c.setCategoryId(rs.getInt("categoryId"));
//                c.setCategoryName(rs.getString("categoryName"));
//                c.setCategoryStatus(rs.getInt("categoryStatus"));
                return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // check name cate 
    public Category checkNameCate(String categoryName) {

        try {
            String sql = "Select * from Category Where categoryName = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    // add category

public void insertCategory(Category category) {
    try (Connection con = DBContext();
         PreparedStatement ps = con.prepareStatement("INSERT INTO Category (CategoryName, CategoryStatus) VALUES (?, ?)")) {

        ps.setString(1, category.getCategoryName());
        ps.setInt(2, category.getCategoryStatus());
        
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Category inserted successfully.");
        } else {
            System.out.println("Failed to insert category.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, "Error inserting category", ex);
    }
}


    public List<Category> getCategoriesByName(String search) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE categoryName LIKE ?";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + search + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("categoryStatus")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, "Error getting categories by name", ex);
        }

        return categories;
    }

    public void deleteCategory(int categoryId) {
        try {
            String sql = "DELETE FROM Category WHERE CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// update cate

    public void updateCategory(Category category) {
        try {
            String sql = "UPDATE Category SET CategoryName = ?  WHERE CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();

        // Example of adding a new category
        Category newCategory = new Category("New Categorysss", 1); // Assuming 1 is an active status
        categoryDAO.insertCategory(newCategory);

        // Example of retrieving all categories and printing them
        List<Category> categories = categoryDAO.getAllCategory();
        for (Category category : categories) {
            System.out.println("Category ID: " + category.getCategoryId());
            System.out.println("Category Name: " + category.getCategoryName());
            System.out.println("Category Status: " + category.getCategoryStatus());
            System.out.println("-----------------------");
        }
    }
   







  
    public int getTotalCategory() {
        int totalCategories = 0;
        String sql = "SELECT COUNT(*) AS TotalCategories FROM Category";

        try (Connection con = DBContext(); PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                totalCategories = rs.getInt("TotalCategories");
            }
        } catch (SQLException ex) {
        }
        return totalCategories;
    }

}
