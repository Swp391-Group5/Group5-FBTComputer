/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cart;

/**
 *
 * @author DELL DN
 */
public class CartDetail {
    private int detailCartId;
    private int productId;
    private String productName;
    private String productImage;
    private int numberInCart;
    private double price;
    private int cartId;

    public CartDetail() {
    }

    public CartDetail(int productId, String productName, String productImage, int numberInCart, double price) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.numberInCart = numberInCart;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDetailCartId() {
        return detailCartId;
    }

    public void setDetailCartId(int detailCartId) {
        this.detailCartId = detailCartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "detailCartId=" + detailCartId + ", productId=" + productId + ", productImage=" + productImage + ", numberInCart=" + numberInCart + ", price=" + price + ", cartId=" + cartId + '}';
    }


    
    
}
