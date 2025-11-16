package com.example.contactmanagement.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.contactmanagement.DTO.LoginRequest;
import com.example.contactmanagement.DTO.RegisterRequest;
import com.example.contactmanagement.Service.AuthorizationService;



@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
	
   private final AuthorizationService authService;
   
   public AuthorizationController(AuthorizationService authService) {
	    this.authService = authService;
   }
   
   
   @PostMapping("/register")
   public String register(@RequestBody RegisterRequest req) {
       return authService.register(req);
   }
   @PostMapping("/login")
   public String login(@RequestBody LoginRequest req) {
       return authService.login(req);
   }
}