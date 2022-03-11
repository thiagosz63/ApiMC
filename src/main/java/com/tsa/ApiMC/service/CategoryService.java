package com.tsa.ApiMC.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	public Category insert(Category obj) {
		return repository.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possua Produtos");
		}
	}
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	public Page<CategoryDTO> findPage(Integer page, Integer linesPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPage,Direction.valueOf(direction), orderBy);
		Page<Category> pages =  repository.findAll(pageRequest);
		return pages.map(x -> new CategoryDTO(x));
	}
	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(),objDTO.getName());
		
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
