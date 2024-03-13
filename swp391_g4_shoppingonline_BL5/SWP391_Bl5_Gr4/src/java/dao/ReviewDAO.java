/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.ImageProduct;
import model.Product;
import model.Review;
import model.User;

/**
 *
 * @author User
 */
public class ReviewDAO extends DBContext {

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT * from Review;";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
//                Review review = pDao.getProductByID(rs.getInt("ProductId"), true);
//
//                reviews.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public Review getReviewByID(int reviewId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where ReviewId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, reviewId);
            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ImageProductDAO imageDao = new ImageProductDAO();

            Product product = new Product();
            Category category = new Category();
            ArrayList<ImageProduct> listImage;

            if (rs.next()) {
                category = cDao.getCategoryByID(rs.getInt("Category"));

                listImage = new ArrayList<>();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("Name"));
                product.setPrice(rs.getDouble("Price"));

                product.setStatus(rs.getBoolean("Status"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setCategory(category);
                product.setDescription(rs.getString("Description"));

                product.setImages(listImage);

            }
//            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Review> getAllReviewsByProductId(int productId) {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where Product = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            UserDAO ud = new UserDAO();
            ProductDAO pd = new ProductDAO();
            while (rs.next()) {
                Review review = new Review(rs.getInt("ReviewId"), ud.getUserByID(rs.getInt("User")), pd.getProductByID(rs.getInt("Product")), rs.getFloat("Rate"), rs.getBoolean("Favor"), rs.getString("Comment"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public List<Review> getAllReviewsByRate(int productid) {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where  Product = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productid);
            ResultSet rs = stm.executeQuery();
            UserDAO ud = new UserDAO();
            ProductDAO pd = new ProductDAO();
            while (rs.next()) {
                Review review = new Review(rs.getInt("ReviewId"), ud.getUserByID(rs.getInt("User")), pd.getProductByID(rs.getInt("Product")), rs.getFloat("Rate"), rs.getBoolean("Favor"), rs.getString("Comment"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public List<Review> getAllReviewsByMaxToLow(int productId) {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where Product = ? order by Rate desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);

            ResultSet rs = stm.executeQuery();
            UserDAO ud = new UserDAO();
            ProductDAO pd = new ProductDAO();
            while (rs.next()) {
                Review review = new Review(rs.getInt("ReviewId"), ud.getUserByID(rs.getInt("User")), pd.getProductByID(rs.getInt("Product")), rs.getFloat("Rate"), rs.getBoolean("Favor"), rs.getString("Comment"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public List<Review> getAllReviewsByLowToMax(int productId) {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where Product = ? order by Rate";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            UserDAO ud = new UserDAO();
            ProductDAO pd = new ProductDAO();
            while (rs.next()) {
                Review review = new Review(rs.getInt("ReviewId"), ud.getUserByID(rs.getInt("User")), pd.getProductByID(rs.getInt("Product")), rs.getFloat("Rate"), rs.getBoolean("Favor"), rs.getString("Comment"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }

    public void insert(Review review) {
        try {
            String sql = "INSERT INTO [dbo].[Review]\n"
                    + "           ([User]\n"
                    + "           ,[Product]\n"
                    + "           ,[Rate]\n"
                    + "           ,[Favor]\n"
                    + "           ,[Comment])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, review.getUser().getUserId());
            stm.setInt(2, review.getProduct().getProductId());
            stm.setFloat(3, review.getRate());
            stm.setBoolean(4, review.isFavor());
            stm.setString(5, review.getComment());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getMaxProductID() {
        try {
            String sql = "SELECT Max(ProductId) as 'max'\n"
                    + "  FROM [Product] ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("max");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void insertFavorProduct(Review review) {
        try {
            ProductDAO pDao = new ProductDAO();
            String sql = "INSERT INTO [dbo].[Review]\n"
                    + "           ([User]\n"
                    + "           ,[Product]\n"
                    + "           ,[Rate]\n"
                    + "           ,[Favor]\n"
                    + "           ,[Comment])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, review.getUser().getUserId());
            stm.setInt(2, review.getProduct().getProductId());
            stm.setFloat(3, review.getRate());
            stm.setBoolean(4, true);
            stm.setString(5, review.getComment());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFavorProduct(int favor, int reviewId) {
        try {
            String sql = "UPDATE [dbo].[Review]\n"
                    + " SET [Favor] = ? WHERE ReviewId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, favor);
            stm.setInt(2, reviewId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Review> getAllReviews(int offset, int recordsPerPage, float rating) {
        List<Review> listReview = new ArrayList<>();
        try {
            HashMap<Integer, Object> setter = new HashMap<>();
            int count = 0;
            String sql = "select * from [Review] where Rate >= 1 and Rate <= 5";
            if (rating != -1) {
                sql += " AND Rate = ?";
                setter.put(++count, rating);
            }
            if (offset != -1 && recordsPerPage != -1) {
                sql += " order by ReviewId offset ? Row\n"
                        + "  Fetch next ? rows only";
                setter.put(++count, offset);
                setter.put(++count, recordsPerPage);
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            ProductDAO pDao = new ProductDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                Product fromProduct = pDao.getProductByID(rs.getInt("Product"), true);

                listReview.add(new Review(
                        rs.getInt("ReviewId"),
                        fromUser,
                        fromProduct,
                        rs.getFloat("Rate"),
                        rs.getBoolean("Favor"),
                        rs.getString("Comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReview;
    }

    public int getTotalReviewsByRating() {

        try {
            int count = 0;
            String sql = "select COUNT(Rate) from Review where Rate >= 1 and Rate <= 5";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public float getAvgRating() {
        try {

            String query = " SELECT AVG(rate) AS AverageRate "
                    + "FROM Review where Rate >= 1 and Rate <= 5\n";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Review> getAllReviewsByFavor(int userId) {
        List<Review> listReview = new ArrayList<>();
        try {
            String sql = "select * from [Review] where Favor = 1 and [User] = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            ProductDAO pDao = new ProductDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                Product fromProduct = pDao.getProductByID(rs.getInt("Product"), true);
                listReview.add(new Review(
                        rs.getInt("ReviewId"),
                        fromUser,
                        fromProduct,
                        rs.getFloat("Rate"),
                        rs.getBoolean("Favor"),
                        rs.getString("Comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReview;
    }

    public List<Review> getAllReviewsByFavorAndProductId(int userId, int productId) {
        List<Review> listReview = new ArrayList<>();
        try {
            String sql = "select * from [Review] where [User] = ? and Product = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, productId);
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            ProductDAO pDao = new ProductDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                Product fromProduct = pDao.getProductByID(rs.getInt("Product"), true);
                listReview.add(new Review(
                        rs.getInt("ReviewId"),
                        fromUser,
                        fromProduct,
                        rs.getFloat("Rate"),
                        rs.getBoolean("Favor"),
                        rs.getString("Comment")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReview;
    }

    public static boolean isReviewExists(List<Review> listReview, int userId, int productId) {
        for (Review existingReview : listReview) {
            // Kiểm tra xem đánh giá đã tồn tại cho sản phẩm và người dùng cụ thể chưa
            if (existingReview.getUser().getUserId() == userId && existingReview.getProduct().getProductId() == productId) {
                return true;
            }
        }
        return false;
    }
    public List<Review> getAllReviewsByRate(int productid , int rate) {
        List<Review> reviews = new ArrayList<Review>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT *\n"
                    + "  FROM [Review] where  Product = ? and Rate =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productid);
              stm.setInt(2, rate);
            ResultSet rs = stm.executeQuery();
            UserDAO ud = new UserDAO();
            ProductDAO pd = new ProductDAO();
            while (rs.next()) {
                Review review = new Review(rs.getInt("ReviewId"), ud.getUserByID(rs.getInt("User")), pd.getProductByID(rs.getInt("Product")), rs.getFloat("Rate"), rs.getBoolean("Favor"), rs.getString("Comment"));
                reviews.add(review);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }
    public Review getReviewByProductAndUser(int userId, int productId){
        try {
            String sql = "select * from [Review] where [User] = ? and Product = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, productId);
            ResultSet rs = stm.executeQuery();
            UserDAO uDao = new UserDAO();
            ProductDAO pDao = new ProductDAO();
            while (rs.next()) {
                User fromUser = uDao.getUserByID(rs.getInt("User"));
                Product fromProduct = pDao.getProductByID(rs.getInt("Product"), true);
               return new Review(
                        rs.getInt("ReviewId"),
                        fromUser,
                        fromProduct,
                        rs.getFloat("Rate"),
                        rs.getBoolean("Favor"),
                        rs.getString("Comment"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
        ReviewDAO review = new ReviewDAO();
        UserDAO ud = new UserDAO();
        User u = ud.getUserByID(2);
        ProductDAO pd = new ProductDAO();
        Product p = pd.getProductByID(1, true);
        List<Review> reviewL = review.getAllReviewsByFavorAndProductId(2,1);
        if(isReviewExists(reviewL,u.getUserId(),p.getProductId()))
            {
                System.out.println("2");
            }else{
            System.out.println("rewẻ2");
        }
    }
}
