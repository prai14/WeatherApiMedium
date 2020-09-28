package com.hackerrank.weather.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.service.WeatherService;

@RestController
public class WeatherApiRestController {
	
	@Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/weather", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Weather> createNewWeather(@RequestBody @Valid Weather weather) {
        return new ResponseEntity<Weather>(weatherService.createWeather(weather), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/erase", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllWeathers() {
        weatherService.deleteAllWeathers();
    }

    @RequestMapping(value = "/weather/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteWeatherById(@RequestParam Integer id) {
        weatherService.deleteWeatherById(id);;
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Weather> getAllWeathers(
            @RequestParam(value = "city", required = false) String city,  @RequestParam(value = "sort", defaultValue = "id") String sort
    ) {
        Sort sortby;
        if(sort.indexOf('-') == 0){
            sortby = Sort.by(sort.substring(1));
            sortby.descending();
        } else {
            sortby = Sort.by(sort);
        }


        List<Weather> records = weatherService.getAllWeathers(sortby);
        if(city == null || city == ""){
            return records;
        } else {
           return records != null && records.size() > 0 ? records.stream().filter(r -> r.getCity().toLowerCase().equals(city.toLowerCase())).collect(Collectors.toList()) : null;
        }
    }

    @RequestMapping(value = "/weather/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Weather getWeatherById(Integer id) {
        return weatherService.getWeatherById(id);
    }
}
