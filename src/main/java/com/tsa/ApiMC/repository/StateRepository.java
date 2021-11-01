package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State,Integer>{

}
