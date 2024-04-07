package com.securetrustbank.admin.controller;
import com.securetrustbank.admin.dto.TransactionDetailsResponse;
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
public class TransactionDetailsController {
    private final AdminService adminService;
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions(){
        return adminService.getAllAccountTransactions();
    }
    @GetMapping("/userTransaction")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(@PathVariable String userId){
        return adminService.getAllCustomerTransactions(userId);
    }
}
