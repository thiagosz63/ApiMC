package com.tsa.ApiMC.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tsa.ApiMC.entities.Request;
import com.tsa.ApiMC.service.RequestService;

@RestController
@RequestMapping(value = "/request")
public class RequestController {
	
	@Autowired
	private RequestService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Request> find(@PathVariable Integer id) {
		Request obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Request obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
