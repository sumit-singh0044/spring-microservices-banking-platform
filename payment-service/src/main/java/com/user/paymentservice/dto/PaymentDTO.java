package com.user.paymentservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class PaymentDTO {
    private String destination;
    private Long amount;
    private String status;
}
