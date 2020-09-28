package com.hackerrank.weather.service;

import java.util.List;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.domain.Sort;


public interface WeatherService {
	
	void deleteAllWeathers();
    void deleteWeatherById(Integer id);
    Weather createWeather(Weather Weather);

    Weather getWeatherById(Integer id);
    List<Weather> getWeatherByCity(String city);

    List<Weather> getAllWeathers(Sort sort);

}
