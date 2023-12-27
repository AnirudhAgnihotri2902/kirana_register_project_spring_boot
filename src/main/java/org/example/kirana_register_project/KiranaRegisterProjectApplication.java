package org.example.kirana_register_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.kirana_register_project.crud.repository")
public class KiranaRegisterProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(KiranaRegisterProjectApplication.class, args);
    }
}
