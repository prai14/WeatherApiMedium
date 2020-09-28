package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    Optional<Weather> findById(Integer id);
    List<Weather> findByCity(String city);
    List<Weather> findAll(Sort sort);
    List<Weather> findAll();
}
