package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

}
