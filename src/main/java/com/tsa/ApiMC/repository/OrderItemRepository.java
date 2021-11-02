package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

}
