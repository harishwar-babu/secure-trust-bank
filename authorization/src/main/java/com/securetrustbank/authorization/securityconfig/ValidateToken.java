package com.securetrustbank.authorization.securityconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class ValidateToken {
    public String extractUserName(String token){
        return extractAllClaims(token).get("userId").toString();
    }
    public Date getExpirationDate(String token){
        return extractAllClaims(token).getExpiration();
    }
    public String extractRole(String token){
        return extractAllClaims(token).get("role").toString();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        String jwtSecret = "SecureTrustBankFSKfo+3f6jDrc3fOeJ2vtiLoB+Pn/zNpBv1gSykXb0I0jPVH5uoSt/aBzPtw+eq";
        byte[] keys = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keys);
    }
    public boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }
    public boolean tokenValidation(String token){
        return !isTokenExpired(token);
    }

}
