package com.example.multikart.service;


import com.example.multikart.entity.Customer;
import com.example.multikart.repository.CustomerReposiory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerReposiory customerRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + s));
        CustomerDetailImpl customerDetail = new CustomerDetailImpl();
        if (null != customer) {
            customerDetail.setName(customer.getName());
            customerDetail.setPassword(customer.getPassword());
            customerDetail.setEmail(customer.getEmail());
            customerDetail.setId(customer.getId());
            customerDetail.setPhone(customer.getPhone());
            customerDetail.setAddress(customer.getAddress());
        }
        return customerDetail;
    }
}
