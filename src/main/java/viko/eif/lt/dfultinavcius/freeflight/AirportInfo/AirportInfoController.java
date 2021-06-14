package viko.eif.lt.dfultinavcius.freeflight.AirportInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viko.eif.lt.dfultinavcius.freeflight.BackgroundMethods;
import viko.eif.lt.dfultinavcius.freeflight.ExternalAPI.ExternalAPI;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/FreeFlight")
public class AirportInfoController
{
    private final AirportInfoService service;
    private final AirportInfoRepository repo;


    @Autowired
    AirportInfoController(AirportInfoService service, AirportInfoRepository repo)
    {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public ArrayList<IMostVistedAirports> getMostVisited() { return this.repo.getMostVisited();}

    @GetMapping(path = "Nearest")
    public AirportInfo getNearestAirport(HttpServletRequest request) throws IOException, InterruptedException {
        var ip = BackgroundMethods.getClientIpAddress(request);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode ipGeoNode = mapper.readTree(ExternalAPI.getIpGeoreq(ip).body());
        String LongLatiparams = ipGeoNode.get("latitude").asText() + "%2C" + ipGeoNode.get("longitude").asText();
        String country_code = ipGeoNode.get("country_code").asText();

        System.out.println(LongLatiparams);
        System.out.println(country_code);

        JsonNode geoServiceNode = mapper.readTree(ExternalAPI.getGeoServicereq(LongLatiparams,country_code).body());
        AirportInfo airportInfo = new AirportInfo();

        System.out.println(ExternalAPI.getGeoServicereq(LongLatiparams,country_code).body());

        airportInfo.setAirportName(geoServiceNode.get("data").get(0).get("name").asText());
        airportInfo.setCity(geoServiceNode.get("data").get(0).get("cityname").asText());
        airportInfo.setCountry(geoServiceNode.get("data").get(0).get("countrycode").asText());

        service.addNewInfo(airportInfo);
        return airportInfo;
    }

}
