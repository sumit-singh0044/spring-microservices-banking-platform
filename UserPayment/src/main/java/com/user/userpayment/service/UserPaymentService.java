package com.user.userpayment.service;

import com.user.userpayment.dto.AccountResDTO;
import com.user.userpayment.dto.BankDTO;
import com.user.userpayment.repository.UserPaymentRepository;
import com.user.userpayment.dto.AccountRequest;
import com.user.userpayment.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;
    public UserPaymentService(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    public Account saveAccountDetails(AccountRequest request) {

        // Implement the logic to save account details here
        // For example, you can call a repository to save the account information in the database

        // For now, let's just return a success response

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setUserID(request.getId());
        account.setAccountNumber("ACC"+request.getId()+request.getId()*123456);

        Account acc =  userPaymentRepository.save(account);
        return acc;
    }

    public List<Account> getAllAccount() {
        return userPaymentRepository.findAll();
    }

    @Transactional
    public void deleteAccount(Long userId) {
        userPaymentRepository.deleteByUserID(userId);
    }



//    Client service

    public AccountResDTO getAccountDetails(BankDTO bankDTO) {

        Account account = userPaymentRepository
                .getAccountByAccountNumber(bankDTO.getBankAccountNumber())
                .orElseThrow(() -> new RuntimeException(
                        "Account not found: " + bankDTO.getBankAccountNumber()
                ));

        return AccountResDTO.builder()
                .bankAccount(account.getAccountNumber())
                .balance(account.getBalance())
                .build();
    }
}

//------+-----------------+--------+----------------+---------+
//        | id   | email           | userID | account_number | balance |
//        +------+-----------------+--------+----------------+---------+
//        | 1036 | sumit@gmail.com |     47 | ACC475802432   |    1000 |
//        | 1037 | ayush@gmail.com |     48 | ACC485925888   |    1000 |
//        +------+-----------------+--------+----------------+---------+
