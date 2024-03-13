/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Category {
    private int categoryId;
    private String categoryName;
    private boolean status;
    private String description;

    public Category(int categoryId, String categoryName, boolean status, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
        this.description = description;
    }
    
     public Category( String categoryName, boolean status, String description) {
        this.categoryName = categoryName;
        this.status = status;
        this.description = description;
    }


    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
