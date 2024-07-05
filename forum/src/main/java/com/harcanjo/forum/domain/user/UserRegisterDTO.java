package com.harcanjo.forum.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
		@NotBlank(message = "The name cannot be blank")
		String name,
		@NotBlank(message = "The email cannot be blank")
		@Email(message = "The email is not valid")
		String email,
		@NotBlank(message = "The password cannot be blank")
	    @Size(min = 8, max = 20, message = "The password must be between 8 and 20 characters long")
	    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
	             message = "The password must contain at least one uppercase letter, one lowercase letter, and one digit")
		String password) {

}
