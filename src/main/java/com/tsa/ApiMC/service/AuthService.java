package com.tsa.ApiMC.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tsa.ApiMC.entities.Client;
import com.tsa.ApiMC.repository.ClientRepository;
import com.tsa.ApiMC.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if(client == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		String newPass = newPassWord();
		client.setSenha(bCryptPasswordEncoder.encode(newPass));
		
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client,newPass);
	}

	private String newPassWord() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if(opt == 0) { //gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) { //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
		
	}
}
