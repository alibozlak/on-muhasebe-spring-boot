package dev.bozlak.on_muhasebe_spring_boot.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String generateJwtToken(UserDetails userDetails, Map<String,Object> extraClaims);
    Boolean isTokenValid(String jwtToken, UserDetails userDetails);
    String getUserNameFromToken(String jwtToken);
    Short getAdminIdFromToken(String jwtToken);
    Integer getUserIdFromToken(String jwtToken);
}
