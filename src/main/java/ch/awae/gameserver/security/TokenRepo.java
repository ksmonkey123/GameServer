package ch.awae.gameserver.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {

	Optional<Token> findByToken(String token);

}
