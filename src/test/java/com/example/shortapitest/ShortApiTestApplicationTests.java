package com.example.shortapitest;

import com.example.shortapitest.access.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ShortApiTestApplicationTests {

	@Autowired
	UrlRepository urlRepository;

	@Test
	void contextLoads() {

		//웹에서 변경하고 싶은 url을 받아옵니다.
		String url = "https://www.google.com/search?q=%E3%84%B4&oq=%E3%84%B4&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDkzMGowajE1qAIAsAIA&sourceid=chrome&ie=UTF-8";


		//step01
		//동일한 url이 있는지 검사
		//urlRepository.findByUrl(url);






	}

}
