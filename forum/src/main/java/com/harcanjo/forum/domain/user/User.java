package com.harcanjo.forum.domain.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.harcanjo.forum.domain.profile.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private Boolean active;

	// TODO: add this relationship to Profile
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
        name = "user_profile",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
	private Set<Profile> profiles;

	public User(UserRegisterDTO data, String passwordEncrypted) {
		this.name = data.name();
		this.email = data.email();
		this.password = passwordEncrypted;
		this.active = true;
		this.profiles = new HashSet<>();
	}
	
	public User(UserCreationDTO data, String passwordEncrypted) {
		this.name = data.name();
		this.email = data.email();
		this.password = passwordEncrypted;
		this.active = true;
		this.profiles = new HashSet<>();
	}

	public void updateUserInformations(@Valid UserUpdateDTO data) {
		if (data.name() != null) {
			this.name = data.name();
		}
		
		if (data.password() != null) {
			this.password = data.password();
		}
	}

	public void inactivateUser() {
		this.active = false;		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return this.email;
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
	
}
