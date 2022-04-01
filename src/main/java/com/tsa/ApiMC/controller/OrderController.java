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

import com.tsa.ApiMC.entities.Order;
import com.tsa.ApiMC.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService service;

	@GetMapping("/{id}")
	public ResponseEntity<Order> find(@PathVariable Integer id) {
		Order obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
