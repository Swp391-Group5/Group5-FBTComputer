/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class Product {

    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productBrand;
    private String productImage;
    private boolean productStatus;
    private String createBy;
    private String createDate;
    private String modifyBy;
    private String  modifyDate;
    private int categoryId;
    private String image1;
    private String image2;
    private String image3;
    private Category category;
    private List<ProductImange> productImanges;
    private Specification specification;
    private int numberInCart;
    public Product() {
    }

    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.category = category;
    }

    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate, Specification specification) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.specification = specification;
    }

    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.categoryId = categoryId;
    }

    public Specification getSpecification() {
        return specification;
    }

    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate, int categoryId, String image1, String image2, String image3) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.categoryId = categoryId;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }
    public Product(int productId, String productName, float productPrice, int productQuantity, String productImage, int numberInCart) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.numberInCart = numberInCart;
    }
public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, String createDate, String modifyBy, String modifyDate, Category category, List<ProductImange> productImanges, Specification specification) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.category = category;
        this.productImanges = productImanges;
        this.specification = specification;
    }



    public Product(int productId, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, String modifyBy, String modifyDate, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.category = category;
    }
    public Product(int productId, String productName, float productPrice, int productQuantity, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductImange> getProductImanges() {
        return productImanges;
    }

    public void setProductImanges(List<ProductImange> productImanges) {
        this.productImanges = productImanges;
    }

   @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productBrand=" + productBrand + ", productImage=" + productImage + ", productStatus=" + productStatus + ", createBy=" + createBy + ", createDate=" + createDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", category=" + category + ", productImanges=" + productImanges + ", specification=" + specification + '}';
    }

}
