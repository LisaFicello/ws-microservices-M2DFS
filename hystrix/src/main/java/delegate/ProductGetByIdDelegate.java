package delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductGetByIdDelegate {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProduct_Fallback")
    public String getProduct(Integer id){
        //se mettre au début de la ligne puis cms+option+V choisir ce qu'on veut
        String response = restTemplate.exchange("http://localhost:9090/Produits/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }, id).getBody();

        return "NORMAL FLOW !!! - Product: " + id + "\nProduct Details: " + response + " -";
    }

    // implement a fallback method
    public String getProduct_Fallback(Integer id){
        return "Fallback response:: No product details available temporarily for : " + id;
    }

    //this is a bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
