package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.AppUserRole;
import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value="loginRepoImpl")
public class LoginRepoImpl implements LoginRepo {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Login> findByUsername(String name){
        final String SELECT_USER = "select * from Login where userName=:name";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name);
        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER, parameters, (rs, rowNum) ->
                    Optional.of(new Login(
                            rs.getInt("id"),
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("role")
                    )));
        }catch (EmptyResultDataAccessException e){
            System.out.println("No record found in database for "+name);
            return Optional.empty();
        }

    }

    @Override
    public int save(Login login) {
        System.out.println(login);
        final String INSERT_LOGIN = "insert into Login(userName, password, email, role) values(:userName, :password, :email, :role)";
        System.out.println(INSERT_LOGIN);

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", login.getUsername())
                .addValue("password", login.getPassword())
                .addValue("email", login.getEmail())
                .addValue("role", login.getRole());
        int param = namedParameterJdbcTemplate.update(INSERT_LOGIN, parameters, holder);
        login.setId(holder.getKey().intValue());
        final int id = holder.getKey().intValue();
        return param;
    }

    @Override
    public String changePassword(ChangePasswordRequest data) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        final String SELECT_PASSWORD = "select password from Login where userName=:name";

        SqlParameterSource name = new MapSqlParameterSource()
                .addValue("name", username);

        String oldPassword = namedParameterJdbcTemplate.queryForObject(SELECT_PASSWORD,name,String.class);

        if(!bCryptPasswordEncoder.matches(data.getOldPassword(),oldPassword)){
            throw new IllegalStateException("Password is not correct");
        }

        String encodedNewPassword = bCryptPasswordEncoder.encode(data.getNewPassword());
        final String CHANGE_PASSWORD = "update Login set password=:newPassword where userName=:userName";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", username)
                .addValue("newPassword", encodedNewPassword);

        namedParameterJdbcTemplate.update(CHANGE_PASSWORD, parameters);

        return "Password changed successfully";
    }
}
