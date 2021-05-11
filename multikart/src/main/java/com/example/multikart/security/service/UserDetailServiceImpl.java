package com.example.multikart.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.multikart.entity.Users;
import com.example.multikart.repository.UserRepository;

//de lay thong tin tu userDetailImpl hien co de kiem tra .Class nay de lam dieu do va do spring security cung cap
@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByNameJWT(username).orElseThrow(() -> new UsernameNotFoundException("không tìm thấy tài khoản nào:"+ username));
		UserDetailImpl userDetail=new UserDetailImpl();
		if(user != null) {
			Set<GrantedAuthority> authority=new HashSet<>();
			if(user.getRoles() != null ) {
				user.getRoles().forEach(r -> {
					authority.add(new SimpleGrantedAuthority(r.getName()));
					r.getPermissions().forEach(p ->{
						authority.add(new SimpleGrantedAuthority(p.getName()));
					});
				});
			}
			userDetail.setId(user.getId());
			userDetail.setUsername(user.getName());
			userDetail.setEmail(user.getEmail());
			userDetail.setPassword(user.getPassword());
			userDetail.setAuthorities(authority);
		}
		return userDetail;
		
	}
	
}
