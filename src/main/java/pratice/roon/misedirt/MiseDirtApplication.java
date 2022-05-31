package pratice.roon.misedirt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiseDirtApplication {

    static {
        System.setProperty("spring.config.location", "classpath:/application.yml,classpath:/openapi-secret.yml");
    }

    public static void main(String[] args) {
        SpringApplication.run(MiseDirtApplication.class, args);
    }

}
