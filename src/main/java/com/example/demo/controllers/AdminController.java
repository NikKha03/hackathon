package com.example.demo.controllers;

import com.example.demo.models.ForUser.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hackathon/admin")
@AllArgsConstructor
public class AdminController {
    UserRepository userRepository;

    //TODO??
    @GetMapping("/all-users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    //TODO??
    @GetMapping("/find-user/{email}")
    public User findUserByEmail(@PathVariable("email") String email) {

        return userRepository.findByEmail(email);
    }

    @Transactional
    @DeleteMapping("/delete-user/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable("email") String email) {
        userRepository.deleteByEmail(email);

        JSONObject jsonObject = new JSONObject().put("massage", "Пользователь с почтой " + email + " успешно удален!");

        return ResponseEntity.ok(jsonObject.toString());
    }
}
