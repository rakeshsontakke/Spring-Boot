package com.emp.tech.service;

import com.emp.tech.member.model.Member;
import com.emp.tech.model.Response;

public interface MemberService {

	Response geAllMembers();

	Response getMember(long memberId);

	Response createMember(Member member);

	Response updateMember(Member member);

	Response removeMember(long memberId);

}
