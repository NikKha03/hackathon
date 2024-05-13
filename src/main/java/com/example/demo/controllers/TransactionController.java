package com.example.demo.controllers;

import com.example.demo.DAO.StoreDAO;
import com.example.demo.DAO.TransferDAO;
import com.example.demo.models.Transfer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/hackathon")
@AllArgsConstructor
public class TransactionController {
    private final TransferDAO transferDAO;
    private final StoreDAO storeDAO;

    // перевод денег пользователю по его email
    @Transactional
    @PostMapping("/transfer/{email}")
    ResponseEntity<?> transfer(@PathVariable("email") String email, @RequestBody Transfer transfer, Principal principal) {
        transferDAO.createTransfer(transfer, principal, email);
//        waitingForTransaction();
        transferDAO.writeOffMoney(transfer, principal);
        transferDAO.writeOnMoney(transfer, email);

        JSONObject jsonObject = new JSONObject().put("massage", "Перевод успешно выполнен!");
        return ResponseEntity.ok(jsonObject.toString());
    }

    // покупка товара в магазине по id товара
    @PostMapping("/store/{productId}")
    ResponseEntity<?> shoppingInStore(@PathVariable("productId") Long productId, Principal principal) {
        storeDAO.createPurchase(productId, principal);

        JSONObject jsonObject = new JSONObject().put("massage", "Товар куплен!");
        return ResponseEntity.ok(jsonObject.toString());
    }

    //TODO: выбросить ошибку, если недостаточно денег на счету
    @SneakyThrows
    private void waitingForTransaction() {
        Thread.sleep(60);
        throw new Exception("Недостаточно денег на счету!");
    }
}
