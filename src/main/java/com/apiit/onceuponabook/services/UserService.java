package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.enums.UserRole;
import com.apiit.onceuponabook.models.Login;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.repositories.LoginRepository;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    LoginRepository loginRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public ResponseEntity<Boolean> registerUser(User newUser){
        Optional<User> userOptional=userRepo.findById(newUser.getEmail());
        if(!userOptional.isPresent()){
//            Login login=new Login();
//            login.setPassword(bcryptEncoder.encode(newUser.getPassword()));
//            login.setLastLogin(new Date());
//            login.setRole(newUser.getRole());
//            login.setUser(newUser);
//            loginRepo.save(login);


//            newUser.setLogin(login);
            newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
            userRepo.save(newUser);
            return new ResponseEntity<>(true,HttpStatus.OK);

        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
