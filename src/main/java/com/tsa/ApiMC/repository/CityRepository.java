package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City,Integer>{

}
