/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author milvh
 */
public class Account {
    
    int uid;
    String user;
    String pass;
    int isAdmin;
    int isSell;

    public Account() {
    }

    public Account(int uid, String user, String pass, int isAdmin, int isSell) {
        this.uid = uid;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
        this.isSell = isSell;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsSell() {
        return isSell;
    }

    public void setIsSell(int isSell) {
        this.isSell = isSell;
    }

    @Override
    public String toString() {
        return "Account{" + "uid=" + uid + ", user=" + user + ", pass=" + pass + ", isAdmin=" + isAdmin + ", isSell=" + isSell + '}';
    }
    
    
    
}
