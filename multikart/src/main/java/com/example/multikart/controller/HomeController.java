package com.example.multikart.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.multikart.dto.request.CartDto;
import com.example.multikart.dto.request.CustomerDto;
import com.example.multikart.entity.Customer;
import com.example.multikart.entity.Verification;
import com.example.multikart.repository.CustomerReposiory;
import com.example.multikart.repository.VerificationRepository;
import com.example.multikart.service.CustomerService;
import com.example.multikart.service.ProductService;

@Controller
@RequestMapping
public class HomeController {

	@Autowired
	public ProductService productService;
	@Autowired
	public CustomerService customerService;
	@Autowired
	public VerificationRepository verificationRepository;
	@Autowired
	public CustomerReposiory customerRepository;

	@GetMapping("/")
	public String index() {
		return "master";
	}

	@GetMapping("/danh-sach-san-pham")
	public ModelAndView product() {
		ModelAndView modelAndView = new ModelAndView("product-list");
		modelAndView.addObject("listProducts", productService.listProduct());
		return modelAndView;
	}

	@GetMapping("/chi-tiet-san-pham/{slug}")
	public ModelAndView productDetail(@PathVariable String slug) {
		ModelAndView modelAndView = new ModelAndView("product-detail");
		modelAndView.addObject("product", productService.findBySlug(slug));
		return modelAndView;
	}

	@GetMapping("/dang-ky")
	public String register() {
		return "register";
	}

	@PostMapping("/dang-ky")
	public ResponseEntity<Object> postRegister(@RequestBody CustomerDto customerDto) {
		return customerService.register(customerDto);
	}

	@GetMapping("/dang-nhap")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (error != null) {
			modelAndView.addObject("error", "T??n ho???c m???t kh???u kh??ng ????ng");
		}
		return modelAndView;

	}

	@GetMapping("/thanh-toan")
	public ResponseEntity checkout() {
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@GetMapping("/verify-email")
	public ModelAndView verifi(@RequestParam(value = "token", required = true) String token) {
		ModelAndView modelAndView = new ModelAndView("verifi-token");
		Verification verification = verificationRepository.findByToken(token);
		if (verification == null) {
			modelAndView.addObject("error", "M?? n??y kh??ng h???p l???");
		} else if (verification.getExpriedDate().isBefore(LocalDateTime.now())) {
			modelAndView.addObject("error", "M?? n??y h???t h???n");
		} else {
			Customer customer = verification.getCustomer();
			customer.setIsActived(true);
			customerRepository.save(customer);
			modelAndView.addObject("success", "K??ch ho???t th??nh c??ng");
		}
		return modelAndView;

	}

	@GetMapping("/search")
	public ResponseEntity search(@RequestParam(value = "key", required = true) String key) {
		return new ResponseEntity(productService.searchProduct(key), HttpStatus.OK);
	}

	@PostMapping("/add-to-cart")
	public ResponseEntity addCart(@RequestBody CartDto cartDto,HttpSession session) {
		
		List<CartDto> listCarts = (List<CartDto>) session.getAttribute("listCarts");//add thanh kieu list luc dau la object
		//l??c ?????u listCarts s??? tr??? v??? null
		if(listCarts!=null) {
			for(CartDto cartDto1:listCarts) {
				if(cartDto.getCode().equals(cartDto1.getCode())) {//s??? l???y nh??ng s???n ph???m gi???ng nhau
					cartDto1.setQuantity(cartDto1.getQuantity()+cartDto.getQuantity());//hi???u ????n gi???n s??? l?????ng l??c ?????u mua l?? 3 ???n v??o add to cart nh??ng h??? l???i mu???n mua 4 c??ng s???n ph???m th?? n?? s??? c??ng th??m
				}
				else {
					listCarts.add(cartDto);
				}
			}
		}
		else {
			List<CartDto> cart=new ArrayList<>();
			cart.add(cartDto);
		}
		
		return new ResponseEntity(cartDto,HttpStatus.OK);
	}
}
