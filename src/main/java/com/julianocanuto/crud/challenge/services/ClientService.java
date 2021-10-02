package com.julianocanuto.crud.challenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julianocanuto.crud.challenge.entities.Client;
import com.julianocanuto.crud.challenge.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<Client> findAllPaged(PageRequest pageRequest) {
		Page<Client> listOfClients = repository.findAll(pageRequest);
		return listOfClients;
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.isPresent() ? obj.get() : new Client();
		return entity;
	}
}
