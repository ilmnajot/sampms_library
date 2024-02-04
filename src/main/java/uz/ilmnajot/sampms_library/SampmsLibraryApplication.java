package uz.ilmnajot.sampms_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories
public class SampmsLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampmsLibraryApplication.class, args);
    }

}
