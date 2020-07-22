package com.emp.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.tech.employee.model.Employee;
import com.emp.tech.member.model.Member;
import com.emp.tech.model.Response;
import com.emp.tech.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/members")
	public Response geAllMembers() {

		log.info("Entered in geAllMembers method of MemberController");
		Response resp = null;
		resp = memberService.geAllMembers();
		log.info("Left from geAllMembers method of MemberController");
		return resp;
	}

	@GetMapping("/members/{id}")
	public Response getMember(@PathVariable("id") long memberId) {
		
		log.info("Entered in getMember method of MemberController");
		Response resp = null;
		resp = memberService.getMember(memberId);
		log.info("Left from getMember method of MemberController");
		return resp;
	}

	@PostMapping("/members")
	public Response createMember(@RequestBody Member member) {

		log.info("Entered in createMember method of MemberController");
		Response resp = null;
		resp = memberService.createMember(member);
		log.info("Left from createMember method of MemberController");
		return resp;
	}

	@PutMapping("/members")
	public Response updateMember(@RequestBody Member member) {

		log.info("Entered in updateMember method of MemberController");
		Response resp = null;
		resp = memberService.updateMember(member);
		log.info("Left from updateMember method of MemberController");
		return resp;
	}

	@DeleteMapping("/members/{id}")
	public Response removeMember(@PathVariable("id") long memberId) {

		log.info("Entered in removeMember method of MemberController");
		Response resp = null;
		resp = memberService.removeMember(memberId);
		log.info("Left from removeMember method of MemberController");
		return resp;
	}
}
