package com.user.paymentservice.client;

import com.user.paymentservice.dto.AccounResDTO;
import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.PaymentReqDTO;
import com.user.paymentservice.dto.PaymentResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "UserPayment", path = "/accounts")
public interface PaymentClinet {

    @PostMapping("/bank")
    ResponseEntity<AccounResDTO> getBankDetailsClient(BankDTO bankDTO);

    @PostMapping("/credit")
    ResponseEntity<PaymentResDTO> amountTransferClient(@RequestBody PaymentReqDTO dto);

}
