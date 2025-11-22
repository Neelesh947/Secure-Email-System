package in.neelesh.Email.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtUtils {

	private final String jwtSecret = "MySuperSecretKeyForJWTGeneration12345";
	private final long defaultExpirationMs = 24 * 60 * 60 * 1000;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String generateToken(String role, String email, String uuid, String fullName) {
		long expirationInMs = getExpirationByRole(role);
		return Jwts.builder().setSubject(email).claim("role", role).claim("Name", fullName).claim("userId", uuid)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expirationInMs))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	private long getExpirationByRole(String role) {
		switch (role.toUpperCase()) {
		case "ADMIN":
			return 2 * 60 * 60 * 1000;
		case "USER":
		default:
			return defaultExpirationMs;
		}
	}

	public String getEmailFromToken(String token) {
		return parseClaims(token).getSubject();
	}

	public String getRoleFromToken(String token) {
		return parseClaims(token).get("role", String.class);
	}

	public boolean validateToken(String token) {
		try {
			parseClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	private Claims parseClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
}
