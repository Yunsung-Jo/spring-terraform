package com.example.demo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import com.example.demo.support.annotation.IntegrationTest;
import com.example.demo.support.util.JsonUtils;

@IntegrationTest
class MemberControllerTest {

	@Autowired
	MockMvcTester mockMvcTester;

	@Autowired
	JsonUtils jsonUtils;

	@Test
	void register_success() {

		MemberRequest request = new MemberRequest("홍길동");

		MvcTestResult mvcTestResult = mockMvcTester.post()
			.uri("/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonUtils.toJson(request))
			.exchange();

		assertThat(mvcTestResult)
			.hasStatus(HttpStatus.CREATED)
			.bodyJson()
			.convertTo(MemberResponse.class)
			.satisfies(response -> {
				assertThat(response.name()).isEqualTo("홍길동");
				assertThat(response.count()).isEqualTo(1);
			});
	}
}
