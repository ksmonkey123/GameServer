package ch.awae.gameserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.awae.gameserver.core.User;
import ch.awae.gameserver.core.UserRepo;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepo userRepo, PasswordEncoder bcrypt) {
		return (args) -> {
			if (!userRepo.findByUsername("test").isPresent()) {
				userRepo.save(new User("test", bcrypt.encode("password")));
			}
		};
	}
}
