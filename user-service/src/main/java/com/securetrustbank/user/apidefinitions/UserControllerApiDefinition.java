package com.securetrustbank.user.apidefinitions;

import com.securetrustbank.user.dto.UpdateDetailsDto;
import com.securetrustbank.user.dto.UserDetailsDto;
import com.securetrustbank.user.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "user-details",description = "User Details")
public interface UserControllerApiDefinition {
    @Operation(summary = "view Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Profile shown"),
            @ApiResponse(responseCode = "401",description = "access level exception,token exceptions")
    })
    ResponseEntity<UserDetailsDto> getProfile() throws UserNotFoundException;
    @Operation(summary = "update Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "updated"),
            @ApiResponse(responseCode = "401",description = "access level exception,token exceptions")
    })
    ResponseEntity<UserDetailsDto> updateProfile(@RequestBody UpdateDetailsDto updateDetails)
            throws UserNotFoundException;
    @Operation(summary = "view Profile by id. Note this is for admin functionality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "profile view"),
            @ApiResponse(responseCode = "401",description = "access level exceptions,token exceptions")
    })
    ResponseEntity<UserDetailsDto> getProfile(@PathVariable String userId) throws UserNotFoundException;
    @Operation(summary = "view All Profiles by Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "all profiles displayed"),
            @ApiResponse(responseCode = "401",description = "access level exception,token exceptions")
    })
    ResponseEntity<List<UserDetailsDto>> allProfiles();


}
