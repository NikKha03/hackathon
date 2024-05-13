package com.example.demo.controllers;

import com.example.demo.models.Store;
import com.example.demo.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hackathon")
@AllArgsConstructor
public class StoreController {
    private StoreRepository storeRepository;

    // получение всех доступных товаров
    @GetMapping("/store")
    public List<Store> store() {
        return storeRepository.findAll();
    }
}
