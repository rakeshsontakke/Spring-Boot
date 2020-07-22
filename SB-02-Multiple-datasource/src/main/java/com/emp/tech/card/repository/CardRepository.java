package com.emp.tech.card.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.tech.card.model.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
