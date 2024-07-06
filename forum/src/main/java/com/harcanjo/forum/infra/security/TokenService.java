package com.harcanjo.forum.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.harcanjo.forum.domain.user.User;

@Service
public class TokenService {
	
	public String generateToken(User user) {
		try {
		    var algorithm = Algorithm.HMAC256("12345678");
		    return JWT.create()
		        .withIssuer("API Forum Hub")
		        .withSubject(user.getEmail())
		        .withClaim("id", user.getId())
		        .withExpiresAt(expirationDate())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Error while generating token jwt. ", exception);
		}
	}

	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
