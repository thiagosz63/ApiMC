package com.tsa.ApiMC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsa.ApiMC.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

}
