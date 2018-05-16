package ch.awae.gameserver.core;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	@Getter
	private Long id;

	@NotBlank
	@Size(max = 15)
	@Column(nullable = false, unique = true, updatable = false)
	@Getter
	@Setter
	private String username;

	@NotBlank
	@Size(max = 100)
	@Column(nullable = false)
	@Getter
	@Setter
	private String password;

	@Column(updatable = false, nullable = false)
	@Getter
	private Timestamp memberSince;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.memberSince = new Timestamp(System.currentTimeMillis());
	}

}
