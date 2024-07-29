/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cart;

import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author DELL DN
 */
public class Cart {

    private int cartId;
    private int customerId;
    private double totalCost;
    private int roleId;
    private boolean isSaved;
    private List<Product> items;

    public Cart() {
        this.items = new ArrayList<>(); // NOT NULL
    }

    public Cart(List<Product> items) {
        this.items = items;
        this.isSaved = false;
    }

    public Cart(int customerId, double totalCost, int roleId) {
        this.customerId = customerId;
        this.totalCost = totalCost;
        this.roleId = roleId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public void addItems(List<Product> ci) {
        if (ci != null) {
            items.addAll(ci);
        }
    }

    public void adds(Product ci) {
        for (Product product : items) {
            if (ci.getProductId() == product.getProductId()) {
                if ((product.getNumberInCart() + 1) <= product.getProductQuantity()) {
                    product.setNumberInCart(product.getNumberInCart() + 1);
                }
                return;
            }
        }
        items.add(ci);
    }

    public void setNumInCart(Product ci) {
        for (Product product : items) {
            if (ci.getProductId() == product.getProductId()) {
                product.setNumberInCart(ci.getProductQuantity());
            }
            return;
        }
    }

    public int getNumInCart(Product ci) {
        for (Product product : items) {
            if (ci.getProductId() == product.getProductId()) {
                return product.getNumberInCart();
            }
        }
        return 0;
    }

    public void minus(Product ci) {
        for (Product product : items) {
            if (ci.getProductId() == product.getProductId()) {
                if (product.getNumberInCart() > 1) {
                    product.setNumberInCart(product.getNumberInCart() - 1);
                }
                return;
            }
        }
    }

    public void updateItem(int productId, int quantity) {
        for (Product product : items) {
            if (product.getProductId() == productId) {
                if (quantity <= 0) {
                    // Đặt giá trị numberInCart thành 1 làm mặc định khi quantity <= 0
                    product.setNumberInCart(1);
                } else if (quantity <= product.getProductQuantity()) {
                    // Nếu số lượng nhỏ hơn hoặc bằng số lượng sản phẩm có trong kho, cập nhật số lượng
                    product.setNumberInCart(quantity);
                } else {
                    // Nếu số lượng lớn hơn số lượng sản phẩm có trong kho, đặt số lượng giỏ hàng bằng số lượng trong kho
                    product.setNumberInCart(product.getProductQuantity());
                }
                return;
            }
        }
    }

    public void remove(int id) {
        for (Product product : items) {
            if (product.getProductId() == id) {
                items.remove(product);
                return;
            }
        }
    }

    public double getAmount() {
        double s = 0;
        for (Product product : items) {
            s += product.getProductPrice() * product.getNumberInCart();
        }
        return s;
    }

    public List<Product> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", customerId=" + customerId + ", totalCost=" + totalCost + '}';
    }

}
