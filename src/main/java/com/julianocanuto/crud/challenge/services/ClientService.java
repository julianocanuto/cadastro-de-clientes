package com.julianocanuto.crud.challenge.services;

import java.util.List;

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
	public List<Client> findAll() {
		List<Client> listOfClients = repository.findAll();
		return listOfClients;
	}

	@Transactional(readOnly = true)
	public Page<Client> findAllPaged(PageRequest pageRequest) {
		Page<Client> listOfClients = repository.findAll(pageRequest);
		return listOfClients;
	}
}
