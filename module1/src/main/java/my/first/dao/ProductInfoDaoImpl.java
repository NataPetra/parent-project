package my.first.dao;

import my.first.MysqlJdbcDataSource;
import my.first.model.ProductInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductInfoDaoImpl implements ProductInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    private final MysqlJdbcDataSource dataSource;

    public ProductInfoDaoImpl() {
        this.dataSource = new MysqlJdbcDataSource();
    }

    @Override
    public void create(ProductInfo productInfo) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(productInfo);
    }

    @Override
    public List<ProductInfo> readAll() {
        List<ProductInfo> products = new ArrayList<>();
        try {
            final Connection connection = dataSource.getConnection();
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM product_info");
            while (resultSet.next()) {
                final ProductInfo productInfo = new ProductInfo();
                productInfo.setId(resultSet.getInt("id"));
                productInfo.setName(resultSet.getString("name"));
                productInfo.setPrice(resultSet.getDouble("price"));
                products.add(productInfo);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
//        return sessionFactory.getCurrentSession()
//                .createQuery("from product_info", ProductInfo.class)
//                .list();
    }

    @Override
    public void update(ProductInfo productInfo) {
        create(productInfo);
    }

    @Override
    public void delete(ProductInfo productInfo) {
        sessionFactory.getCurrentSession()
                .delete(productInfo);
    }
}
