package com.julianocanuto.crud.challenge.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.julianocanuto.crud.challenge.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
