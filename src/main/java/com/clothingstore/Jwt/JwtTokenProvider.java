package com.clothingstore.Jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.clothingstore.DTO.MyUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private final String secret ="qwertyuiop";
	
	//thời gian có hiệu lực của jwt
	//7ngay
	private final Long expiration = 604800000L; //1000*60*2L; 
	
	//Tạo jwt từ thông tin của user
	public String genarateToken(MyUser myuser) {
		// tạo ngày hết hạn của expiration
		Date DateExpiration = new Date(new Date().getTime()+expiration);
		return Jwts.builder().setSubject(myuser.getUsername()).setIssuer("JWT.IO")
				.setExpiration(DateExpiration)
				.claim("email", myuser.getUsername())
				.claim("role", myuser.getAuthorities().toArray())
				.signWith(SignatureAlgorithm.HS512,secret)
				.compact();
	}
	//lấy thông tin từ jwt
	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.get("email", String.class);
	} 
	
	public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
        	System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
        	System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
        	System.out.println("JWT claims string is empty.");
        }
        return false;
    }
}
