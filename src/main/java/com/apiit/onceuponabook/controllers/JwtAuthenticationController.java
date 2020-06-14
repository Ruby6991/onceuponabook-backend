package com.apiit.onceuponabook.controllers;


import com.apiit.onceuponabook.config.JwtTokenUtil;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.JwtUserDetailsService;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<UserDTO> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        Optional<User> userOptional = userRepo.findById(authenticationRequest.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserDTO userDTO=new UserDTO(token,user.getFirstName(),user.getRole());
            return ResponseEntity.ok(userDTO);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<Boolean> getTokenExpirationDate(@RequestBody UserDTO userDTO){
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDTO.getEmail());
        boolean status = jwtTokenUtil.validateToken(userDTO.getJwtToken(), userDetails);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }
}
