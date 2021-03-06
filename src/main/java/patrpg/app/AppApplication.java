package patrpg.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("patrpg.app.entity")
public class AppApplication {

    // Spring Framework 5.2.8.RELEASE
    // Spring Boot 2.3.3.RELEASE
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
