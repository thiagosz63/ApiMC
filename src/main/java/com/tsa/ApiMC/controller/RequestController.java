package com.tsa.ApiMC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsa.ApiMC.entities.Request;
import com.tsa.ApiMC.service.RequestService;

@RestController
@RequestMapping(value = "/request")
public class RequestController {
	
	@Autowired
	private RequestService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Request obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
