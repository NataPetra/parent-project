package my.first.dao;

//import my.first.MysqlDataSource;
import my.first.MysqlJdbcDataSource;
import my.first.MysqlSessionFactory;
import my.first.model.ProductInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductInfoDaoImpl implements ProductInfoDao {

//    public static final String PRODUCT_INFO_CREATE = "INSERT INTO product_info (id, name, price) VALUES (?, ?, ?)";
    private final MysqlJdbcDataSource dataSource;
    private final SessionFactory sessionFactory;

//    public ProductInfoDaoImpl() {
//        try {
//            this.dataSource = new MysqlDataSource();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public ProductInfoDaoImpl() {
        this.dataSource = new MysqlJdbcDataSource();
        this.sessionFactory = MysqlSessionFactory.getInstance();
    }

    public ProductInfoDaoImpl(MysqlJdbcDataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(ProductInfo productInfo) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_INFO_CREATE);) {
//            preparedStatement.setInt(1, productInfo.getId());
//            preparedStatement.setString(2, productInfo.getName());
//            preparedStatement.setDouble(3, productInfo.getPrice());
//            preparedStatement.executeQuery();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.saveOrUpdate(productInfo);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<ProductInfo> readAll() {
        List<ProductInfo> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery("SELECT id, name, price FROM product_info");
            while (resultSet.next()) {
                final ProductInfo productInfo = new ProductInfo();
                productInfo.setId(resultSet.getInt("id"));
                productInfo.setName(resultSet.getString("name"));
                productInfo.setPrice(resultSet.getDouble("price"));
                products.add(productInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(ProductInfo productInfo) {
        create(productInfo);
    }

    @Override
    public void delete(ProductInfo productInfo) {
        Transaction tx = null;
        try (Session sess = sessionFactory.openSession()) {
            tx = sess.beginTransaction();
            sess.delete(productInfo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
