package pratice.roon.misedirt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiseDirtApplication {

    static {
        System.setProperty("spring.config.location", "classpath:/application.yml,classpath:/openapi-secret.yml");
    }

    public static void main(String[] args) {
        SpringApplication.run(MiseDirtApplication.class, args);
    }

}
