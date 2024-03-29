package com.julianocanuto.crud.challenge.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julianocanuto.crud.challenge.dto.ClientDTO;
import com.julianocanuto.crud.challenge.entities.Client;
import com.julianocanuto.crud.challenge.repositories.ClientRepository;
import com.julianocanuto.crud.challenge.services.exceptions.DatabaseException;
import com.julianocanuto.crud.challenge.services.exceptions.ResourceNotFoundException;

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
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		ClientDTO clientDTO = new ClientDTO(entity);
		return clientDTO;
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		Client entity = new Client();
		entity.setName(clientDTO.getName());
		entity.setCpf(clientDTO.getCpf());
		entity.setIncome(clientDTO.getIncome());
		entity.setChildren(clientDTO.getChildren());
		entity.setBirthDate(clientDTO.getBirthDate());
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client entity = repository.getOne(id);
			entity.setName(clientDTO.getName());
			entity.setCpf(clientDTO.getCpf());
			entity.setIncome(clientDTO.getIncome());
			entity.setChildren(clientDTO.getChildren());
			entity.setBirthDate(clientDTO.getBirthDate());
			entity = repository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " not found");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}
