package com.user.paymentservice.exception;

public class GetBankDetailsFallbackException extends RuntimeException {
    public GetBankDetailsFallbackException(String message) {
        super(message);
    }
}
