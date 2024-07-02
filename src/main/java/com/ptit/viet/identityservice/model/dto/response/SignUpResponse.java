package com.ptit.viet.identityservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private String username;
    private String password;
    private List<String> authorities;
}
