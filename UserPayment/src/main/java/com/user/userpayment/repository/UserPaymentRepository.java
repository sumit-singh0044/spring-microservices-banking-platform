package com.user.userpayment.repository;

import com.user.userpayment.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPaymentRepository extends JpaRepository<Account, Long> {

    void deleteByUserID(Long userID);

    Optional<Account> getAccountByAccountNumber(String accountNumber);


}
