package com.tsa.ApiMC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category find(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);

	}

	public Category update(Category obj) {	
		find(obj.getId());
		return repository.save(obj);
	}

}
