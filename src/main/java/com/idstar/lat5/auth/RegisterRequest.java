package com.idstar.lat5.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String name;
    private String phone_number;
    private String domicile;
    private String gender;
    private String password;
    private String fullname;

}
