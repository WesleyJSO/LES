package br.com.les.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.User;
import br.com.les.backend.repository.UserRepository;
import br.com.les.backend.utils.Util;

@Component
public class SecurityService implements UserDetailsService {
	
	@Autowired private UserRepository userRepository;
	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
	
	// Retrieve logged user
	public static DomainEntity getAuthenticatedUser () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		DomainEntity entity = null;
		if ( authentication.isAuthenticated() ) {
			entity = (DomainEntity) authentication.getPrincipal();
		}
		return entity;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Logger logger = LoggerFactory.getLogger( getClass() );
		logger.debug("Trying to authenticate ", username);
		User user = userRepository.findByEmail( username );
		if ( null == user ) {
			throw new UsernameNotFoundException(Util.INVALID_USER_IDENTIFICATION);
		}
		return user;
	}
	
	public static String encodeUserPassword ( String password ) {
		return passwordEncoder.encode( password );
	}
	
	public static boolean checkMatchPassword (String oldPassword, String storedPassword) {
		return passwordEncoder.matches(oldPassword, storedPassword);
	}
}
