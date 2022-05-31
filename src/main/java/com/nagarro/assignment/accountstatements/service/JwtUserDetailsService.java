package com.nagarro.assignment.accountstatements.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.assignment.accountstatements.exception.AppException;
import com.nagarro.assignment.accountstatements.exception.ErrorCodes;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return new User("admin", "$2a$10$F5LIO3Y8sYHSYdvRMdEl2eMjSKxUuY61lrUPNmACjxtqEs9EN/YBK",
					new ArrayList<>());
		} else if ("user".equals(username)) {
			return new User("user", "$2a$10$Mv5QPHOmtJRMM4eU0LNNFuOy061oxH8b8vzQMZt9T2nIess5jKduK",
					new ArrayList<>());
		} else {
			throw new AppException(ErrorCodes.SYS_INVALID_USER);
		}
	}
}
