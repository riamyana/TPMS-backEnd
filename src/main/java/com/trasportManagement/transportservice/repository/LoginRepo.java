package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.Login;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo{
    Optional<Login> findByUsername(String name);
    int save(Login login);

    String changePassword(ChangePasswordRequest data);
}
