package com.tsa.ApiMC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsa.ApiMC.controller.utils.URL;
import com.tsa.ApiMC.dto.ProductDTO;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping()
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		
		String nomeDecoder = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<ProductDTO> list = service.search(nomeDecoder,ids,page, linesPage, direction, orderBy);
		return ResponseEntity.ok().body(list);
	}
}

