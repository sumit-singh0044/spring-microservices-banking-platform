package com.user.paymentservice.service;

import com.user.paymentservice.client.PaymentClinet;
import com.user.paymentservice.dto.AccounResDTO;
import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.PaymentReqDTO;
import com.user.paymentservice.dto.PaymentResDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private PaymentClinet paymentClinet;

    public PaymentService(PaymentClinet paymentClinet) {
        this.paymentClinet = paymentClinet;
    }


    public AccounResDTO getBankDetails(BankDTO dto) {

        return paymentClinet.getBankDetailsClient(dto).getBody();
    }


    public PaymentResDTO processClientPayment(PaymentReqDTO dto) {

        PaymentResDTO response = paymentClinet
                .amountTransferClient(dto)
                .getBody();

        if ("FAILURE".equals(response.getStatus())) {
            return PaymentResDTO.builder()
                    .destination(response.getDestination())
                    .amount(response.getAmount())
                    .status("Transfer failed")
                    .build();
        }

        return response; 

    }
}
