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
import model.Slider;

/**
 *
 * @author admin
 */
public class SlideDAO extends DBContext {

    public Slider getFirstSlider() {
        String sql = "SELECT TOP 1 * FROM [dbo].[Slider] WHERE [status] = 1 ORDER BY [slider_id] ASC";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                // Retrieve data from ResultSet and create a Slider object
                int sliderId = rs.getInt("slider_id");
                String sliderTitle = rs.getString("slider_title");
                String sliderImage = rs.getString("slider_image");
                String backlink = rs.getString("backlink");
                String note = rs.getString("note");
                boolean status = rs.getBoolean("status");
                int updatedBy = rs.getInt("updated_by");

                Slider slider = new Slider(sliderId, sliderTitle, sliderImage, backlink, note, status, updatedBy);
                return slider;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching slider: " + e.getMessage());
        }

        return null; // Return null if no slider is found or an error occurs
    }

    public List<Slider> getALLSlider_True_False() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Slider]";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderId(rs.getInt("slider_id"));
                slider.setSliderTitle(rs.getString("slider_title"));
                slider.setSliderImage(rs.getString("slider_image"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setNote(rs.getString("note"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setUpdatedBy(rs.getInt("updated_by"));

                list.add(slider);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Slider GetSliderDetailbyID(int sliderId) {
        String sql = "SELECT * FROM [dbo].[Slider] WHERE slider_id = ?";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, sliderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderId(rs.getInt("slider_id"));
                slider.setSliderTitle(rs.getString("slider_title"));
                slider.setSliderImage(rs.getString("slider_image"));
                slider.setBacklink(rs.getString("backlink"));
                slider.setNote(rs.getString("note"));
                slider.setStatus(rs.getBoolean("status"));
                slider.setUpdatedBy(rs.getInt("updated_by"));

                return slider;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateStatusSlider(int id, boolean status) {
        String sql = "UPDATE [dbo].[Slider] SET status = ? WHERE slider_id = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void AddSliderById(String slider_title, String backlink, String url_thumbnail, int status) {
        String sql = "INSERT INTO [dbo].[Slider] (slider_title, slider_image, backlink, [status]) VALUES (?, ?, ?, ?)";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, slider_title);
            st.setString(2, url_thumbnail);
            st.setString(3, backlink);
            st.setInt(4, status);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(String sliderId) {
        String sql = "   DELETE FROM [dbo].[Slider]\n"
                + "      WHERE slider_id = ?";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, sliderId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void UpdateSliderById(int slider_id, String slider_title, String backlink, String url_thumbnail) {
        try {
            String sql = "UPDATE [dbo].[Slider]\n"
                    + "   SET [slider_title] = ?\n"
                    + "      ,[slider_image] = ?\n"
                    + "      ,[backlink] = ?\n"
                    + " WHERE slider_id = ?";
            Connection connection = DBContext();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, slider_title);
            st.setString(2, url_thumbnail);
            st.setString(3, backlink);
            st.setInt(4, slider_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String getUrlImageById(int id) {
        String sql = "SELECT slider_image FROM [dbo].[Slider] WHERE slider_id = ?";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("slider_image");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getcountSlider() {
        String sql = "SELECT count(*) FROM [dbo].[Slider] where [status] = 1";
        try {
            Connection con = DBContext();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public List<Slider> getALLSlider() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Slider] WHERE [status] = 1";
        try {
            Connection con = DBContext(); // Assuming getConnection() returns a valid Connection object
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("slider_id");
                String title = rs.getString("slider_title");
                String image = rs.getString("slider_image");
                String backlink = rs.getString("backlink");
                boolean status = rs.getBoolean("status");
                int updatedBy = rs.getInt("updated_by");

                Slider slider = new Slider();
                slider.setSliderId(id);
                slider.setSliderTitle(title);
                slider.setSliderImage(image);
                slider.setBacklink(backlink);
                slider.setStatus(status);
                slider.setUpdatedBy(updatedBy);

                list.add(slider);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }

    public static void main(String[] args) {
        // Create an instance of SlideDAO
        SlideDAO slideDAO = new SlideDAO();

        // Call getFirstSlider() to retrieve the first slider
        Slider slider = slideDAO.getFirstSlider();

        // Check if a slider was retrieved
        if (slider != null) {
            System.out.println("Slider found:");
            System.out.println("ID: " + slider.getSliderId());
            System.out.println("Title: " + slider.getSliderTitle());
            System.out.println("Image: " + slider.getSliderImage());
            System.out.println("Backlink: " + slider.getBacklink());
            System.out.println("Note: " + slider.getNote());
            System.out.println("Status: " + slider.isStatus());
            System.out.println("Updated by: " + slider.getUpdatedBy());
        } else {
            System.out.println("No active slider found.");
        }
    }
}
