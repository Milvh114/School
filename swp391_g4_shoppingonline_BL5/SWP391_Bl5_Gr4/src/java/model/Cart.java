/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.Builder;

/**
 *
 * @author User
 */

public class Cart {

    private int cartId;
    private double totalOrder;
    private User user;

    public Cart(int cartId, int totalOrder, User user) {
        this.cartId = cartId;
        this.totalOrder = totalOrder;
        this.user = user;
    }
 public Cart( int totalOrder, User user) {
      
        this.totalOrder = totalOrder;
        this.user = user;
    }

    
    public Cart() {

    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartId=" + cartId + ", totalOrder=" + totalOrder + ", user=" + user + '}';
    }

}
