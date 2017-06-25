package com.hw.xyls;

import com.hw.xyls.dao.user.RoleMapper;
import com.hw.xyls.pojo.user.Role;
import com.hw.xyls.service.image.IimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
public class ImgprceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImgprceApplication.class, args);
	}


}
