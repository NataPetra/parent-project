package my.first.service;

import my.first.dao.ProductInfoDao;
import my.first.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Transactional
    public void addNewProduct(ProductInfo product) {
        //TODO: aad field validation; check product name duplicates;
        productInfoDao.create(product);
    }

    @Transactional
    public List<ProductInfo> getAll() {
        return productInfoDao.readAll();
    }
}
