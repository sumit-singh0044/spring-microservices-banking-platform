package com.user.userpayment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentReqDTO {
    private String sourceAccountNumber;
    private String destinationBankAccount;
    private Long amount;
}
