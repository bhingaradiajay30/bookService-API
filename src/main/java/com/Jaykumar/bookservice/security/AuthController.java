package com.Jaykumar.bookservice.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	 @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserService userService;

	    @Autowired
	    JwtUtils jwtUtils;

	    @PostMapping("/signin")
	    public String authenticateUser(@Validated @RequestBody SignupRequest loginRequest) {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);
	        return jwt;
	    }

	    @PostMapping("/signup")
	    public String registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
	        userService.registerUser(signUpRequest.getUsername(), signUpRequest.getPassword());
	        return "User registered successfully!";
	    }
}