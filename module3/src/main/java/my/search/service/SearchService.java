package my.search.service;

import my.first.dao.ProductInfoDao;
import my.first.dao.ProductInfoDaoImpl;
import my.first.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

//    @Autowired
    private final ProductInfoDao productInfoDao;

    @Autowired
    @Qualifier("simpleHttpClient")
    private HttpClient httpClient;

    public SearchService() {
        this(new ProductInfoDaoImpl());
    }

    public SearchService(ProductInfoDao productInfoDao) {
        this.productInfoDao = productInfoDao;
    }
//    private final static List<ProductInfo> warehouse = new ArrayList<>(10);
//
//    static {
//        warehouse.add(getInstance(1, "Coffee Black", 3.5));
//        warehouse.add(getInstance(2, "Coffee Espresso", 2.9));
//        warehouse.add(getInstance(3, "Coffee Latte", 4.0));
//        warehouse.add(getInstance(4, "Coffee Latte Big", 4.5));
//        warehouse.add(getInstance(5, "Black Tea", 2.5));
//        warehouse.add(getInstance(6, "Green Tea", 2.5));
//        warehouse.add(getInstance(7, "Water Still", 1.5));
//        warehouse.add(getInstance(8, "Water Sparkling", 1.5));
//        warehouse.add(getInstance(9, "Coca Cola", 2.0));
//        warehouse.add(getInstance(10, "Pepsi Cola", 2.0));
//    }

    public List<ProductInfo> search(String pattern){
        if (pattern == null){
            return Collections.emptyList();
        }

        return productInfoDao.readAll()
                .stream()
                .filter(productInfo -> productInfo
                        .getName()
                        .toLowerCase()
                        .contains(pattern.toLowerCase()))
                .collect(Collectors.toList());
    }

//    /**
//     * Factory method
//     *
//     * @param id
//     * @param name
//     * @param price
//     * @return
//     */
//    static ProductInfo getInstance(int id, String name, double price){
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setId(id);
//        productInfo.setName(name);
//        productInfo.setPrice(price);
//        return productInfo;
//    }
}
