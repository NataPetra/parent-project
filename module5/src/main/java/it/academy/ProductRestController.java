package it.academy;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductRestController {

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "5") int pageSize
    ){
        return new ResponseEntity<>(
                List.of(
                        ProductDto.builder().id("1").name("iphone13").price(2499.49).build(),
                        ProductDto.builder().id("2").name("iphone14").price(3499.49).build()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDto> getProduct(
            @PathVariable(name = "id") String id
    ) {
        return new ResponseEntity<>(
                ProductDto.builder().id(id).name("iPhone 13").price(2499.49).build(),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/products/{id}", consumes = "application/json")
    public ResponseEntity<ProductDto> postProduct(
            @PathVariable(name = "id") String id,
            @RequestBody ProductDto productDto
    ) {
        //todo validation logic and call DAO
        productDto.setId(id);
        return new ResponseEntity<>(
                productDto,
                new MultiValueMapAdapter<>(
                        Map.of("my.header", List.of("my.value"))
                ),
                HttpStatus.OK
        );
    }




}
