package com.securetrustbank.admin.serviceimplementation;
import com.securetrustbank.admin.dto.TransactionDetailsResponse;
import com.securetrustbank.admin.dto.UserDetailsDto;
import com.securetrustbank.admin.exceptions.UserNotFoundException;
import com.securetrustbank.admin.feignresponse.TransactionDetailsFeignResponse;
import com.securetrustbank.admin.feignresponse.UserServiceFeignResponse;
import com.securetrustbank.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserServiceFeignResponse userServiceFeignResponse;
    private final TransactionDetailsFeignResponse transactionDetailsFeignResponse;
    @Override
    public ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions() {
        return transactionDetailsFeignResponse.getAllAccountTransactions();
    }

    @Override
    public ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(String userId) {
        return transactionDetailsFeignResponse.getAllCustomerTransactions(userId);
    }

    @Override
    public ResponseEntity<UserDetailsDto> getProfile(String userId) throws UserNotFoundException {
        return userServiceFeignResponse.getProfile(userId);
    }

    @Override
    public ResponseEntity<List<UserDetailsDto>> allProfiles() {
        return userServiceFeignResponse.allProfiles();
    }
}
