package Controller;

import delegate.ProductDeleteDelegate;
import delegate.ProductGetByIdDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDeleteController {
    @Autowired
    ProductDeleteDelegate pdc;
    @GetMapping("/deleteProductsDetails/{id}")
    public String deleteProducts(@PathVariable Integer id) {
        return pdc.deleteProduct(id);
    }
}
