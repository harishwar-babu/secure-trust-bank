package com.securetrustbank.admin.controller;

import com.securetrustbank.admin.apidefinitions.UserDetailsControllerApiDefinition;
import com.securetrustbank.admin.dto.UserDetailsDto;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import com.securetrustbank.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserDetailsController implements UserDetailsControllerApiDefinition {
    private final AdminService adminService;
    @GetMapping("/profile/{userId}")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<UserDetailsDto> getProfile(@PathVariable String userId)throws UserNotFoundException{
        return adminService.getProfile(userId);
    }
    @GetMapping("/allProfiles")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<UserDetailsDto>> allProfiles(){
        return adminService.allProfiles();
    }
}
