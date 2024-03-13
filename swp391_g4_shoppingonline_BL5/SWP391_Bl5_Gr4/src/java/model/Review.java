/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author User
 */
@Builder
@Getter
@Setter
public class Review {
    private int reviewId;
    private User user;
    private Product product;
    private float rate;
    private boolean Favor;
    private String comment;

    public Review(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Review(int reviewId, User user, Product product, float rate, boolean Favor, String comment) {
        this.reviewId = reviewId;
        this.user = user;
        this.product = product;
        this.rate = rate;
        this.Favor = Favor;
        this.comment = comment;
    }
     public Review(User user, Product product, float rate, boolean Favor, String comment) {
        this.user = user;
        this.product = product;
        this.rate = rate;
        this.Favor = Favor;
        this.comment = comment;
    }
}
