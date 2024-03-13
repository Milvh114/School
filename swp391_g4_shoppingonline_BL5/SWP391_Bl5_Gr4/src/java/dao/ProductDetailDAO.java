/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Category;
import model.Color;
import model.ImageProduct;
import model.Product;
import model.ProductDetail;
import model.Size;

/**
 *
 * @author Admin
 */
public class ProductDetailDAO extends DBContext {

    public ProductDetail getProductDetailByID(int productdId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [ProductDetail] where ProductId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productdId);

            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ColorDAO coDao = new ColorDAO();
            ProductDAO pdao = new ProductDAO();
            SizeDAO sdao = new SizeDAO();
            ImageProductDAO imageDao = new ImageProductDAO();
            Color cl = new Color();
            Size s = new Size();
            ProductDetail pd = new ProductDetail();
            Category category = new Category();
            ArrayList<ImageProduct> listImage;

            if (rs.next()) {
                cl = coDao.getColerByID(rs.getInt("Color"));
                pd.setProductDetailId(rs.getInt("ProductDetailId"));
                pd.setProduct(pdao.getProductByID(rs.getInt("ProductId")));
                pd.setSize(sdao.getSizeByID(rs.getInt("Size")));
                pd.setColor(cl);
                pd.setQuantity(rs.getInt("Quantity"));
            }
            return pd;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductDetail getProductDetailBySizeIdAndColorId(int productid, int sizeid, int colorid) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [ProductDetail] where [ProductId] = ? and [Size] = ? and [Color] =? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productid);
            stm.setInt(2, sizeid);
            stm.setInt(3, colorid);
            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ColorDAO coDao = new ColorDAO();
            ProductDAO pdao = new ProductDAO();
            SizeDAO sdao = new SizeDAO();
            Color cl = new Color();
            ProductDetail pd = new ProductDetail();
            if (rs.next()) {
                cl = coDao.getColerByID(rs.getInt("Color"));
                pd.setProductDetailId(rs.getInt("ProductDetailId"));
                pd.setProduct(pdao.getProductByID(rs.getInt("ProductId")));
                pd.setSize(sdao.getSizeByID(rs.getInt("Size")));
                pd.setColor(cl);
                pd.setQuantity(rs.getInt("Quantity"));
            }
            return pd;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   

    public static void main(String[] args) {
        ProductDetailDAO d = new ProductDetailDAO();
        System.out.println(d.getProductDetailByID(5).getProduct().getProductName());
    }
}
