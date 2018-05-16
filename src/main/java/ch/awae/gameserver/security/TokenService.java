package ch.awae.gameserver.security;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ch.awae.gameserver.core.User;

@Service
public class TokenService {

	@Autowired
	private TokenRepo repo;

	@Autowired
	private SecurityFacade securityFacade;

	public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;

	@Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
	public void evictExpiredTokens() {
		// TODO: implement
	}

	public Token getToken(String tokenString) {
		Token token = repo.findByToken(tokenString).orElse(null);
		if (token == null)
			return null;
		// only return token if it is still valid
		if (token.getExpirationDate().after(new Date()))
			return token;
		return null;
	}

	public void revokeToken(String tokenString) {
		repo.findByToken(tokenString).ifPresent(repo::delete);
	}

	public String createToken() {
		User user = securityFacade.getCurrentUser();
		String token = UUID.randomUUID().toString();
		Timestamp cre_dat = new Timestamp(System.currentTimeMillis());
		Timestamp exp_dat = new Timestamp(System.currentTimeMillis() + HALF_AN_HOUR_IN_MILLISECONDS);

		repo.save(new Token(user, token, cre_dat, exp_dat));
		return token;
	}

}
