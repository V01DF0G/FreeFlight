package viko.eif.lt.dfultinavcius.freeflight.cucumber;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import viko.eif.lt.dfultinavcius.freeflight.AirportInfo.AirportInfo;
import viko.eif.lt.dfultinavcius.freeflight.FreeflightApplication;

import java.lang.reflect.Type;


public class CucumberConfiguration
{
    private  final ObjectMapper objectMapper;

    public CucumberConfiguration()
    {
        objectMapper = new ObjectMapper();
    }


    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, final Type to)
    {
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }

}
