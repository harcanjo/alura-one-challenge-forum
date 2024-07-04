package com.harcanjo.forum.user;

import java.util.ArrayList;
import java.util.List;

import com.harcanjo.forum.profile.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "User")
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

	/*
	@ManyToMany
    @JoinTable(
        name = "user_profile",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
	private List<Profile> profiles;
	*/

	public User(UserRegisterDTO data) {
		this.name = data.name();
		this.email = data.email();
		this.password = data.password();
		// this.profiles = new ArrayList<>();
	}
	
}
