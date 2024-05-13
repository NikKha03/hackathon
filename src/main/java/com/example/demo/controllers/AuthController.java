package com.example.demo.controllers;

import com.example.demo.DTO.SigninRequest;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.UserInfo;
import com.example.demo.models.ForUser.Wallet;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WalletRepository;
import com.example.demo.token.JwtCore;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hackathon")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;
    private WalletRepository walletRepository;
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;
    private RoleRepository roleRepository;

    // регистрация пользователя
    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsUserByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Пользователь с таким username уже существует");
        }

        if (userRepository.existsUserByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Пользователь с таким email уже существует");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRoles(roleRepository.getRoleByRoleName("USER"));
        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user);
        userInfoRepository.save(userInfo);

        Wallet wallet = new Wallet();
        wallet.setQuantity(100);
        wallet.setUserId(user);
        walletRepository.save(wallet);

        JSONObject jsonObject = new JSONObject().put("message", "Регистрация прошла успешно!");

        return ResponseEntity.ok(jsonObject.toString());
    }

    // авторизация пользователя
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        User user = userRepository.getUserByUsername(signinRequest.getUsername());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("accessToken", jwt);

        return ResponseEntity.ok(jsonObject.toString());
    }
}
