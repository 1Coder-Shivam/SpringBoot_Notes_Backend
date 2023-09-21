package com.nagarro.notes.service.impl;

import com.nagarro.notes.dto.UserDto;
import com.nagarro.notes.entity.CustomUserDetails;
import com.nagarro.notes.entity.User;
import com.nagarro.notes.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServiceImpl repository;

	@Autowired
	private UserMapper mapper;

	@Override
	public CustomUserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		final UserDto userDto=this.repository.fetchUserByEmailId(emailId);
		User user = mapper.dtoToUser(userDto);
		if(user==null) {
			return null;
		}
		else {
			return new CustomUserDetails(user);
		}
	}
}
