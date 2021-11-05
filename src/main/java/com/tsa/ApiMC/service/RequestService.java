package com.tsa.ApiMC.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Request;
import com.tsa.ApiMC.repository.RequestRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;

	public Request buscar(Integer id) {
		Optional<Request> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Request.class.getName()));
	}

}
