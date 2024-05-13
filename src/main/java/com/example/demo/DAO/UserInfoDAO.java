package com.example.demo.DAO;

import com.example.demo.models.ForUser.User;
import com.example.demo.models.ForUser.UserInfo;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Repository
@AllArgsConstructor
public class UserInfoDAO {
    private final NamedParameterJdbcTemplate template;
    private UserRepository userRepository;

    public void setProfile(Principal principal, @RequestBody UserInfo userInfo) {
        User user = userRepository.getUserByUsername(principal.getName());
        Long userInfoId = user.getUserInfo().getUserInfoId();

        String name = userInfo.getName();
        if (name != null) {
            String sqlUpdateName = String.format("UPDATE user_info SET name = '%s' " +
                    "WHERE user_info_id = %d", name, userInfoId);
            template.update(sqlUpdateName, new MapSqlParameterSource());
        }

        String surname = userInfo.getSurname();
        if (surname != null) {
            String sqlUpdateSurname = String.format("UPDATE user_info SET surname = '%s' " +
                    "WHERE user_info_id = %d", surname, userInfoId);
            template.update(sqlUpdateSurname, new MapSqlParameterSource());
        }

        String tg = userInfo.getTg();
        if (tg != null) {
            String sqlUpdateTg = String.format("UPDATE user_info SET tg = '%s' " +
                    "WHERE user_info_id = %d", tg, userInfoId);
            template.update(sqlUpdateTg, new MapSqlParameterSource());
        }

        // TODO
        // LocalDate dateOfBirth = userInfo.getDateOfBirth();
    }
}
