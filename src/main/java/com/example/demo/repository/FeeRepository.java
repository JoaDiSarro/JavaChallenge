package com.example.demo.repository;

import com.example.demo.repository.dao.FeeDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<FeeDAO, Integer> {
}