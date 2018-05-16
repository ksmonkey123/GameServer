package ch.awae.gameserver.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.awae.gameserver.core.User;
import lombok.Getter;

@Getter
public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = -2223297468611589462L;

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String token;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities,
			String token) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.token = token;
	}

	public static UserPrincipal create(User user, String token) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities, token);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "UserPrincipal [id=" + id + ", username=" + username + ", password=" + password + ", token=" + token
				+ ", authorities=" + authorities + "]";
	}
}
