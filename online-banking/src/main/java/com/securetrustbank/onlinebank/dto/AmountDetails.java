package com.securetrustbank.onlinebank.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AmountDetails {
    @Min(value = 0,message = "value required")
    private Double amount;
}
