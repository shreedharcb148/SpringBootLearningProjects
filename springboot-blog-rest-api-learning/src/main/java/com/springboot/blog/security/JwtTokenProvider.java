package com.springboot.blog.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt.secrete}")
	private String jwtSecret;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private String jwtExpirationDate;
	
	// generate JWT Token
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		
		Date currentDate = new Date();
		
		Date expireDate = new Date(currentDate.getTime()+jwtExpirationDate);
		
		String token = Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(expireDate)
			.signWith(key())
			.compact();
		
		return token;
		
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret));
	}
	
	//get username from jwt token
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parseClaimsJwt(token)
			.getBody();
		
		String username = claims.getSubject();
		return username;
	}
	
	//valid JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
		
		return true;
		}catch(MalformedJwtException mal) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		}catch(ExpiredJwtException exp) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Jwt token expired");
		}catch(UnsupportedJwtException uns) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
		}catch(IllegalArgumentException ill) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
		
	}
}
 