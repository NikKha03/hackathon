package com.example.demo.controllers;

import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.UserInfo;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/hackathon/user")
@AllArgsConstructor
public class UserController {
    private final NamedParameterJdbcTemplate template;
    private UserRepository userRepository;

    @GetMapping(path = "/wallet")
    ResponseEntity<?> getWallet(Principal principal) {

        User user = userRepository.getUserByUsername(principal.getName());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currency", user.getWallet().getCurrency());
        jsonObject.put("quantity", user.getWallet().getQuantity());

        return ResponseEntity.ok(jsonObject.toString());
    }

    @GetMapping(path = "/profile")
    ResponseEntity<?> getProfile(Principal principal) {

        User user = userRepository.getUserByUsername(principal.getName());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", user.getUserInfo().getName());
        jsonObject.put("surname", user.getUserInfo().getSurname());
        jsonObject.put("dateOfBirth", user.getUserInfo().getDateOfBirth());
        jsonObject.put("tg", user.getUserInfo().getTg());

        return ResponseEntity.ok(jsonObject.toString());
    }

    @PostMapping(path = "/profile")
    ResponseEntity<?> setProfile(Principal principal, @RequestBody UserInfo userInfo) {

        User user = userRepository.getUserByUsername(principal.getName());
        String name = userInfo.getName();
        Long userInfoId = user.getUserInfo().getUserInfoId();

        String sql = String.format("UPDATE user_info SET name = '%s' " +
                "WHERE user_info_id = %d", name, userInfoId);
        template.update(sql, new MapSqlParameterSource());

        JSONObject jsonObject = new JSONObject().put("massage", "Данные обновлены!");

        return ResponseEntity.ok(jsonObject.toString());
    }
}
