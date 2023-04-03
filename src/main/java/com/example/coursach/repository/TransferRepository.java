package com.example.coursach.repository;

import com.example.coursach.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}