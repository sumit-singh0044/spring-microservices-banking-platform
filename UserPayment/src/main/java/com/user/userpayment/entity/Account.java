package com.user.userpayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Long userID;
    private String AccountNumber;
    private Long balance;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public long getUserID() {
//        return userID;
//    }
//
//    public void setUserID(long userID) {
//        this.userID = userID;
//    }
//
//    public String getAccountNumber() {
//        return AccountNumber;
//    }
//
//    public void setAccountNumber(String accountNumber) {
//        this.AccountNumber = accountNumber;
//    }
}
