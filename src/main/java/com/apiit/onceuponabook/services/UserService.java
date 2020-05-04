package com.apiit.onceuponabook.services;

import com.apiit.onceuponabook.dtos.AddressDTO;
import com.apiit.onceuponabook.dtos.UserDTO;
import com.apiit.onceuponabook.enums.UserRole;
import com.apiit.onceuponabook.models.Address;
import com.apiit.onceuponabook.models.Login;
import com.apiit.onceuponabook.models.User;
import com.apiit.onceuponabook.repositories.AddressRepository;
import com.apiit.onceuponabook.repositories.LoginRepository;
import com.apiit.onceuponabook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    LoginRepository loginRepo;

    @Autowired
    AddressRepository addressRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelClassToDTO modelClassToDTO;

    public ResponseEntity<Boolean> registerUser(User newUser){
        Optional<User> userOptional=userRepo.findById(newUser.getEmail());
        if(!userOptional.isPresent()){
            newUser.setRole(UserRole.Customer);
            newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
            userRepo.save(newUser);
            return new ResponseEntity<>(true,HttpStatus.OK);

        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<UserDTO> getUser(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            return new ResponseEntity<>(modelClassToDTO.userToDTO(user),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> deleteUser(String id){
        try{
            userRepo.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (EmptyResultDataAccessException erda_ex){

        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    public ResponseEntity<User> updateUser(String id, User newUser){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            int loginId = user.getLogin().getId();

            Optional<Login> loginOptional = loginRepo.findById(loginId);
            Login login = loginOptional.get();
            Login newLogin = newUser.getLogin();

            login.setUser(newUser);
            login = loginRepo.save(login);

            newUser.setLogin(login);
            newUser = userRepo.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<AddressDTO>> getAddressList(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            List<AddressDTO> addressDTOList = new ArrayList<>();
            List<Address> addressList = addressRepo.findByUser(userOptional.get());
            for(Address add: addressList){
                addressDTOList.add(modelClassToDTO.addressToDTO(add));
            }
            return new ResponseEntity<>(addressDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AddressDTO> newAddress(Address newAddress){
        Optional<User> userOptional = userRepo.findById(newAddress.getUser().getEmail());
        if(userOptional.isPresent()){
            newAddress.setUser(userOptional.get());
            newAddress = addressRepo.save(newAddress);
            return new ResponseEntity<>(modelClassToDTO.addressToDTO(newAddress), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<AddressDTO> updateAddress(Address updateAddress){
        Optional<Address> addressOptional = addressRepo.findById(updateAddress.getId());
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();

            updateAddress.setUser(address.getUser());
            updateAddress.setAddress(address.getAddress());
            updateAddress.setCity(address.getCity());
            updateAddress.set
        }
    }



}