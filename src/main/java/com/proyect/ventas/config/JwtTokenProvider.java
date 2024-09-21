package com.proyect.ventas.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "CAAB5BEF04B2CCA3F56B2B793BDF62C7DFF57A0C88DE6D7A2478745334ED68DA";
    private final long expirationTime = 86400000;  //24 horas

    private Key key;

    @PostConstruct
    protected  void init(){
        //convertir clave secreta en una clave firma
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    //Generar un token basado en la autenticacion
    public String generateToken(String username) {
        // Genera el token con el nombre de usuario y la fecha de expiración
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    //Extraer cuqleuir reclamo especifico del token
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Obtener todos los Claims (reclamos) del token JWT
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Verificar si el token a expirado
    public boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
    }

    // Obtener la fecha de expiración del token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //Validar Token JWT
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
