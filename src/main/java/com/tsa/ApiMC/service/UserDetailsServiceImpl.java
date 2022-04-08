package com.tsa.ApiMC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Client;
import com.tsa.ApiMC.repository.ClientRepository;
import com.tsa.ApiMC.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(email);
		if(client == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(client.getId(), client.getEmail(),client.getSenha(), client.getPerfis());
	}

}
