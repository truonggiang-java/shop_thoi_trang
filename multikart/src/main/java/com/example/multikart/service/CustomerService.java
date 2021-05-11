package com.example.multikart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.multikart.convertor.CustomerConvertor;
import com.example.multikart.dto.request.CustomerDto;
import com.example.multikart.entity.Customer;
import com.example.multikart.entity.Verification;
import com.example.multikart.repository.CustomerReposiory;
import com.example.multikart.repository.VerificationRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerReposiory customerRepository;
	@Autowired
	private VerificationRepository verificationRepository;
	@Autowired
	private CustomerConvertor customerConvertor;
	@Autowired
	private EmailService emailService;
	public ResponseEntity<Object> register(CustomerDto customerDto){
		try {
			Customer customer=customerRepository.save(customerConvertor.converter(customerDto));
			Verification verification=new Verification();
			verification.setCustomer(customer);
			verificationRepository.save(verification);
			String message = "<h3>Vui lòng bấm vào đây để xác thực email</h3><br>";
            message += "<a href='http://localhost:8080/verify-email?token="+verification.getToken()+"' style='background-color: #008CBA;\n" +
                    "border: none;\n" +
                    "  color: white;\n" +
                    "  padding: 20px 100px;\n" +
                    "  text-align: center;\n" +
                    "  text-decoration: none;\n" +
                    "  display: inline-block;\n" +
                    "  font-size: 16px;\n" +
                    "  margin: 4px 2px;\n" +
                    "  cursor: pointer;'>Verify your email</a>";
            emailService.sendEmail(customer.getEmail(), "Xac thuc email", message);
			return new ResponseEntity<>("Đăng ký thành công",HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Có lỗi" ,HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
