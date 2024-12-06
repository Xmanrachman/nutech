package com.nutech.test.sims.ppob.authapi.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nutech.test.sims.ppob.authapi.interfaces.UserServiceInterface;
import com.nutech.test.sims.ppob.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {
	
	
	private final UserRepository userRepo;
	
	@Override
	public UserDetailsService userDetailsService() {
		
		 return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepo.findByEmailUser(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
	}


}
