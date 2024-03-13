/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@ToString
public class Product {
    private int productId;
    private String productName;
    private double price;
    private boolean status;
    private Date createDate;
    private Category category;
    private String Description;
    private List<ImageProduct> images;

    
    public Product() {
    }

    public Product(int productId, String productName, double price, boolean status, Date createDate, Category category, String Description, List<ImageProduct> images) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.createDate = createDate;
        this.category = category;
        this.Description = Description;
        this.images = images;
    }

    public Product(int productId, String productName, double price, boolean status, Date createDate, Category category, String Description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.status = status;
        this.createDate = createDate;
        this.category = category;
        this.Description = Description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public List<ImageProduct> getImages() {
        return images;
    }

    public void setImages(List<ImageProduct> images) {
        this.images = images;
    }

    
}
