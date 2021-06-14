package viko.eif.lt.dfultinavcius.freeflight.AirportInfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viko.eif.lt.dfultinavcius.freeflight.BackgroundMethods;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/v1/FreeFlight")
public class AirportInfoController
{
    @GetMapping(path = "Nearest")
    public AirportInfo getNearestAirport(HttpServletRequest request)
    {
        System.out.println(BackgroundMethods.getClientIpAddress(request));
        return new AirportInfo();
    }

}
