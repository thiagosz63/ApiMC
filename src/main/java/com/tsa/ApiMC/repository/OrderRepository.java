package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{

}
