package com.example.demo.controllers;

import com.example.demo.DAO.DAO;
import com.example.demo.models.Purchase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/hackathon")
@AllArgsConstructor
public class TransactionController {
    private final DAO dao;

    @Transactional
    @PostMapping("/transfer/{email}")
    ResponseEntity<?> transfer(@PathVariable("email") String email, @RequestBody Purchase purchase, Principal principal) {
        dao.createPurchase(purchase, principal, email);
        waitingForTransaction();
        dao.writeOffMoney(purchase, principal);
        dao.writeOnMoney(purchase, email);

        return ResponseEntity.ok("massage: Перевод успешно выполнен!");
    }

    @SneakyThrows
    private void waitingForTransaction() {
        Thread.sleep(60);
        throw new Exception("Недостаточно денег на счету!");
    }
}
