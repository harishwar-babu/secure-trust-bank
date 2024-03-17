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
    public String generateToken(String userName){
        AuthenticationEntity authenticationEntity;
        authenticationEntity = authenticationRepository.findByUserId(userName).orElse(new AuthenticationEntity());
        if(authenticationEntity.getUserId()==null) {
            authenticationEntity = authenticationRepository.findByEmail(userName).orElse(new AuthenticationEntity());
        }
        String userId = authenticationEntity.getUserId();
        String userRole = authenticationEntity.getRole();
        String typeOfService = authenticationEntity.getBankService()!=null?authenticationEntity.getBankService():
                authenticationEntity.getCreditCardService();
        Map<String,String> claims = new HashMap<>();
        claims.put("userId",userId);
        claims.put("role",userRole);
        claims.put("service-type",typeOfService);
        return createToken(claims,userName);
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
