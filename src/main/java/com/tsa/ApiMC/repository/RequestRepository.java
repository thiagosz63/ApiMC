package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer>{

}
