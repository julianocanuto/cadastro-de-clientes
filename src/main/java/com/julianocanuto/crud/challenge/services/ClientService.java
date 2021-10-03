package com.julianocanuto.crud.challenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julianocanuto.crud.challenge.dto.ClientDTO;
import com.julianocanuto.crud.challenge.entities.Client;
import com.julianocanuto.crud.challenge.repositories.ClientRepository;
import com.julianocanuto.crud.challenge.services.exceptions.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> listOfClients = repository.findAll(pageRequest);
		return listOfClients.map(client -> new ClientDTO(client));

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		ClientDTO clientDTO = new ClientDTO(entity);
		return clientDTO;
	}
}
