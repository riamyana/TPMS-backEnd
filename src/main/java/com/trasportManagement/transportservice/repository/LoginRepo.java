package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.CustomLogin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo{
    Optional<CustomLogin> findByUsername(String name);
    int save(CustomLogin login);

    String changePassword(ChangePasswordRequest data);
}
