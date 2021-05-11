package com.example.multikart.convertor;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.example.multikart.dto.request.BrandRequest;
import com.example.multikart.entity.Brand;


@Component
public class BrandConvertor {
	public Brand convertor(BrandRequest brandRequest,Integer id,HttpServletRequest request) {
		Brand brand=new Brand();
		if(id != null) {
			brand.setId(id);
			brand.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		}
		else {
			brand.setId(null);
			brand.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			brand.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		}
		brand.setName(brandRequest.getName());
		brand.setSlug(brandRequest.getSlug());
		brand.setStatus(brandRequest.isStatus());
		try {
			brand.setImage(brandRequest.getImage().getOriginalFilename());//getOrigin.. tra ve ten tep ban dau trong file khach hang va no kieu String
			if(brandRequest.getImage().isEmpty()) {
				throw new Exception("Failed to store empty file");
			}
			Path roothLocation =Paths.get("src/main/resources/static/logos"); //lay duong dan
			Path destinationFile=roothLocation.resolve(Paths.get(brandRequest.getImage().getOriginalFilename())).normalize().toAbsolutePath(); //normalize tra la ve duong dan hiện tại và nó sẽ loại bỏ những phần dư thừa
			//toAbsolutePath dùng để trả về một đối tượng Path là một đường dẫn tuyệt đối
			if(!destinationFile.getParent().equals(roothLocation.toAbsolutePath())) {
				throw new Exception("Cannot store file outside current directory");
			}
			try(InputStream inputStream=brandRequest.getImage().getInputStream()){//InputStream được sử dụng để đọc dữ liệu dạng byte tại mỗi thời điểm chỉ đọc một byte
				Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return brand;
	}
}
