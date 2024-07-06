package com.harcanjo.forum.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		var jwtToken = tokenRecover(request);		
		var subject = tokenService.getSubject(jwtToken);
		
		
		
		filterChain.doFilter(request, response);		
	}

	private String tokenRecover(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		
		if(authorizationHeader == null) {
			throw new RuntimeException("The JWT token was not sent in the authorization header.");
		}
		
		return authorizationHeader.replace("Bearer ", "");
	}

}
