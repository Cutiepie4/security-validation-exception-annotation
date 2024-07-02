package com.ptit.viet.identityservice.controller;

import com.ptit.viet.identityservice.exception.ApiException;
import com.ptit.viet.identityservice.mapper.IUserMapper;
import com.ptit.viet.identityservice.model.dto.request.LoginRequest;
import com.ptit.viet.identityservice.model.dto.request.SignUpRequest;
import com.ptit.viet.identityservice.model.dto.response.ApiResponse;
import com.ptit.viet.identityservice.model.dto.response.LoginResponse;
import com.ptit.viet.identityservice.model.dto.response.SignUpResponse;
import com.ptit.viet.identityservice.security.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IUserMapper userMapper;

    private final AuthenticationServiceImpl authenticationService;

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<?>> test() {
        return ResponseEntity.ok(ApiResponse.builder().message("okok").build());
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest loginRequest) throws ApiException {
        LoginResponse response = authenticationService.authenticate(loginRequest);
        if (response == null)
            return ResponseEntity.status(400).body(ApiResponse.builder().message("Login failed").build());
        return ResponseEntity.ok(ApiResponse.builder().code(200).message("Login success!").data(response).build());
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<?>> signUp(@RequestBody SignUpRequest signUpRequest) throws ApiException {
        SignUpResponse signUpResponse = userMapper.toUserResponse(authenticationService.createUser(signUpRequest));
        return ResponseEntity.status(201).body(ApiResponse.builder().code(201).message("Sign up successfully").data(signUpResponse).build());
    }
}
