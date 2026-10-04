package com.user.paymentservice.controller;

import com.user.paymentservice.dto.AccounResDTO;
import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.PaymentReqDTO;
import com.user.paymentservice.dto.PaymentResDTO;
import com.user.paymentservice.entity.PaymentTransfer;
import com.user.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/validate")
    public ResponseEntity<AccounResDTO> creditAmount(@RequestBody BankDTO dto) {
        AccounResDTO res = paymentService.getBankDetails(dto);
        return ResponseEntity.status(200).body(res);
    }

}
