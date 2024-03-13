/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import model.Product;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.ImageProduct;
import model.Order;
import model.OrderDetail;
import model.ProductDetail;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        ProductDAO pDao = new ProductDAO();

        try {
            String sql = "SELECT * from Product;";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product product = pDao.getProductByID(rs.getInt("ProductId"), true);

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }
    
      public List<ProductDetail> get5ProductBestSeller() {
        List<ProductDetail> products = new ArrayList<ProductDetail>();
        ProductDetailDAO pdDao = new ProductDetailDAO();

        try {
            String sql = "SELECT TOP 5 od.ProductDetail AS ProductId, COUNT(od.ProductDetail) AS TotalPurchases, " +
                     "SUM(od.Quantity) AS TotalQuantity " +
                     "FROM OrderDetail od JOIN ProductDetail pd ON od.ProductDetail = pd.ProductDetailId " +
                     "GROUP BY od.ProductDetail " +
                     "ORDER BY TotalQuantity DESC";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                   int productId = rs.getInt("ProductId");
                ProductDetail productDetail = pdDao.getProductDetailByID(productId);

                products.add(productDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }

    public List<Product> get5ProductNewArrival() {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "SELECT TOP 5  p.*  FROM  Product p\n"
                    + "                WHERE\n"
                    + "             p.Status = 1 ORDER BY p.createDate DESC;";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ImageProductDAO imageDao = new ImageProductDAO();

            Category category = new Category();

            while (rs.next()) {
                Product product = new Product();
                category = cDao.getCategoryByID(rs.getInt("Category"));
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("Name"));
                product.setPrice(rs.getDouble("Price"));
             
                product.setStatus(rs.getBoolean("Status"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setCategory(category);
                product.setDescription(rs.getString("Description"));
                ArrayList<ImageProduct> listImage = imageDao.getAllImageByProductID(rs.getInt("ProductId"));
                product.setImages(listImage);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    Product getProductDetailsByID(int productID) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Product] where ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
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
                //listImage = imageDao.getAllImageByProductID(product.getProductId());
                product.setImages(listImage);

            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product getProductByID(int productID, boolean status) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Product] where ProductId = ? and Status = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            stm.setBoolean(2, status);
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
                listImage = imageDao.getAllImageByProductID(product.getProductId());
                product.setImages(listImage);

            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Product getProductID(int productID) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Product] where ProductId = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
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
                listImage = imageDao.getAllImageByProductID(product.getProductId());
                product.setImages(listImage);

            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Product> listProduct(int page, int LAST_PAGE) {
        List<Product> products = new ArrayList<Product>();
        try {
            String sql = "select * from Product order by ProductId  offset (?-1) * ? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, LAST_PAGE);
            stm.setInt(3, LAST_PAGE);
            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ImageProductDAO imageDao = new ImageProductDAO();

            Category category = new Category();

            while (rs.next()) {
                Product product = new Product();
                category = cDao.getCategoryByID(rs.getInt("Category"));
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("Name"));
                product.setPrice(rs.getDouble("Price"));
                product.setStatus(rs.getBoolean("Status"));
                product.setCreateDate(rs.getDate("createDate"));
                product.setCategory(category);
                product.setDescription(rs.getString("Description"));
                ArrayList<ImageProduct> listImage = imageDao.getAllImageByProductID(rs.getInt("ProductId"));
                product.setImages(listImage);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getTotalProducts() {

        try {

            String query = "  select count(productId) from Product";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void insert(Product product) {
        try {
            String sql = "INSERT INTO [dbo].[Product]\n"
                    + "           ([ProductId]\n"
                    + "           ,[Name]\n"
                    + "           ,[Price]\n"
                    + "           ,[Status]\n"
                    + "           ,[createDate]\n"
                    + "           ,[Category]\n"
                    + "           ,[Description])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, product.getProductId());
            stm.setString(2, product.getProductName());
            stm.setDouble(3, product.getPrice());
            stm.setBoolean(4, product.isStatus());
            stm.setDate(5, product.getCreateDate());
            stm.setInt(6, product.getCategory().getCategoryId());
            stm.setString(7, product.getDescription());
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
    
     public int getTotalProductID(int productId) {
//        try {
//            String sql = "  SELECT Quantity FROM [Product]  where ProductId = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//              stm.setInt(1, productId);
//            ResultSet rs = stm.executeQuery();
//            if (rs.next()) {
//                return rs.getInt("Quantity");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return -1;
    }

    public Product getProductByID(int productID) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [Product] Where ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);

            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ImageProductDAO imageDao = new ImageProductDAO();

            Product product = new Product();
            Category category = new Category();
            ArrayList<ImageProduct> listImage;

            while (rs.next()) {
                category = cDao.getCategoryByID(rs.getInt("Category"));
                product = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getBoolean("Status"),
                        rs.getDate("createDate"),
                        category,
                        rs.getString("Description"));
                listImage = new ArrayList<>();
                listImage = imageDao.getAllImageByProductID(product.getProductId());
                product.setImages(listImage);
//                    product.getChildren().add(children);
            }
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Product product) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Price] = ?\n"
                    + "      ,[Status] = ?\n"
                    + "      ,[Category] = ?\n"
                    + "      ,[Description] = ?\n"
                    + " WHERE ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getProductName());
            stm.setDouble(2, product.getPrice());
            stm.setBoolean(3, product.isStatus());
            stm.setInt(4, product.getCategory().getCategoryId());
            stm.setString(5, product.getDescription());
            stm.setInt(6, product.getProductId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      public void updateQuantity(int productId , int quantity) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [Quantity] = ?\n"
                    + " WHERE ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, productId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void De(int productId) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET  [Status] = 0\n"
                    + "      \n"
                    + " WHERE ProductId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        ProductDAO product = new ProductDAO();
        Category category = new Category();
        category.setCategoryId(1);
        
            
        
       List<ProductDetail> p = product.get5ProductBestSeller();
        for (ProductDetail productDetail : p) {
        // Ensure that getProduct() is not null before accessing getProduct().getProductName()
        if (productDetail.getProduct() != null) {
            System.out.println(productDetail.getProduct().getProductName());
        } else {
            System.out.println("Product is null for ProductDetail with ID: " + productDetail.getProductDetailId());
        }
    }
//
//     
//        product.update(p);
        //  System.out.println(product.getProductByID(3,true));
//        System.out.println(product.getTotalProducts());
    }

    public List<Product> getAllProductsForSearch(String textSearch, int categoryID, int sortOption, int moreOption) {
        List<Product> products = new ArrayList<>();
        ProductDAO pDao = new ProductDAO();
        try {
            String sql = "SELECT * FROM Product WHERE status=1";

            if (!textSearch.isEmpty() && !textSearch.equalsIgnoreCase("")) {

                textSearch = "%" + textSearch.trim() + "%";

                sql += " AND [Name] LIKE ?";
            }

            if (categoryID != -1) {
                sql += " AND Category = ?";
            }
            switch (sortOption) {
                case -1:
                    break;
                case 1:
                    sql += " order by Name asc\n";
                    break;
                case 2:
                    sql += " order by Name desc\n";
                    break;
                case 3:
                    sql += " order by Price asc\n";
                    break;
                case 4:
                    sql += " order by Price desc\n";
                    break;
                case 5:
                    sql += " ORDER BY createDate desc\n";
                    break;
                case 6:

                    break;
            }
            if (moreOption == -1) {
                sql += "";
            }
            if (moreOption == 5) {
                sql += " ORDER BY createDate desc\n";

            }
            if (moreOption == 6) {
                sql = "select * From (SELECT TOP 100 PERCENT\n"
                        + "                    od.Product,   \n"
                        + "                COUNT(od.Product) AS TotalPurchases,\n"
                        + "                 SUM(od.Quantity) AS TotalQuantity\n"
                        + "FROM\n"
                        + "          OrderDetail od JOIN Product p on od.Product = p.ProductId where p.Status = 1\n"
                        + "GROUP BY\n"
                        + " od.Product\n"
                        + "\n"
                        + "ORDER BY\n"
                        + "TotalQuantity DESC) as ProductBestSeller right join Product on ProductBestSeller.Product = Product.ProductId";
            }
            if (!textSearch.isEmpty() && !textSearch.equalsIgnoreCase("") && moreOption == 6) {
                textSearch = "%" + textSearch + "%";
                sql += " WHERE [Name] LIKE ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            int parameterIndex = 1;
            if (!textSearch.isEmpty() && !textSearch.equalsIgnoreCase("")) {
                stm.setString(parameterIndex++, textSearch);
            }

            if (categoryID != -1) {
                stm.setInt(parameterIndex++, categoryID);
            }

            System.out.println("Final SQL: " + sql);
            // Execute the query
            ResultSet rs = stm.executeQuery();

            CategoryDAO cDao = new CategoryDAO();
            ImageProductDAO imageDao = new ImageProductDAO();

            Category category = new Category();

            while (rs.next()) {
                if (sortOption == 6) {
                    Product product = pDao.getProductByID(rs.getInt("Product"), true);

                    products.add(product);
                } else {
                    Product product = new Product();
                    category = cDao.getCategoryByID(categoryID);
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setStatus(rs.getBoolean("Status"));
                    product.setCreateDate(rs.getDate("createDate"));
                    product.setCategory(category);
                    product.setDescription(rs.getString("Description"));
                    ArrayList<ImageProduct> listImage = imageDao.getAllImageByProductID(rs.getInt("ProductId"));
                    product.setImages(listImage);
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public int getTotalProductsViewByCustomer() {

        try {

            String query = "  select count(productId) from Product where Status = 1";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> getAllProductsByPage( int offset, int recordsPerPage) {
        List<Product> products = new ArrayList<Product>();
        try {
            ProductDAO pDao = new ProductDAO();
            int count = 0;
            HashMap<Integer, Object> setter = new HashMap<>();

            String sql = "SELECT * from Product where status = 1 ";
            if (offset != -1 && recordsPerPage != -1) {
                sql += " order by ProductId offset ? Row\n"
                        + "  Fetch next ? rows only";
                setter.put(++count, offset);
                setter.put(++count, recordsPerPage);
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = pDao.getProductByID(rs.getInt("ProductId"), true);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }

}
