package com.tsa.ApiMC;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.repository.CategoryRepository;

@SpringBootApplication
public class ApiMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository reposytory;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "informatica");
		Category cat2 = new Category(null, "escritorio");
		reposytory.saveAll(Arrays.asList(cat1,cat2));
		
	}

}
