package com.mathew.expense_tracker.service;

import com.mathew.expense_tracker.dto.AuthRequest;
import com.mathew.expense_tracker.dto.AuthResponse;
import com.mathew.expense_tracker.model.User;
import com.mathew.expense_tracker.repository.UserRepository;
import com.mathew.expense_tracker.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new AuthResponse(jwtUtil.generateToken(request.getEmail()));
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return new AuthResponse(jwtUtil.generateToken(request.getEmail()));
    }
}