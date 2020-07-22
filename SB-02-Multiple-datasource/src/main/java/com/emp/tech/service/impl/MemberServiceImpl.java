package com.emp.tech.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.tech.AppConstants;
import com.emp.tech.employee.model.Employee;
import com.emp.tech.member.model.Member;
import com.emp.tech.member.repository.MemberRepository;
import com.emp.tech.model.Response;
import com.emp.tech.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Response geAllMembers() {

		Response resp = null;
		List<Member> members = null;
		members = (List<Member>) memberRepository.findAll();
		resp = new Response(AppConstants.SUCCESS, "successfully fetched", members);
		return resp;
	}

	@Override
	public Response getMember(long memberId) {
		
		Response resp = null;
		Optional<Member> memberOpt = memberRepository.findById(memberId);
		if(memberOpt.isPresent()) {
			resp = new Response(AppConstants.SUCCESS, "successfully fetched", memberOpt.get());
		} else {
			resp = new Response(AppConstants.FAILURE, "Member not exist", new Member());
		}
		return resp;
	}

	@Override
	public Response createMember(Member member) {

		Response resp = null;
		member = memberRepository.save(member);
		resp = new Response(AppConstants.SUCCESS, "successfully created", member);
		return resp;
	}

	@Override
	public Response updateMember(Member member) {

		Response resp = null;
		Optional<Member> memberOpt = memberRepository.findById(member.getId());
		if(memberOpt.isPresent()) {
			member = memberRepository.save(member);
			resp = new Response(AppConstants.SUCCESS, "successfully updated", member);
		} else {
			resp = new Response(AppConstants.FAILURE, "Member not exist", null);
		}
		
		return resp;
	}

	@Override
	public Response removeMember(long memberId) {
		
		Response resp = null;
		Optional<Member> memberOpt = memberRepository.findById(memberId);
		if(memberOpt.isPresent()) {
			memberRepository.deleteById(memberId);
			resp = new Response(AppConstants.SUCCESS, "successfully deleted", memberOpt.get());
		} else {
			resp = new Response(AppConstants.FAILURE, "Emp not exist", null);
		}
		return resp;
	}

}
