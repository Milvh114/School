/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Customer {
    
    private int UserID;
    private String FullName;
    private String Email;
    private String Phone;
    private Date DOB;
    private String Address;
    private String Avatar;
    private boolean Status;
    private String Description;
    private List<Order> orders;

    public Customer(int UserID, String FullName, String Email, String Phone, Date DOB, String Address, String Avatar, boolean Status, String Description, List<Order> orders) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.DOB = DOB;
        this.Address = Address;
        this.Avatar = Avatar;
        this.Status = Status;
        this.Description = Description;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

  

   
    public Customer() {
    }

    public Customer(int UserID, String FullName, String Email, String Phone, Date DOB, String Address, String Avatar, boolean Status, String Description) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.DOB = DOB;
        this.Address = Address;
        this.Avatar = Avatar;
        this.Status = Status;
        this.Description = Description;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
    @Override
    public String toString() {
        return "Customer{" + "UserID=" + UserID + ", FullName=" + FullName + ", Email=" + Email + ", Phone=" + Phone + ", DOB=" + DOB + ", Address=" + Address + ", Avatar=" + Avatar + ", Status=" + Status + ", Description=" + Description + '}';
    }
    
}
