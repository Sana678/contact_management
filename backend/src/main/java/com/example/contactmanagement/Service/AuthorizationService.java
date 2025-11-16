package com.example.contactmanagement.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.contactmanagement.DTO.LoginRequest;
import com.example.contactmanagement.DTO.RegisterRequest;
import com.example.contactmanagement.Entity.UserEntity;
import com.example.contactmanagement.Repository.UserRepository;
import com.example.contactmanagement.Util.JwtUtil;


@Service
public class AuthorizationService {
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizationService.class);
	
private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

private final JwtUtil jwtUtil;

public AuthorizationService(UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
}

public String register(RegisterRequest req) {
    log.info("Registering user with email={} phone={}", req.getEmail(), req.getPhone());

    try {
        UserEntity u = new UserEntity();
        u.setEmail(req.getEmail());
        u.setPhone(req.getPhone());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(u);

        log.info("User registered successfully with id={}", u.getId());
        return "registered";
    } catch (Exception ex) {
        log.error("Error registering user email={} phone={}", req.getEmail(), req.getPhone(), ex);
        throw ex;
    }
}

public String login(LoginRequest req) {
    log.info("User login attempt with username={}", req.getUsername());

    try {
    	UserEntity u = userRepository.findByEmail(req.getUsername())
    		    .or(() -> userRepository.findByPhone(req.getUsername()))
    		    .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            log.warn("Bad credentials for username={}", req.getUsername());
            throw new RuntimeException("Bad credentials");
        }

        String token = jwtUtil.generateToken(u.getEmail());
        log.info("User logged in successfully: username={}", req.getUsername());
        return token;
    } catch (Exception ex) {
        log.error("Error logging in user username={}", req.getUsername(), ex);
        throw ex;
    }
}
}