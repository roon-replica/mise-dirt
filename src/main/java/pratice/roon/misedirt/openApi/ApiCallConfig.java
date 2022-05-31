package pratice.roon.misedirt.openApi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiCallConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
