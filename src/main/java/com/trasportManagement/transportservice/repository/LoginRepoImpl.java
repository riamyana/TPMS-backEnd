package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.CustomLogin;

import com.trasportManagement.transportservice.model.ForgotPassword;
import com.trasportManagement.transportservice.model.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Optional<CustomLogin> findByUsername(String name){
        final String SELECT_USER = "SELECT * FROM Login WHERE userName=:name";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", name);
        try {
            return namedParameterJdbcTemplate.queryForObject(SELECT_USER, parameters, (rs, rowNum) ->
                    Optional.of(new CustomLogin(
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
    public int save(CustomLogin login) {
        System.out.println(login);
        final String INSERT_LOGIN = "INSERT INTO Login(userName, password, email, role) VALUES(:userName, :password, :email, :role)";
        System.out.println(INSERT_LOGIN);

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", login.getUserName())
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

        final String SELECT_PASSWORD = "SELECT password FROM Login WHERE userName=:name";

        SqlParameterSource name = new MapSqlParameterSource()
                .addValue("name", username);

        String oldPassword = namedParameterJdbcTemplate.queryForObject(SELECT_PASSWORD,name,String.class);

        if(!bCryptPasswordEncoder.matches(data.getOldPassword(),oldPassword)){
            throw new BadCredentialsException("Password is not correct");
        }

        String encodedNewPassword = bCryptPasswordEncoder.encode(data.getNewPassword());
        final String CHANGE_PASSWORD = "UPDATE Login SET password=:newPassword WHERE userName=:userName";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", username)
                .addValue("newPassword", encodedNewPassword);

        namedParameterJdbcTemplate.update(CHANGE_PASSWORD, parameters);

        return "Password changed successfully";
    }

    @Override
    public String changeForgotPassword(ForgotPassword data) {

        String encodedNewPassword = bCryptPasswordEncoder.encode(data.getNewPassword());
        final String CHANGE_PASSWORD = "UPDATE Login SET password=:newPassword WHERE userName=:userName";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", data.getUserName())
                .addValue("newPassword", encodedNewPassword);

        namedParameterJdbcTemplate.update(CHANGE_PASSWORD, parameters);

        return "Password changed successfully";
    }

    @Override
    public List<RegistrationRequest> findUserById(int id) {

        final String SELECT_USER_DETAIL = "SELECT * FROM Login WHERE id=:id";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);

        List<RegistrationRequest> userList = namedParameterJdbcTemplate.query(SELECT_USER_DETAIL, parameters, (rs, i) ->
                new RegistrationRequest(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role")
                )
        );
        return userList;
    }
}
