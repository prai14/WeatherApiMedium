package com.hackerrank.weather.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService{

	@Autowired
	WeatherRepository repo;
		
	@Override
	public void deleteAllWeathers() {
		repo.findAll();
		
	}

	@Override
	public void deleteWeatherById(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public List<Weather> getWeatherByCity(String city) {
		// TODO Auto-generated method stub
		// Optional<Weather> weatheropt = repo.findByName(name);
		// return weatheropt.isPresent() ? weatheropt.get(): null;

	return repo.findByCity(city);
	}

	@Override
	public Weather createWeather(Weather weather) {
		// TODO Auto-generated method stub
		final com.hackerrank.weather.model.Weather save = repo.save(weather);
		return getWeatherById(save.getId());
		
	}

	@Override
	public Weather getWeatherById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Weather> weatheropt = repo.findById(id);
		return weatheropt.isPresent() ? weatheropt.get(): null;
	}

	@Override
	public List<Weather> getAllWeathers(Sort sort) {
		// TODO Auto-generated method stub
		return repo.findAll(sort);
	}


	

}
