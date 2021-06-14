package viko.eif.lt.dfultinavcius.freeflight.AirportInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AirportInfoService
{
    private final AirportInfoRepository airportInfoRepository;
    @Autowired
    public AirportInfoService(AirportInfoRepository airportInfoRepository) {this.airportInfoRepository = airportInfoRepository; }

    public void addNewInfo(AirportInfo info)
    {
        airportInfoRepository.save(info);
    }
}
