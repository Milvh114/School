/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.ToString;

/**
 *
 * @author Admin
 */
@ToString
public class ProductDetail {
    private int productDetailId;
    private Size size;
    private Color color;
    private int quantity;
    private Product product;

    public ProductDetail(int productDetailId, Size size, Color color, int quantity, Product product) {
        this.productDetailId = productDetailId;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.product = product;
    }

    public ProductDetail() {
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

   
    
}
