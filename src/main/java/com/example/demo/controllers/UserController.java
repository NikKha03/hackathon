package com.example.demo.controllers;

import com.example.demo.DAO.UserInfoDAO;
import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.UserInfo;
import com.example.demo.models.Purchase;
import com.example.demo.models.Transfer;
import com.example.demo.repository.PurchaseHistory;
import com.example.demo.repository.TransferHistory;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/hackathon/user")
@AllArgsConstructor
public class UserController {
    private final NamedParameterJdbcTemplate template;
    private UserRepository userRepository;
    private UserInfoDAO userInfoDAO;
    private TransferHistory transferHistory;
    private PurchaseHistory purchaseHistory;

    // кошелек пользователя
    @GetMapping(path = "/wallet")
    ResponseEntity<?> getWallet(Principal principal) {

        User user = userRepository.getUserByUsername(principal.getName());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("currency", user.getWallet().getCurrency());
        jsonObject.put("quantity", user.getWallet().getQuantity());

        return ResponseEntity.ok(jsonObject.toString());
    }

    // дополнительные данные о пользователе
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

    // изменение дополнительных данных о пользователе
    @PostMapping(path = "/profile")
    ResponseEntity<?> setProfile(Principal principal, @RequestBody UserInfo userInfo) {
        userInfoDAO.setProfile(principal, userInfo);

        JSONObject jsonObject = new JSONObject().put("massage", "Данные обновлены!");
        return ResponseEntity.ok(jsonObject.toString());
    }

    // исходящие переводы
    @GetMapping(path = "/outgoing-transfer-history")
    public List<Transfer> outgoingTransferHistory(Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        return transferHistory.findByFromUserId(user.getUserId());
    }

    // входящие переводы
    @GetMapping(path = "/incoming-transfer-history")
    public List<Transfer> incomingTransferHistory(Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        return transferHistory.findByToUserId(user.getUserId());
    }

    // история покупок в магазине
    @GetMapping(path = "/purchase-history")
    public List<Purchase> purchaseHistory(Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        return purchaseHistory.findByUserId(user.getUserId());
    }
}
