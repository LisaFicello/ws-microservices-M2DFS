package Controller;

import delegate.ProductMargeDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductMargeController {
    @Autowired
    ProductMargeDelegate pmd;
    @GetMapping("/getMargeDetails/")
    public String getMarge(){
        return pmd.getMargeProducts();
    }
}