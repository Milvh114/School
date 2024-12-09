package com.milvh.app.recycleviewassignment;

public class Category {

    private int ImagePath;

    private String Name;

    private  String Phone;



    public Category() {
    }

    public Category(int imagePath, String name, String phone) {
        ImagePath = imagePath;
        Name = name;
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getImagePath() {
        return ImagePath;
    }

    public void setImagePath(int ImagePath) {
        this.ImagePath = ImagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name;
    }


}
