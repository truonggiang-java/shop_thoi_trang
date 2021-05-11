package com.example.multikart.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multikart.payload.LoginRequest;
import com.example.multikart.payload.response.JwtResponse;
import com.example.multikart.repository.UserRepository;
import com.example.multikart.security.jwt.JwtUtils;
import com.example.multikart.security.service.UserDetailImpl;

@Controller
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		String username=userRepository.findByEmail(loginRequest.getEmail());
		// Xác thực từ username và password.
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		//neu khong xay ra exception tuc la thong tin hop le
		//Set thong tin authentication vao security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//tra ve jwt cho nguoi dung
		String jwt=jwtUtils.generateJwtToken(authentication);
		
		UserDetailImpl userDetail=(UserDetailImpl) authentication.getPrincipal();
		List<String> roles=userDetail.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,userDetail.getId(),userDetail.getUsername(),userDetail.getEmail(),roles));
		
		
	}
}
