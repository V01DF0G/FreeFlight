package viko.eif.lt.dfultinavcius.freeflight.AirportInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Repository
public interface AirportInfoRepository extends JpaRepository<AirportInfo, Integer>
{
    @GetMapping
    @Query(value = "select airport_info.airport_name,airport_info.city,airport_info.country,count(*) as occurences from airport_info group by airport_info.airport_name,airport_info.city,airport_info.country", nativeQuery = true)
    ArrayList<IMostVistedAirports> getMostVisited();
}
