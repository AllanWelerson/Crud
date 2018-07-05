package com.crud.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.crud.models.User;
import com.crud.repository.UserRepository;

@Repository
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		User user = ur.findOne(login);
		System.out.println(user.getName() + " - " + user.getLogin() + " - " + user.getPassword());
		if(user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return user;
		//return new User(user.getUsername(), user.getPassword(),true, true, true,true,user.getAuthorities());
	}

}
