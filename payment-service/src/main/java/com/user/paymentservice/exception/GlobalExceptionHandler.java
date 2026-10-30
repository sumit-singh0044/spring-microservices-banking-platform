package com.user.paymentservice.exception;

import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.FallBackExDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GetBankDetailsFallbackException.class)
    public ResponseEntity<FallBackExDTO> getBankDetailsFallback(
            GetBankDetailsFallbackException ex) {

        FallBackExDTO dto =  new FallBackExDTO(ex.getMessage(), new Date());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }
}
