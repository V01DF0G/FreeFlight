package viko.eif.lt.dfultinavcius.freeflight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FreeflightApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeflightApplication.class, args);
    }

    @GetMapping("/")
    public String hello()
    {
        return "Hello to the FreeFlight REST API !";
    }


}
