/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ImageProduct {
    private int imageId;
    private int productId;
    private String image;

    public ImageProduct() {
    }

    public ImageProduct(int imageId, int productId, String image) {
        this.imageId = imageId;
        this.productId = productId;
        this.image = image;
    }

    public ImageProduct(int productId, String image) {
        this.productId = productId;
        this.image = image;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
