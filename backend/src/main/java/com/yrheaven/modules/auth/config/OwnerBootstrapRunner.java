package com.yrheaven.modules.auth.config;

import com.yrheaven.modules.auth.entity.Role;
import com.yrheaven.modules.auth.entity.User;
import com.yrheaven.modules.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OwnerBootstrapRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(OwnerBootstrapRunner.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ownerEmail;
    private final String ownerPassword;
    private final String ownerName;
    private final String ownerPhone;

    public OwnerBootstrapRunner(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.bootstrap.owner-email}") String ownerEmail,
            @Value("${app.bootstrap.owner-password}") String ownerPassword,
            @Value("${app.bootstrap.owner-name}") String ownerName,
            @Value("${app.bootstrap.owner-phone}") String ownerPhone
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.ownerEmail = ownerEmail;
        this.ownerPassword = ownerPassword;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    @Override
    public void run(String... args) {
        if (userRepository.existsByRole(Role.ROLE_OWNER)) {
            return;
        }

        User owner = new User();
        owner.setEmail(ownerEmail.trim().toLowerCase());
        owner.setFullName(ownerName.trim());
        owner.setPhone(ownerPhone.trim());
        owner.setPasswordHash(passwordEncoder.encode(ownerPassword));
        owner.setRole(Role.ROLE_OWNER);
        owner.setActive(true);

        userRepository.save(owner);
        log.info("Initialized initial ROLE_OWNER account for email: {}", owner.getEmail());
    }
}