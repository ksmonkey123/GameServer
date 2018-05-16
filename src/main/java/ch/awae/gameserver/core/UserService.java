package ch.awae.gameserver.core;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.awae.gameserver.exception.BadRequestException;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Transactional
	public User createUser(String username, String password) {
		if (!userRepo.findByUsername(username).isPresent())
			return userRepo.save(new User(username, password));
		else
			throw new BadRequestException("user already exists with that name");
	}

}
