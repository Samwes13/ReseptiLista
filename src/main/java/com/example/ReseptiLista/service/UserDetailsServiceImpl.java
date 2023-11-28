package com.example.ReseptiLista.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ReseptiLista.domain.AppUserRepository;
import com.example.ReseptiLista.domain.AppUser;


/**
 * This class is used by spring controller to authenticate and authorize user
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	private final AppUserRepository repository;

	@Autowired
	public UserDetailsServiceImpl(AppUserRepository repository) {
		this.repository = repository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	AppUser curruser = repository.findByUsername(username);

		UserBuilder builder = null;
    	if (curruser == null) {
	    	throw new UsernameNotFoundException("User not found.");
    	}
    	else {
	    	builder = org.springframework.security.core.userdetails.User.withUsername(username);
	    	builder.password(curruser.getPasswordHash());
	    	builder.roles(curruser.getRole()); 
    	}
    	
	    return builder.build();
    }

}