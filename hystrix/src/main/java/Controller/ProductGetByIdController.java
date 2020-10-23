package Controller;

import delegate.ProductGetByIdDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductGetByIdController {
    @Autowired
    ProductGetByIdDelegate pgbid;
    @GetMapping("/getProductsDetails/{id}")
    public String getProducts(@PathVariable Integer id) {
        return pgbid.getProduct(id);
    }
}