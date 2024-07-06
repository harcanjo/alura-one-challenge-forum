package com.harcanjo.forum.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.harcanjo.forum.domain.user.User;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		System.out.println(secret);
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("API Forum Hub")
		        .withSubject(user.getEmail())
		        .withClaim("id", user.getId())
		        .withClaim("name", user.getName())
		        .withExpiresAt(expirationDate())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Error while generating token jwt. ", exception);
		}
	}

	public String getSubject(String jwtToken) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("API Forum Hub")		        
		        .build()
		        .verify(jwtToken)
		        .getSubject();
		} catch (JWTVerificationException exception){
		    throw new RuntimeException("JWT token invalid or expired!", exception);
		}
	}
	
	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
