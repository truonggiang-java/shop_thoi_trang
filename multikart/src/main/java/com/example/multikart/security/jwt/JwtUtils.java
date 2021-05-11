package com.example.multikart.security.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.multikart.security.service.UserDetailImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	//JWT_secret la bi mat chi co phai server biet
	private final String jwtSecret="truonggiangsecretkey";
	//thoi gian co hieu luc
	private final int jwtExpirationMs=86400000;
	//tao ra jwt tu chuoi thong tin user
	public String generateJwtToken(Authentication authentication) {
		UserDetailImpl userPrincipal=(UserDetailImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date().getTime() + jwtExpirationMs)))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
				
	}
	
	//lay thong tin user tu jwt
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	
	//bat loi
	public boolean validateToken(String autheToken) {
		try {
			 Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(autheToken);
			return true;
		}catch (SignatureException e) {
			logger.error("Mã không hợp lệ {}",e.getMessage()); // ma khong hop le
		}catch(ExpiredJwtException e) {
			logger.error("Mã hết hạn {}",e.getMessage());
		}catch(MalformedJwtException e) {
			logger.error("Mã thông báo không hợp lệ {}",e.getMessage());
		}catch(UnsupportedJwtException e) {
			logger.error("Không hỗ trợ mã này",e.getMessage());
		}catch (IllegalArgumentException e) {
            logger.error("chuỗi xác nhận quyền sở hữu trống: {}", e.getMessage());
        }
		return false;
	}
}
