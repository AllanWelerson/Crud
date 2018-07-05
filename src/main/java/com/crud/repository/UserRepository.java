package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
