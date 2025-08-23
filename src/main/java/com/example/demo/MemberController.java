package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/register")
	public ResponseEntity<MemberResponse> register(@RequestBody MemberRequest req) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(memberService.register(req));
	}
}
