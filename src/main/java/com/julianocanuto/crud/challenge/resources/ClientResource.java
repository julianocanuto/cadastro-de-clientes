package com.julianocanuto.crud.challenge.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julianocanuto.crud.challenge.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@GetMapping
	public ResponseEntity<List<Client>> findAll() {

		
		List<Client> list = new ArrayList<>();
		list.add(new Client(1L, "Maria da Silva", "79615238452", 3522.44, Instant.parse("1994-07-20T10:30:00Z"), 3));
		list.add(new Client(2L, "Joaquim Ribeiro", "85615238496", 15263.0, Instant.parse("1994-07-20T10:30:00Z"), 1));
		list.add(new Client(3L, "Orlando Pontes", "72215248502", 7552.99, Instant.parse("1994-07-20T10:30:00Z"), 0));

		return ResponseEntity.ok().body(list);
	}

}
