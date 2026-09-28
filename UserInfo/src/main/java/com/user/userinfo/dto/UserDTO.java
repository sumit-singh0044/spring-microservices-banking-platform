package com.user.userinfo.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private String accountNumber;
}
