package viko.eif.lt.dfultinavcius.freeflight.AirportInfo;


import javax.persistence.*;

@Entity
@Table
public class AirportInfo
{
    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1
    )
    @GeneratedValue( strategy = GenerationType.SEQUENCE)

    private Long id;
    private String country;
    private String city;
    private String airportName;

    public AirportInfo(Long id, String country, String city, String airportName) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.airportName = airportName;
    }

    public AirportInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
