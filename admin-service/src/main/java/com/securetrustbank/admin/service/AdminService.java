package com.securetrustbank.admin.service;

import com.securetrustbank.admin.dto.TransactionDetailsResponse;
import com.securetrustbank.admin.dto.UserDetailsDto;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AdminService {
    ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions();
    ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(String userId);
    ResponseEntity<UserDetailsDto> getProfile(String userId)throws UserNotFoundException;
    ResponseEntity<List<UserDetailsDto>> allProfiles();

}
