package com.user.paymentservice.controller;

import com.user.paymentservice.dto.PaymentDTO;
import com.user.paymentservice.entity.PaymentTransfer;
import com.user.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{acc}")
    public ResponseEntity<PaymentDTO> getPayments(@PathVariable String acc) {
        PaymentDTO dto = paymentService.getAllPayments(acc);
        return ResponseEntity.status(200).body(dto);
    }

}
