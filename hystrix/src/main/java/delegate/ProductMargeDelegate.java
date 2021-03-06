package delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductMargeDelegate {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getMargeProducts_Fallback")
    public String getMargeProducts(){
        //se mettre au début de la ligne puis cms+option+V choisir ce qu'on veut
        String response = restTemplate.exchange("http://localhost:9090/AdminProduits",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                }).getBody();

        return "NORMAL FLOW !!! Marge Details: " + response + " -";
    }

    // implement a fallback method
    public String getMargeProducts_Fallback(){
        return "Fallback response:: No marge details available";
    }

    //this is a bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
