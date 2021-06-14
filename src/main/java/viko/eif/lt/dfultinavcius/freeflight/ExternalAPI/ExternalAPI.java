package viko.eif.lt.dfultinavcius.freeflight.ExternalAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalAPI
{
    public static HttpResponse<String> getIpGeoreq(String ip) throws IOException, InterruptedException {
        HttpRequest ipGeoReq = HttpRequest.newBuilder()
                .uri(URI.create("https://free-geo-ip.p.rapidapi.com/json/" + ip))
                .header("x-rapidapi-key", "a1aef2c13cmsh1db981720bb680ep131df9jsn4718336aca9c")
                .header("x-rapidapi-host", "free-geo-ip.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return HttpClient.newHttpClient().send(ipGeoReq, HttpResponse.BodyHandlers.ofString());
    }

    public static HttpResponse<String> getGeoServicereq(String LongLatiparams, String countrycode) throws IOException, InterruptedException {
        HttpRequest GeoServicereq = HttpRequest.newBuilder()
                .uri(URI.create("https://geo-services-by-mvpc-com.p.rapidapi.com/airports?language=en&radius=50&location=" + LongLatiparams + "&countrycode=" + countrycode))
                .header("x-rapidapi-key", "a1aef2c13cmsh1db981720bb680ep131df9jsn4718336aca9c")
                .header("x-rapidapi-host", "geo-services-by-mvpc-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> GeoServiceresp = HttpClient.newHttpClient().send(GeoServicereq, HttpResponse.BodyHandlers.ofString());

        return HttpClient.newHttpClient().send(GeoServicereq, HttpResponse.BodyHandlers.ofString());
    }


}
