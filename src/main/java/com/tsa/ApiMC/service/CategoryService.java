package com.tsa.ApiMC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.repository.CategoryRepository;

@Service
public class CategoryService {
 
	@Autowired
	private CategoryRepository repository;
	
	public Category buscar(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
}
