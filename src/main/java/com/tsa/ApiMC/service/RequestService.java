package com.tsa.ApiMC.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.OrderItem;
import com.tsa.ApiMC.entities.PaymentBillet;
import com.tsa.ApiMC.entities.Product;
import com.tsa.ApiMC.entities.Request;
import com.tsa.ApiMC.entities.enums.PaymentStatus;
import com.tsa.ApiMC.repository.OrderItemRepository;
import com.tsa.ApiMC.repository.PaymentRepository;
import com.tsa.ApiMC.repository.ProductRepository;
import com.tsa.ApiMC.repository.RequestRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public Request find(Integer id) {
		Optional<Request> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Request.class.getName()));
	}

	public @Valid Request insert(@Valid Request obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPayment().setStatus(PaymentStatus.Pendente);
		obj.getPayment().setRequest(obj);
		if(obj.getPayment() instanceof PaymentBillet) {
			PaymentBillet pagto = (PaymentBillet) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(OrderItem orderItem : obj.getItens()) {
			orderItem.setDesconto(0.0);
			Optional<Product> proOptional = productRepository.findById(orderItem.getProduct().getId());
			orderItem.setPreco(proOptional.get().getPrice());
			orderItem.setRequest(obj);
		}
		orderItemRepository.saveAll(obj.getItens());
		return obj;
		
	}

}
