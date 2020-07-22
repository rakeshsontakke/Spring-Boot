package com.emp.tech.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.tech.member.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
