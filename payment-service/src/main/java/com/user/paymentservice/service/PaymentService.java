package com.user.paymentservice.service;

import com.user.paymentservice.client.PaymentClinet;
import com.user.paymentservice.dto.AccounResDTO;
import com.user.paymentservice.dto.BankDTO;
import com.user.paymentservice.dto.PaymentReqDTO;
import com.user.paymentservice.dto.PaymentResDTO;
import com.user.paymentservice.exception.GetBankDetailsFallbackException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PaymentService {

    private PaymentClinet paymentClinet;
    private final CircuitBreakerRegistry circuitBreakerRegistry;


    public PaymentService(PaymentClinet paymentClinet ,  CircuitBreakerRegistry circuitBreakerRegistry) {

        this.paymentClinet = paymentClinet;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }


//    @RateLimiter(name= "payment-service-getBank" , fallbackMethod = "getBankDetailsFallback")
//    @Retry(name = "payment-service-getBank" , fallbackMethod = "getBankDetailsFallback")
    @CircuitBreaker(
            name = "payment-service-getBank",
            fallbackMethod = "getBankDetailsFallback"
    )
    public AccounResDTO getBankDetails(BankDTO dto) {

        return paymentClinet.getBankDetailsClient(dto).getBody();
    }

    public AccounResDTO getBankDetailsFallback(
            BankDTO dto,
            Throwable ex) {

        log.error(
                "Fallback executed. Reason: {}",
                ex.getMessage()
        );

        throw new GetBankDetailsFallbackException(
                "Unable to fetch account details"
        );
    }

    public void printCircuitBreakerState() {

        var circuitBreaker = circuitBreakerRegistry
                .circuitBreaker("payment-service-getBank");

        log.info(
                "Circuit Breaker State: {}",
                circuitBreaker.getState()
        );
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
