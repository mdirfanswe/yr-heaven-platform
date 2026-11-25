package com.yrheaven.modules.auth.service;

import com.yrheaven.common.exception.BusinessException;
import com.yrheaven.modules.auth.dto.AuthResponse;
import com.yrheaven.modules.auth.dto.LoginRequest;
import com.yrheaven.modules.auth.dto.SignupRequest;
import com.yrheaven.modules.auth.entity.Role;
import com.yrheaven.modules.auth.entity.User;
import com.yrheaven.modules.auth.repository.UserRepository;
import com.yrheaven.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthResponse registerCustomer(SignupRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();
        if (userRepository.existsByEmailIgnoreCase(normalizedEmail)) {
            throw new BusinessException("An account with this email already exists.", HttpStatus.CONFLICT);
        }

        User customer = new User();
        customer.setFullName(request.fullName().trim());
        customer.setEmail(normalizedEmail);
        customer.setPasswordHash(passwordEncoder.encode(request.password()));
        customer.setPhone(request.phone() != null && !request.phone().isBlank() ? request.phone().trim() : null);
        // Public registration strictly creates ROLE_CUSTOMER accounts
        customer.setRole(Role.ROLE_CUSTOMER);
        customer.setActive(true);

        User saved = userRepository.save(customer);
        String token = jwtService.generateToken(saved);

        return new AuthResponse(token, saved.getId(), saved.getFullName(), saved.getEmail(), saved.getRole().name());
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(normalizedEmail, request.password())
        );

        User user = userRepository.findByEmailIgnoreCase(normalizedEmail)
                .orElseThrow(() -> new BusinessException("User account not found.", HttpStatus.UNAUTHORIZED));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getId(), user.getFullName(), user.getEmail(), user.getRole().name());
    }

    @Transactional(readOnly = true)
    public AuthResponse getCurrentUser(String email) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BusinessException("User not found.", HttpStatus.NOT_FOUND));
        return new AuthResponse(null, user.getId(), user.getFullName(), user.getEmail(), user.getRole().name());
    }
}