package viko.eif.lt.dfultinavcius.freeflight.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import viko.eif.lt.dfultinavcius.freeflight.AirportInfo.AirportInfoController;
import viko.eif.lt.dfultinavcius.freeflight.FreeflightApplication;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = {FreeflightApplication.class, CucumberIT.class, AirportInfoController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(plugin = {"pretty"}, tags = "", features = "src/test/java/resources/cucumber/features")
public class CucumberIT
{

}
