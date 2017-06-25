package com.hw.xyls;

import com.hw.xyls.service.image.IimageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImgprceApplicationTests {
	@Autowired
	IimageService iimageService;

	@Test
	public void contextLoads() {
//		iimageService.createResultScheduled();
	}

}
