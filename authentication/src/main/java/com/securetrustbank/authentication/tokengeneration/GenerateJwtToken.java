package com.securetrustbank.authentication.tokengeneration;
import com.securetrustbank.authentication.configurations.JwtConfiguration;
import com.securetrustbank.authentication.entity.AuthenticationEntity;
import com.securetrustbank.authentication.repository.AuthenticationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Component
@AllArgsConstructor
public class GenerateJwtToken {
    private JwtConfiguration jwtConfiguration;
    private AuthenticationRepository authenticationRepository;
    public String generateToken(String userId,String type){
        String userRole;
        userRole = authenticationRepository.findByUserId(userId).orElse(new AuthenticationEntity()).getRole();
        if(userRole==null) {
            userRole = authenticationRepository.findByEmailId(userId).orElse(new AuthenticationEntity()).getRole();
        }
        Map<String,String> claims = new HashMap<>();
        claims.put("userId",userId);
        claims.put("role",userRole);
        claims.put("service-type",type);
        System.out.println(claims);
        return createToken(claims,userId);
    }

    private String createToken(Map<String, String> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(
                        jwtConfiguration.getExpirationTime())))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    Key getSignKey(){
        byte[] keys = Decoders.BASE64.decode(jwtConfiguration.getSecretKey());
        return Keys.hmacShaKeyFor(keys);
    }
}
