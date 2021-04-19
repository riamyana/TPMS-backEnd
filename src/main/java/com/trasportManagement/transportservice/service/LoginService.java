package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.Login;
import com.trasportManagement.transportservice.repository.LoginRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {
    @Qualifier("loginRepoImpl")
    private final LoginRepo loginRepo;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
        return loginRepo.findByUsername(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, name)));
    }

    public String signUpUser(Login login) {
        boolean userExists = loginRepo.findByUsername(login.getUsername()).isPresent();

        if(userExists){
            throw new IllegalStateException("User name already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(login.getPassword());

        login.setPassword(encodedPassword);

        loginRepo.save(login);

        return "Sign Up Successfully";
    }

    public String changePassword(ChangePasswordRequest data) {
        return loginRepo.changePassword(data);
    }
}
