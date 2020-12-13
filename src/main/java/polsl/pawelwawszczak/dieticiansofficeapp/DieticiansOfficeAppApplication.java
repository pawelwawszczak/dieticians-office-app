package polsl.pawelwawszczak.dieticiansofficeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class DieticiansOfficeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DieticiansOfficeAppApplication.class, args);
    }

}
