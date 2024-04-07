package com.securetrustbank.admin.feignresponse;
import com.securetrustbank.admin.dto.UserDetailsDto;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;
@FeignClient(name = "user-service",url = "/securebnk/user")
public interface UserServiceFeignResponse {
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAuthority('manager')")
    @CircuitBreaker(name = "user-service",fallbackMethod = "profileFallBack")
    ResponseEntity<UserDetailsDto> getProfile(@PathVariable String userId)throws UserNotFoundException;
    @GetMapping("/allProfiles")
    @PreAuthorize("hasAuthority('manager')")
    @CircuitBreaker(name = "user-service",fallbackMethod = "allProfilesFallBack")
    ResponseEntity<List<UserDetailsDto>> allProfiles();


    default ResponseEntity<UserDetailsDto> profileFallBack(CallNotPermittedException exception){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new UserDetailsDto());
    }
    default ResponseEntity<List<UserDetailsDto>> allProfilesFallBack(CallNotPermittedException exception){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ArrayList<>());
    }
}
