package com.tsa.ApiMC.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.dto.ProductDTO;
import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Product.class.getName()));
	}
	
	public Page<ProductDTO> search(String nome, List<Integer> ids,Integer page, Integer linesPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPage,Direction.valueOf(direction), orderBy);
		List<Category> categorias = categoryRepository.findAllById(ids);
		Page<Product> product = repository.findDistinctByNameContainingAndCategoriesIn(nome,categorias,pageRequest);
		return product.map(x-> new ProductDTO(x));
		
	}

}
