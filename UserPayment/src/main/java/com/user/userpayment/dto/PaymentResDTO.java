package com.user.userpayment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResDTO {
    private String destination;
    private Long amount;
    private String status;

}
