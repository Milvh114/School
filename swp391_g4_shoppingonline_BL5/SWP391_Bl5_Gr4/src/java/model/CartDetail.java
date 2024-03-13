/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class CartDetail {
    private int cartDetailId;
    private Cart cart;
    private ProductDetail productdetail;
    private int Quantity;

    public CartDetail() {
    }

    public CartDetail(int cartDetailId, Cart cart, ProductDetail productdetail, int Quantity) {
        this.cartDetailId = cartDetailId;
        this.cart = cart;
        this.productdetail = productdetail;
        this.Quantity = Quantity;
    }

    public int getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(int cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ProductDetail getProductdetail() {
        return productdetail;
    }

    public void setProductdetail(ProductDetail productdetail) {
        this.productdetail = productdetail;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return "CartDetail{" + "cartDetailId=" + cartDetailId + ", cart=" + cart + ", productdetail=" + productdetail + ", Quantity=" + Quantity + '}';
    }

    
    
}
