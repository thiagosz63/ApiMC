package com.tsa.ApiMC.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsa.ApiMC.entities.OrderItem;
import com.tsa.ApiMC.entities.PaymentBillet;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.entities.Order;
import com.tsa.ApiMC.entities.enums.PaymentStatus;
import com.tsa.ApiMC.repository.OrderItemRepository;
import com.tsa.ApiMC.repository.PaymentRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.repository.OrderRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;

	@Transactional(readOnly = true)
	public Order find(Integer id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Order.class.getName()));
	}

	@Transactional
	public @Valid Order insert(@Valid Order obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setStatus(PaymentStatus.Pendente);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentBillet) {
			PaymentBillet pagto = (PaymentBillet) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem orderItem : obj.getItens()) {
			orderItem.setDesconto(0.0);
			Optional<Product> proOptional = productRepository.findById(orderItem.getProduct().getId());
			orderItem.setProduct(proOptional.get());
			orderItem.setPreco(orderItem.getProduct().getPrice());
			orderItem.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;

	}

}
