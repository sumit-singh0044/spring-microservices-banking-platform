package com.user.paymentservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentReqDTO {
    private String destinationBankAccount;
    private Long amount;
}
