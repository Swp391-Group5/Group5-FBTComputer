package dal;

import Cart.Cart;
import Cart.CartDetail;
import static dal.DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class CartDAO extends DBContext {

    public void insertCart(Cart cart) {
        try {
            String sql = "INSERT INTO Cart (CustomerId, TotalCost, RoleId) VALUES (?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cart.getCustomerId());
            ps.setDouble(2, cart.getAmount());
            ps.setInt(3, cart.getRoleId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCartTotalCost(double totalAmount, int cartId) {
        try {
            String sql = "UPDATE Cart SET TotalCost = ? WHERE CartId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, totalAmount);
            ps.setInt(2, cartId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCartId(int customerId, int roleId) {
        try {
            String sql = "select CartId from Cart where CustomerId = ? AND RoleId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("CartId");
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    public void insertCartDetail(CartDetail cartdetail, int cartId) {
        try {
            String sql = "INSERT INTO CartDetail (ProductId, ProductName, ProductImage, NumberInCart, Price, CartId) VALUES (?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartdetail.getProductId());
            ps.setString(2, cartdetail.getProductName());
            ps.setString(3, cartdetail.getProductImage());
            ps.setInt(4, cartdetail.getNumberInCart());
            ps.setDouble(5, cartdetail.getPrice());
            ps.setInt(6, cartId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Update detail Shopping Cart with 
    public void updateCartDetail(int numberInCart, int cartId, int productId) {
        try {
            String sql = "UPDATE CartDetail SET NumberInCart = ? WHERE CartId = ? AND ProductId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, numberInCart);
            ps.setInt(2, cartId);
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCartDetail(int productId, int cartId) {
        try {
            String sql = "DELETE FROM CartDetail WHERE ProductId = ? AND CartId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, cartId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCartDetailAfterCheckout(int cartId) {
        try {
            String sql = "DELETE FROM CartDetail WHERE CartId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCart(int cartId) {
        try {
            String sql = "DELETE FROM Cart WHERE CartId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkExistedCart(int customerId, int roleId) {
        boolean checkCustomer = false;
        try {
            String sql = "SELECT COUNT(*) FROM Cart WHERE CustomerId = ? AND RoleId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkCustomer = true;
                }
            }
        } catch (SQLException ex) {
        }
        return checkCustomer;
    }

    public boolean checkExistedCartDetail(int ProductId, int CartId) {
        boolean checkProduct = false;
        try {
            String sql = "SELECT COUNT(*) FROM CartDetail WHERE ProductId = ? AND CartId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ProductId);
            ps.setInt(2, CartId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkProduct = true;
                }
            }
        } catch (SQLException ex) {
        }
        return checkProduct;
    }

    public Cart getCartByCustomerId(int customerId, int roleId) {
        Cart cart = new Cart();
        try {
            String sql = "SELECT * FROM Cart WHERE CustomerId = ? AND RoleId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, roleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cart.setCartId(rs.getInt("CartId"));
                cart.setCustomerId(rs.getInt("CustomerId"));
                cart.setTotalCost(rs.getDouble("TotalCost"));
                cart.setRoleId(rs.getInt("RoleId"));
                cart.setIsSaved(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cart;
    }

    public List<Product> getCartDetailsByCartId(int cartId) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = """
                         SELECT CartDetail.*, Product.ProductQuantity
                         FROM dbo.CartDetail
                         LEFT JOIN dbo.Product ON CartDetail.ProductId = Product.ProductId WHERE CartId = ?""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();

                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setNumberInCart(rs.getInt("NumberInCart"));
                product.setProductPrice(rs.getFloat("Price"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        CartDAO cart = new CartDAO();
        List<Product> p = cart.getCartDetailsByCartId(22);
        //Cart d = cart.getCartByCustomerId(3);
        System.out.println(p);
    }
}
