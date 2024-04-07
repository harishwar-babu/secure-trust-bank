package com.securetrustbank.admin.apidefinitions;
import com.securetrustbank.admin.dto.UserDetailsDto;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "admin-user-details",description = "View All User Details by admin")
public interface UserDetailsControllerApiDefinition {
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
