package com.securetrustbank.user.controller;
import com.securetrustbank.user.apidefinitions.UserControllerApiDefinition;
import com.securetrustbank.user.dto.UpdateDetailsDto;
import com.securetrustbank.user.dto.UserDetailsDto;
import com.securetrustbank.user.exceptions.UserNotFoundException;
import com.securetrustbank.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController implements UserControllerApiDefinition {
    private UserService userService;
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<UserDetailsDto> getProfile() throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        return ResponseEntity.ok(userService.viewProfile(userId));
    }
    @PutMapping("/updateProfile")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<UserDetailsDto> updateProfile(@RequestBody UpdateDetailsDto updateDetails)
            throws UserNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        return ResponseEntity.ok(userService.updateProfile(userId,updateDetails));
    }
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<UserDetailsDto> getProfile(@PathVariable String userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.viewProfile(userId));
    }
    @GetMapping("/allProfiles")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<UserDetailsDto>> allProfiles(){
        return ResponseEntity.ok(userService.viewAllUserProfiles());
    }
}
