package com.example.coursach.repository;

import com.example.coursach.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findCardByNumberAndCvv(String number, Integer CVV);

    Card findCardBynumber(String number);

    Card findCardById(int id);

}