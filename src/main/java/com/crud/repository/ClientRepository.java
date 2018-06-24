package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
