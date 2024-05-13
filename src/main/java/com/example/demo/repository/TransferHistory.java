package com.example.demo.repository;

import com.example.demo.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferHistory extends JpaRepository<Transfer, Long> {
    List<Transfer> findByFromUserId(Long userId);

    List<Transfer> findByToUserId(Long userId);
}
