package viko.eif.lt.dfultinavcius.freeflight.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import viko.eif.lt.dfultinavcius.freeflight.AirportInfo.AirportInfo;
import viko.eif.lt.dfultinavcius.freeflight.FreeflightApplication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class FreeFlightApplicationSteps
{
    private boolean reachable;

    @When("^the server is up")
    public void Ping() throws IOException {
        reachable = InetAddress.getByName("localhost").isReachable(20);
    }

    @Then("^the server should give an user success status")
    public void PingSuccess()
    {
        Assertions.assertEquals(true, reachable);
    }


}
