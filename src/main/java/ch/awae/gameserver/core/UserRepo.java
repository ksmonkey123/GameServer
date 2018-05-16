package ch.awae.gameserver.core;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String user);

}