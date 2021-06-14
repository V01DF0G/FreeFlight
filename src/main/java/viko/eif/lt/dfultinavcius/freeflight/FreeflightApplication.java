package viko.eif.lt.dfultinavcius.freeflight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FreeflightApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FreeflightApplication.class, args);
    }

    @GetMapping("/")
    @ResponseBody
    public String hello()
    {
        return "Hello to the FreeFlight REST API !";
    }


}
