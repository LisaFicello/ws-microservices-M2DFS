package Controller;

import delegate.ProductUpdateDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductUpdateController {
    @Autowired
    ProductUpdateDelegate pmd;
    @GetMapping("/updateProducts/{id}/")
    public String updateProducts(@PathVariable Integer id){
        return pmd.updateProduct(id);
    }
}
