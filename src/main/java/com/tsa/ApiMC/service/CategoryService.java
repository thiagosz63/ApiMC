package com.tsa.ApiMC.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsa.ApiMC.dto.CategoryDTO;
import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.service.exceptions.DataIntegrityException;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Category find(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Category.class.getName()));
	}
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	public Category insert(Category obj) {
		return repository.save(obj);

	}

	public Category update(Category obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possua Produtos");
		}

	}
}
