package com.user.paymentservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PaymentResDTO {
    private String destination;
    private Long amount;
    private String status;
}
