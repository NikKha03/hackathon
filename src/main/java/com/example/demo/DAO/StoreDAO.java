package com.example.demo.DAO;

import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.Wallet;
import com.example.demo.models.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Principal;

@Repository
@AllArgsConstructor
public class StoreDAO {
    private final NamedParameterJdbcTemplate template;
    private UserRepository userRepository;
    private StoreRepository storeRepository;

    public void createPurchase(Long productId, Principal principal) {
        User user = userRepository.getUserByUsername(principal.getName());
        Store product = storeRepository.findByProductId(productId);

        String sqlPurchaseHistory = String.format("INSERT INTO purchase_history (user_id, product_id, price) "
                + "VALUES (%d, %d, %d)", user.getUserId(), productId , product.getCost());
        template.update(sqlPurchaseHistory, new MapSqlParameterSource());

        Wallet userWallet = user.getWallet();
        int operation = userWallet.getQuantity() - product.getCost();

        String sqlWallet = String.format("UPDATE wallet SET quantity = %d WHERE user_id = %d", operation, user.getUserId());
        template.update(sqlWallet, new MapSqlParameterSource());
    }

}
