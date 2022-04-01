package com.tsa.ApiMC.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Address;
import com.tsa.ApiMC.entities.Category;
import com.tsa.ApiMC.entities.City;
import com.tsa.ApiMC.entities.Client;
import com.tsa.ApiMC.entities.OrderItem;
import com.tsa.ApiMC.entities.Payment;
import com.tsa.ApiMC.entities.PaymentBillet;
import com.tsa.ApiMC.entities.PaymentCard;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.entities.Order;
import com.tsa.ApiMC.entities.State;
import com.tsa.ApiMC.entities.enums.ClientType;
import com.tsa.ApiMC.entities.enums.PaymentStatus;
import com.tsa.ApiMC.repository.AddressRepository;
import com.tsa.ApiMC.repository.CategoryRepository;
import com.tsa.ApiMC.repository.CityRepository;
import com.tsa.ApiMC.repository.ClientRepository;
import com.tsa.ApiMC.repository.OrderItemRepository;
import com.tsa.ApiMC.repository.PaymentRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.repository.OrderRepository;
import com.tsa.ApiMC.repository.StateRepository;

@Service
public class DBService {
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
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public void instantiateTestDataBase() throws ParseException {
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e Banho");
		Category cat4 = new Category(null, "Eletronicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumação");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mause", 80.00);
		Product p4 = new Product(null, "Mesa de Escritorio", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "Tv True Color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajur", 100.00);
		Product p10 = new Product(null, "pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		categoryReposytory.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productReposytory.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Urberlandia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateReposytory.saveAll(Arrays.asList(est1, est2));
		cityReposytory.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PessoaFisica);
		cli1.getFone().addAll(Arrays.asList("27368954", "872524552"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 203", "jardim", "98959484784", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "centro", "9858249698", cli1, c2);

		cli1.getAddress().addAll(Arrays.asList(e1, e2));

		clientReposytory.saveAll(Arrays.asList(cli1));
		addressReposytory.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new PaymentCard(null, PaymentStatus.Quitado, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentBillet(null, PaymentStatus.Pendente, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getOrder().addAll(Arrays.asList(ped1, ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

		OrderItem ip1 = new OrderItem(ped1, p1, 0.00, 1, 2000.00);
		OrderItem ip2 = new OrderItem(ped1, p3, 0.00, 2, 80.00);
		OrderItem ip3 = new OrderItem(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
