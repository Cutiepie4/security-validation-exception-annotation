package com.ptit.viet.identityservice.security;

import com.ptit.viet.identityservice.exception.ApiException;
import com.ptit.viet.identityservice.model.dto.request.LoginRequest;
import com.ptit.viet.identityservice.model.dto.request.SignUpRequest;
import com.ptit.viet.identityservice.model.dto.response.LoginResponse;
import com.ptit.viet.identityservice.model.entity.Authority;
import com.ptit.viet.identityservice.model.entity.User;
import com.ptit.viet.identityservice.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl {

    private final AuthenticationManager authenticationManager;

    private final IUserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public User createUser(SignUpRequest request) throws ApiException {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ApiException(400, "Username existed.");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(List.of(Authority.USER))
                .build();
        return userRepository.save(user);
    }

    public LoginResponse authenticate(LoginRequest loginRequest) throws ApiException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (Exception e) {
            throw new ApiException(401, "Unauthorized");
        }
        String token = jwtUtil.generateToken(loginRequest.getUsername());
        return new LoginResponse(token);
    }
}
