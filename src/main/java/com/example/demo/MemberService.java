package com.example.demo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberResponse register(MemberRequest request) {
		Member member = memberRepository.save(new Member(request.name()));
		Long c = memberRepository.count();

		return new MemberResponse(member.getId(), member.getName(), c);
	}
}
