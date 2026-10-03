package com.user.paymentservice.service;

import com.user.paymentservice.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDTO getAllPayments(String acc) {
        return PaymentDTO.builder().destination(acc)
                .amount(1234L)
                .status("success")
                .build();
    }
}
