package com.ptit.viet.identityservice.model.dto.request;

import com.ptit.viet.identityservice.model.entity.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotEmpty
    private List<Authority> authorities;
}
