package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.*;
import com.trasportManagement.transportservice.repository.LoginRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {
    @Qualifier("loginRepoImpl")
    private final LoginRepo loginRepo;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ImageUploadService imageUploadService;

    private final static String USER_NOT_FOUND_MSG =
            "user with name %s not found";

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

//        if(loginRepo.findByUsername(name).isEmpty()){
//            throw new UsernameNotFoundException(
//                        String.format(USER_NOT_FOUND_MSG, name));
//        }else{
//            return loginRepo.findByUsername(name);
//        }
        CustomLogin login = loginRepo.findByUsername(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, name)));

        return new Login(login);
    }

    public String signUpUser(CustomLogin login) {
        boolean userExists = loginRepo.findByUsername(login.getUserName()).isPresent();

        if(userExists){
            throw new IllegalStateException("User name already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(login.getPassword());

        login.setPassword(encodedPassword);

        loginRepo.save(login);

        return "Sign Up Success";
    }

    public String changePassword(ChangePasswordRequest data) {
        return loginRepo.changePassword(data);
    }

    public String changeForgotPassword(ForgotPassword data) {
        return loginRepo.changeForgotPassword(data);
    }

    public CustomLogin updateUserProfile(CustomLogin login, MultipartFile multipartFile) {
        if(multipartFile != null){
            File file = imageUploadService.uploadImage(multipartFile);

            if(file != null) {
                login.setProfileImage(file.getName());
            }
            else
            {
                throw  new TPMSCustomException("Unable to upload Users Proofs.", HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            throw  new TPMSCustomException("Something went wrong. Please try again to upload documents", HttpStatus.NOT_FOUND);
        }

        int n = loginRepo.updateUserProfile(login);
        if(n == 0){
            throw new TPMSCustomException("Unable to update Member Profile", HttpStatus.NOT_FOUND);
        }

        return login;
    }

    public List<RegistrationRequest> findUserByUserId(int id) {
        List<RegistrationRequest> userList = loginRepo.findUserById(id);

        if(userList.isEmpty()){
            throw new TPMSCustomException("No user details found for given user id : " + id, HttpStatus.NOT_FOUND);
        }

        return userList;
    }
}