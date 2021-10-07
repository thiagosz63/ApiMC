package com.tsa.ApiMC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@GetMapping
	public String listar() {
		return "rest esta fucionando";
	}
}
