package my.first.web;

import my.first.model.ProductInfo;
import my.first.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/add-prod.html")
    public String showProd(){
        return "add-prod";
    }

    @PostMapping("/add-prod.html")
    public String addProd(ProductInfo productInfo){
        System.out.println(productInfo);
        productService.addNewProduct(productInfo);
        return "redirect:/index.html";

    }

}
