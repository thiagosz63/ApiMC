package com.tsa.ApiMC;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.entities.City;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.entities.State;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.repository.CityRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.repository.StateRepository;

@SpringBootApplication
public class ApiMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryReposytory;
	
	@Autowired
	private ProductRepository productReposytory;
	
	@Autowired
	private CityRepository cityReposytory;
	
	@Autowired
	private StateRepository stateReposytory;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "informatica");
		Category cat2 = new Category(null, "escritorio");
		
		Product p1 = new Product(null, "computador", 2000.00);
		Product p2 = new Product(null, "impressora", 800.00);
		Product p3 = new Product(null, "mause", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryReposytory.saveAll(Arrays.asList(cat1,cat2));
		productReposytory.saveAll(Arrays.asList(p1,p2,p3));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Urberlandia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateReposytory.saveAll(Arrays.asList(est1,est2));
		cityReposytory.saveAll(Arrays.asList(c1,c2,c3));
		
	
		
	}

}
