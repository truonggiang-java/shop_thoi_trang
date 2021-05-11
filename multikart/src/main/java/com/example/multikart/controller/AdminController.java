package com.example.multikart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.multikart.service.BrandService;
import com.example.multikart.service.CategoryService;
import com.example.multikart.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public BrandService brandService;
	
	@Autowired
	public CategoryService categoryService;
	@Autowired
	public ProductService	productService;
	
	@GetMapping("/categories")
	public String category() {
		return "/admin/category";
	}
	
	@GetMapping("/brands")
	public String brand() {
		return "/admin/brand";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/admin/loginAdmin";
	}
	
	
	
	@GetMapping(value="/quan-ly-san-pham")
	public ModelAndView productAdd() {
		ModelAndView modelAndView =new ModelAndView("/admin/product/product");
		modelAndView.addObject("listProducts",productService.listProductAll());
		return modelAndView;
	}
	
	@GetMapping("/them-moi-san-pham")
	public ModelAndView productFromAdd() {
		ModelAndView modelAndView=new ModelAndView("admin/product/productFromAdd");
		modelAndView.addObject("listBrands",brandService.findBrand());
		modelAndView.addObject("listCategories", categoryService.findListSelect());
		return modelAndView;
	}
}
