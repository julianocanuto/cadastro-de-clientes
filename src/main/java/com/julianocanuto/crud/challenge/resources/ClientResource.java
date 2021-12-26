package com.julianocanuto.crud.challenge.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.julianocanuto.crud.challenge.dto.ClientDTO;
import com.julianocanuto.crud.challenge.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clients")
@Api(value = "REST API Clients")
@CrossOrigin(value = "*")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping
	@ApiOperation(value = "Return a list of pages with all clients register")
	public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<ClientDTO> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Return a client register given an Id")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO clientDTO = service.findById(id);

		return ResponseEntity.ok().body(clientDTO);
	}

	@PostMapping
	@ApiOperation(value = "Create a new client register")
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
		clientDTO = service.insert(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clientDTO);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "Update a client register given an Id")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
		clientDTO = service.update(id, clientDTO);

		return ResponseEntity.ok().body(clientDTO);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "Delete a client register given an Id")
	public ResponseEntity<Void> update(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
