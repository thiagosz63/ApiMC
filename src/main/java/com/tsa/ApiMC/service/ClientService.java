package com.tsa.ApiMC.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsa.ApiMC.dto.ClientDTO;
import com.tsa.ApiMC.dto.ClientNewDTO;
import com.tsa.ApiMC.entities.Address;
import com.tsa.ApiMC.entities.City;
import com.tsa.ApiMC.entities.Client;
import com.tsa.ApiMC.entities.enums.ClientType;
import com.tsa.ApiMC.repository.AddressRepository;
import com.tsa.ApiMC.repository.ClientRepository;
import com.tsa.ApiMC.service.exceptions.DataIntegrityException;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private ClientRepository repository;
	@Autowired
	private AddressRepository addressRepository;

	@Transactional(readOnly = true)
	public Client find(Integer id) {
		Optional<Client> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto não encontrado! id:" + id + " Tipo: " + Client.class.getName()));
	}

	@Transactional
	public Client insert(Client obj) {
			obj = repository.save(obj);
			addressRepository.saveAll(obj.getAddress());
			return obj;
	}

	@Transactional
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que tenha Pedidos");
		}
	}

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findPage(Integer page, Integer linesPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Client> pages = repository.findAll(pageRequest);
		return pages.map(x -> new ClientDTO(x));
	}

	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null,null);
	}

	public Client fromDTO(ClientNewDTO objDTO) {
		Client cl1 = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				ClientType.toEnum(objDTO.getType()),bCryptPasswordEncoder.encode(objDTO.getSenha()));

		City city = new City(objDTO.getCityId(),null,null);

		Address add = new Address(null, objDTO.getLogradouro(), objDTO.getNumber(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cl1, city);

		cl1.getAddress().add(add);
		cl1.getFone().add(objDTO.getFone1());
		if (objDTO.getFone2() != null) {
			cl1.getFone().add(objDTO.getFone2());
		}
		if (objDTO.getFone3() != null) {
			cl1.getFone().add(objDTO.getFone3());
		}
		
		return cl1;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

	}
}
