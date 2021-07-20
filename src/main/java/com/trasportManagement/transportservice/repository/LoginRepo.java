package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.CustomLogin;
import com.trasportManagement.transportservice.model.ForgotPassword;
import com.trasportManagement.transportservice.model.RegistrationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepo{
    Optional<CustomLogin> findByUsername(String name);
    int save(CustomLogin login);

    String changePassword(ChangePasswordRequest data);

    String changeForgotPassword(ForgotPassword data);

    List<RegistrationRequest> findUserById(int id);

    int updateUserProfile(CustomLogin login);
}
