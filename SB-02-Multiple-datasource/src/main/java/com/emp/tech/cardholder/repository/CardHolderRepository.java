package com.emp.tech.cardholder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.tech.cardholder.model.CardHolder;

public interface CardHolderRepository extends JpaRepository<CardHolder, Long> {
}
