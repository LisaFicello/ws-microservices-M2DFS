package delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductUpdateDelegate {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "updateProduct_Fallback")
    public String updateProduct(Integer id){
        //se mettre au début de la ligne puis cms+option+V choisir ce qu'on veut
        String response = restTemplate.exchange("http://localhost:9090/updateProduit/{id}",
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<String>() {
                },id).getBody();

        return "NORMAL FLOW !!! Update Details: " + response + " -";
    }

    // implement a fallback method
    public String updateProduct_Fallback(Integer id){
        return "Fallback response:: No update details available for : " + id;
    }

    //this is a bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
