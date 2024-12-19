package joels.peoplehub.domain.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="users")
@Entity(name="user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		return pass;
	}
	@Override
	public String getUsername() {
		return user;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isAccountNonExpired() {
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
		@Override
		public String getPassword() {
			return pass;
		}
		@Override
		public String getUsername() {
			return user;
		}
		@Override
		public boolean isEnabled() {
			return true;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		@Override
		public boolean isAccountNonLocked() {
			return true;
		}
		@Override
		public boolean isAccountNonExpired() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return List.of(new SimpleGrantedAuthority("ROLE_USER"));
			}
			@Override
			public String getPassword() {
				return pass;
			}
			@Override
			public String getUsername() {
				return user;
			}
			@Override
			public boolean isEnabled() {
				return true;
			}
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			@Override
			public boolean isAccountNonExpired() {
				
}
