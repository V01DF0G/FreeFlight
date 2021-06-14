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

        HttpRequest ipGeoReq = HttpRequest.newBuilder()
                .uri(URI.create("https://free-geo-ip.p.rapidapi.com/json/" + ip))
                .header("x-rapidapi-key", "a1aef2c13cmsh1db981720bb680ep131df9jsn4718336aca9c")
                .header("x-rapidapi-host", "free-geo-ip.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> ipGeoResp = HttpClient.newHttpClient().send(ipGeoReq, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode ipGeoNode = mapper.readTree(ipGeoResp.body());

        String LongLatipar = ipGeoNode.get("latitude").asText() + "%2C" + ipGeoNode.get("longitude").asText();

        HttpRequest GeoServicereq = HttpRequest.newBuilder()
                .uri(URI.create("https://geo-services-by-mvpc-com.p.rapidapi.com/airports?language=en&radius=50&location=" + LongLatipar + "&countrycode=" + ipGeoNode.get("country_code").asText()))
                .header("x-rapidapi-key", "a1aef2c13cmsh1db981720bb680ep131df9jsn4718336aca9c")
                .header("x-rapidapi-host", "geo-services-by-mvpc-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> GeoServiceresp = HttpClient.newHttpClient().send(GeoServicereq, HttpResponse.BodyHandlers.ofString());

        JsonNode geoServiceNode = mapper.readTree(GeoServiceresp.body());
        AirportInfo airportInfo = new AirportInfo();

        airportInfo.setAirportName(geoServiceNode.get("data").get(0).get("name").asText());
        airportInfo.setCity(geoServiceNode.get("data").get(0).get("cityname").asText());
        airportInfo.setCountry(geoServiceNode.get("data").get(0).get("countrycode").asText());

        service.addNewInfo(airportInfo);
        return airportInfo;
    }

}
