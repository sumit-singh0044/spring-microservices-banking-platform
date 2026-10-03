package com.user.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "UserPayment")
public interface PaymentClinet {



}
