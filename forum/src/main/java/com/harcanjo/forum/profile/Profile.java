package com.harcanjo.forum.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="profiles")
@Entity(name="Profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Boolean active;
	
	public Profile(ProfileRegisterDTO data){
		this.name = data.name();
		this.active = true;
	}

	public void updateProfileInformations(@Valid ProfileUpdateDTO data) {
		if (data.name() != null) {
			this.name = data.name();
		}		
	}

	public void inactivateProfile() {
		this.active = false;		
	}

}
