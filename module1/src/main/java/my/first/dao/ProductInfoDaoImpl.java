package my.first.dao;

import my.first.model.ProductInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductInfoDaoImpl implements ProductInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(ProductInfo productInfo) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(productInfo);
    }

    @Override
    public List<ProductInfo> readAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from product_info", ProductInfo.class)
                .list();
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
