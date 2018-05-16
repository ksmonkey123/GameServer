package ch.awae.gameserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ch.awae.gameserver.core.User;
import ch.awae.gameserver.core.UserRepo;

@Component
public class SecurityFacade {

	@Autowired
	private UserRepo userRepository;

	public UserPrincipal getCurrentPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public Long getCurrentUserId() {
		return getCurrentPrincipal().getId();
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getCurrentToken() {
		return getCurrentPrincipal().getToken();
	}

	public User getCurrentUser() {
		return userRepository.findById(getCurrentUserId()).get();
	}

}
