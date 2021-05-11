package com.example.multikart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.multikart.convertor.BrandConvertor;
import com.example.multikart.dto.request.BrandRequest;
import com.example.multikart.entity.Brand;
import com.example.multikart.projecttions.brand.BrandProjectionById;
import com.example.multikart.projecttions.brand.BrandProjections;
import com.example.multikart.projecttions.common.SelectCommom;
import com.example.multikart.repository.BrandRepository;

@Service
public class BrandService {
	@Autowired 
	private BrandRepository brandRepository;
	
	@Autowired
	private BrandConvertor brandConvertor;
	
	public List<SelectCommom> findBrand(){
		return brandRepository.findListBrand();
	}
	
	public List<BrandProjections> findAllBrand(){
		return brandRepository.findAllBrand();
	}
	
	public BrandProjectionById findByIdBrand(Integer id) {
		return brandRepository.findByIdBrand(id);
	}
	
	public ResponseEntity<Object> update(BrandRequest brandRequest,Integer id,HttpServletRequest request){
		try {
			Brand brand=brandRepository.save(brandConvertor.convertor(brandRequest, id, request));
			return new ResponseEntity<>(brandRepository.findOneId(brand.getId()),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Có lỗi cập nhập xảy ra mong liên hệ với 09**** ",HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Object> create(BrandRequest brandRequest,HttpServletRequest request){
		try {
			Brand brand=brandRepository.save(brandConvertor.convertor(brandRequest, brandRequest.getId(), request));
			return new ResponseEntity<>(brand,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Có lỗi tạo mới xảy ra", HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<ServiceResponse> delete(Integer id){
		ServiceResponse response=new ServiceResponse();
		try {
			brandRepository.deleteById(id);
			response.setStatus("success delete");
			response.setData("Bạn đã xóa thành công");
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus("error delete");
			response.setData("Xóa nhãn hiệu bị lỗi");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
}
