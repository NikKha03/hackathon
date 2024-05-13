package com.example.demo.DAO;

import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.Wallet;
import com.example.demo.models.Transfer;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Principal;

@Repository
@AllArgsConstructor
public class TransferDAO {
    private final NamedParameterJdbcTemplate template;
    private UserRepository userRepository;

    public void createTransfer(Transfer transfer, Principal principal, String email) {
        User fromUser = userRepository.getUserByUsername(principal.getName());
        User toUser = userRepository.findByEmail(email);

        String sql = String.format("INSERT INTO transfer_history (from_user_id, to_user_id, price) "
                + "VALUES (%d, %d, %d)", fromUser.getUserId(), toUser.getUserId() , transfer.getPrice());
        template.update(sql, new MapSqlParameterSource());
    }

    public void writeOffMoney(Transfer purchase, Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        Wallet userWallet = user.getWallet();
        int operation = userWallet.getQuantity() - purchase.getPrice();

        String sql = String.format("UPDATE wallet SET quantity = %d WHERE user_id = %d", operation, user.getUserId());
        template.update(sql, new MapSqlParameterSource());
    }

    public void writeOnMoney(Transfer transfer, String email) {
        User user = userRepository.findByEmail(email);
        Wallet userWallet = user.getWallet();
        int operation = userWallet.getQuantity() + transfer.getPrice();

        String sql = String.format("UPDATE wallet SET quantity = %d WHERE user_id = %d", operation, user.getUserId());
        template.update(sql, new MapSqlParameterSource());
    }

}