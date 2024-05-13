package com.example.demo.repository;

import com.example.demo.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistory extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUserId(Long userId);
}
