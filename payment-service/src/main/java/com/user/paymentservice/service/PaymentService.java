package com.user.paymentservice.service;

import com.user.paymentservice.client.PaymentClinet;
import com.user.paymentservice.dto.AccounResDTO;
import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.PaymentReqDTO;
import com.user.paymentservice.dto.PaymentResDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentClinet paymentClinet;

    public PaymentService(PaymentClinet paymentClinet) {
        this.paymentClinet = paymentClinet;
    }


    public AccounResDTO getBankDetails(BankDTO dto) {

        return paymentClinet.getBankDetailsClient(dto).getBody();
    }
}
