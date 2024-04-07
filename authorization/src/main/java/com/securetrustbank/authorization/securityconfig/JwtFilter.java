package com.securetrustbank.authorization.securityconfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final ValidateToken validateToken;
    @Autowired
    public JwtFilter(ValidateToken validateToken){
        this.validateToken=validateToken;
    }
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if(requestUri.contains("/apiDocs") || requestUri.contains("actuator/health") ||
                requestUri.contains("swagger-ui/index.html")) {
            return;
        }
        String authHead = request.getHeader("Authorization");
        String token=null;
        String userName=null;
        try{
            if(authHead!=null && authHead.startsWith("Bearer")){
                token=authHead.substring(7);
                userName=validateToken.extractUserName(token);
                if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    List<SimpleGrantedAuthority> roles = Arrays.stream(validateToken.extractRole(token).split(","))
                            .map(SimpleGrantedAuthority::new).toList();
                    UserDetails userDetails = new User(userName,"password",roles);
                    if(validateToken.tokenValidation(token)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                                (userDetails,null,userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        System.out.println(SecurityContextHolder.getContext().getAuthentication());
                    }
                }
            }
            if(authHead==null){
                throw new AccountNotFoundException("account does not exists");
            }
            filterChain.doFilter(request,response);
        }
        catch (Exception exception){
            handlerExceptionResolver.resolveException(request,response,null,exception);
        }
    }
}
