package dev.bozlak.on_muhasebe_spring_boot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public String generateJwtToken(UserDetails userDetails, Map<String,Object> extraClaims) {
        return this.buildToken(extraClaims, userDetails);
    }

    @Override
    public Boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        return !this.isTokenExpired(jwtToken) &&
                userDetails.getUsername().equals(this.getUserNameFromToken(jwtToken));
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails){
        long currentTimeMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + (long) 1000 * 60 * 60 * 24 * 30))
                .signWith(this.getSigningKey())
                .compact();
    }

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaims(String jwtToken){
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private <T> T extractClaim(String jwtToken, Function<Claims,T> claimsResolver){
        final Claims claims = this.extractClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String jwtToken){
        return this.extractClaim(jwtToken, Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwtToken){
        return this.extractExpiration(jwtToken).before(new Date());
    }

    @Override
    public String getUserNameFromToken(String jwtToken){
        return this.extractClaim(jwtToken, Claims::getSubject);
    }

    @Override
    public Short getAdminIdFromToken(String jwtToken) {
        Claims claims = this.extractClaims(jwtToken);
        return claims.get("adminId", Short.class);
    }
}
