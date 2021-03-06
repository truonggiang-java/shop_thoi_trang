package com.example.multikart.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.multikart.repository.UserRepository;
import com.example.multikart.security.service.UserDetailImpl;
import com.example.multikart.security.service.UserDetailServiceImpl;

//class nay co nhiem vu kiem tra yeu cau cua nguoi dung truoc khi no den dich
public class AuthTokenFilter extends OncePerRequestFilter{ 
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	private static final Logger logger =LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt=parseJwt(request);
			if(jwt != null && jwtUtils.validateToken(jwt)) {
				String username=jwtUtils.getUserNameFromJwtToken(jwt); 
				UserDetails userDetails=new UserDetailImpl();
				if(userRepository.findByNameJWT(username) != null) {
					userDetails = userDetailServiceImpl.loadUserByUsername(username); // lay thong tin nguoi dung
				}
				//neu nguoi dung hop le set thong tin trong SECURITY CONTEXT
				UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Kh??ng th??? ?????t x??c th???c ng?????i d??ng"+e);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth=request.getHeader("Authorization");
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
