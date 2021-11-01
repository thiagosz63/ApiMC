package com.tsa.ApiMC;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tsa.ApiMC.entities.Address;
import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.entities.City;
import com.tsa.ApiMC.entities.Client;
import com.tsa.ApiMC.entities.Payment;
import com.tsa.ApiMC.entities.PaymentBillet;
import com.tsa.ApiMC.entities.PaymentCard;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.entities.Request;
import com.tsa.ApiMC.entities.State;
import com.tsa.ApiMC.entities.enums.ClientType;
import com.tsa.ApiMC.entities.enums.PaymentStatus;
import com.tsa.ApiMC.repository.AddressRepository;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.repository.CityRepository;
import com.tsa.ApiMC.repository.ClientRepository;
import com.tsa.ApiMC.repository.PaymentRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.repository.RequestRepository;
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
	
	@Autowired
	private ClientRepository clientReposytory;
	
	@Autowired
	private AddressRepository addressReposytory;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
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
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PessoaFisica);
		cli1.getFone().addAll(Arrays.asList("27368954","872524552"));
		
		Address e1 = new Address(null,"Rua Flores","300","Apto 203","jardim", "98959484784", cli1, c1);
		Address e2 = new Address(null,"Avenida Matos","105","Sala 800","centro", "9858249698", cli1, c2);
		
		cli1.getAddress().addAll(Arrays.asList(e1,e2));
		
		clientReposytory.saveAll(Arrays.asList(cli1));
		addressReposytory.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Request ped1 = new Request(null, sdf.parse("30/09/2017 10:32"),cli1, e1);
		Request ped2 = new Request(null, sdf.parse("10/10/2017 19:35"),cli1, e2);
		
		Payment pagto1 = new PaymentCard(null,PaymentStatus.Quitado,ped1,6);
		ped1.setPayment(pagto1);
		
		Payment pagto2 = new PaymentBillet(null,PaymentStatus.Pendente,ped2, sdf.parse("20/10/2017 00:00"),null);
		ped2.setPayment(pagto2);
		
		cli1.getRequest().addAll(Arrays.asList(ped1,ped2));
		
		requestRepository.saveAll(Arrays.asList(ped1,ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
	}

}
